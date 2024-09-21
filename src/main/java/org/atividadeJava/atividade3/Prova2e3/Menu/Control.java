package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.ImagePanel;
import org.atividadeJava.atividade3.Prova2e3.Menu.Components.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control extends JFrame {
    private JPanel mainPanel;
    private JPanel logo;
    private JLabel labelHeader;
    private JPanel notificationPanel;
    private JPanel userPanel;
    private JPanel solicitationPanel;
    private JPanel consultPanel;
    private JPanel historicPanel;
    private JPanel relatoryPanel;
    private JButton solicitationButton;
    private JButton consultButton;
    private JButton historicButton;
    private JButton relatoryButton;
    private JPanel roundPanel;

    public Control() {
        setContentPane(mainPanel);
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        solicitationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        });
    }

    private void createUIComponents() {
        logo = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Logo.png");
        solicitationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/solicite-Photoroom.png");
        consultPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/consulte-Photoroom.png");
        historicPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/historico carinha sus-Photoroom.png");
        relatoryPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/geracao nao z-Photoroom.png");
    }
}
