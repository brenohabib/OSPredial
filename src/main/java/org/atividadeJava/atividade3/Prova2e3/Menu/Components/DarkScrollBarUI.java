package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class DarkScrollBarUI extends BasicScrollBarUI {
    private static final int SCROLLBAR_WIDTH = 12;
    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 20;
    private static final int MIN_THUMB_SIZE = 40;
    private static final int MAX_THUMB_SIZE = Integer.MAX_VALUE;
    private Color backgroundColor;

    public DarkScrollBarUI(Color backgroundColor) {
        super();
        setBackgroundColor(backgroundColor);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(101, 67, 33);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(SCROLLBAR_WIDTH, MIN_THUMB_SIZE);
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(SCROLLBAR_WIDTH, MAX_THUMB_SIZE);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = thumbColor != null ? thumbColor : Color.WHITE;
        g2.setPaint(color);

        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width / 2, thumbBounds.height, ARC_WIDTH, ARC_HEIGHT);
        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(backgroundColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }
}
