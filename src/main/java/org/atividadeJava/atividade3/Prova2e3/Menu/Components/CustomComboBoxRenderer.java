package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import java.awt.*;

public class CustomComboBoxRenderer extends JLabel implements ListCellRenderer<String> {

    public CustomComboBoxRenderer() {
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        setText(value);
        if (isSelected) {
            setBackground(new Color(235, 151, 88));
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        setBorder(BorderFactory.createEmptyBorder());
        return this;
    }
}
