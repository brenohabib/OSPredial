package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JTextField blockInput;
    private JTextField apartmentInput;
    private JButton registerButton;
    private JTextField nameInput;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JTextField lobbyInput;
    private JPanel logo;
    private JLabel labelPortaria;
    private JLabel labelBloco;
    private JLabel labelApto;

    private void createUIComponents() {
        frontPanel = new RoundedPanel(30);
        nameInput = new RoundedTextField(15);
        emailInput = new RoundedTextField(15);
        passwordInput = new RoundedPasswordField(15);
        lobbyInput = new RoundedTextField(10);
        apartmentInput = new RoundedTextField(10);
        blockInput = new RoundedTextField(10);

        frontPanel = new RoundedPanel(30);

        mainPanel = new ImagePanel("src/main/java/Caixa 2.png");
        logo = new ImagePanel("src/main/java/Logo.png");
        registerButton = new RoundedButton();
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

                if (nameInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, preencha todos os campos");
                    return;
                }

                Matcher emailMatcher = emailPattern.matcher(emailInput.getText());
                if (!emailMatcher.matches()) {
                    JOptionPane.showMessageDialog(mainPanel, "Formato de e-mail inválido.");
                    return;
                }

                if (passwordInput.getPassword().length < 8) {
                    JOptionPane.showMessageDialog(mainPanel, "A senha deve ter no mínimo 8 caracteres.");
                    return;
                }

                JOptionPane.showMessageDialog(mainPanel, "Registro realizado com sucesso!");
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
}
