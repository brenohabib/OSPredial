package org.atividadeJava.atividade3.Prova2e3.Menu;

import org.atividadeJava.atividade3.Prova2e3.Menu.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.LocalDateTime;

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
    private JButton button1;
    private JButton button2;
    private JPanel circlePanel;
    private JTextArea textArea1;
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

    private static final String FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/os.csv";

    private void createUIComponents() {
        logo = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/Logo.png");
        solicitationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/solicite-Photoroom.png");
        consultPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/consulte-Photoroom.png");
        historicPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/historico carinha sus-Photoroom.png");
        relatoryPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/geracao nao z-Photoroom.png");
        notificationPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/notification-icon.png");
        userPanel = new ImagePanel("src/main/java/org/atividadeJava/atividade3/Prova2e3/Menu/images/user-icon.png");
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

    public Control() {
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
        scrollOSTable.getVerticalScrollBar().setUI(new DarkScrollBarUI());

        scrollHistoric.setOpaque(false);
        scrollHistoric.getViewport().setOpaque(false);
        scrollHistoric.setBorder(BorderFactory.createEmptyBorder());
        scrollHistoric.getVerticalScrollBar().setUI(new DarkScrollBarUI());

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
                String description = textArea1.getText().trim().replaceAll("\\s+", " ");;
                String lobby = (String) lobbyCB.getSelectedItem();
                String block = (String) blockCB.getSelectedItem();
                String apartment = (String) apartmentCB.getSelectedItem();
                String priority = (String) priorityCB.getSelectedItem();
                try {
                    saveToCSV(putID(), description, lobby, block, apartment, priority, LocalDateTime.now(), LocalDateTime.now());
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
        solicitationButton.addMouseListener(new MouseAdapter() {
        });
        solicitationButton.addMouseListener(new MouseAdapter() {
        });
        solicitationButton.addMouseListener(new MouseAdapter() {
        });
    }

    private int putID() {
        int maiorId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
                           String description,
                           String lobby,
                           String block,
                           String apartment,
                           String priority,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) throws IOException {

        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf("%d,%s,%s,%s,%s,%s,%s,%s,%s,0%n",
                    ID,
                    description,
                    lobby,
                    block,
                    apartment,
                    priority,
                    "Pendente",
                    createdAt.getDayOfMonth() +"/"+createdAt.getMonthValue() +"/"+ createdAt.getYear(),
                    updatedAt.getDayOfMonth() +"/"+ updatedAt.getMonthValue() +"/"+ updatedAt.getYear());
        }
    }

    private void updateScreen() {
        idCB.removeAllItems();
        idCB.addItem(Integer.toString(putID()));
        textArea1.setText("");
    }
}