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
    // vazio vai ser denotado por 0
    // heroi vai ser denotado por 1
    // armadilha perda fixa vai ser denotada por 2 e perda aleatoria por 3
    private int nArmadilhas;
    // monstro normal vai ser denotado por 4 e monstro Chefao por 5
    private int nMonstros;
    // elixir vai ser denotado por 6
    private int nElixir;

    public Tabuleiro(int nArmadilhas, int nMonstros, int nElixir) {
        this.comprimento = 10;
        this.largura = 5;
        this.tabuleiro = new int[comprimento][largura];
        this.nArmadilhas = nArmadilhas;
        this.nMonstros = nMonstros;
        this.nElixir = nElixir;
    }

    public void preencheTabuleiro() {

        // preenche tabuleiro com vazio
        for (int i = 0; i < comprimento; i++) {
            for (int j = 0; j < largura; j++) {
                tabuleiro[i][j] = 0;
            }
        }

        this.tabuleiro[0][2] = 1; // posicao inicial do heroi
        this.tabuleiro[9][2] = 5; // posicao do Chefao

        // preenche tabuleiro com armadilhas
        for (int i = 0; i < nArmadilhas; i++) {
            int x = (int) (Math.random() * comprimento);
            int y = (int) (Math.random() * largura);
            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = (int) (Math.random() * 2 + 2);
            } else {
                i--;
            }
        }

        // preenche tabuleiro com elixir
        for (int i = 0; i < nElixir; i++) {
            int x = (int) (Math.random() * comprimento);
            int y = (int) (Math.random() * largura);
            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = 6;
            } else {
                i--;
            }
        }

        // preenche tabuleiro com monstros normais
        for (int i = 0; i < nMonstros; i++) {
            int x = (int) (Math.random() * comprimento);
            int y = (int) (Math.random() * largura);
            if (tabuleiro[x][y] == 0) {
                tabuleiro[x][y] = 4;
            } else {
                i--;
            }
        }
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void printTabuleiro() {
        for (int i = 0; i < comprimento; i++) {
            for (int j = 0; j < largura; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}
