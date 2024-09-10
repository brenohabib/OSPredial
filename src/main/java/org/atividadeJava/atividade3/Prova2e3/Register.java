package org.atividadeJava.atividade3.Prova2e3;

import javax.swing.*;
import java.awt.*;

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
    private JButton registrarButton;
    private JTextField nameInput;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JTextField lobbyInput;

    public Register() throws HeadlessException {
        super("Menu de Registro");
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

}
