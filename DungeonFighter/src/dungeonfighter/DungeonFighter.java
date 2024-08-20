
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

    public void actionPerformed(ActionEvent e) {
        
    }

    public static int jogo(){
        final int[] menu = new int[1];
        menu[0] = 0;

        JFrame frame = new JFrame("Dungeon Fighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int fheight = (int) size.getHeight();
        int fwidth = (int) size.getWidth();

        JButton iniciar = new JButton("Iniciar Jogo");
        JButton debugar = new JButton("Debugar");
        JButton sair = new JButton("Sair");

        iniciar.setBounds((fwidth/2) - 300, (fheight/2) + 100, 200, 30);
        sair.setBounds((fwidth/2) - 100, (fheight/2) + 100, 200, 30);
        debugar.setBounds((fwidth/2) + 100, (fheight/2) + 100, 200, 30);

        iniciar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu[0] = 1;
                frame.dispose();
            }
        });

        sair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        debugar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu[0] = 2;
                frame.dispose();
            }
        });

        frame.add(iniciar);
        frame.add(sair);
        frame.add(debugar);

        while(menu[0] == 0){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        return menu[0];
    }
    
    public static Heroi escolheHeroi(){
        JFrame frame = new JFrame("Escolha seu heroi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(MAXIMIZED_BOTH);
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
    
    public static void distribuiPontos(Heroi heroi, Runnable onClose){
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
        frame.setExtendedState(MAXIMIZED_BOTH);
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
                    onClose.run();
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
    
    public static void main(String[] args) {
        int menu = jogo();
        if(menu == 1){
            Heroi heroi = escolheHeroi();
            distribuiPontos(heroi, new Runnable(){
            @Override
            public void run() {
                // Ação que será executada após a janela de distribuição de pontos ser fechada
                TabuleiroInterface tabuleiroInterface = new TabuleiroInterface(5, 3, 4, heroi); // Exemplo com 5 armadilhas, 3 monstros, 2 elixires
                tabuleiroInterface.setVisible(true); // Agora, a janela do tabuleiro é aberta
            }
        });
        }else{
            System.out.println("Debugando...");
        }
    }
}