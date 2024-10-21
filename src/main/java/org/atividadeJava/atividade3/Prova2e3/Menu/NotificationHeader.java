package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.RoundedButton;
import org.atividadeJava.atividade3.Prova2e3.OSNotificationEmailer;
import org.atividadeJava.atividade3.Prova2e3.User.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NotificationHeader extends JPanel {
    private JPanel mainPanel;
    private JLabel text;
    private JButton sendEmailButton;

    public NotificationHeader(Person user) {
        OSNotificationEmailer emailer = new OSNotificationEmailer(user);

        mainPanel.add(text);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        add(mainPanel);
        sendEmailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sendEmailButton.setForeground(Color.white);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendEmailButton.setForeground(Color.black);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                sendEmailButton.setBackground(new Color(65, 103, 51));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                sendEmailButton.setBackground(new Color(168, 207, 69));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Thread thread = new Thread(emailer);
                thread.start();
                JOptionPane.showMessageDialog(null, "Email Enviado!", "" , JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void createUIComponents() {
        sendEmailButton = new RoundedButton();
    }

    public void hideSendEmailButton() {
        sendEmailButton.setVisible(false);
    }
}
