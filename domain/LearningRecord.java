//오답 노트와 학습 이력

package backend.domain;

public class LearningRecord {
    private String gameType; // 게임유형
    private String questionText; // 문제내용
    private String myAnswer; // 내가 고른 답
    private String correctAnswer; // 정답

    public LearningRecord(String gameType, String questionText, String myAnswer, String correctAnswer) {
        this.gameType = gameType;
        this.questionText = questionText;
        this.myAnswer = myAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String toFileString() {
        return String.format("%s|%s|%s|%s", gameType, questionText, myAnswer, correctAnswer);
    }
}
