package dungeonfighter;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FundoBatalha extends JPanel {
    private Image imagemFundo;

    public FundoBatalha(String caminhoImagem) {
        try {
            imagemFundo = ImageIO.read(getClass().getClassLoader().getResourceAsStream(caminhoImagem));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }
}