/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Ajuste as cores para tons mais escuros e ligeiramente mais claros
        Color roxoEscuro = new Color(60, 0, 90);  // Mais escuro nas bordas
        Color roxoClaro = new Color(80, 0, 120);  // Um pouco mais claro no centro
        GradientPaint gradient = new GradientPaint(0, 0, roxoEscuro, width / 2, height / 2, roxoClaro, true);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }
}