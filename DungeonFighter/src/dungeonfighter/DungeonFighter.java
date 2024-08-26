
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
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class DungeonFighter extends JFrame implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        
    }

    public static int jogo() {
        final int[] menu = new int[1];
        menu[0] = 0;

        JFrame frame = new JFrame("Dungeon Fighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        // carrega a imagem
        BufferedImage imagemOriginal = null;
        System.out.println(System.getProperty("user.dir"));
        try {
            imagemOriginal = ImageIO.read(new File(System.getProperty("user.dir") + "/DungeonFighter/src/Logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // arruma a iamgem
        JPanel painelLogo = new JPanel();
        painelLogo.setLayout(new BorderLayout());
        JLabel labelImagem = new JLabel();
        painelLogo.add(labelImagem, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image imagemRedimensionada = imagemOriginal.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon imagemIcone = new ImageIcon(imagemRedimensionada);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        labelImagem.setIcon(imagemIcone);
        
        frame.add(painelLogo, BorderLayout.NORTH);

        // configura os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        
        JButton iniciar = new JButton("Iniciar Jogo");
        JButton debugar = new JButton("Debugar");
        JButton sair = new JButton("Sair");
        iniciar.setPreferredSize(new Dimension(200, 100));
        debugar.setPreferredSize(new Dimension(200, 100));
        sair.setPreferredSize(new Dimension(200, 100));
        
        Font fonte = new Font("Arial", Font.BOLD, 20); 
        iniciar.setFont(fonte);
        debugar.setFont(fonte);
        sair.setFont(fonte);
        
        painelBotoes.add(iniciar, gbc);

        gbc.gridx = 1;
        painelBotoes.add(debugar, gbc);

        gbc.gridx = 2;
        painelBotoes.add(sair, gbc);
        
        frame.add(painelBotoes, BorderLayout.CENTER);
        
        // texto dos desenvolvedores
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
    
    public static String nomeHeroi (){
        JFrame frameNome = new JFrame("Nome do Herói");
        JTextField nomeField;
        final String[] nome = {null}; // final para ser usada no actionlistener
        frameNome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameNome.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // texto
        JLabel label = new JLabel("Digite o nome do seu herói:");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        // pega o nome do heroi
        nomeField = new JTextField(20);
        nomeField.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nomeField, gbc);

        // botão proximo
        JButton proximoButton = new JButton("Próximo");
        proximoButton.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(proximoButton, gbc);

        proximoButton.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
               try {
                    nome[0] = nomeField.getText();
                    if (nome[0] == null || nome[0].trim().isEmpty()) {
                        throw new Exception("Erro: Você precisa digitar um nome");
                    }
                    frameNome.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frameNome, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frameNome.add(panel);
        frameNome.setVisible(true); 

        while (frameNome.isDisplayable()) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return nome[0]; 
    }
    
    public static Heroi escolheHeroi(String nome){
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
        JLabel texto = new JLabel("Escolha a classe de " + nome + ":", SwingConstants.CENTER);
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
                heroi[0] = new Barbaro(nome);
                frame.dispose();
            }
        });

        guerreiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                heroi[0] = new Guerreiro(nome);
                frame.dispose();
            }
        });

        paladino.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                heroi[0] = new Paladino(nome);
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
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setLayout(null);

        JLabel pontosLabel = new JLabel("Pontos restantes: 5");
        JLabel explicaLabel = new JLabel("Cada ponto aumenta o atributo escolhido em 2.");
        JLabel ataqueLabel = new JLabel("Ataque atual: " + heroi.getAtaque());
        JLabel saudeLabel = new JLabel("Saude atual: " + heroi.getSaude());
        JLabel defesaLabel = new JLabel("Defesa atual: " + heroi.getDefesa());
        JLabel mensagemConfirmacao = new JLabel("Você ainda tem pontos para distribuir.");
        
        Font fonte = new Font("Arial", Font.BOLD, 30);
        
        pontosLabel.setBounds(340, 50, 300, 100);
        explicaLabel.setBounds(285, 100, 500, 100);
        ataqueLabel.setBounds(380, 185, 300, 100);
        defesaLabel.setBounds(380, 285, 300, 100);
        saudeLabel.setBounds(380, 385, 300, 100);
        mensagemConfirmacao.setBounds(315, 480, 500, 100);
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

        ataqueMais.setBounds(250, 200, 70, 70);
        ataqueMenos.setBounds(650, 200, 70, 70); // x, y, largura, altura
        defesaMais.setBounds(250, 300, 70, 70); 
        defesaMenos.setBounds(650, 300, 70, 70);
        saudeMais.setBounds(250, 400, 70, 70);
        saudeMenos.setBounds(650, 400, 70, 70);
        confirmar.setBounds(870, 290, 200, 100);
        
        ataqueMais.setFont(fonte);
        ataqueMenos.setFont(fonte);
        defesaMais.setFont(fonte);
        defesaMenos.setFont(fonte);
        saudeMais.setFont(fonte);
        saudeMenos.setFont(fonte);
        confirmar.setFont(new Font("Arial", Font.BOLD, 30));

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
        while(true){
            final int[] menu = new int[1];
            final Heroi[] heroi = new Heroi[1];
            CountDownLatch latch = new CountDownLatch(1);
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
                protected Void doInBackground() throws Exception {
                    try{
                        menu[0] = jogo();
                        String nome = nomeHeroi();
                        heroi[0] = escolheHeroi(nome);
                        distribuiPontos(heroi[0]);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    return null;
                }
                protected void done(){
                    try{
                        boolean debug = menu[0] != 1;
                        TabuleiroInterface tabuleiroInterface = new TabuleiroInterface(5, 3, 4, heroi[0], debug, latch); // Exemplo com 5 armadilhas, 3 monstros, 2 elixires
                        tabuleiroInterface.setVisible(true);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            worker.execute();
            try{
                latch.await();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}