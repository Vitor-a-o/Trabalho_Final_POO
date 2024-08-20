
package dungeonfighter;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton; 
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DungeonFighter extends JFrame implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        
    }

    public static int jogo(){
        final int[] menu = new int[1];
        menu[0] = 0;

        JFrame frame = new JFrame("Dungeon Fighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int fheight = (int) size.getHeight();
        int fwidth = (int) size.getWidth();

        // adicionando o logo do jogo
        BufferedImage imagemOriginal = null;
        try {
            imagemOriginal = ImageIO.read(new File("Logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Image imagemRedimensionada = imagemOriginal.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon imagemIcone = new ImageIcon(imagemRedimensionada);
        JLabel labelImagem = new JLabel(imagemIcone);
        frame.add(labelImagem, BorderLayout.NORTH);

        // adicionando os botões usando GridBagLayout
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton iniciar = new JButton("Iniciar Jogo");
        JButton debugar = new JButton("Debugar");
        JButton sair = new JButton("Sair");
        
        Font fonte = new Font("Arial", Font.BOLD, 20); 
        iniciar.setFont(fonte);
        debugar.setFont(fonte);
        sair.setFont(fonte);
        
        iniciar.setPreferredSize(new Dimension(300, 100));
        debugar.setPreferredSize(new Dimension(300, 100));
        sair.setPreferredSize(new Dimension(300, 100));
        
        // botão iniciar:
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10); // margens para separar os botões
        painelBotoes.add(iniciar, gbc);

        // botão debugar:
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelBotoes.add(debugar, gbc);

        // botão sair:
        gbc.gridx = 2;
        gbc.gridy = 0;
        painelBotoes.add(sair, gbc);
        frame.add(painelBotoes, BorderLayout.CENTER);
        
        JLabel textoDevs = new JLabel("Desenvolvedores: Eduarda Medeiros, Vitor Oliveira e Larissa Schonhofen");
        textoDevs.setVerticalAlignment(SwingConstants.CENTER);
        textoDevs.setHorizontalAlignment(SwingConstants.CENTER); 
        textoDevs.setFont(fonte);
        frame.add(textoDevs, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        
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
        
        // implementando os botões:
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton barbaro = new JButton("Bárbaro");
        JButton guerreiro = new JButton("Guerreiro");
        JButton paladino = new JButton("Paladino");
        
        Font fonte = new Font("Arial", Font.BOLD, 20); 
        barbaro.setFont(fonte);
        guerreiro.setFont(fonte);
        paladino.setFont(fonte);
        
        barbaro.setPreferredSize(new Dimension(300, 100));
        guerreiro.setPreferredSize(new Dimension(300, 100));
        paladino.setPreferredSize(new Dimension(300, 100));

        JPanel painelNorte = new JPanel(new BorderLayout());
        painelNorte.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20)); // margens: cima, esquerda, baixo, direita
        JLabel texto = new JLabel("Escolha sua classe: ", SwingConstants.CENTER);
        texto.setFont(new Font("Arial", Font.BOLD, 40));
        painelNorte.add(texto, BorderLayout.CENTER);
        frame.add(painelNorte, BorderLayout.NORTH);
        
        // botão iniciar:
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10); // margens para separar os botões
        painelBotoes.add(barbaro, gbc);

        // botão debugar:
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelBotoes.add(guerreiro, gbc);

        // botão sair:
        gbc.gridx = 2;
        gbc.gridy = 0;
        painelBotoes.add(paladino, gbc);
        frame.add(painelBotoes, BorderLayout.CENTER);

        // descrições das classes:
        JPanel painelDescricoes = new JPanel(new BorderLayout());
        JLabel labelEsquerda = new JLabel("Habilidade especial do Bárbaro: GOLPE FURIOSO (Desfere um ataque que causa 50% a mais de dano)", SwingConstants.CENTER);
        JLabel labelCentro = new JLabel("Habilidade especial do Paladino: RECUPERAÇÃO (Recupera 50% dos seus pontos de vida totais)", SwingConstants.CENTER);
        JLabel labelDireita = new JLabel("Habilidade especial do Guerreiro: POSTURA DEFENSIVA (Aumenta sua defesa em 50% durante duas rodadas)", SwingConstants.CENTER);
        labelEsquerda.setFont(fonte);
        labelCentro.setFont(fonte);
        labelDireita.setFont(fonte);
        
        // bordas dos textos:
        labelEsquerda.setBorder(new EmptyBorder(20, 20, 20, 20)); // margens: cima, esquerda, inferior, direita
        labelCentro.setBorder(new EmptyBorder(10, 20, 20, 20)); 
        labelDireita.setBorder(new EmptyBorder(10, 20, 50, 20));

        painelDescricoes.add(labelEsquerda, BorderLayout.NORTH);
        painelDescricoes.add(labelCentro, BorderLayout.CENTER);
        painelDescricoes.add(labelDireita, BorderLayout.SOUTH);
        frame.add(painelDescricoes, BorderLayout.SOUTH);
        
        frame.setVisible(true);

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
        JLabel explicaLabel = new JLabel("Cada ponto aumenta o atributo escolhido em 2.");
        JLabel ataqueLabel = new JLabel("Ataque atual: " + heroi.getAtaque());
        JLabel saudeLabel = new JLabel("Saude atual: " + heroi.getSaude());
        JLabel defesaLabel = new JLabel("Defesa atual: " + heroi.getDefesa());
        JLabel mensagemConfirmacao = new JLabel("Você ainda tem pontos para distribuir.");
        
        Font fonte = new Font("Arial", Font.BOLD, 30);
        
        pontosLabel.setBounds(600, 0, 300, 100);
        explicaLabel.setBounds(530, 50, 500, 100);
        ataqueLabel.setBounds(600, 150, 300, 100);
        defesaLabel.setBounds(600, 300, 300, 100);
        saudeLabel.setBounds(600, 450, 300, 100);
        mensagemConfirmacao.setBounds(550, 550, 500, 100);
        mensagemConfirmacao.setVisible(true);
        
        pontosLabel.setFont(fonte);
        explicaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ataqueLabel.setFont(fonte);
        saudeLabel.setFont(fonte);
        defesaLabel.setFont(fonte);
        mensagemConfirmacao.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.add(pontosLabel);
        frame.add(explicaLabel);
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

        ataqueMais.setBounds(300, 150, 100, 100);
        ataqueMenos.setBounds(1000, 150, 100, 100); // x, y, largura, altura
        defesaMais.setBounds(300, 300, 100, 100); 
        defesaMenos.setBounds(1000, 300, 100, 100);
        saudeMais.setBounds(300, 450, 100, 100);
        saudeMenos.setBounds(1000, 450, 100, 100);
        confirmar.setBounds(1150, 590, 200, 100);
        
        ataqueMais.setFont(fonte);
        ataqueMenos.setFont(fonte);
        defesaMais.setFont(fonte);
        defesaMenos.setFont(fonte);
        saudeMais.setFont(fonte);
        saudeMenos.setFont(fonte);
        confirmar.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.add(ataqueMais);
        frame.add(ataqueMenos);
        frame.add(defesaMais);
        frame.add(defesaMenos);
        frame.add(saudeMais);
        frame.add(saudeMenos);
        frame.add(confirmar);

        frame.setVisible(true);

        ataqueMais.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] > 0){
                    heroi.setAtaque(heroi.getAtaque() + 2);
                    pontosLabel.setText("Pontos restantes: " + --pontosRestantes[0]);
                    ataqueLabel.setText("Ataque atual: " + heroi.getAtaque());
                    ataqueAdd[0] += 1;
                    if(pontosRestantes[0] == 0) mensagemConfirmacao.setVisible(false);
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
                    mensagemConfirmacao.setVisible(true);
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
                    if(pontosRestantes[0] == 0) mensagemConfirmacao.setVisible(false);
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
                    mensagemConfirmacao.setVisible(true);
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
                    if(pontosRestantes[0] == 0) mensagemConfirmacao.setVisible(false);
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
                    mensagemConfirmacao.setVisible(true);
                }
            }
        });

        confirmar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pontosRestantes[0] == 0){
                    mensagemConfirmacao.setVisible(false);
                    frame.dispose();
                    onClose.run();
                    confirmacao[0] = true;
                }else{
                    mensagemConfirmacao.setVisible(true);
                }
            }
        });

       while(confirmacao[0] == false){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
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