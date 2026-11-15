//오답 노트 조회 & 최종 학습 이력 관리
package backend.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import backend.domain.LearningRecord;
import backend.utils.FileIOUtils;

public class HistoryService {
    private static final String RECORD_FILE_PATH = "user_record.txt";
    private static final String HISTORY_FILE_PATH = "game_history.txt";

    // 오답 노트
    public List<LearningRecord> getWrongAnswerNotes() {
        List<LearningRecord> wrongNotes = new ArrayList<>();
        List<String> lines = FileIOUtils.readLines(RECORD_FILE_PATH);

        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                String myAnswer = parts[2].trim();
                String correctAnswer = parts[3].trim();

                if (!myAnswer.equalsIgnoreCase(correctAnswer)) {
                    wrongNotes.add(new LearningRecord(parts[0].trim(), parts[1].trim(), myAnswer, correctAnswer));
                }
            }
        }
        return wrongNotes;
    }

    // 최종 학습 이력 저장 (게임유형|맞힌개수/전체|최종점수|날짜)
    public void saveFinalHistory(String gameType, int correctCount, int totalCount, int finalScore) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String historyLine = gameType + "|" + correctCount + "/" + totalCount + "|" + finalScore + "점|" + date;

        FileIOUtils.appendLine(HISTORY_FILE_PATH, historyLine);
    }

    // 최종 학습 이력 전체 조회
    public List<String> getFinalHistory() {
        return FileIOUtils.readLines(HISTORY_FILE_PATH);
    }
}