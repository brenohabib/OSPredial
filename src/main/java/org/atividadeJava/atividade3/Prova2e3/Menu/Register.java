package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.*;

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
    private JPanel logo;
    private JLabel labelPortaria;
    private JLabel labelBloco;
    private JLabel labelApto;

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
    }

    private void createUIComponents() {
        frontPanel = new RoundedPanel(30);
        nameInput = new RoundedTextField(15);
        emailInput = new RoundedTextField(15);
        passwordInput = new RoundedPasswordField(15);
        lobbyInput = new RoundedTextField(10);
        apartmentInput = new RoundedTextField(10);
        blockInput = new RoundedTextField(10);

        frontPanel = new RoundedPanel(30);

        mainPanel = new ImagePanel("C:\\Users\\breno\\Documentos\\Estudos\\Códigos\\atividade\\src\\main\\java\\Caixa 2.png");
        logo = new ImagePanel("C:\\Users\\breno\\Documentos\\Estudos\\Códigos\\atividade\\src\\main\\java\\Logo.png");
        registrarButton = new RoundedButton();
    }
}
