/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dungeonfighter;

/**
 *
 * @author Duda
 *
 */

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JButton; 
import javax.swing.JPanel; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class DungeonFighter extends JFrame implements ActionListener{

    public DungeonFighter(){
        super("Dungeon Fighter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Code to handle button click
    }
    
    public static Heroi escolheHeroi(){
        Scanner s = new Scanner(System.in);
        System.out.println("Digite qual a classe do seu heroi: ");
        System.out.println("1. Barbaro");
        System.out.println("2. Guerreiro");
        System.out.println("3. Paladino");
        System.out.println("Sua entrada: ");
        int escolha = s.nextInt();
        while(escolha < 1 || escolha > 3){
            System.out.println("Escolha invalida. Digite novamente: ");
            escolha = s.nextInt();
        }
        
        if(escolha == 1){
            return new Barbaro();
        }
        if(escolha == 2){
            return new Guerreiro();
        }
        if(escolha == 3){
            return new Paladino();
        }
        return null;
    }
    
    public static void distribuiPontos(Heroi heroi){
        Scanner s = new Scanner(System.in);
        int pontos = 5;
        while(pontos > 0){
            int escolha;
            System.out.println("Voce tem " + pontos + " pontos para distribuir entre ataque, defesa e saude.");
            System.out.println("Ataque atual: " + heroi.getAtaque());
            System.out.println("Defesa atual: " + heroi.getDefesa());
            System.out.println("Saude atual: " + heroi.getSaude());
            System.out.println("Digite 1 para colocar um ponto em Ataque.");
            System.out.println("Digite 2 para colocar um ponto em Defesa.");
            System.out.println("Digite 3 para colocar um ponto em Saude.");
            System.out.println("Entrada: ");
            escolha = s.nextInt();
            while(escolha < 1 || escolha > 3){
                System.out.println("Escolha invalida. Digite novamente: ");
                escolha = s.nextInt();
            }
            if(escolha == 1){
                heroi.setAtaque(heroi.getAtaque() + 2);
                pontos--;
            }
            if(escolha == 2){
                heroi.setDefesa(heroi.getDefesa() + 2);
                pontos--;
            }
            if(escolha == 3){
                heroi.setSaude(heroi.getSaude() + 2);
                pontos--;
            }
        }
    }
    
    public static int checaPosicao(Heroi heroi, Tabuleiro tabuleiro, int x, int y){
        int evento = tabuleiro.getEvento(x, y);
        int pontosDeVida;
        switch(evento){
            case 0:
                break;
            case 1:
                System.out.println("Erro: Nao deveria ser possivel");
                break;
            case 2:
                System.out.println("Voce caiu em uma armadilha!");
                pontosDeVida = 3;
                heroi.setVidaAtual(heroi.getVidaAtual()-3);
                if(heroi.getVidaAtual() <= 0){
                    return -1;
                }else{
                    System.out.println("Voce perdeu " + pontosDeVida + " pontos de vida. Agora, voce tem " + heroi.getVidaAtual() + " pontos de vida.");
                }
                break;
            case 3:
                System.out.println("Voce caiu em uma armadilha!");
                pontosDeVida = (int) (Math.random() * 6);
                heroi.setVidaAtual(heroi.getVidaAtual()-3);
                if(heroi.getVidaAtual() <= 0){
                    return -1;
                }else{
                    System.out.println("Voce perdeu " + pontosDeVida + " pontos de vida. Agora, voce tem " + heroi.getVidaAtual() + " pontos de vida.");
                }
                break;
            case 4:
                Monstro comum = new MonstroNormal();
                Batalha b1 = new Batalha(heroi,comum);
                b1.iniciar();
                if(!heroi.getVivo()) return -1;
                break;
            case 5:
                Monstro boss = new Chefao();
                Batalha b2 = new Batalha(heroi,boss);
                b2.iniciar();
                if(!heroi.getVivo()) return -1;
                else return 1;
            case 6:
                heroi.incrementaElixir();
                if(heroi.getQuantidadeElixir() == 3){
                    System.out.println("Voce encontrou um elixir, mas voce nao tem mais capacidade para carregar outro elixir.");
                }else{
                    heroi.incrementaElixir();
                    System.out.println("Voce encontrou um elixir! Agora, voce tem " + heroi.getQuantidadeElixir() + " elixir.");
                }
                break;
        }
        return 0;
    }
    
    public static void inicioJogo(Heroi heroi, Tabuleiro tabuleiro){
        Scanner s = new Scanner(System.in);
        int continua = 0, xHeroi = 0, yHeroi = 4, escolha;
        // valor continua: flag para saber se o jogo deve acabar
        // continua = 1 é vitoria e continua = -1 é derrota.
        while(continua == 0){
            tabuleiro.printTabuleiro();
            System.out.println("Voce esta na posicao [" + xHeroi + ", " + yHeroi + "].");
            System.out.println("Para onde deseja se mover?");
            System.out.println("1. Para frente");
            System.out.println("2. Para tras");
            System.out.println("3. Para a esquerda.");
            System.out.println("4. Para a direita");
            escolha = s.nextInt();
            while(escolha < 1 || escolha > 4 || ((escolha == 1) && (xHeroi == 4)) || ((escolha == 2) && (xHeroi == 0)) || ((escolha == 3) && (yHeroi == 0))|| ((escolha == 4) && (yHeroi == 9))){
                System.out.println("Escolha invalida. Digite novamente: ");
                escolha = s.nextInt();
            }
            if(escolha == 1){ // FRENTE
                continua = checaPosicao(heroi, tabuleiro, xHeroi+1, yHeroi);
                if(continua == 0){
                    xHeroi++;
                    tabuleiro.setEvento(1,xHeroi,yHeroi);
                    tabuleiro.setEvento(0,xHeroi-1,yHeroi);
                }
            }
            if(escolha == 2){ // tras
                continua = checaPosicao(heroi, tabuleiro, xHeroi-1, yHeroi);
                if(continua == 0){
                    xHeroi--;
                    tabuleiro.setEvento(1,xHeroi,yHeroi);
                    tabuleiro.setEvento(0,xHeroi+1,yHeroi);
                }
            }
            if(escolha == 3){ // ESQUERDA
                continua = checaPosicao(heroi, tabuleiro, xHeroi, yHeroi-1);
                if(continua == 0){
                    yHeroi--;
                    tabuleiro.setEvento(1,xHeroi,yHeroi);
                    tabuleiro.setEvento(0,xHeroi,yHeroi+1);
                }
            }
            if(escolha == 4){ // direita
                continua = checaPosicao(heroi, tabuleiro, xHeroi, yHeroi+1);
                if(continua == 0){
                    yHeroi++;
                    tabuleiro.setEvento(1,xHeroi,yHeroi);
                    tabuleiro.setEvento(0,xHeroi,yHeroi-1);
                }
            }
        }
        if(continua == 1) System.out.println("Voce venceu! :)");
        if(continua == -1) System.out.println("Voce perdeu! :(");
    }
    
    public static void main(String[] args) {
        DungeonFighter jogo = new DungeonFighter();
        Heroi heroi = escolheHeroi();
        distribuiPontos(heroi);
        Tabuleiro tabuleiro = new Tabuleiro(5, 5, 3);
        tabuleiro.preencheTabuleiro();
        inicioJogo(heroi, tabuleiro);
    }
}