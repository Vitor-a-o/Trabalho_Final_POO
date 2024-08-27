
package dungeonfighter;

import javax.swing.JOptionPane;

public class Armadilha extends Item {
    public Armadilha (int pontosDeVida){
        super(pontosDeVida);
    }
    public void modificaVida (Heroi heroi){
        heroi.setVidaAtual(heroi.getVidaAtual()-super.getPontosDeVida());
        if(heroi.getVidaAtual() <= 0){
            JOptionPane.showMessageDialog(null, "Você caiu em uma armadilha! Você perdeu " + super.getPontosDeVida() + " pontos de vida.");
            heroi.setVivo(false);
        }else{
            JOptionPane.showMessageDialog(null, "Você caiu em uma armadilha! \n Você perdeu " + super.getPontosDeVida() + " pontos de vida. Agora, você tem " + heroi.getVidaAtual() + " pontos de vida.");
        }
    }
}