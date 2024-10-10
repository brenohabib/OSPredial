package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Tecnician;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Assignment extends JPanel {
    private JPanel mainPanel;
    private JTable tecTable;
    private final int currentOSId;

    public Assignment(int osId, JDialog dialog) {
        this.currentOSId = osId;
        createUIComponents();
        JScrollPane scrollPane = new JScrollPane(tecTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        addDoubleClickListener(dialog);
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
        List<Tecnician> technicians = readTechniciansFromCSV();

        String[] columnNames = {"ID", "Name", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Tecnician tech : technicians) {
            model.addRow(new Object[]{tech.getId(), tech.getName(), tech.getEmail()});
        }

        tecTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tecTable.setPreferredScrollableViewportSize(new Dimension(300, tecTable.getRowHeight() * model.getRowCount()));
    }

    private List<Tecnician> readTechniciansFromCSV() {
        List<Tecnician> techs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/atividadeJava/atividade3/Prova2e3/tecnicians.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String email = values[2];
                techs.add(new Tecnician(id, name, email));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar o arquivo \"tecnicians.csv\".", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return techs;
    }

    private void addDoubleClickListener(JDialog dialog) {
        tecTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int technicianId = (int) target.getValueAt(row, 0);
                    dialog.setVisible(false);
                    saveTechnicianToOS(technicianId, currentOSId);
                }
            }
        });
    }

    private void saveTechnicianToOS(int technicianId, int osId) {
        String fileName = "src/main/java/org/atividadeJava/atividade3/Prova2e3/tecnicians_os.csv";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(technicianId + "," + osId);

            OSTechnicianUpdater.updateOSWithTechnicianName();


        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao associar o técnico à OS ou atualizar os.csv", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showTechnicianSelectionDialog(Component parentComponent, int x, int y, int osId) {
        JDialog dialog = new JDialog((Frame) null, "", true);
        Assignment assignmentPanel = new Assignment(osId, dialog);
        dialog.setContentPane(assignmentPanel);

        assignmentPanel.addEscKeyBinding(dialog);

        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }
}