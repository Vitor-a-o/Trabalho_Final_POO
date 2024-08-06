/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */

public abstract class Heroi extends Personagem {
    private int quantidadeElixir;
    private int maxElixir;
    private int dicas; // para evitar as armadilhas
    
    public Heroi(int ataque, int defesa, int saude, String nome){
        super(ataque, defesa, saude, nome);
        this.quantidadeElixir = 0;
        this.maxElixir = 2;
        // defini arbitrariamente que o personagem carrega no máximo 3 elixir
        // era pra ser uma quantidade E, então acho que é pra gente definir mesmo
        this.dicas = 3;
    }
    
    public void usaDica(int n){
        if(dicas>0){
            // revela se tem armadilha na linha/coluna n
            dicas--;
        }else{
            
        }
    }
    
    abstract void habilidadeEspecial();
        // a ser implementada nas subclasses
        // SO PODE SER USADA UMA VEZ POR BATALHA

    
    public void movimentar(){
        // implementar
    }

    // incrementa a quantidade de elixir
    public void incrementaElixir(){
        if(quantidadeElixir < maxElixir){
            quantidadeElixir++;
        }
    }
}
