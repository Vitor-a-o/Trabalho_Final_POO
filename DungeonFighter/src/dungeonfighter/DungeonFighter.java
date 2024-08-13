
package dungeonfighter;

import java.util.Scanner;

public class DungeonFighter {
    
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
                System.out.println("Voce encontrou um monstro!");
                System.out.println("A batalha iniciou.");
                Monstro comum = new MonstroNormal();
                Batalha b1 = new Batalha(heroi,comum);
                b1.iniciar();
                if(!heroi.getVivo()) return -1;
                break;
            case 5:
                System.out.println("Voce encontrou o chefao! Prepare-se.");
                System.out.println("A batalha iniciou.");
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
    public static void revelarArmadilhas(Tabuleiro tabuleiro){
       int linhaOuColuna = (int) (Math.random() * 2);
       // se linhaOuColuna = 0, vai informar sobre a linha, se for 1, sobre a coluna
       if(linhaOuColuna == 0){
           int linha = (int) (Math.random() * 5);
           boolean temArmadilha = false;
           for (int i = 0; i < 9; i++){
               if(tabuleiro.getEvento(linha,i) == 2 || tabuleiro.getEvento(linha,i) == 3){
                   temArmadilha = true;
               }
           }
           if(temArmadilha){
               System.out.println("Na linha " + linha + ", tem pelo menos uma armadilha.");
           }else{
               System.out.println("Na linha " + linha + ", nao tem nenhuma armadilha.");
           }
       }else{
           int coluna = (int) (Math.random() * 10);
           boolean temArmadilha = false;
           for (int i = 0; i < 5; i++){
               if(tabuleiro.getEvento(i,coluna) == 2 || tabuleiro.getEvento(i,coluna) == 3){
                   temArmadilha = true;
               }
           }
           if(temArmadilha){
               System.out.println("Na coluna " + coluna + ", tem pelo menos uma armadilha.");
           }else{
               System.out.println("Na coluna " + coluna + ", nao tem nenhuma armadilha.");
           }
       }
       
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
            System.out.println("5. Usar dica."); // coloquei como uma quinta opção, provavelmente vai ser um botão na interface
            escolha = s.nextInt();
            while(escolha < 1 || escolha > 5 || ((escolha == 1) && (xHeroi == 4)) || ((escolha == 2) && (xHeroi == 0)) || ((escolha == 3) && (yHeroi == 0))|| ((escolha == 4) && (yHeroi == 9))){
                if(escolha < 1 || escolha > 5) System.out.println("Escolha invalida. Digite novamente: ");
                else System.out.println("Movimento invalido. Escolha novamente: ");
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
            if(escolha == 2){ // TRAS
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
            if(escolha == 4){ // DIREITA
                continua = checaPosicao(heroi, tabuleiro, xHeroi, yHeroi+1);
                if(continua == 0){
                    yHeroi++;
                    tabuleiro.setEvento(1,xHeroi,yHeroi);
                    tabuleiro.setEvento(0,xHeroi,yHeroi-1);
                }
            }
            if(escolha == 5){
                if(heroi.getDicas() == 0){
                    System.out.println("Voce nao tem mais dicas para usar.");
                }else{
                    revelarArmadilhas(tabuleiro);
                    heroi.usaDica();
                }
            }
        }
        if(continua == 1) System.out.println("Voce venceu! :)");
        if(continua == -1) System.out.println("Voce perdeu! :(");
    }
    
    public static void main(String[] args) {
        Heroi heroi = escolheHeroi();
        distribuiPontos(heroi);
        Tabuleiro tabuleiro = new Tabuleiro(5, 5, 3);
        tabuleiro.preencheTabuleiro();
        inicioJogo(heroi, tabuleiro);
    }
}