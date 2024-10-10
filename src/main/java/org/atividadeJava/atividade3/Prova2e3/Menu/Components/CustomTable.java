package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CustomTable extends JTable {
    private static final String FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/os.csv";
    private static final String[] COLUMN_NAMES = {"ID", "Técnico", null, "Portaria", "Bloco", "Apartamento", "Prioridade", "Status", "Criado", "Atualizado"};
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 0);
    private static final Color GRID_COLOR = Color.decode("#B86E49");
    private static final Color HEADER_BACKGROUND = Color.decode("#B86E49");
    private static final Color HEADER_FOREGROUND = Color.WHITE;
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Color EVEN_ROW_COLOR = Color.decode("#EEC599");
    private static final Color ODD_ROW_COLOR = Color.decode("#FCD1A2");
    private static final Color SELECTED_ROW_COLOR = Color.decode("#A0522D");

    private int selectedRow = -1;

    public CustomTable(boolean onlyFinished) {
        super();
        setupTable();
        populateTable(onlyFinished);
        setupRowSelection();
    }

    private void setupTable() {
        setBackground(BACKGROUND_COLOR);
        setGridColor(GRID_COLOR);

        JTableHeader header = getTableHeader();
        header.setBackground(HEADER_BACKGROUND);
        header.setForeground(HEADER_FOREGROUND);
        header.setFont(HEADER_FONT);
    }

    private void setupRowSelection() {
        getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = getSelectedRow();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                paintSelectedRow(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                paintSelectedRow(e);
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row == selectedRow || isSelected) {
                    c.setBackground(SELECTED_ROW_COLOR);
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? EVEN_ROW_COLOR : ODD_ROW_COLOR);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });
    }
    private void paintSelectedRow(MouseEvent e) {
        Point point = e.getPoint();
        int row = rowAtPoint(point);
        if (row != -1) {
            setRowSelectionInterval(row, row);
            selectedRow = row;
            repaint();
        }
    }

    private void populateTable(boolean onlyFinished) {
        Object[][] data;
        if (onlyFinished) {
            data = readFinishedTasks();
        } else {
            data = readUnfinishedTasks();
        }
        DefaultTableModel model = new DefaultTableModel(data, COLUMN_NAMES);
        setModel(model);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        setRowSorter(sorter);

        setupSorters(sorter);
        removeColumn(getColumnModel().getColumn(2));

        resizeTableColumns();
    }
    public static void updateTable(CustomTable table, boolean onlyFinished) {
        table.populateTable(onlyFinished);
    }

    private void setupSorters(TableRowSorter<DefaultTableModel> sorter) {
        sorter.setComparator(0, Comparator.comparingInt((String o) -> Integer.parseInt(o)));
        sorter.setComparator(6, createPriorityComparator());
        sorter.setComparator(7, createStatusComparator());
        sorter.setComparator(8, createDateComparator());
        sorter.setComparator(9, createDateComparator());
    }

    private Comparator<String> createPriorityComparator() {
        List<String> priorities = Arrays.asList("Baixa", "Média", "Alta");
        return (o1, o2) -> {
            int index1 = (o1 == null) ? priorities.size() : priorities.indexOf(o1);
            int index2 = (o2 == null) ? priorities.size() : priorities.indexOf(o2);
            return Integer.compare(index1, index2);
        };
    }

    private Comparator<String> createStatusComparator() {
        List<String> statuses = Arrays.asList("Pendente", "Em Progresso", "Finalizado");
        return (o1, o2) -> {
            int index1 = (o1 == null) ? statuses.size() : statuses.indexOf(o1);
            int index2 = (o2 == null) ? statuses.size() : statuses.indexOf(o2);
            return Integer.compare(index1, index2);
        };
    }

    private Comparator<String> createDateComparator() {
        return (o1, o2) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date1 = sdf.parse(o1);
                Date date2 = sdf.parse(o2);
                return date1.compareTo(date2);
            } catch (ParseException e) {
                return 0;
            }
        };
    }

    private Object[][] readUnfinishedTasks() {
        return readCSVData(row -> !row[7].equals("Finalizado"));
    }

    private Object[][] readFinishedTasks() {
        return readCSVData(row -> row[7].equals("Finalizado"));
    }

    private Object[][] readCSVData(java.util.function.Predicate<String[]> filter) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(",");
                if (filter.test(columns)) {
                    data.add(columns);
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
        return data.toArray(new Object[0][]);
    }

    private void resizeTableColumns() {
        JTableHeader header = getTableHeader();
        FontMetrics metrics = header.getFontMetrics(header.getFont());

        for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
            int width = metrics.stringWidth(getColumnName(columnIndex));

            for (int rowIndex = 0; rowIndex < getRowCount(); rowIndex++) {
                Object value = getValueAt(rowIndex, columnIndex);
                if (value != null) {
                    width = Math.max(width, metrics.stringWidth(value.toString()));
                }
            }
            getColumnModel().getColumn(columnIndex).setPreferredWidth(width + 2);
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}