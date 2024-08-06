/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import java.util.Random;

/**
 *
 * @author Duda
 */
public class PerdaAleatoria extends Armadilha {
    
    // IMPLEMENTAR VALOR ALEATORIO
    
    public PerdaAleatoria(){
        int randomInt = new Random().nextInt(4);
        super.setPontosDeVida(randomInt); // nemero definido aleatoriamente
    }
}
