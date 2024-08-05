/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class PerdaFixa extends Armadilha{
    public PerdaFixa(int pontosDeVida){
        super.setPontosDeVida(5); // numero definido arbitrariamente
    }
    
    @Override
    public void diminuiVida(Personagem p){
        p.setVidaAtual(p.getVidaAtual() - pontosDeVida);
        if(p.getVidaAtual() < 0) p.setVivo(false);
    }
}
