package org.atividadeJava.atividade3.Prova2e3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OSReport extends JFrame {
    public OSReport(List<OS> osList, List<Feedback> feedbackList) {
        setTitle("Relatório de Ordens de Serviço");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Técnico", "Descrição", "Prioridade", "Status", "Data de Criação", "Estrelas", "Feedback"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (OS os : osList) {
            Feedback feedback = feedbackList.stream()
                    .filter(f -> f.getOsId() == os.getId())
                    .findFirst()
                    .orElse(null);

            model.addRow(new Object[]{
                    os.getId(),
                    os.getTechnician(),
                    os.getDescription(),
                    os.getPriority(),
                    os.getStatus(),
                    os.getCreatedAt(),
                    feedback != null ? feedback.getStars() : "Sem Avaliação",
                    feedback != null ? feedback.getFeedbackText() : "Sem Feedback"
            });
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }
}
