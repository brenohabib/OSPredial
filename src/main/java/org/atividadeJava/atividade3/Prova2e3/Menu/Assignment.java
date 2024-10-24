package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.CustomTable;
import org.atividadeJava.atividade3.Prova2e3.User.Tecnician;

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
    private final JTable historicTable;

    public Assignment(JTable table,int osId, JDialog dialog) {
        this.currentOSId = osId;
        createUIComponents();
        JScrollPane scrollPane = new JScrollPane(tecTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        this.historicTable = table;

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
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/tecnicians.csv"))) {
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
                    CustomTable.updateTable((CustomTable) historicTable, false);
                }
            }
        });
    }

    private void saveTechnicianToOS(int technicianId, int osId) {
        String fileName = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/tecnicians_os.csv";
        List<String[]> technicianOSList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                technicianOSList.add(line.split(",", -1));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo \"tecnicians_os.csv\".", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        boolean found = false;
        for (String[] record : technicianOSList) {
            if (record[1].equals(String.valueOf(osId))) {
                record[0] = String.valueOf(technicianId);
                found = true;
                break;
            }
        }

        if (!found) {
            technicianOSList.add(new String[]{String.valueOf(technicianId), String.valueOf(osId)});
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] record : technicianOSList) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
            updateOSWithTechnicianName(technicianId, osId);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a associação no arquivo \"tecnicians_os.csv\".", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOSWithTechnicianName(int technicianId, int osId) {
        String technicianName = getTechnicianName(technicianId);
        String osFileName = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/os.csv";
        List<String[]> osList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(osFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                osList.add(line.split(",", -1));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo \"os.csv\".", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean updated = false;
        for (String[] record : osList) {
            if (record[0].equals(String.valueOf(osId))) {
                record[1] = technicianName;
                updated = true;
                break;
            }
        }

        if (updated) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(osFileName))) {
                for (String[] record : osList) {
                    bw.write(String.join(",", record));
                    bw.newLine();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar o nome do técnico no arquivo \"os.csv\".", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getTechnicianName(int technicianId) {
        String fileName = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/tecnicians.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (Integer.parseInt(values[0]) == technicianId) {
                    return values[1];
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo \"tecnicians.csv\".", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }

    public static void showTechnicianSelectionDialog(JTable table,Component parentComponent, int x, int y, int osId) {
        JDialog dialog = new JDialog((Frame) null, "", true);
        Assignment assignmentPanel = new Assignment(table,osId, dialog);
        dialog.setContentPane(assignmentPanel);

        assignmentPanel.addEscKeyBinding(dialog);

        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocation(x, y);
        dialog.setVisible(true);
    }

}