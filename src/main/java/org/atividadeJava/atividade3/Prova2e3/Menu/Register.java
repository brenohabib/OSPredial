package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Admin;
import org.atividadeJava.atividade3.Prova2e3.Login;
import org.atividadeJava.atividade3.Prova2e3.Menu.Components.*;
import org.atividadeJava.atividade3.Prova2e3.Resident;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Register extends JFrame {

    private static final Color FOCUS_COLOR = new Color(235, 151, 88);

    private JPanel mainPanel;
    private JPanel locationPanel;
    private JPanel locationRegistrationPanel;
    private JPanel registerPanel;
    private JPanel backgroundPanel;
    private JPanel frontPanel;
    private JPanel residentRegistrationPanel;
    private JButton confirmButton;
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
    private JLabel mainLabel;
    private JPanel namePanel;

    private boolean onRegister = true;
    private final Login loginService = new Login();

    private void createUIComponents() {
        frontPanel = new RoundedPanel(30);
        nameInput = new RoundedTextField(15);
        emailInput = new RoundedTextField(15);
        passwordInput = new RoundedPasswordField(15);
        frontPanel = new RoundedPanel(30);
        lobbyCB = new CustomComboBox();
        blockCB = new CustomComboBox();
        apartmentCB = new CustomComboBox();
        confirmButton = new RoundedButton();
        mainPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Caixa 2.png");
        logo = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Logo.png");
    }

    public Register() throws HeadlessException {
        super("Menu de Registro");
        UIManager.put("ComboBox.selectionBackground", FOCUS_COLOR);
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

        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                confirmButton.setForeground(Color.white);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                confirmButton.setForeground(Color.black);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                confirmButton.setBackground(new Color(65, 103, 51));
                confirmButton.setSize(195, 48);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                confirmButton.setBackground(new Color(168, 207, 69));
                confirmButton.setSize(200, 50);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(onRegister) {
                    String name = nameInput.getText();
                    String email = emailInput.getText();
                    String password = new String(passwordInput.getPassword());
                    String lobby = (String) lobbyCB.getSelectedItem();
                    String block = (String) blockCB.getSelectedItem();
                    String apartment = (String) apartmentCB.getSelectedItem();
                    loginService.processRegistration(name, email, password, lobby, block, apartment, mainPanel);
                    changeToLogin();
                } else {
                    String name = nameInput.getText();
                    String email = emailInput.getText();
                    String password = new String(passwordInput.getPassword());
                    loginService.processLogin(email, password, mainPanel);
                    try {

                        if (loginService.isLoginValid(email, password)) {
                            dispose();
                            if(loginService.isAdminLogged()) {
                                new Control(new Admin());
                            }
                            else{
                                new Resident(name, email);
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        hasAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(onRegister) {
                    changeToLogin();
                } else {
                    changeToRegister();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void changeToLogin() {
        lobbyCB.setVisible(false);
        blockCB.setVisible(false);
        apartmentCB.setVisible(false);
        labelPortaria.setVisible(false);
        labelBloco.setVisible(false);
        labelApto.setVisible(false);
        hasAccountButton.setText("<html><u>não possui uma conta?</u></html>");
        mainLabel.setText("LOGIN DO RESIDENTE");
        confirmButton.setText("Login");
        namePanel.setVisible(false);
        onRegister = false;
    }

    private void changeToRegister() {
        lobbyCB.setVisible(true);
        blockCB.setVisible(true);
        apartmentCB.setVisible(true);
        labelPortaria.setVisible(true);
        labelBloco.setVisible(true);
        labelApto.setVisible(true);
        hasAccountButton.setText("<html><u>já possui uma conta?</u></html>");
        mainLabel.setText("REGISTRO DE RESIDENTE");
        confirmButton.setText("Registrar");
        namePanel.setVisible(true);
        onRegister = true;
    }
}
