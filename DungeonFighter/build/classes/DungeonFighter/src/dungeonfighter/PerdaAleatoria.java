/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class PerdaAleatoria extends Armadilha {
    
    // IMPLEMENTAR VALOR ALEATORIO
    
    public PerdaAleatoria(int pontosDeVida){
        super.setPontosDeVida(pontosDeVida);
    }
    
    @Override
    public void diminuiVida(Personagem p){
        p.setVidaAtual(p.getVidaAtual() - super.getPontosDeVida());
        if(p.getVidaAtual() < 0) p.setVivo(false);
    }
}
