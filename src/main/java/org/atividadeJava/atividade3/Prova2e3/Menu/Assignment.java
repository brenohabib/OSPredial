package org.atividadeJava.atividade3.Prova2e3.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Assignment extends JPanel {
    private JPanel mainPanel;
    private JTable tecTable;

    public Assignment() {
        createUIComponents();

        JScrollPane scrollPane = new JScrollPane(tecTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private void addEscKeyBinding(JDialog dialog) {
        dialog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeWindow");
        dialog.getRootPane().getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dialog.dispose();
            }
        });
    }

    private void createUIComponents() {

        String[] columnNames = {"ID", "Name", "Specialty"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        model.addRow(new Object[]{1, "John Doe", "Electrical"});
        model.addRow(new Object[]{2, "Jane Smith", "Plumbing"});
        model.addRow(new Object[]{3, "Bob Johnson", "HVAC"});

        tecTable = new JTable(model);
        tecTable.setPreferredScrollableViewportSize(new Dimension(300, tecTable.getRowHeight() * model.getRowCount()));
    }

    public static void showTechnicianSelectionDialog(Component parentComponent, int x, int y) {
        JDialog dialog = new JDialog((Frame) null, "Selecione um Técnico", true);

        Assignment assignmentPanel = new Assignment();
        dialog.setContentPane(assignmentPanel);

        assignmentPanel.addEscKeyBinding(dialog);

        dialog.pack();
        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }
}
