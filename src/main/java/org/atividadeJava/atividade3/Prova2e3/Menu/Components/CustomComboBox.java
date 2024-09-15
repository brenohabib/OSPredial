package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomComboBox extends JComboBox<String> {
    private static final Color ORANGE_COLOR = new Color(235, 151, 88);

    public CustomComboBox() {
        setUI(new CustomComboBoxUI());
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setFont(new Font("Arial", Font.PLAIN, 14));

        setRenderer(new CustomComboBoxRenderer());

        setFocusable(false);

        setEditor(new BasicComboBoxEditor() {
            @Override
            protected JTextField createEditorComponent() {
                JTextField editor = super.createEditorComponent();
                editor.setBorder(BorderFactory.createEmptyBorder());
                editor.setFocusable(false);
                return editor;
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(ORANGE_COLOR);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(Color.WHITE);
            }
        });
    }

    private static class CustomComboBoxUI extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton arrowButton = new JButton();
            arrowButton.setPreferredSize(new Dimension(20, 20));
            arrowButton.setBorderPainted(false);
            arrowButton.setContentAreaFilled(false);
            arrowButton.setFocusPainted(false);
            arrowButton.setUI(new BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    int width = c.getWidth();
                    int height = c.getHeight();
                    int size = Math.min(width, height) / 2;
                    int x = (width - size);
                    int y = (height - size);
                    g2.setColor(ORANGE_COLOR);
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.drawString("V", x - 4, y + 4);
                    g2.dispose();
                }
            });
            return arrowButton;
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            g.setColor(hasFocus ? ORANGE_COLOR : comboBox.getBackground());
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
}
