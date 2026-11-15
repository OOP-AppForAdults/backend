package backend.domain;

public class CardSortingQuestion {
    private String correctAnswer; // 정답
    private String questionText; // 카드에 적힐 문장

    public CardSortingQuestion(String correctAnswer, String questionText) {
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
    }

    // 분류 영역 일치 여부 검증 ("O" 또는 "X")
    public boolean isCorrect(String userAnswer) {
        if (userAnswer == null) {
            return false;
        }
        return this.correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }
}