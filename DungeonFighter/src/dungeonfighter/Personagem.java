/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public abstract class Personagem {
    private int ataque;
    private int defesa;
    private int saude;
    private int vidaTotal;
    private int vidaAtual;
    private String nome;
    private boolean vivo;
    
    public Personagem(int ataque, int defesa, int saude){
        this.ataque = ataque;
        this.defesa = defesa;
        this.saude = saude;
        this.vidaTotal = saude + 10; // os pontos de vida são Y+Saude
        this.vidaAtual = vidaTotal;
        this.vivo = true;
    }
    
    public int getAtaque(){
        return ataque;
    }
    
    public int getDefesa(){
        return defesa;
    }
    
    public int getSaude(){
        return saude;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getVidaTotal(){
        return vidaTotal;
    }
    
    public int getVidaAtual(){
        return vidaAtual;
    }
    
    public boolean getVivo(){
        return vivo;
    }
    
    public void setAtaque(int ataque){
        this.ataque = ataque;
    }
    
    public void setDefesa(int defesa){
        this.defesa = defesa;
    }
    
    public void setSaude(int saude){
        this.saude = saude;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setVidaTotal(int vidaTotal){
        this.vidaTotal = vidaTotal;
    }
    
    public void setVidaAtual(int vidaAtual){
        this.vidaAtual = vidaAtual;
    }
   
    public void setVivo(boolean vivo){
        this.vivo = vivo;
    }
    
}
