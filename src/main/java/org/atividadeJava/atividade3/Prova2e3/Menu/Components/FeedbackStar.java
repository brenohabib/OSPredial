package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class FeedbackStar extends DefaultTableCellRenderer {
    private final int starSize = 16;
    private static Map<Integer, Float> osIdToAverage = new HashMap<>();
    private JTable table;

    public FeedbackStar(JTable table) {
        this.table = table;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        float rating = value instanceof Number ? ((Number) value).floatValue() : 0;
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawStars((Graphics2D) g, rating, getWidth(), getHeight());
            }
        };
        panel.setOpaque(true);
        panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        return panel;
    }

    private void drawStars(Graphics2D g2d, float rating, int cellWidth, int cellHeight) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int starSpacing = 2;
        int starsWidth = (starSize + starSpacing) * 5 - starSpacing;
        int xOffset = (cellWidth - starsWidth) / 2;
        int yOffset = (cellHeight - starSize) / 2;

        for (int i = 0; i < 5; i++) {
            float fill = Math.min(1, Math.max(0, rating - i));
            int x = xOffset + i * (starSize + starSpacing);
            drawStar(g2d, x, yOffset, fill);
        }
    }

    private void drawStar(Graphics2D g2d, int x, int y, float fill) {
        Shape star = new Polygon(
                new int[]{x + 8, x + 11, x + 16, x + 12, x + 13, x + 8, x + 3, x + 4, x, x + 5},
                new int[]{y + 0, y + 6, y + 6, y + 9, y + 15, y + 11, y + 15, y + 9, y + 6, y + 6},
                10
        );

        g2d.setColor(Color.YELLOW);
        Rectangle2D fillArea = new Rectangle2D.Float(x, y, starSize * fill, starSize);
        g2d.clip(fillArea);
        g2d.fill(star);
        g2d.setClip(null);

        g2d.setColor(Color.GRAY);
        g2d.draw(star);
    }

    public static void readCSVAndCalculateAverages(String filePath) throws IOException, CsvValidationException {
        osIdToAverage.clear();
        Map<Integer, Integer> osIdToCount = new HashMap<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.readNext();

            String[] row;
            while ((row = csvReader.readNext()) != null) {
                int osId = Integer.parseInt(row[1]);
                float stars = Float.parseFloat(row[2]);

                osIdToAverage.put(osId, osIdToAverage.getOrDefault(osId, 0f) + stars);
                osIdToCount.put(osId, osIdToCount.getOrDefault(osId, 0) + 1);
            }

            for (Map.Entry<Integer, Float> entry : osIdToAverage.entrySet()) {
                int osId = entry.getKey();
                float totalStars = entry.getValue();
                int count = osIdToCount.get(osId);
                osIdToAverage.put(osId, totalStars / count);
            }
        }
    }

    public void updateStars(int osId, float newRating) {
        if (table == null) return;

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        osIdToAverage.put(osId, newRating);

        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i, 1);
            if (value != null && Integer.parseInt(value.toString()) == osId) {
                int starColumn = model.getColumnCount() - 1;
                model.setValueAt(newRating, i, starColumn);
                break;
            }
        }

        table.repaint();
    }

    public static float getAverageStars(int osId) {
        return osIdToAverage.getOrDefault(osId, 0f);
    }
}