package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.ImagePanel;
import org.atividadeJava.atividade3.Prova2e3.Menu.Components.RoundedPanel;
import org.atividadeJava.atividade3.Prova2e3.Menu.Components.circlePanel;

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
    private JPanel solicitationBackground;
    private JPanel consultBackground;
    private JPanel historicBackground;
    private JPanel relatoryBackground;
    private JButton button1;
    private JButton button2;
    private JPanel circlePanel;

    public Control() {
        setContentPane(mainPanel);
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        solicitationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        consultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        historicButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        relatoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
    }

    private void createUIComponents() {
        logo = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Logo.png");
        solicitationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/solicite-Photoroom.png");
        consultPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/consulte-Photoroom.png");
        historicPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/historico carinha sus-Photoroom.png");
        relatoryPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/geracao nao z-Photoroom.png");
        notificationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/notification-icon.png");
        userPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/user-icon.png");
        solicitationBackground = new RoundedPanel(90);
        consultBackground = new RoundedPanel(90);
        historicBackground = new RoundedPanel(90);
        relatoryBackground = new RoundedPanel(90);
        circlePanel = new circlePanel(1080);
    }
}
