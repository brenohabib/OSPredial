package org.atividadeJava.atividade3.Prova2e3;

import javax.swing.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    private static final String FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/records.csv";
    private static boolean adminLogged;

    public static boolean isAdminLogged() {
        return adminLogged;
    }

    public void processRegistration(String name, String email, String password, String lobby, String block, String apartment, JPanel mainPanel) {
        try {
            if (isRegisterValid(email, name, password, mainPanel)) {
                saveToCSV(putID(),name, email, password, lobby, block, apartment);
                showMessage("Registro realizado com sucesso!", JOptionPane.INFORMATION_MESSAGE, mainPanel);
            }
        } catch (IOException ex) {
            showMessage("Erro ao salvar o registro.", JOptionPane.ERROR_MESSAGE, mainPanel);
        }
    }

    private int putID() {
        int maiorId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] colunas = linha.split(",");
                if (colunas.length > 0) {
                    try {
                        int id = Integer.parseInt(colunas[0]);
                        if (id > maiorId) {
                            maiorId = id;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao tentar definir ID: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            return 1;
        }
        return maiorId + 1;
    }

    public void processLogin(String email, String password, JPanel mainPanel) {
        try {
            if (isLoginValid(email, password) && email.equals("admin@admin.com")) {
                showMessage("Admin logado com sucesso!", JOptionPane.INFORMATION_MESSAGE, mainPanel);
                adminLogged = true;
            } else if (isLoginValid(email, password)) {
                showMessage("Login realizado com sucesso!", JOptionPane.INFORMATION_MESSAGE, mainPanel);
                adminLogged = false;
            } else {
                showMessage("Email ou senha inválidos.", JOptionPane.ERROR_MESSAGE, mainPanel);
            }
        } catch (IOException ex) {
            showMessage("Erro ao verificar o login.", JOptionPane.ERROR_MESSAGE, mainPanel);
        }
    }

    public boolean isLoginValid(String email, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 3 && email.equals(fields[2]) && password.equals(fields[3])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRegisterValid(String email, String name, String password, JPanel mainPanel) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher emailMatcher = emailPattern.matcher(email);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Por favor, preencha todos os campos", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!emailMatcher.matches()) {
            JOptionPane.showMessageDialog(mainPanel, "Formato de e-mail inválido.", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(mainPanel, "A senha deve ter no mínimo 8 caracteres.", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 1 && email.equals(fields[1])) {
                    JOptionPane.showMessageDialog(mainPanel, "Este e-mail já foi cadastrado.", "", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void saveToCSV(int ID, String name, String email, String password, String lobby, String apartment, String block) throws IOException {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf("%d,%s,%s,%s,%s,%s,%s%n", ID, name, email, password, lobby, apartment, block);
        }
    }

    private void showMessage(String message, int messageType, JPanel mainPanel) {
        JOptionPane.showMessageDialog(mainPanel, message, "", messageType);
    }


}
