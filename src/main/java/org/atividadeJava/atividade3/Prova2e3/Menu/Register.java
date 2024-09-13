package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends JFrame {

    private JPanel mainPanel;
    private JPanel locationPanel;
    private JPanel locationRegistrationPanel;
    private JPanel registerPanel;
    private JPanel backgroundPanel;
    private JPanel frontPanel;
    private JPanel residentRegistrationPanel;
    private JButton registerButton;
    private JTextField nameInput;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JPanel logo;
    private JLabel labelPortaria;
    private JLabel labelBloco;
    private JLabel labelApto;
    private JButton hasAccountButton;
    private JComboBox<String> lobbyCB;
    private JComboBox<String> blockCB;
    private JComboBox<String> apartmentCB;

    private void createUIComponents() {
        frontPanel = new RoundedPanel(30);
        nameInput = new RoundedTextField(15);
        emailInput = new RoundedTextField(15);
        passwordInput = new RoundedPasswordField(15);
        frontPanel = new RoundedPanel(30);
        registerButton = new RoundedButton();
        mainPanel = new ImagePanel("src/main/java/Caixa 2.png");
        logo = new ImagePanel("src/main/java/Logo.png");
    }

    public Register() throws HeadlessException {
        super("Menu de Registro");
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        labelPortaria.setForeground(Color.white);
        labelBloco.setForeground(Color.white);
        labelApto.setForeground(Color.white);
        hasAccountButton.setText("<html><u>já possui uma conta?</u></html>");

        String[] lobbyOptions = new String[]{"Portaria Principal", "Portaria Oeste"};
        for (String option : lobbyOptions) {
            lobbyCB.addItem(option);
        }
        String[] blockOptions = new String[]{"1", "2", "3", "4", "5"};
        for (String option : blockOptions) {
            blockCB.addItem(option);
        }
        String[] apartmentOptions = new String[]{"101", "102", "103", "201", "202", "203", "301", "302", "303"};
        for (String option : apartmentOptions) {
            apartmentCB.addItem(option);
        }

        pack();
        setVisible(true);

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registerButton.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                registerButton.setForeground(Color.black);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Matcher emailMatcher = emailPattern.matcher(emailInput.getText());
                if (nameInput.getText().isEmpty() ||
                        emailInput.getText().isEmpty() ||
                        passwordInput.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, preencha todos os campos", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!emailMatcher.matches()) {
                    JOptionPane.showMessageDialog(mainPanel, "Formato de e-mail inválido.", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (passwordInput.getPassword().length < 8) {
                    JOptionPane.showMessageDialog(mainPanel, "A senha deve ter no mínimo 8 caracteres.", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                    saveToCSV(
                            nameInput.getText(),
                            emailInput.getText(),
                            new String(passwordInput.getPassword()),
                            (String) lobbyCB.getSelectedItem(),
                            (String )blockCB.getSelectedItem(),
                            (String) apartmentCB.getSelectedItem()
                    );
                    JOptionPane.showMessageDialog(mainPanel, "Registro realizado com sucesso!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao salvar o registro.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                registerButton.setBackground(new Color(65, 103, 51));
                registerButton.setSize(195, 48);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                registerButton.setBackground(new Color(168, 207, 69));
                registerButton.setSize(200, 50);
            }
        });
    }

    private void saveToCSV(String name, String email, String password, String lobby, String apartment, String block) throws IOException {
        String filePath = "src/main/java/org/atividadeJava/atividade3/Prova2e3/records.csv";

        try (FileWriter fileWriter = new FileWriter(filePath, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.printf("%s,%s,%s,%s,%s,%s%n", name, email, password, lobby, apartment, block);
        }
    }
}
