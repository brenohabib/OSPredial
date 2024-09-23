package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import java.awt.*;

public class circlePanel extends JPanel {

    private int cornerRadius = 15;

    public circlePanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(-178, -220, getWidth()*2, getHeight()*2, cornerRadius, cornerRadius);
    }
}