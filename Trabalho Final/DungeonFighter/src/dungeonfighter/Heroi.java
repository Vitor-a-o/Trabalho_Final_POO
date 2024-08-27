/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

public abstract class Heroi extends Personagem {
    private int quantidadeElixir;
    private int quantidadeMaxElixir;
    private int dicas; // para evitar as armadilhas
    private Elixir[] bolsaDeElixir;
    
    public Heroi(int ataque, int defesa, int saude){
        super(ataque, defesa, saude);
        this.quantidadeElixir = 0;
        this.dicas = 3;
        this.quantidadeMaxElixir = 3;
        bolsaDeElixir = new Elixir[quantidadeMaxElixir];
    }
    
    public int getDicas(){
        return dicas;
    }
    
    public void usaDica(){
        dicas--;
    }

    public int getQuantidadeElixir(){
        return quantidadeElixir;
    }
    
    public void usarElixir(){
        bolsaDeElixir[quantidadeElixir - 1] = null;
        quantidadeElixir--;
    }
    
    public void incrementaElixir(){
        bolsaDeElixir[quantidadeElixir] = new Elixir(10);
        quantidadeElixir++;
    }
    
    public int getQuantidadeMaxElixir(){
        return quantidadeMaxElixir;
    }
    
    public Elixir[] getBolsaDeElixir() {
        return bolsaDeElixir;
    }
}
