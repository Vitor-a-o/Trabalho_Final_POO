/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class Barbaro extends Heroi{
    public Barbaro(int ataque, int defesa, int saude, String nome){
        super(ataque, defesa, saude, nome);
    }
    
    @Override
    public void habilidadeEspecial(){
        // nome: Golpe furioso
        // desfere um ataque que causa 50% a mais de dano
    }
    
}
