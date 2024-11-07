package org.atividadeJava.atividade3.Prova2e3.Menu;

import com.opencsv.exceptions.CsvValidationException;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import org.atividadeJava.atividade3.Prova2e3.CSVReader;
import org.atividadeJava.atividade3.Prova2e3.Feedback;
import org.atividadeJava.atividade3.Prova2e3.OS;
import org.atividadeJava.atividade3.Prova2e3.OSReport;
import org.atividadeJava.atividade3.Prova2e3.User.Admin;
import org.atividadeJava.atividade3.Prova2e3.Menu.Components.*;
import org.atividadeJava.atividade3.Prova2e3.User.Person;
import org.atividadeJava.atividade3.Prova2e3.User.Resident;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Control extends JFrame {
    private JPanel mainPanel;
    private JPanel logo;
    private JLabel labelHeader;
    private JPanel notificationPanel;
    private JPanel userPanel;
    private JPanel solicitationPanel;
    private JPanel consultPanel;
    private JPanel historicPanel;
    private JPanel relatoryPanel;
    private JButton solicitationButton;
    private JButton consultButton;
    private JButton historicButton;
    private JButton relatoryButton;
    private JPanel solicitationBackground;
    private JPanel consultBackground;
    private JPanel historicBackground;
    private JPanel relatoryBackground;
    private JButton notificationButton;
    private JButton accountMenu;
    private JPanel circlePanel;
    private JTextArea descriptionTextField;
    private JButton backButton;
    private JButton confirmButton;
    private JComboBox<String> idCB;
    private JComboBox<String> lobbyCB;
    private JComboBox<String> blockCB;
    private JComboBox<String> apartmentCB;
    private JComboBox<String> priorityCB;
    private JPanel header;
    private JPanel actionPanel;
    private JPanel serviceSolicitationPanel;
    private JTable OSTable;
    private JPanel serviceConsultPanel;
    private JScrollPane scrollOSTable;
    private JPanel serviceHistoricPanel;
    private JScrollPane scrollHistoric;
    private JTable historicTable;
    private JLabel userLabel;
    private JLabel priorityLabel;

    private static final String OS_FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/os.csv";
    private static final String FEEDBACK_FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/feedbacks.csv";
    private final DrawerController drawer;

    private void createUIComponents() {
        logo = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Logo.png");
        solicitationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/solicite-Photoroom.png");
        consultPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/consulte-Photoroom.png");
        historicPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/historico carinha sus-Photoroom.png");
        relatoryPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/geracao nao z-Photoroom.png");
        notificationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Notificacion.png");
        userPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/0809 Grêmio Rio Verde.png");
        solicitationBackground = new RoundedPanel(90);
        consultBackground = new RoundedPanel(90);
        historicBackground = new RoundedPanel(90);
        relatoryBackground = new RoundedPanel(90);
        circlePanel = new CirclePanel(1080);
        confirmButton = new RoundedButton();
        backButton = new RoundedButton();
        lobbyCB = new CustomComboBox();
        blockCB = new CustomComboBox();
        apartmentCB = new CustomComboBox();
        idCB = new CustomComboBox();
        priorityCB = new CustomComboBox();
        OSTable = new CustomTable(false);
        historicTable = new CustomTable(true);
    }

    public Control(Person user) {
        setContentPane(mainPanel);
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        idCB.addItem(Integer.toString(putID()));
        String[] lobbyOptions = new String[]{"Portaria Principal", "Portaria Oeste"};
        for (String option : lobbyOptions) {
            lobbyCB.addItem(option);
        }
        String[] blockOptions = new String[]{"1", "2", "3", "4", "5"};
        for (String option : blockOptions) {
            blockCB.addItem(option);
        }
        String[] apartmentOptions = new String[]{"101", "102", "103", "201", "202", "203", "301", "302", "303"};
        for (String option : apartmentOptions) {
            apartmentCB.addItem(option);
        }
        String[] priorityOptions = new String[]{"Baixa", "Média", "Alta"};
        for (String option : priorityOptions) {
            priorityCB.addItem(option);
        }
        scrollOSTable.setOpaque(false);
        scrollOSTable.getViewport().setOpaque(false);
        scrollOSTable.setBorder(BorderFactory.createEmptyBorder());
        scrollOSTable.getVerticalScrollBar().setUI(new DarkScrollBarUI(Color.decode("#FCD1A2")));

        scrollHistoric.setOpaque(false);
        scrollHistoric.getViewport().setOpaque(false);
        scrollHistoric.setBorder(BorderFactory.createEmptyBorder());
        scrollHistoric.getVerticalScrollBar().setUI(new DarkScrollBarUI(Color.decode("#FCD1A2")));

        NotificationHeader notification = new NotificationHeader(user);

        userLabel.setText(user.getName());
        if (user instanceof Resident) {
            priorityLabel.setVisible(false);
            priorityCB.setVisible(false);
        }

        drawer = Drawer.newDrawer(this)
                .leftDrawer(false)
                .addChild(notification)
                //.background(Color.decode("#ECB051"))
                .drawerBackground(Color.decode("#F2F2C9"))
                .drawerWidth(400)
                .duration(200)
                .addChild(new ScrollNotification())
                .build();

        pack();
        setVisible(true);

        solicitationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                serviceSolicitationPanel.setVisible(true);
                backButton.setVisible(true);
                actionPanel.setVisible(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                solicitationBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        consultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                actionPanel.setVisible(false);
                backButton.setVisible(true);
                serviceConsultPanel.setVisible(true);
                CustomTable.updateTable((CustomTable) OSTable, false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                consultBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        historicButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionPanel.setVisible(false);
                backButton.setVisible(true);
                serviceHistoricPanel.setVisible(true);
                CustomTable.updateTable((CustomTable) historicTable, true);
                try {
                    addStarColumn(historicTable, "Estrelas");
                } catch (CsvValidationException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                hideColumn(historicTable, 2);
                hideColumn(historicTable, 5);
                hideColumn(historicTable, 7);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                historicBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        relatoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#c6c6a6"));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#f2f2c9"));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#FFFFD6"));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                relatoryBackground.setBackground(Color.decode("#c6c6a6"));
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String description = descriptionTextField.getText().trim().replaceAll("\\s+", "\\\\n");;
                String lobby = (String) lobbyCB.getSelectedItem();
                String block = (String) blockCB.getSelectedItem();
                String apartment = (String) apartmentCB.getSelectedItem();
                String priority = (String) priorityCB.getSelectedItem();
                try {
                    saveToCSV(putID(),"",description, lobby, block, apartment, priority, LocalDateTime.now(), LocalDateTime.now());
                    JOptionPane.showMessageDialog(mainPanel,"Ordem de serviço salva com sucesso!");
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(mainPanel,"Erro ao salvar ordem de serviço\n"+err.getMessage());
                    throw new RuntimeException(err);
                }
                updateScreen();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                confirmButton.setForeground(Color.white);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                confirmButton.setForeground(Color.black);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                confirmButton.setBackground(new Color(65, 103, 51));
                confirmButton.setSize(195, 48);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                confirmButton.setBackground(new Color(168, 207, 69));
                confirmButton.setSize(200, 50);
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                serviceSolicitationPanel.setVisible(false);
                serviceConsultPanel.setVisible(false);
                serviceHistoricPanel.setVisible(false);
                backButton.setVisible(false);
                actionPanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                backButton.setBackground(new Color(255, 186, 152));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backButton.setBackground(new Color(255, 91, 43));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(255, 91, 43));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(255, 138, 102));
            }
        });
        OSTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            private void showPopup(MouseEvent e) {
                int row = OSTable.rowAtPoint(e.getPoint());
                if (row != -1) {
                    OSTable.setRowSelectionInterval(row, row);

                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem viewDetails = new JMenuItem("Detalhes");

                    if (user instanceof Admin) {
                        JMenuItem setTec = getTecnicianAssignment(e);
                        popupMenu.add(setTec);
                        popupMenu.add(finalizeMenuItem(e));
                    }

                    viewDetails.addActionListener(ev -> {
                        Object rowData = OSTable.getValueAt(row, 0);
                        System.out.println("View details for row: " + rowData);
                        showDescription((Integer) rowData);
                    });

                    popupMenu.add(viewDetails);

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        historicTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            private void showPopup(MouseEvent e) {
                int row = historicTable.rowAtPoint(e.getPoint());
                if (row != -1) {
                    historicTable.setRowSelectionInterval(row, row);

                    JPopupMenu popupMenu = new JPopupMenu();

                    JMenuItem viewDetails = new JMenuItem("Detalhes");
                    viewDetails.addActionListener(ev -> {
                        Object rowData = historicTable.getValueAt(row, 0);

                        try {
                            int osId = Integer.parseInt(rowData.toString());
                            System.out.println("View details for row: " + osId);
                            showDescription(osId);
                        } catch (NumberFormatException exception) {
                            System.err.println("Erro: O valor da célula não é um número inteiro.");
                        }
                    });


                    String status = historicTable.getValueAt(row, 7).toString();
                    if ("Finalizado".equals(status)) {
                        JMenuItem addFeedback = feedbackMenuItem(e);
                        popupMenu.add(addFeedback);
                    }

                    popupMenu.add(viewDetails);

                    if (user instanceof Admin) {
                        JMenuItem reopenOS = getReopenOSMenuItem(row);
                        popupMenu.add(reopenOS);
                        popupMenu.add(feedbackMenuItem(e));
                    }

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            private JMenuItem getReopenOSMenuItem(int row) {
                JMenuItem reopenOS = new JMenuItem("Reabrir OS");
                reopenOS.addActionListener(ev -> {
                    try {
                        reopenHistoricOS(row);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Erro ao reabrir OS: " + ex.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });
                return reopenOS;
            }

            private void reopenHistoricOS(int row) throws IOException {
                String idOS = historicTable.getValueAt(row, 0).toString();

                Path path = Paths.get(OS_FILE_PATH);
                List<String> lines = Files.readAllLines(path);

                for (int i = 1; i < lines.size(); i++) {
                    String[] columns = lines.get(i).split(",");

                    if (columns[0].equals(idOS)) {
                        columns[7] = "Em Andamento";
                        columns[10] = "Não Finalizado";

                        String updatedLine = String.join(",", columns);
                        lines.set(i, updatedLine);

                        Files.write(path, lines);

                        CustomTable.updateTable((CustomTable) OSTable, false);
                        CustomTable.updateTable((CustomTable) historicTable, true);

                        JOptionPane.showMessageDialog(null,
                                "OS reaberta com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            }

            private void updateStarCell(JTable table, int osId) throws IOException, CsvValidationException {
                FeedbackStar.readCSVAndCalculateAverages(FEEDBACK_FILE_PATH);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rowCount = model.getRowCount();
                int starColumn = model.getColumnCount() - 1;

                for (int i = 0; i < rowCount; i++) {
                    Object currentOsId = model.getValueAt(i, 0);
                    if (currentOsId != null && currentOsId.toString().equals(String.valueOf(osId))) {
                        float starAverage = FeedbackStar.getAverageStars(osId);

                        model.setValueAt(starAverage, i, starColumn);

                        Rectangle cellRect = table.getCellRect(i, starColumn, true);
                        table.repaint(cellRect);
                        break;
                    }
                }
            }

            private JMenuItem feedbackMenuItem(MouseEvent e) {
                int row = historicTable.rowAtPoint(e.getPoint());
                JMenuItem feedback = new JMenuItem("Adicionar Feedback");

                feedback.addActionListener(ev -> {
                    try {
                        String idOS = historicTable.getValueAt(row, 0).toString();

                        Path feedbackPath = Paths.get(FEEDBACK_FILE_PATH);


                        JPanel feedbackPanel = new JPanel(new GridLayout(2, 1, 5, 5));

                        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JLabel starsLabel = new JLabel("Avaliação (1-5 estrelas):");
                        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(5.0, 1.0, 5.0, 1.0);
                        JSpinner starsSpinner = new JSpinner(spinnerModel);
                        starsPanel.add(starsLabel);
                        starsPanel.add(starsSpinner);

                        JPanel commentPanel = new JPanel(new BorderLayout());
                        JLabel commentLabel = new JLabel("Comentário:");
                        JTextArea commentArea = new JTextArea(3, 20);
                        commentArea.setLineWrap(true);
                        commentArea.setWrapStyleWord(true);
                        JScrollPane scrollPane = new JScrollPane(commentArea);
                        commentPanel.add(commentLabel, BorderLayout.NORTH);
                        commentPanel.add(scrollPane, BorderLayout.CENTER);

                        feedbackPanel.add(starsPanel);
                        feedbackPanel.add(commentPanel);

                        int result = JOptionPane.showConfirmDialog(null,
                                feedbackPanel,
                                "Adicionar Feedback",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            double stars = (Double) starsSpinner.getValue();
                            String comment = commentArea.getText().trim();

                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String currentDate = now.format(formatter);

                            String feedbackLine = String.format("%d,%s,%.1f,\"%s\",%s",
                                    getNextFeedbackId(),
                                    idOS,
                                    stars,
                                    comment.replace("\"", "\"\""),
                                    currentDate);

                            List<String> feedbackLines = new ArrayList<>();

                            if (Files.exists(feedbackPath)) {
                                feedbackLines = Files.readAllLines(feedbackPath);
                            } else {
                                feedbackLines.add("id,osId,stars,comment,date");
                            }

                            feedbackLines.add(feedbackLine);
                            Files.write(feedbackPath, feedbackLines);

                            try {
                                FeedbackStar.readCSVAndCalculateAverages(FEEDBACK_FILE_PATH);
                                CustomTable.updateTable((CustomTable) OSTable, false);

                                JOptionPane.showMessageDialog(null,
                                        "Feedback adicionado com sucesso!",
                                        "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                        updateStarCell(historicTable, Integer.parseInt(idOS));

                            } catch (CsvValidationException ex) {
                                throw new IOException("Erro ao processar arquivo CSV", ex);
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Erro ao adicionar feedback: " + ex.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });

                return feedback;
            }
        });
        
        notificationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (drawer.isShow()) {
                    drawer.hide();
                }
                else {
                    drawer.show();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(255, 91, 43));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(255, 138, 102));
            }
        });
        relatoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<OS> osList = CSVReader.readOSFile("src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/os.csv");
                List<Feedback> feedbackList = CSVReader.readFeedbackFile("src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/feedbacks.csv");

                SwingUtilities.invokeLater(() -> {
                    OSReport report = new OSReport(osList, feedbackList);
                    report.setVisible(true);
                });
            }
        });
    }
    private JMenuItem getTecnicianAssignment(MouseEvent e) {
        int row = OSTable.rowAtPoint(e.getPoint());
        JMenuItem setTec = new JMenuItem("Alterar técnico");
        String idOS = OSTable.getValueAt(row, 0).toString();
        setTec.addActionListener(ev -> Assignment.showTechnicianSelectionDialog(OSTable,e.getComponent(), e.getXOnScreen(), e.getYOnScreen(), Integer.parseInt(idOS)));
        return setTec;
    }
    private JMenuItem finalizeMenuItem(MouseEvent e) {
        int row = OSTable.rowAtPoint(e.getPoint());
        JMenuItem finalize = new JMenuItem("Finalizar");
        finalize.addActionListener(ev -> {
            try {
                String idOS = OSTable.getValueAt(row, 0).toString();

                Path path = Paths.get(OS_FILE_PATH);
                List<String> lines = Files.readAllLines(path);

                for (int i = 1; i < lines.size(); i++) {
                    String[] columns = lines.get(i).split(",");

                    if (columns[0].equals(idOS)) {
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String currentDate = now.format(formatter);

                        columns[7] = "Finalizado";
                        columns[9] = currentDate;
                        columns[10] = currentDate;

                        String updatedLine = String.join(",", columns);
                        lines.set(i, updatedLine);

                        Files.write(path, lines);
                        CustomTable.updateTable((CustomTable) OSTable, false);

                        JOptionPane.showMessageDialog(null, "OS finalizada com sucesso!");
                        break;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao finalizar OS: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return finalize;
    }

    public static void hideColumn(JTable table, int columnIndex) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        table.getColumnModel().removeColumn(column);
    }

    public static void addStarColumn(JTable table, String columnName) throws CsvValidationException, IOException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        FeedbackStar.readCSVAndCalculateAverages(FEEDBACK_FILE_PATH);

        model.addColumn(columnName);

        for (int i = 0; i < rowCount; i++) {
            Object osIdObj = model.getValueAt(i, 0);
            if (osIdObj != null) {
                int osId = Integer.parseInt(osIdObj.toString());
                float starAverage = FeedbackStar.getAverageStars(osId);
                model.setValueAt(starAverage, i, model.getColumnCount() - 1);
            }
        }

        TableColumn starColumn = table.getColumnModel().getColumn(model.getColumnCount() - 1);
        starColumn.setCellRenderer(new FeedbackStar(table));

        starColumn.setPreferredWidth(100);
    }

    private long getNextFeedbackId() throws IOException {
        Path feedbackPath = Paths.get(FEEDBACK_FILE_PATH);
        if (!Files.exists(feedbackPath)) {
            return 1L;
        }

        List<String> lines = Files.readAllLines(feedbackPath);
        if (lines.size() <= 1) {
            return 1L;
        }

        String lastLine = lines.get(lines.size() - 1);
        String[] columns = lastLine.split(",");
        try {
            return Long.parseLong(columns[0]) + 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return lines.size();
        }
    }

    private int putID() {
        int maiorId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(OS_FILE_PATH))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] colunas = linha.split(",");
                if (colunas.length > 0) {
                    try {
                        int id = Integer.parseInt(colunas[0]);
                        if (id > maiorId) {
                            maiorId = id;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao tentar definir ID: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            return 1;
        }
        return maiorId + 1;
    }

    private void saveToCSV(int ID,
                           String tecnicianName,
                           String description,
                           String lobby,
                           String block,
                           String apartment,
                           String priority,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) throws IOException {

        try (FileWriter fileWriter = new FileWriter(OS_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,Não Finalizado%n",
                    ID,
                    tecnicianName,
                    description,
                    lobby,
                    block,
                    apartment,
                    priority,
                    "Pendente",
                    String.format("%02d/%02d/%d", createdAt.getDayOfMonth(), createdAt.getMonthValue(), createdAt.getYear()),
                    String.format("%02d/%02d/%d", updatedAt.getDayOfMonth(), updatedAt.getMonthValue(), updatedAt.getYear()));
        }
    }

    private void updateScreen() {
        idCB.removeAllItems();
        idCB.addItem(Integer.toString(putID()));
        descriptionTextField.setText("");
    }

    private void showDescription(int osId) {
        String filePath = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/os.csv";
        String descricao = CSVReader.getDescriptionById(filePath, osId);

        descricao = descricao.replace("\\n", "<br>");

        JFrame frame = new JFrame("Ordem de Serviço");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ScrollPane scrollPane = new ScrollPane();

        JLabel labelDescricao = new JLabel("<html>" + descricao + "</html>");
        labelDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.add(labelDescricao, BorderLayout.WEST);
        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}