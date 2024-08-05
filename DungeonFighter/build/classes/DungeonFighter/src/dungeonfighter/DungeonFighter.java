/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class DungeonFighter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Heroi heroi = new Guerreiro(20, 15, 100, "Heroi");
        //Heroi heroi = new Barbaro(20, 15, 100, "Heroi");
        //Heroi heroi = new Paladino(20, 15, 100, "Heroi");
        
        Monstro pequenoPotencia = new MonstroNormal(15, 10, 50, "Monstro Normal");
        Monstro chefao = new Chefao(25, 20, 150, "Chefao");

        System.out.println("Batalha contra um monstro normal:");
        Batalha batalha1 = new Batalha(heroi, pequenoPotencia);
        batalha1.iniciar();
           
        if (heroi.getVivo()) {
            System.out.println("\nBatalha contra o Chefao:");
            Batalha batalha2 = new Batalha(heroi, chefao);
            batalha2.iniciar();
        }
    }
}
