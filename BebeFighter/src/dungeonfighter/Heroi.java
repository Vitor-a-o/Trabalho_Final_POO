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
    private int quantidadeMaxElixir;
    private int dicas; // para evitar as armadilhas
    
    public Heroi(int ataque, int defesa, int saude){
        super(ataque, defesa, saude);
        this.quantidadeElixir = 0;
        this.dicas = 3;
        this.quantidadeMaxElixir = 3;
    }
    
    public int getDicas(){
        return dicas;
    }
    
    public void usaDica(){
        dicas--;
    }
    
    public void movimentar(){
        // implementar
    }
    
    public int getQuantidadeElixir(){
        return quantidadeElixir;
    }
    
    public void usarElixir(){
        quantidadeElixir--;
    }
    
    public void incrementaElixir(){
        quantidadeElixir++;
    }
    
    public int getQuantidadeMaxElixir(){
        return quantidadeMaxElixir;
    }
    
}
