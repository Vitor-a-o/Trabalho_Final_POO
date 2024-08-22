/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import java.awt.BorderLayout;
import java.util.concurrent.CountDownLatch;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Batalha extends JFrame {
    private Heroi heroi;
    private Monstro monstro;
    private Random random;
    private boolean usouEspecial;
    private int turnosEspecial;
    private boolean batalhaAtiva;
    // controla quantos turnos ainda tem pra usar o especial
    // 2 para o guerreiro, 1 para o barbaro
    
    private JLabel vidaMonstro;
    private JLabel vida;
    
    public Batalha(Heroi heroi, Monstro monstro) {
        this.heroi = heroi;
        this.monstro = monstro;
        this.random = new Random();
        this.usouEspecial = false;
        this.batalhaAtiva = true;
        
        setTitle("Batalha");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLayout(new BorderLayout());
        
        // textos informativos:  
                  
        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        
        JLabel ataque = new JLabel("Ataque: " + heroi.getAtaque());
        JLabel defesa = new JLabel("Defesa: " + heroi.getDefesa());
        vida = new JLabel("Vida: " + heroi.getVidaAtual());
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
        vidaMonstro = new JLabel("Vida: " + monstro.getVidaAtual());
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
            imagemHeroi = ImageIO.read(new File("src/heroi.jpg"));
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

        add(painelEsquerdo, BorderLayout.WEST);
        
        BufferedImage imagemMonstro = null;
        try {
            imagemMonstro = ImageIO.read(new File("src/monstro.png"));
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

        add(painelDireito, BorderLayout.EAST);
        
        
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
        add(painelContainer, BorderLayout.CENTER);

        atacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turno(heroi, monstro);
                verificarFimBatalha();
                atualizarStatus();
                if (monstro.getVivo()){
                    JOptionPane.showMessageDialog(null, "É a vez do inimigo de atacar.");
                }
                turno(monstro, heroi);
                atualizarStatus();
            }
        });

        usarHabilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!usouEspecial){
                    if(heroi instanceof Barbaro){
                        turnosEspecial = 1;
                        JOptionPane.showMessageDialog(null, "Habilidade Especial: Golpe Furioso \nSeu próximo ataque causará 50% mais dano.");
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
                        JOptionPane.showMessageDialog(null, "Habilidade Especial: Recuperação \n Você recuperou 50% da sua vida.");
                        vida.setText("Vida: " + heroi.getVidaAtual());
                    }
                    usouEspecial = true;
                    turno(heroi, monstro);
                    if (monstro.getVivo()) {
                        JOptionPane.showMessageDialog(null, "É a vez do inimigo de atacar.");
                        turno(monstro, heroi);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Você já usou a habilidade especial nessa batalha.");
                }
            }
        });

        usarElixir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Elixir elixir = new Elixir(10);
                if(heroi.getQuantidadeElixir() > 0){
                    elixir.modificaVida(heroi);
                    vida.setText("Vida: " + heroi.getVidaAtual());
                    JOptionPane.showMessageDialog(null, "É a vez do inimigo de atacar.");
                    turno(monstro, heroi);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Você não tem nenhum elixir para usar.");
                }
            }
        });
    }

    public void iniciar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
        if(monstro instanceof Chefao){
            JOptionPane.showMessageDialog(null, "A batalha contra o chefão iniciou!");
        }else{
            JOptionPane.showMessageDialog(null, "Você encontrou um monstro!");
        }
    }

    public boolean getBatalhaAtiva() {
        return batalhaAtiva;
    }
    
    public void atualizarStatus(){
        vida.setText("Vida: " + heroi.getVidaAtual());
        vidaMonstro.setText("Vida: " + monstro.getVidaAtual());
    }

    private void verificarFimBatalha(){
        if(!heroi.getVivo() || !monstro.getVivo()){
            batalhaAtiva = false;
            dispose();
        }
    }

        private void turno(Personagem atacante, Personagem defensor) {

            int ataqueBonus = random.nextInt(6); // sorteia um número de 0 a 5 pra somar ao ataque
            int defesaBonus = random.nextInt(6); // sorteia um número de 0 a 5 pra somar a defesa

            int ataqueRodada = atacante.getAtaque() + ataqueBonus;
            if((atacante instanceof Barbaro) && (turnosEspecial>0)){
                ataqueRodada *= 1.5;
                turnosEspecial--;
                JOptionPane.showMessageDialog(null, "Seu ataque será 50% mais forte esta rodada.");
            }
            int defesaRodada = defensor.getDefesa() + defesaBonus;
            if((defensor instanceof Guerreiro) && (turnosEspecial>0)){
                defesaRodada *= 1.5;
                if(turnosEspecial == 2){
                    JOptionPane.showMessageDialog(null, "Sua defesa sera 50% mais eficaz por 2 turnos.");
                }
                if(turnosEspecial == 1){
                    JOptionPane.showMessageDialog(null, "Sua defesa sera 50% mais eficaz por 1 turno.");
                }
                turnosEspecial--;
            }
        int dano = ataqueRodada - defesaRodada;
        if (dano > 0) { // se o dano for maior que 0, o defensor toma dano
            defensor.setVidaAtual(defensor.getVidaAtual() - dano);
            JOptionPane.showMessageDialog(null, atacante.getNome() + " ataca " + defensor.getNome() + " e causa " + dano + " de dano.");
            if(defensor.getVidaAtual() <= 0){
                defensor.setVidaAtual(0);
                defensor.setVivo(false);
            }
        } else { // se o dano for menor que 0, o atacante toma dano
            atacante.setVidaAtual(atacante.getVidaAtual() + dano); // soma porque o dano é negativo
            JOptionPane.showMessageDialog(null, atacante.getNome() + " ataca " + defensor.getNome() + ", mas " + defensor.getNome() + " repele o ataque!");           
            JOptionPane.showMessageDialog(null, atacante.getNome() + " perde " + Math.abs(dano) + " pontos de vida.");
            if(atacante.getVidaAtual() <= 0){
                atacante.setVidaAtual(0);
                atacante.setVivo(false);
            }
        }
        atualizarStatus();
        verificarFimBatalha();
    }
}