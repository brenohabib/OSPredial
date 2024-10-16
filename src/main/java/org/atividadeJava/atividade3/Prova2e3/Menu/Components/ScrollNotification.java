package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScrollNotification extends JScrollPane {
    private static final String CSV_FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/os.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ScrollNotification() {
        super(new JPanel(new GridLayout(0, 1, 0, 5)));
        getVerticalScrollBar().setUI(new DarkScrollBarUI(Color.decode("#F2F2C9")));
        setBorder(null);
        JPanel contentPanel = (JPanel) getViewport().getView();
        contentPanel.setBackground(Color.decode("#F2F2C9"));
        contentPanel.setBorder(null);
        List<String[]> notifications = readAndFilter();
        for (String[] notification : notifications) {
            contentPanel.add(create(notification));
        }
    }

    private List<String[]> readAndFilter() {
        List<String[]> notificacoesAtrasadas = new ArrayList<>();
        LocalDate dataLimite = LocalDate.now().minusDays(3);

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] dados = line.split(",");
                LocalDate dataCriacao = LocalDate.parse(dados[8], DATE_FORMATTER);
                if (dataCriacao.isBefore(dataLimite) && "Pendente".equals(dados[7])) {
                    notificacoesAtrasadas.add(dados);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notificacoesAtrasadas;
    }

    private JPanel create(String[] dados) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        panel.add(new JLabel("ID: " + dados[0]), gbc);
        panel.add(new JLabel("Técnico: " + dados[1]), gbc);
        panel.add(new JLabel("Local: " + dados[3] + ", Bloco " + dados[4] + ", Apto " + dados[5]), gbc);
        panel.add(new JLabel("Prioridade: " + dados[6]), gbc);
        panel.add(new JLabel("Status: " + dados[7]), gbc);
        panel.add(new JLabel("Criado em: " + dados[8]), gbc);

        return panel;
    }
}