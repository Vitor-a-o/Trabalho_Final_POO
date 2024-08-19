
package dungeonfighter;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton; 
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.tools.Tool;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class DungeonFighter extends JFrame implements ActionListener{
    private Heroi heroi;

    public DungeonFighter(){
        
    }

    public Heroi getHeroi(){
        return heroi;
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    
    public static Heroi escolheHeroi(){
        JFrame frame = new JFrame("Escolha seu heroi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton barbaro = new JButton("Barbaro");
        JButton guerreiro = new JButton("Guerreiro");
        JButton paladino = new JButton("Paladino");

        barbaro.setBounds(50, 100, 150, 30);
        guerreiro.setBounds(50, 150, 150, 30);
        paladino.setBounds(50, 200, 150, 30);

        final Heroi[] heroi = new Heroi[1];

        barbaro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                heroi[0] = new Barbaro();
                frame.dispose();
            }
        });

        guerreiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                heroi[0] = new Guerreiro();
                frame.dispose();
            }
        });

        paladino.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                heroi[0] = new Paladino();
                frame.dispose();
            }
        });

        frame.add(barbaro);
        frame.add(guerreiro);
        frame.add(paladino);

        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);

        while(heroi[0] == null){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        return heroi[0];

    }
    
    public static void distribuiPontos(Heroi heroi){
        final int[] pontosRestantes;
        pontosRestantes = new int[1];
        pontosRestantes[0] = 5;
        final int[] ataqueAdd, defesaAdd, saudeAdd;
        ataqueAdd = new int[1];
        defesaAdd = new int[1];
        saudeAdd = new int[1];
        ataqueAdd[0] = 0;
        defesaAdd[0] = 0;
        saudeAdd[0] = 0;
        final boolean[] confirmacao;
        confirmacao = new boolean[1];
        confirmacao[0] = false;

        JFrame frame = new JFrame("Distribuir Pontos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel pontosLabel = new JLabel("Pontos restantes: 5");
        JLabel ataqueLabel = new JLabel("Ataque atual: " + heroi.getAtaque());
        JLabel saudeLabel = new JLabel("Saude atual: " + heroi.getSaude());
        JLabel defesaLabel = new JLabel("Defesa atual: " + heroi.getDefesa());
        JLabel mensagemConfirmacao = new JLabel("Voce ainda tem pontos para distribuir.");
        
        pontosLabel.setBounds(100, 20, 300, 30);
        ataqueLabel.setBounds(100, 60, 300, 30);
        saudeLabel.setBounds(100, 140, 300, 30);
        defesaLabel.setBounds(100, 100, 300, 30);
        mensagemConfirmacao.setBounds(100, 180, 300, 30);
        mensagemConfirmacao.setVisible(false);
        
        frame.add(pontosLabel);
        frame.add(ataqueLabel);
        frame.add(saudeLabel);
        frame.add(defesaLabel);
        frame.add(mensagemConfirmacao);


        JButton ataqueMais = new JButton("+");
        JButton ataqueMenos = new JButton("-");
        JButton defesaMais = new JButton("+");
        JButton defesaMenos = new JButton("-");
        JButton saudeMais = new JButton("+");
        JButton saudeMenos = new JButton("-");
        JButton confirmar = new JButton("Confirmar");

        ataqueMais.setBounds(230, 60, 50, 30);
        ataqueMenos.setBounds(20, 60, 50, 30);
        defesaMais.setBounds(230, 100, 50, 30); 
        defesaMenos.setBounds(20, 100, 50, 30);
        saudeMais.setBounds(230, 140, 50, 30);
        saudeMenos.setBounds(20, 140, 50, 30);
        confirmar.setBounds(150, 280, 150, 30);

        ataqueMais.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] > 0){
                    heroi.setAtaque(heroi.getAtaque() + 2);
                    pontosLabel.setText("Pontos restantes: " + --pontosRestantes[0]);
                    ataqueLabel.setText("Ataque atual: " + heroi.getAtaque());
                    ataqueAdd[0] += 1;
                    mensagemConfirmacao.setVisible(false);
                }
            }
        });

        ataqueMenos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ataqueAdd[0] > 0){
                    heroi.setAtaque(heroi.getAtaque() - 2);
                    pontosLabel.setText("Pontos restantes: " + ++pontosRestantes[0]);
                    ataqueLabel.setText("Ataque atual: " + heroi.getAtaque());
                    ataqueAdd[0] -= 1;
                    mensagemConfirmacao.setVisible(false);
                }
            }
        });

        defesaMais.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] > 0){
                    heroi.setDefesa(heroi.getDefesa() + 2);
                    pontosLabel.setText("Pontos restantes: " + --pontosRestantes[0]);
                    defesaLabel.setText("Defesa atual: " + heroi.getDefesa());
                    defesaAdd[0] += 1;
                    mensagemConfirmacao.setVisible(false);
                }
            }
        });

        defesaMenos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(defesaAdd[0] > 0){
                    heroi.setDefesa(heroi.getDefesa() - 2);
                    pontosLabel.setText("Pontos restantes: " + ++pontosRestantes[0]);
                    defesaLabel.setText("Defesa atual: " + heroi.getDefesa());
                    defesaAdd[0] -= 1;
                    mensagemConfirmacao.setVisible(false);
                }
            }
        });

        saudeMais.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] > 0){
                    heroi.setSaude(heroi.getSaude() + 2);
                    pontosLabel.setText("Pontos restantes: " + --pontosRestantes[0]);
                    saudeLabel.setText("Saude atual: " + heroi.getSaude());
                    saudeAdd[0] += 1;
                    mensagemConfirmacao.setVisible(false);
                }
            }
        });

        saudeMenos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(saudeAdd[0] > 0){
                    heroi.setSaude(heroi.getSaude() - 2);
                    pontosLabel.setText("Pontos restantes: " + ++pontosRestantes[0]);
                    saudeLabel.setText("Saude atual: " + heroi.getSaude());
                    saudeAdd[0] -= 1;
                    mensagemConfirmacao.setVisible(false);
                }
            }
        });

        confirmar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] == 0){
                    frame.dispose();
                    confirmacao[0] = true;
                }else{
                    mensagemConfirmacao.setVisible(true);
                }
            }
        });

        

        frame.add(ataqueMais);
        frame.add(ataqueMenos);
        frame.add(defesaMais);
        frame.add(defesaMenos);
        frame.add(saudeMais);
        frame.add(saudeMenos);
        frame.add(confirmar);

        frame.setVisible(true);

       while(confirmacao[0] == false){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public static int checaPosicao(Heroi heroi, Tabuleiro tabuleiro, int x, int y){
        int evento = tabuleiro.getEvento(x, y);
        int pontosDeVida;
        switch(evento){
            case 0: // Nenhum evento na posição do tabuleiro
                break;
            case 2: // Armadilha de perda fixa
                Armadilha a1 = new Armadilha(3);
                a1.tiraVida(heroi);
                if(heroi.getVivo() == false){
                    return -1;
                }
                break;
            case 3: // Armadilha de perda aleatoria (1 a 5)
                Armadilha a2 = new Armadilha((int) (Math.random() * 5) + 1);
                a2.tiraVida(heroi);
                if(heroi.getVivo() == false){
                    return -1;
                }
                break;
            case 4: // Monstro comum
                System.out.println("Voce encontrou um monstro!");
                System.out.println("A batalha iniciou.");
                Monstro comum = new MonstroNormal();
                Batalha b1 = new Batalha(heroi,comum);
                b1.iniciar();
                if(!heroi.getVivo()) return -1;
                break;
            case 5: // Chefão
                System.out.println("Voce encontrou o chefao! Prepare-se.");
                System.out.println("A batalha iniciou.");
                Monstro boss = new Chefao();
                Batalha b2 = new Batalha(heroi,boss);
                b2.iniciar();
                if(!heroi.getVivo()) return -1;
                else return 1;
            case 6: // Elixir
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
        
        JFrame frame = new JFrame("Dungeon Fighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int fheight = (int) size.getHeight();
        int fwidth = (int) size.getWidth();

        JPanel statusHeroi = new JPanel();
        statusHeroi.setLayout(null);
        statusHeroi.setBounds((fwidth - 300), 0, 300, 300);

        JLabel nomeHeroi = new JLabel("Nome: " + heroi.getNome());
        JLabel ataqueHeroi = new JLabel("Ataque: " + heroi.getAtaque());
        JLabel defesaHeroi = new JLabel("Defesa: " + heroi.getDefesa());
        JLabel saudeHeroi = new JLabel("Saude: " + heroi.getSaude());
        JLabel elixirHeroi = new JLabel("Elixir: " + heroi.getQuantidadeElixir());

        nomeHeroi.setBounds(10, 10, 300, 30);
        ataqueHeroi.setBounds(10, 50, 300, 30);
        defesaHeroi.setBounds(10, 90, 300, 30);
        saudeHeroi.setBounds(10, 130, 300, 30);
        elixirHeroi.setBounds(10, 170, 300, 30);

        statusHeroi.add(nomeHeroi);
        statusHeroi.add(ataqueHeroi);
        statusHeroi.add(defesaHeroi);
        statusHeroi.add(saudeHeroi);
        statusHeroi.add(elixirHeroi);

        frame.add(statusHeroi);

        int continua = 0, xHeroi = 0, yHeroi = 4, escolha;
        
    }
    
    public static void main(String[] args) {
        Heroi heroi = escolheHeroi();
        System.out.println("Voce escolheu o heroi " + heroi.getNome() + ".");
        distribuiPontos(heroi);
        Tabuleiro tabuleiro = new Tabuleiro(5, 5, 3);
        tabuleiro.preencheTabuleiro();
        inicioJogo(heroi, tabuleiro);
    }
}