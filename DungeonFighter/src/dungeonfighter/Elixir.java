/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import javax.swing.JOptionPane;

public class Elixir extends Item{
    public Elixir (int pontosDeVida){
        super(pontosDeVida);
    }
    public void modificaVida (Heroi heroi){
        if(heroi.getVidaAtual() + super.getPontosDeVida() > heroi.getVidaTotal() ){
            heroi.setVidaAtual(heroi.getVidaTotal());
        }else{
            heroi.setVidaAtual(heroi.getVidaAtual() + super.getPontosDeVida());
        }
        JOptionPane.showMessageDialog(null, "Você usou um elixir. Agora, você tem " + heroi.getVidaAtual() + " pontos de vida.");
    }
}