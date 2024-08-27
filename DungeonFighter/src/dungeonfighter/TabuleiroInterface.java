/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import javax.imageio.ImageIO;

public class TabuleiroInterface extends JFrame {
    private JButton[][] botoes;
    private TabuleiroMatriz implementacaoTabuleiro; 
    private Heroi heroithis;
    private Heroi copiaHeroi;
    private int xHeroi, yHeroi;
    private boolean debug;
    private CountDownLatch latch;
    
    // textos informativos dos atributos do herói:
    private JLabel textoAtaque;
    private JLabel textoDefesa;
    private JLabel textoVida;
    private JLabel textoElixir;
    private JLabel textoDicas;
    
    private ImageIcon vazioIcon;
    private ImageIcon heroiIcon;
    private ImageIcon chefaoIcon;

    public TabuleiroInterface(int nArmadilhas, int nMonstros, int nElixir, Heroi heroi, boolean debug, CountDownLatch latch) {
        
        setTitle("Tabuleiro do Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.latch = latch;
        this.heroithis = heroi;
        this.xHeroi = 0;
        this.yHeroi = 4;
        this.debug = debug;
        
        // configurando o painel customizado
        CustomPanel painelPrincipal = new CustomPanel();
        painelPrincipal.setLayout(new BorderLayout());
        setContentPane(painelPrincipal);
        
        // objeto do tipo tabuleiro para gerar os eventos
        implementacaoTabuleiro = new TabuleiroMatriz(nArmadilhas, nMonstros, nElixir);
        implementacaoTabuleiro.preencheTabuleiro();

        //fazer copia do tabuleiro
        TabuleiroMatriz tabuleiro = new TabuleiroMatriz(nArmadilhas, nMonstros, nElixir);
        tabuleiro.setTabuleiro(implementacaoTabuleiro.getTabuleiro());

        //fazer copia do heroi
        if(heroithis instanceof Barbaro){
            copiaHeroi = new Barbaro(heroithis.getNome());
        }if(heroithis instanceof Guerreiro){
            copiaHeroi = new Guerreiro(heroithis.getNome());
        }else{
            copiaHeroi = new Mago(heroithis.getNome());
        }

        copiaHeroi.setAtributos(heroithis.getAtaque(), heroithis.getDefesa(), heroithis.getVidaTotal());

        // botoes é a matriz de botões do tabuleiro
        botoes = new JButton[implementacaoTabuleiro.getTabuleiro().length][implementacaoTabuleiro.getTabuleiro()[0].length];

        //frame de opções
        JFrame frameOpcoes = new JFrame();
        frameOpcoes.setTitle("Opções");
        frameOpcoes.setLayout(null);
        frameOpcoes.setSize(400, 400);

        // botão de reiniciar
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.setBounds(150, 50, 100, 50);
        botaoReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                heroithis.setAtributos(copiaHeroi.getAtaque(), copiaHeroi.getDefesa(), copiaHeroi.getVidaTotal());
                implementacaoTabuleiro.setTabuleiro(tabuleiro.getTabuleiro());
                xHeroi = 0;
                yHeroi = 4;
                atualizarInterface();
                atualizarStatus();
                frameOpcoes.setVisible(true);
            }
        });

        // botão de sair
        JButton botaoSair = new JButton("Novo Jogo");
        botaoSair.setBounds(150, 150, 100, 50);
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                latch.countDown();
                frameOpcoes.setVisible(false);
                dispose();
            }
        });

        frameOpcoes.add(botaoReiniciar);
        frameOpcoes.add(botaoSair);
        frameOpcoes.setVisible(false);

        // botão de opções
        JButton botaoOpcoes = new JButton("Sair");
        botaoOpcoes.setFont(new Font("Arial", Font.BOLD, 18));
        botaoOpcoes.setBounds(screenSize.width - 100, 0, 100, 50);
        botaoOpcoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameOpcoes.setVisible(true);
            }
        });
        add(botaoOpcoes);

        JPanel painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(5, 10));
        painelTabuleiro.setOpaque(false);

        int margemHorizontal = 150;
        int margemVertical = 30;
        painelTabuleiro.setBorder(BorderFactory.createEmptyBorder(margemVertical, margemHorizontal, margemVertical, margemHorizontal));

        botoes = new JButton[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                botoes[i][j] = new JButton();
                 int tamanhoBotao = Math.min(painelTabuleiro.getWidth() / 10, painelTabuleiro.getHeight() / 5);
                botoes[i][j].setPreferredSize(new Dimension (tamanhoBotao, tamanhoBotao));
                botoes[i][j].addActionListener(new BotaoActionListener(i, j));
                painelTabuleiro.add(botoes[i][j]);
            }
        }

        painelPrincipal.add(painelTabuleiro, BorderLayout.CENTER);
        
        chefaoIcon = redimensionarImagem("/src/pixelchefao.png", 150, 150);
        vazioIcon = redimensionarImagem("/src/fundopadrao.png", 150, 150);
        if (heroi instanceof Barbaro) {
            heroiIcon = redimensionarImagem("/src/pixelbarbaro.png", 150, 150);
        } else if (heroi instanceof Guerreiro) {
            heroiIcon = redimensionarImagem("/src/pixelguerreiro.png", 150, 150);
        } else if (heroi instanceof Paladino) {
            heroiIcon = redimensionarImagem("/src/pixelpaladin.png", 150, 150);
        }
        
        atualizarInterface();
        
        // borda superior:
        JLabel textoSuperior;
        if(heroithis instanceof Barbaro){
            textoSuperior = new JLabel("Classe: Bárbaro", SwingConstants.CENTER);
            textoSuperior.setForeground(Color.WHITE);
        }else if(heroithis instanceof Guerreiro){
            textoSuperior = new JLabel("Classe: Guerreiro", SwingConstants.CENTER);
            textoSuperior.setForeground(Color.WHITE);
        }else{
            textoSuperior = new JLabel("Classe: Paladino", SwingConstants.CENTER);
            textoSuperior.setForeground(Color.WHITE);
        }
        textoSuperior.setFont(new Font("Arial", Font.BOLD, 30));
        textoSuperior.setOpaque(false);
        painelPrincipal.add(textoSuperior, BorderLayout.NORTH);

        // painel inferior para informar ataque, defesa, vida, elixir e dicas
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelInferior.setOpaque(false);

        Font fonte = new Font("Arial", Font.BOLD, 18);
        textoAtaque = new JLabel("Ataque: " + heroithis.getAtaque(), SwingConstants.CENTER);
        textoAtaque.setForeground(Color.WHITE);
        textoDefesa = new JLabel("Defesa: " + heroithis.getDefesa(), SwingConstants.CENTER);
        textoDefesa.setForeground(Color.WHITE);
        textoVida = new JLabel("Vida: " + heroithis.getVidaAtual(), SwingConstants.CENTER);
        textoVida.setForeground(Color.WHITE);
        textoElixir = new JLabel("Quantidade de Elixir: " + heroithis.getQuantidadeElixir(), SwingConstants.CENTER);
        textoElixir.setForeground(Color.WHITE);
        textoDicas = new JLabel("Dicas restantes: " + heroithis.getDicas(), SwingConstants.CENTER);
        textoDicas.setForeground(Color.WHITE);
        
        textoAtaque.setFont(fonte);
        textoDefesa.setFont(fonte);
        textoVida.setFont(fonte);
        textoElixir.setFont(fonte);
        textoDicas.setFont(fonte);

        BotaoArredondado botaoDica = new BotaoArredondado("Usar dica");
        botaoDica.setFont(fonte);
        painelInferior.add(textoAtaque);
        painelInferior.add(textoDefesa);
        painelInferior.add(textoVida);
        painelInferior.add(textoElixir);
        painelInferior.add(textoDicas);
        botaoDica.setPreferredSize(new Dimension(120, 50)); 
        painelInferior.add(botaoDica);
        
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);
        
        // quando o botão da dica é clicado:
        botaoDica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(heroithis.getDicas()>0){
                    int linhaOuColuna = (int) (Math.random() * 2);// se linhaOuColuna = 0, vai informar sobre a linha, se for 1, sobre a coluna
                    // se linhaOuColuna = 0, vai informar sobre a linha, se for 1, sobre a coluna
                    if(linhaOuColuna == 0){
                        int linha = (int) (Math.random() * 5);
                        boolean temArmadilha = false;
                        for (int i = 0; i < 9; i++){
                            if(implementacaoTabuleiro.getEvento(linha,i) == 2 || implementacaoTabuleiro.getEvento(linha,i) == 3){
                                temArmadilha = true;
                            }
                        }
                        if(temArmadilha){
                            JOptionPane.showMessageDialog(null, "Na linha " + linha + ", tem pelo menos uma armadilha.");
                        }else{
                            JOptionPane.showMessageDialog(null, "Na linha " + linha + ", nao tem nenhuma armadilha.");
                        }
                    }else{
                        int coluna = (int) (Math.random() * 10);
                        boolean temArmadilha = false;
                        for (int i = 0; i < 5; i++){
                            if(implementacaoTabuleiro.getEvento(i,coluna) == 2 || implementacaoTabuleiro.getEvento(i,coluna) == 3){
                                temArmadilha = true;
                            }
                        }
                        if(temArmadilha){
                            JOptionPane.showMessageDialog(null, "Na coluna " + coluna + ", tem pelo menos uma armadilha.");
                        }else{
                            JOptionPane.showMessageDialog(null, "Na coluna " + coluna + ", nao tem nenhuma armadilha.");
                        }
                    }
                    heroithis.usaDica();
                    atualizarStatus();
                }else{
                    JOptionPane.showMessageDialog(null, "Você não tem mais dicas para usar.");
                }
            }
        });
    }
    
    private BufferedImage carregarImagem(String caminho) {
        try {
            return ImageIO.read(new File(System.getProperty("user.dir") + caminho));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private void atualizarInterface() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                switch (implementacaoTabuleiro.getEvento(i, j)) {
                    case 0:
                        botoes[i][j].setIcon(vazioIcon);
                        botoes[i][j].setText(""); // nada
                        break;
                    case 1:
                        botoes[i][j].setIcon(heroiIcon);
                        botoes[i][j].setText(""); // herói
                        break;
                    case 2:
                        if (debug) {
                            botoes[i][j].setText("AF"); // armadilha fixa
                            botoes[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setVerticalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setForeground(Color.WHITE);
                            botoes[i][j].setOpaque(false);
                            botoes[i][j].setContentAreaFilled(false);
                            botoes[i][j].setIcon(vazioIcon);
                        } else {
                            botoes[i][j].setIcon(vazioIcon);
                            botoes[i][j].setText("");
                        }
                        break;
                    case 3:
                        if (debug) {
                            botoes[i][j].setText("AA"); // armadilha aleatoria
                            botoes[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setVerticalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setForeground(Color.WHITE);
                            botoes[i][j].setOpaque(false);
                            botoes[i][j].setContentAreaFilled(false);
                            botoes[i][j].setIcon(vazioIcon); 
                        } else {
                            botoes[i][j].setIcon(vazioIcon);
                            botoes[i][j].setText("");
                        }
                        break;
                    case 4:
                        if (debug) {
                            botoes[i][j].setText("M"); // monstro normal
                            botoes[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setVerticalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setForeground(Color.WHITE);
                            botoes[i][j].setOpaque(false);
                            botoes[i][j].setContentAreaFilled(false);
                            botoes[i][j].setIcon(vazioIcon);
                        } else {
                            botoes[i][j].setIcon(vazioIcon);
                            botoes[i][j].setText("");
                        }
                        break;
                    case 5:
                            botoes[i][j].setText("");
                            botoes[i][j].setIcon(chefaoIcon); // chefão                    
                        break;
                    case 6:
                        if (debug) {
                            botoes[i][j].setText("E"); // elixir
                            botoes[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setVerticalTextPosition(SwingConstants.CENTER);
                            botoes[i][j].setForeground(Color.WHITE);
                            botoes[i][j].setOpaque(false);
                            botoes[i][j].setContentAreaFilled(false);
                            botoes[i][j].setIcon(vazioIcon);
                        } else {
                            botoes[i][j].setIcon(vazioIcon);
                            botoes[i][j].setText("");
                        }
                        break;
                    default:
                        botoes[i][j].setIcon(vazioIcon);
                        botoes[i][j].setText("");
                        break;
                }
            }
        }
    }
    
    private ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
        try {
            BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + caminho));
            Image imagemRedimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemRedimensionada);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void atualizarStatus() {
        textoAtaque.setText("Ataque: " + heroithis.getAtaque());
        textoDefesa.setText("Defesa: " + heroithis.getDefesa());
        textoVida.setText("Vida: " + heroithis.getVidaAtual());
        textoElixir.setText("Quantidade de Elixir: " + heroithis.getQuantidadeElixir());
        textoDicas.setText("Dicas restantes: " + heroithis.getDicas());
    }

    private class BotaoActionListener implements ActionListener {
        private int linha, coluna;

        public BotaoActionListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((Math.abs(linha - xHeroi) <= 1) && (Math.abs(coluna - yHeroi) == 0)) || ((Math.abs(linha - xHeroi) == 0) && (Math.abs(coluna - yHeroi) <= 1))){
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 0) { // nada
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();

                }
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 2) { // armadilha fixa
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();
                 
                    Armadilha af = new Armadilha(1);
                    af.modificaVida(heroithis);
                    if(heroithis.getVivo() == false){
                        JOptionPane.showMessageDialog(null, "Você perdeu!");
                        latch.countDown();
                        dispose();
                    }
                    atualizarStatus();
                }
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 3) { // armadilha aleatoria
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;
                        
                    atualizarInterface();

                    Armadilha af = new Armadilha((int)((Math.random() * 3) + 1));
                    af.modificaVida(heroithis);
                    if(heroithis.getVivo() == false){
                        JOptionPane.showMessageDialog(null, "Você perdeu!");
                        latch.countDown();
                        dispose();
                    }
                    atualizarStatus();
                }
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 4) { // batalha contra monstro comum
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();

                    Monstro monstro = new MonstroNormal();
                    Batalha batalha = new Batalha(heroithis, monstro);

                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            batalha.iniciar();
                            while(batalha.getBatalhaAtiva() == true){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            return null;
                        }

                        protected void done() {
                            if(heroithis.getVivo() == false){
                                JOptionPane.showMessageDialog(null, "Você perdeu!");
                                latch.countDown();
                                dispose();
                            }else{
                                int atributo =  (int)(Math.random() * 3);
                                int valor = (int)(Math.random() * 3) + 2;
                                if(atributo == 0){
                                    heroithis.setAtaque(heroithis.getAtaque()+valor);
                                    JOptionPane.showMessageDialog(null, "Você venceu a batalha! Seu ataque aumentou " + valor + " pontos.");
                                }
                                if(atributo == 1){
                                    heroithis.setDefesa(heroithis.getDefesa()+valor);
                                    JOptionPane.showMessageDialog(null, "Você venceu a batalha! Seu defesa aumentou " + valor + " pontos.");
                                }
                                if(atributo == 2){
                                    heroithis.setVidaTotal(heroithis.getVidaTotal()+valor);
                                    heroithis.setVidaAtual(heroithis.getVidaAtual()+valor);
                                    JOptionPane.showMessageDialog(null, "Você venceu a batalha! Sua vida aumentou " + valor + " pontos.");
                                }
                            }
                            setVisible(true);
                            atualizarStatus();
                        }
                    };
                    setVisible(false);
                    worker.execute();
                }
                
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 5) { // batalha contra chefão
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();
                    Monstro monstro = new Chefao();
                    Batalha batalha = new Batalha(heroithis, monstro);
                    
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            batalha.iniciar();
                            while(batalha.getBatalhaAtiva() == true){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            return null;
                        }

                        protected void done() {
                            if(heroithis.getVivo() == false){
                                JOptionPane.showMessageDialog(null, "Você perdeu o jogo! :(");
                                latch.countDown();
                                dispose();
                            }else{
                                JOptionPane.showMessageDialog(null, "Você venceu o jogo! :D");
                                latch.countDown();
                                dispose();
                            }
                        }
                    };
                    setVisible(false);
                    worker.execute();
                }
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 6) { // elixir
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();
                        
                    if(heroithis.getQuantidadeMaxElixir() == heroithis.getQuantidadeElixir()){
                        JOptionPane.showMessageDialog(null, "Você encontrou um elixir, mas você não tem espaço para carregar outro elixir.");
                    }else{
                        heroithis.incrementaElixir();
                        JOptionPane.showMessageDialog(null, "Você encontrou um elixir! Agora, você tem " + heroithis.getQuantidadeElixir() + " elixir.");
                    }
                    atualizarStatus();
                }
            }
        }
    }
}
