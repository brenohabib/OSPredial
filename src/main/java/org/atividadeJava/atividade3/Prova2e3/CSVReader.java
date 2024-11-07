package org.atividadeJava.atividade3.Prova2e3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class CSVReader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static List<OS> readOSFile(String filePath) {
        List<OS> osList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                OS os = getOs(line);
                osList.add(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return osList;
    }

    private static OS getOs(String line) {
        String[] values = line.split(",");
        return new OS(
                Integer.parseInt(values[0]),
                values[1],
                values[2],
                values[3],
                Integer.parseInt(values[4]),
                Integer.parseInt(values[5]),
                values[6],
                values[7],
                LocalDate.parse(values[8], DATE_FORMATTER),
                LocalDate.parse(values[9], DATE_FORMATTER),
                values[10]
        );
    }

    public static List<Feedback> readFeedbackFile(String filePath) {
        List<Feedback> feedbackList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Feedback feedback = new Feedback(
                        Integer.parseInt(values[0]),
                        Integer.parseInt(values[1]),
                        Double.parseDouble(values[2]),
                        values[3]
                );
                feedbackList.add(feedback);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public static String getDescriptionById(String filePath, int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int currentId = Integer.parseInt(values[0]);
                if (currentId == id) {
                    return values[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Descrição não encontrada!";
    }
}
