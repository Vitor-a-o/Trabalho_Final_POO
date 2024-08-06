/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 */
public class Tabuleiro {
    private int[][] tabuleiro;
    private int comprimento;
    private int largura;
    private Armadilha armadilha;
    private Heroi heroi;
    private Monstro monstro;

    public Tabuleiro(Heroi heroi) {
        this.comprimento = 10;
        this.largura = 5;
        this.heroi = heroi;
        this.tabuleiro = new int[comprimento][largura];

    }

    public void preencheTabuleiro() {
        for (int i = 0; i < comprimento; i++) {
            for (int j = 0; j < largura; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }
}
