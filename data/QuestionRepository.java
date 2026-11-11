//데이터 객체 변환

package backend.data;

import backend.domain.MultipleChoiceQuestion;
import backend.utils.FileIOUtils;

import java.util.*;

public class QuestionRepository {
    private static final String QUIZ_FILE_PATH = "quiz_data.txt";

    public List<MultipleChoiceQuestion> loadQuizQuestions() {
        List<MultipleChoiceQuestion> questions = new ArrayList<>();
        List<String> lines = FileIOUtils.readLines(QUIZ_FILE_PATH);

        // 파싱
        for (String line : lines) {
            String[] parts = line.split("\\|");

            if (parts.length == 7) {
                String category = parts[0].trim();
                String questionText = parts[1].trim();
                List<String> choices = Arrays.asList(
                        parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
                int correctAnswer = Integer.parseInt(parts[6].trim());

                questions.add(new MultipleChoiceQuestion(category, questionText, choices, correctAnswer));
            }
        }
        return questions;
    }
}
