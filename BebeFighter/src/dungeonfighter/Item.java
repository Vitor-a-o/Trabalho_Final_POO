/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

public abstract class Item {
    private int pontosDeVida;
    
    public Item(int pontosDeVida){
        this.pontosDeVida = pontosDeVida;
    }
    
    public abstract void modificaVida(Heroi h);
    
    public int getPontosDeVida(){
        return pontosDeVida;
    }
}
