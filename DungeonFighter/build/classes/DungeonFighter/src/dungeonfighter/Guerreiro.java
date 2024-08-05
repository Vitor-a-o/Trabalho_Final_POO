/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class Guerreiro extends Heroi{
    public Guerreiro(int ataque, int defesa, int saude, String nome){
        super(ataque, defesa, saude, nome);
    }
    
    @Override
    public void habilidadeEspecial(){
        // nome: Postura defensiva
        // aumenta a defesa em 50% por duas rodadas
    }
   
}
