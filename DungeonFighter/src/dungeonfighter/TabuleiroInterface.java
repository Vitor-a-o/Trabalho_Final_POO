/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.swing.border.EmptyBorder;
import javax.tools.Tool;

public class TabuleiroInterface extends JFrame {
    private Heroi heroiT;
    private JButton[][] botoes;
    private TabuleiroMatriz implementacaoTabuleiro; 
    private int xHeroi, yHeroi;
    private boolean debug;
    private CountDownLatch latch;
    
    // textos informativos dos atributos do herói:
    private JLabel textoAtaque;
    private JLabel textoDefesa;
    private JLabel textoVida;
    private JLabel textoElixir;
    private JLabel textoDicas;

    public TabuleiroInterface(int nArmadilhas, int nMonstros, int nElixir, Heroi heroi, boolean debug, CountDownLatch latch) {
        
        setTitle("Tabuleiro do Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.latch = latch;
        this.heroiT = heroi;
        this.xHeroi = 0;
        this.yHeroi = 4;
        this.debug = debug;
        
        // objeto do tipo tabuleiro para gerar os eventos
        implementacaoTabuleiro = new TabuleiroMatriz(nArmadilhas, nMonstros, nElixir);
        implementacaoTabuleiro.preencheTabuleiro();
        TabuleiroMatriz tabuleiro = implementacaoTabuleiro;

        // botoes é a matriz de botões do tabuleiro
        botoes = new JButton[implementacaoTabuleiro.getTabuleiro().length][implementacaoTabuleiro.getTabuleiro()[0].length];

        //frame de opções
        JFrame frameOpcoes = new JFrame();
        frameOpcoes.setTitle("Opções");
        frameOpcoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOpcoes.setLayout(null);
        frameOpcoes.setSize(400, 400);

        // botão de reiniciar
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.setBounds(150, 50, 100, 50);
        botaoReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                implementacaoTabuleiro = tabuleiro;
                xHeroi = 0;
                yHeroi = 4;
                heroiT = heroi;
                frameOpcoes.setVisible(false);
            }
        });

        // botão de sair
        JButton botaoSair = new JButton("Sair");
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
        JButton botaoOpcoes = new JButton("|||");
        botaoOpcoes.setBounds(screenSize.width - 50, 0, 50, 50);
        botaoOpcoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameOpcoes.setVisible(true);
            }
        });
        add(botaoOpcoes);

        JPanel painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(5, 10));

        int margemHorizontal = 50;
        int margemVertical = 70;
        painelTabuleiro.setBorder(BorderFactory.createEmptyBorder(margemVertical, margemHorizontal, margemVertical, margemHorizontal));

        botoes = new JButton[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setPreferredSize(new Dimension(60, 60));
                botoes[i][j].addActionListener(new BotaoActionListener(i, j));
                painelTabuleiro.add(botoes[i][j]);
            }
        }

        add(painelTabuleiro, BorderLayout.CENTER);
        
        atualizarInterface();
        
        // borda superior:
        JLabel textoSuperior;
        if(heroi instanceof Barbaro){
            textoSuperior = new JLabel("Classe: Bárbaro", SwingConstants.CENTER);
        }if(heroi instanceof Guerreiro){
            textoSuperior = new JLabel("Classe: Guerreiro", SwingConstants.CENTER);
        }else{
            textoSuperior = new JLabel("Classe: Paladino", SwingConstants.CENTER);
        }
        textoSuperior.setFont(new Font("Arial", Font.BOLD, 30));
        add(textoSuperior, BorderLayout.NORTH);

        // painel inferior para informar ataque, defesa, vida, elixir e dicas
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        Font fonte = new Font("Arial", Font.BOLD, 18);
        textoAtaque = new JLabel("Ataque: " + heroi.getAtaque(), SwingConstants.CENTER);
        textoDefesa = new JLabel("Defesa: " + heroi.getDefesa(), SwingConstants.CENTER);
        textoVida = new JLabel("Vida: " + heroi.getVidaAtual(), SwingConstants.CENTER);
        textoElixir = new JLabel("Quantidade de Elixir: " + heroi.getQuantidadeElixir(), SwingConstants.CENTER);
        textoDicas = new JLabel("Dicas restantes: " + heroi.getDicas(), SwingConstants.CENTER);
        
        textoAtaque.setFont(fonte);
        textoDefesa.setFont(fonte);
        textoVida.setFont(fonte);
        textoElixir.setFont(fonte);
        textoDicas.setFont(fonte);

        JButton botaoDica = new JButton("Usar dica");
        botaoDica.setFont(fonte);
        painelInferior.add(textoAtaque);
        painelInferior.add(textoDefesa);
        painelInferior.add(textoVida);
        painelInferior.add(textoElixir);
        painelInferior.add(textoDicas);
        botaoDica.setPreferredSize(new Dimension(120, 50)); // Largura de 200 e altura de 100 pixels
        painelInferior.add(botaoDica);
        

        
        add(painelInferior, BorderLayout.SOUTH);
        
        // quando o botão da dica é clicado:
        botaoDica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(heroi.getDicas()>0){
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
                    heroi.usaDica();
                    atualizarStatus();
                }else{
                    JOptionPane.showMessageDialog(null, "Você não tem mais dicas para usar.");
                }
            }
        });
    }

    private void atualizarInterface() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                switch (implementacaoTabuleiro.getEvento(i, j)) {
                    case 0:
                        botoes[i][j].setText(""); // nada
                        break;
                    case 1:
                        botoes[i][j].setText("H"); // herói
                        break;
                    case 2:
                        if(debug == true){
                            botoes[i][j].setText("AF"); // armadilha fixa 
                        }
                        break;
                    case 3:
                        if(debug == true){
                            botoes[i][j].setText("AA"); // armadilha aleatoria                            
                        }
                        break;
                    case 4:
                        if(debug == true){
                            botoes[i][j].setText("M"); // monstro normal
                        }
                        break;
                    case 5:
                        if(debug == true){
                            botoes[i][j].setText("C"); // chefão                          
                        }
                        break;
                    case 6:
                        if(debug == true){
                            botoes[i][j].setText("E"); // elixir
                        }
                        break;
                }
            }
        }
    }
    
    private void atualizarStatus() {
        textoAtaque.setText("Ataque: " + heroiT.getAtaque());
        textoDefesa.setText("Defesa: " + heroiT.getDefesa());
        textoVida.setText("Vida: " + heroiT.getVidaAtual());
        textoElixir.setText("Quantidade de Elixir: " + heroiT.getQuantidadeElixir());
        textoDicas.setText("Dicas restantes: " + heroiT.getDicas());
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
                 
                    Armadilha af = new Armadilha(3);
                    af.modificaVida(heroiT);
                    if(heroiT.getVivo() == false){
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

                    Armadilha af = new Armadilha((int)((Math.random() * 5) + 1));
                    af.modificaVida(heroiT);
                    if(heroiT.getVivo() == false){
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
                    Batalha batalha = new Batalha(heroiT, monstro);

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
                            if(heroiT.getVivo() == false){
                                JOptionPane.showMessageDialog(null, "Você perdeu!");
                                latch.countDown();
                                dispose();
                            }else{
                                int atributo =  (int)(Math.random() * 3);
                                int valor = (int)(Math.random() * 3) + 2;
                                if(atributo == 0){
                                    heroiT.setAtaque(heroiT.getAtaque()+valor);
                                    JOptionPane.showMessageDialog(null, "Você venceu a batalha! Seu ataque aumentou " + valor + " pontos.");
                                }
                                if(atributo == 1){
                                    heroiT.setDefesa(heroiT.getDefesa()+valor);
                                    JOptionPane.showMessageDialog(null, "Você venceu a batalha! Seu defesa aumentou " + valor + " pontos.");
                                }
                                if(atributo == 2){
                                    heroiT.setVidaTotal(heroiT.getVidaTotal()+valor);
                                    heroiT.setVidaAtual(heroiT.getVidaAtual()+valor);
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
                    Batalha batalha = new Batalha(heroiT, monstro);
                    
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
                            if(heroiT.getVivo() == false){
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
                        
                    if(heroiT.getQuantidadeMaxElixir() == heroiT.getQuantidadeElixir()){
                        JOptionPane.showMessageDialog(null, "Você encontrou um elixir, mas você não tem espaço para carregar outro elixir.");
                    }else{
                        heroiT.incrementaElixir();
                        JOptionPane.showMessageDialog(null, "Você encontrou um elixir! Agora, você tem " + heroiT.getQuantidadeElixir() + " elixir.");
                    }
                    atualizarStatus();
                }
            }
        }
    }

    /*public Tabuleiro getTabuleiro(){
        return implementacaoTabuleiro;
    }
    
    public JButton[][] getBotoes(){
        return botoes;
    }*/
}
