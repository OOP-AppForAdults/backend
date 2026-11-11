//파일 읽기&쓰기

package backend.utils;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class FileIOUtils {

    // 데이터 읽어오기(textFile -> List 변환)
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 오류 (" + filePath + "): " + e.getMessage());
        } finally {
            // 자원 해제
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("스트림 닫기 오류: " + e.getMessage());
                }
            }
        }
        return lines;
    }

    // 파일 데이터 추가
    public static void appendLine(String filePath, String content) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filePath, true), StandardCharsets.UTF_8));
            bw.write(content);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("파일 쓰기 오류 (" + filePath + "): " + e.getMessage());
        } finally {
            // 자원 해제
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.err.println("스트림 닫기 오류: " + e.getMessage());
                }
            }
        }
    }
}
