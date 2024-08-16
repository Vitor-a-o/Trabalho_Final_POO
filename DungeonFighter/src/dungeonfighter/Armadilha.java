/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dungeonfighter;

public class Armadilha {
    int pontosDeVida;
    Armadilha (int pontosDeVida){
        this.pontosDeVida = pontosDeVida;
    }
    public void tiraVida(Heroi heroi){
        System.out.println("Voce caiu em uma armadilha!");
        heroi.setVidaAtual(heroi.getVidaAtual()-3);
        if(heroi.getVidaAtual() <= 0){
            heroi.setVivo(false);
        }else{
            System.out.println("Voce perdeu " + pontosDeVida + " pontos de vida. Agora, voce tem " + heroi.getVidaAtual() + " pontos de vida.");
        }
    }
}