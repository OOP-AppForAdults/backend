//데이터 모델 확정

package backend.domain;

import java.util.*;

public class MultipleChoiceQuestion {
    private String category; // 카테고리
    private String questionText; // 문제내용
    private List<String> choices; // 보기 1,2,3,4
    private int correctAnswer; // 정답번호

    public MultipleChoiceQuestion(String category, String questionText, List<String> choices, int correctAnswer) {
        this.category = category;
        this.questionText = questionText;
        this.choices = new ArrayList<>(choices);
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(int userAnswer) {
        return this.correctAnswer == userAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}