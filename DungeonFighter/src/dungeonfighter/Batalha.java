/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
        
        JFrame frame = new JFrame("Batalha");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600); 
        frame.setLayout(new BorderLayout());
        
        // textos informativos:  
                  
        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        
        JLabel ataque = new JLabel("Ataque: " + heroi.getAtaque());
        JLabel defesa = new JLabel("Defesa: " + heroi.getDefesa());
        JLabel vida = new JLabel("Vida: " + heroi.getVidaAtual());
        Font fonte = new Font("Arial", Font.BOLD, 20); 
        ataque.setFont(fonte);
        defesa.setFont(fonte);
        vida.setFont(fonte);
        ataque.setHorizontalAlignment(JLabel.CENTER);
        defesa.setHorizontalAlignment(JLabel.CENTER);
        vida.setHorizontalAlignment(JLabel.CENTER);
        painelTextos.add(ataque);
        painelTextos.add(defesa);
        painelTextos.add(vida);
        
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelEsquerdo.add(painelTextos); 
       
        JPanel painelTextosMonstro = new JPanel();
        painelTextosMonstro.setLayout(new BoxLayout(painelTextosMonstro, BoxLayout.Y_AXIS));
        
        JLabel ataqueMonstro = new JLabel("Ataque: " + monstro.getAtaque());
        JLabel defesaMonstro = new JLabel("Defesa: " + monstro.getDefesa());
        JLabel vidaMonstro = new JLabel("Vida: " + monstro.getVidaAtual());
        ataqueMonstro.setFont(fonte);
        defesaMonstro.setFont(fonte);
        vidaMonstro.setFont(fonte);
        ataqueMonstro.setHorizontalAlignment(JLabel.CENTER);
        defesaMonstro.setHorizontalAlignment(JLabel.CENTER);
        vidaMonstro.setHorizontalAlignment(JLabel.CENTER);
        painelTextosMonstro.add(ataqueMonstro);
        painelTextosMonstro.add(defesaMonstro);
        painelTextosMonstro.add(vidaMonstro);
        
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelDireito.add(painelTextosMonstro); 
        
        
        BufferedImage imagemHeroi = null;
        try {   
            imagemHeroi = ImageIO.read(new File("heroi.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        JPanel painelHeroi = new JPanel();
        painelHeroi.setLayout(new BorderLayout());
        JLabel labelHeroi = new JLabel();
        painelHeroi.add(labelHeroi, BorderLayout.CENTER);
        Image imagemRedimensionadaHeroi = imagemHeroi.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        ImageIcon imagemHeroiIcone = new ImageIcon(imagemRedimensionadaHeroi);
        labelHeroi.setIcon(imagemHeroiIcone);   
        
        painelEsquerdo.add(painelHeroi);
        
        JLabel labelTituloHeroi = new JLabel("Herói");
        labelTituloHeroi.setFont(new Font("Arial", Font.BOLD, 20));
        labelTituloHeroi.setHorizontalAlignment(JLabel.CENTER);
        painelEsquerdo.add(labelTituloHeroi);

        frame.add(painelEsquerdo, BorderLayout.WEST);
        
        BufferedImage imagemMonstro = null;
        try {
            imagemMonstro = ImageIO.read(new File("monstro.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        JPanel painelMonstro = new JPanel();
        painelMonstro.setLayout(new BorderLayout());
        JLabel labelMonstro = new JLabel();
        painelMonstro.add(labelMonstro, BorderLayout.CENTER);
        Image imagemRedimensionadaMonstro = imagemMonstro.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        ImageIcon imagemMonstroIcone = new ImageIcon(imagemRedimensionadaMonstro);
        labelMonstro.setIcon(imagemMonstroIcone);
        
        painelDireito.add(painelMonstro); 
        
        JLabel labelTituloMonstro = new JLabel("Monstro");
        labelTituloMonstro.setFont(new Font("Arial", Font.BOLD, 20));
        labelTituloMonstro.setHorizontalAlignment(JLabel.CENTER);
        painelDireito.add(labelTituloMonstro);

        frame.add(painelDireito, BorderLayout.EAST);
        
        
         // painel dos botões:
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona margem para o painel

        
        JButton atacar = new JButton("Atacar");
        atacar.setPreferredSize(new Dimension(200, 100));
        atacar.setFont(fonte);
        painelBotoes.add(atacar);
        
        JButton usarHabilidade = new JButton("Habilidade especial");
        usarHabilidade.setPreferredSize(new Dimension(200, 100));
        usarHabilidade.setFont(fonte);
        painelBotoes.add(usarHabilidade);
        
        JButton usarElixir = new JButton("Usar elixir");
        usarElixir.setPreferredSize(new Dimension(200, 100));
        usarElixir.setFont(fonte);
        painelBotoes.add(usarElixir);
        
        JPanel painelContainer = new JPanel(new BorderLayout());
        painelContainer.add(painelBotoes, BorderLayout.CENTER);
        frame.add(painelContainer, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (heroi.getVivo() && monstro.getVivo()) {
            System.out.println("Di  gite qual acao deseja tomar: ");
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