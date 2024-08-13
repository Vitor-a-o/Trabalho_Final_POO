/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

class Batalha {
    private Heroi heroi;
    private Monstro monstro;
    private Random random;
    private boolean usouEspecial;
    private int turnosEspecial; // controla quantos turnos ainda tem pra usar o especial
    // 2 para o guerreiro, 1 para o barbaro

    public Batalha(Heroi heroi, Monstro monstro) {
        this.heroi = heroi;
        this.monstro = monstro;
        this.random = new Random();
        this.usouEspecial = false;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (heroi.getVivo() && monstro.getVivo()) {
            System.out.println("Digite qual acao deseja tomar: ");
            System.out.println("1) Atacar");
            System.out.println("2) Habilidade especial");
            System.out.println("3) Elixir (Quantidade atual: " + heroi.getQuantidadeElixir() + ")");
            int escolha = scanner.nextInt();
            if(escolha != 1 && escolha != 2 && escolha != 3){
                System.out.println("Escolha invalida.");
            }
            if(escolha == 1){
                turno(heroi, monstro);
                if (monstro.getVivo()) {
                    turno(monstro, heroi);
                }
            }
            if(escolha == 2){
                if(!usouEspecial){
                    if(heroi instanceof Barbaro){
                        turnosEspecial = 1;
                    }
                    if(heroi instanceof Guerreiro){
                        turnosEspecial = 2;
                    }
                    if(heroi instanceof Paladino){
                        if(heroi.getVidaAtual() + 0.5*heroi.getVidaAtual() <= heroi.getVidaTotal()){
                            heroi.setVidaAtual((int)(heroi.getVidaAtual()*1.5));
                        }else{
                            heroi.setVidaAtual(heroi.getVidaTotal());
                        }
                        System.out.println("Habilidade Especial: Recuperacao");
                        System.out.println("Voce recuperou 50% da sua vida.");
                    }
                    usouEspecial = true;
                    turno(heroi, monstro);
                    if (monstro.getVivo()) {
                        turno(monstro, heroi);
                    }
                }else{
                    System.out.println("Voce ja usou a habilidade especial.");
                }
            }
            if(escolha == 3){
                if(heroi.getQuantidadeElixir() > 0){
                    heroi.usarElixir();                
                }else{
                    System.out.println("Voce nao tem elixir.");
                }
            }
        }
        if (heroi.getVivo()) {
            System.out.println("O heroi venceu a batalha!");
            return;
        } else {
            System.out.println("Fim de jogo.");
            return;
        }
    }

    private void turno(Personagem atacante, Personagem defensor) {
        
        int ataqueBonus = random.nextInt(6); // sorteia um número de 0 a 5 pra somar ao ataque
        int defesaBonus = random.nextInt(6); // sorteia um número de 0 a 5 pra somar a defesa

        int ataqueRodada = atacante.getAtaque() + ataqueBonus;
        if((atacante instanceof Barbaro) && (turnosEspecial>0)){
            ataqueRodada *= 1.5;
            turnosEspecial--;
            System.out.println("Habilidade Especial: Golpe Furioso");
            System.out.println("Seu ataque sera 50% mais forte.");
        }
        int defesaRodada = defensor.getDefesa() + defesaBonus;
        if((defensor instanceof Guerreiro) && (turnosEspecial>0)){
            defesaRodada *= 1.5;
            turnosEspecial--;
            System.out.println("Habilidade Especial: Postura Defensiva");
            System.out.println("Sua defesa sera 50% mais eficaz por mais " + turnosEspecial + " turnos.");
        }
        
        int dano = ataqueRodada - defesaRodada;
        if (dano > 0) { // se o dano for maior que 0, o defensor toma dano
            defensor.setVidaAtual(defensor.getVidaAtual() - dano);
            System.out.println(atacante.getNome() + " ataca " + defensor.getNome() + " e causa " + dano + " de dano.");
            if(defensor.getVidaAtual() <= 0){
                defensor.setVidaAtual(0);
                defensor.setVivo(false);
            }else{
                System.out.println(defensor.getNome() + " tem " + defensor.getVidaAtual() + " pontos de vida restantes.");
            } 
        } else { // se o dano for menor que 0, o atacante toma dano
            atacante.setVidaAtual(atacante.getVidaAtual() + dano);
            System.out.println(atacante.getNome() + " ataca " + defensor.getNome() + ", mas " + defensor.getNome() + " repele o ataque!");
            
            System.out.println(atacante.getNome() + " perde " + Math.abs(dano) + " pontos de vida.");
            if(atacante.getVidaAtual() <= 0){
                atacante.setVidaAtual(0);
                atacante.setVivo(false);
            }else{
                System.out.println(atacante.getNome() + " tem " + atacante.getVidaAtual() + " pontos de vida restantes.");
            }
        }
    }
}