/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class Paladino extends Heroi {
    public Paladino(int ataque, int defesa, int saude, String nome){
        super(ataque, defesa, saude, nome);
    }
    
    @Override
    public void habilidadeEspecial(){
        // Nome: Recuperacao
        // recupera 50% da vida total
    }
}
