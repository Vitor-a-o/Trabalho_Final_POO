/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dungeonfighter;

import javax.swing.JOptionPane;

public class Armadilha extends Item {
    public Armadilha (int pontosDeVida){
        super(pontosDeVida);
    }
    public void modificaVida (Heroi heroi){
        JOptionPane.showMessageDialog(null, "Você caiu em uma armadilha!");
        heroi.setVidaAtual(heroi.getVidaAtual()-super.getPontosDeVida());
        if(heroi.getVidaAtual() <= 0){
            heroi.setVivo(false);
        }else{
            JOptionPane.showMessageDialog(null, "Você perdeu " + super.getPontosDeVida() + " pontos de vida. Agora, você tem " + heroi.getVidaAtual() + " pontos de vida.");
        }
    }
    // teste duda
}