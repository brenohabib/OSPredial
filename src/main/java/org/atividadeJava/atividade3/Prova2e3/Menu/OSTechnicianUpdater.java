package org.atividadeJava.atividade3.Prova2e3.Menu;

import java.io.*;
import java.util.*;

public class OSTechnicianUpdater {

    private static final String TECHNICIANS_OS_FILE = "src/main/java/org/atividadeJava/atividade3/Prova2e3/tecnicians_os.csv";
    private static final String TECHNICIANS_FILE = "src/main/java/org/atividadeJava/atividade3/Prova2e3/tecnicians.csv";
    private static final String OS_FILE = "src/main/java/org/atividadeJava/atividade3/Prova2e3/os.csv";

    public static void updateOSWithTechnicianName() throws IOException {
        List<String[]> technicianOSList = readCSV(TECHNICIANS_OS_FILE);

        Map<String, String> technicianMap = new HashMap<>();
        List<String[]> technicianList = readCSV(TECHNICIANS_FILE);
        for (String[] technician : technicianList) {
            technicianMap.put(technician[0], technician[1]);
        }

        List<String[]> osList = readCSV(OS_FILE);

        boolean header = true;
        for (String[] os : osList) {
            if (header) {
                header = false;
                continue;
            }
            String osId = os[0];
            for (String[] technicianOS : technicianOSList) {
                if (technicianOS[1].equals(osId)) {
                    String technicianId = technicianOS[0];
                    String technicianName = technicianMap.get(technicianId);
                    if (technicianName != null) {
                        os[1] = technicianName;
                        break;
                    }
                }
            }
        }

        writeCSV(OS_FILE, osList);
    }

    private static List<String[]> readCSV(String fileName) throws IOException {
        List<String[]> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.split(",", -1));
            }
        }
        return lines;
    }

    private static void writeCSV(String fileName, List<String[]> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] line : lines) {
                bw.write(String.join(",", line));
                bw.newLine();
            }
        }
    }
}