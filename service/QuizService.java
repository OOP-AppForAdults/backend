//채점 & 최종 이력 저장
package backend.service;

import java.util.List;

import backend.data.QuestionRepository;
import backend.domain.LearningRecord;
import backend.domain.MultipleChoiceQuestion;
import backend.domain.CardSortingQuestion; //추가(박찬웅)
import backend.utils.FileIOUtils;

public class QuizService {
    private static final String RECORD_FILE_PATH = "user_record.txt";
    private static final int TIME_LIMIT_SECONDS = 10; //추가(박찬웅) | 문제당 제한시간

    private QuestionRepository repository;
    private List<MultipleChoiceQuestion> currentQuestions;
    private List<CardSortingQuestion> currentCards; //추가(박찬웅) | 카드분류
    private int correctCount = 0;
    private int totalCount = 0;
    private long quizStartTime = 0; //추가(박찬웅) | 타임어택

    public QuizService() {
        this.repository = new QuestionRepository();
        this.currentQuestions = repository.loadQuizQuestions();
        this.currentCards = repository.loadCardQuestions(); //추가(박찬웅)
    }

    //추가(박찬웅) | 타임어택
    public void startQuizTimer() {
        this.quizStartTime = System.currentTimeMillis();
    }

    public List<MultipleChoiceQuestion> getQuestions() {
        this.correctCount = 0; //추가(박찬웅)
        this.totalCount = currentQuestions.size(); //추가(박찬웅)
        startQuizTimer(); //추가(박찬웅)
        return currentQuestions;
    }

    // 채점 & 오답 노트 파일 저장
    public boolean submitAnswer(MultipleChoiceQuestion question, int userAnswer) {
        boolean isCorrect = question.isCorrect(userAnswer) && !isTimeOver();

        if (isCorrect) {
            correctCount++;
        }

        // 저장용 객체 생성
        LearningRecord record = new LearningRecord("퀴즈", question.getQuestionText(), String.valueOf(userAnswer),
                String.valueOf(question.getCorrectAnswer()));

        FileIOUtils.appendLine(RECORD_FILE_PATH, record.toFileString());

        startQuizTimer(); //추가(박찬웅) | 타임어택
        return isCorrect;
    }

    //추가(박찬웅) | 카드분류
    public List<CardSortingQuestion> getCards() {
        this.correctCount = 0;
        this.totalCount = currentCards.size();
        startQuizTimer();
        return currentCards;
    }

    public boolean submitAnswer(CardSortingQuestion question, String userAnswer) {
        boolean isCorrect = question.isCorrect(userAnswer) && !isTimeOver();

        if (isCorrect) {
            correctCount++;
        }

        LearningRecord record = new LearningRecord("카드", question.getQuestionText(), userAnswer,
                question.getCorrectAnswer());

        FileIOUtils.appendLine(RECORD_FILE_PATH, record.toFileString());

        startQuizTimer();
        return isCorrect;
    }


    //추가(박찬웅) | 타임어택
    public int getRemainingSeconds() {
        long Seconds = (System.currentTimeMillis() - quizStartTime) / 1000;
        int remainTime = TIME_LIMIT_SECONDS - (int) Seconds;
        return Math.max(0, remainTime);
    }

    public boolean isTimeOver() {
        return getRemainingSeconds() <= 0;
    }

    // 최종 점수 계산
    public int calculateFinalScore() {
        if (totalCount == 0)
            return 0;
        // 만점 100점 기준 계산식
        return (int) Math.round(((double) correctCount / totalCount) * 100);
    }
}