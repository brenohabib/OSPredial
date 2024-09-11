package org.atividadeJava.atividade3.Prova2e3.Menu.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));  // Carregar a imagem PNG
        } catch (IOException ex) {
            System.out.println("Erro ao carregar a imagem: " + ex.getMessage());
        }
        setOpaque(false);  // Permitir fundo transparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            // Obter as dimensões do painel
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            g.drawImage(image, 0, 0, panelWidth, panelHeight, this);
        }
    }
}
