//채점 & 최종 이력 저장
package backend.service;

import java.util.List;

import backend.data.QuestionRepository;
import backend.domain.LearningRecord;
import backend.domain.MultipleChoiceQuestion;
import backend.utils.FileIOUtils;

public class QuizService {
    private static final String RECORD_FILE_PATH = "user_record.txt";

    private QuestionRepository repository;
    private List<MultipleChoiceQuestion> currentQuestions;
    private int correctCount = 0;
    private int totalCount = 0;

    public QuizService() {
        this.repository = new QuestionRepository();
        this.currentQuestions = repository.loadQuizQuestions();
        this.totalCount = currentQuestions.size();
    }

    public List<MultipleChoiceQuestion> getQuestions() {
        return currentQuestions;
    }

    // 채점 & 오답 노트 파일 저장
    public boolean submitAnswer(MultipleChoiceQuestion question, int userAnswer) {
        boolean isCorrect = question.isCorrect(userAnswer);

        if (isCorrect) {
            correctCount++;
        }

        // 저장용 객체 생성
        LearningRecord record = new LearningRecord("퀴즈", question.getQuestionText(), String.valueOf(userAnswer),
                String.valueOf(question.getCorrectAnswer()));

        FileIOUtils.appendLine(RECORD_FILE_PATH, record.toFileString());

        return isCorrect;
    }

    // 최종 점수 계산
    public int calculateFinalScore() {
        if (totalCount == 0)
            return 0;
        // 만점 100점 기준 계산식
        return (int) Math.round(((double) correctCount / totalCount) * 100);
    }
}