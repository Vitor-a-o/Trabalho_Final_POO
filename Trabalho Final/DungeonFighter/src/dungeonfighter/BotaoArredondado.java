package dungeonfighter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BotaoArredondado extends JButton {

    private int arco = 20; // Define o raio do arco para bordas arredondadas

    public BotaoArredondado(String texto) {
        super(texto);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o botão com bordas arredondadas
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arco, arco));

        // Desenha o texto do botão
        g2d.setColor(getForeground());
        FontMetrics fm = g2d.getFontMetrics();
        String texto = getText();
        int x = (getWidth() - fm.stringWidth(texto)) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString(texto, x, y);

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Não desenha borda, pois será desenhada no paintComponent
    }

}