
package dungeonfighter;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton; 
import javax.swing.JPanel; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class DungeonFighter extends JFrame implements ActionListener{
    private Heroi heroi;

    public DungeonFighter(){
        super("Dungeon Fighter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Heroi getHeroi(){
        return heroi;
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    
    public static Heroi escolheHeroi(){
        JFrame frame = new JFrame("Escolha seu heroi");
        JButton barbaro = new JButton("Barbaro");
        JButton guerreiro = new JButton("Guerreiro");
        JButton paladino = new JButton("Paladino");

        barbaro.setBounds(50, 100, 95, 30);
        guerreiro.setBounds(50, 150, 95, 30);
        paladino.setBounds(50, 200, 95, 30);

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

        frame.setSize(200, 300);
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

        JFrame frame = new JFrame("Distribuir Pontos");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel pontosLabel = new JLabel("Pontos restantes: 5");
        pontosLabel.setBounds(100, 20, 300, 30);
        frame.add(pontosLabel);

        JLabel ataqueLabel = new JLabel("Ataque atual: " + heroi.getAtaque());
        ataqueLabel.setBounds(100, 60, 300, 30);
        frame.add(ataqueLabel);

        JLabel defesaLabel = new JLabel("Defesa atual: " + heroi.getDefesa());
        defesaLabel.setBounds(100, 100, 300, 30);
        frame.add(defesaLabel);

        JLabel saudeLabel = new JLabel("Saude atual: " + heroi.getSaude());
        saudeLabel.setBounds(100, 140, 300, 30);
        frame.add(saudeLabel);

        JButton ataqueMais = new JButton("+");
        JButton ataqueMenos = new JButton("-");
        JButton defesaMais = new JButton("+");
        JButton defesaMenos = new JButton("-");
        JButton saudeMais = new JButton("+");
        JButton saudeMenos = new JButton("-");

        ataqueMais.setBounds(230, 60, 50, 30);
        ataqueMenos.setBounds(20, 60, 50, 30);
        defesaMais.setBounds(230, 100, 50, 30); 
        defesaMenos.setBounds(20, 100, 50, 30);
        saudeMais.setBounds(230, 140, 50, 30);
        saudeMenos.setBounds(20, 140, 50, 30);

        ataqueMais.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] > 0){
                    heroi.setAtaque(heroi.getAtaque() + 2);
                    pontosLabel.setText("Pontos restantes: " + --pontosRestantes[0]);
                    ataqueLabel.setText("Ataque atual: " + heroi.getAtaque());
                    ataqueAdd[0] += 1;
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
                }
            }
        });

        

        frame.add(ataqueMais);
        frame.add(ataqueMenos);
        frame.add(defesaMais);
        frame.add(defesaMenos);
        frame.add(saudeMais);
        frame.add(saudeMenos);
        

        frame.setVisible(true);
       
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
        System.out.println("Voce escolheu o heroi " + heroi.getNome() + ".");
        distribuiPontos(heroi);
        DungeonFighter jogo = new DungeonFighter();
        Tabuleiro tabuleiro = new Tabuleiro(5, 5, 3);
        tabuleiro.preencheTabuleiro();
        inicioJogo(heroi, tabuleiro);
    }
}