/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public abstract class Armadilha {
    int pontosDeVida;
    // a gente que decide quantas armadilhas vão ter no tabuleiro
    // elas não ficam visiveis pro jogador
    
    public void diminuiVida(Personagem p){
        p.setVidaAtual(p.getVidaAtual() - pontosDeVida);
        if(p.getVidaAtual() < 0) p.setVivo(false);
    }
    
    public int getPontosDeVida(){
        return pontosDeVida;
    }
    
    public void setPontosDeVida(int pontosDeVida){
        this.pontosDeVida = pontosDeVida;
    }
}
