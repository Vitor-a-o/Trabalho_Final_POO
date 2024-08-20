/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonfighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabuleiroInterface extends JFrame {
    private Heroi heroi;
    private JButton[][] botoes;
    private Tabuleiro implementacaoTabuleiro; 
    private int xHeroi, yHeroi;
    
    // textos informativos dos atributos do herói:
    private JLabel textoAtaque;
    private JLabel textoDefesa;
    private JLabel textoVida;
    private JLabel textoElixir;
    private JLabel textoDicas;

    public TabuleiroInterface(int nArmadilhas, int nMonstros, int nElixir, Heroi heroi) {
        setTitle("Tabuleiro do Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        this.heroi = heroi;
        this.xHeroi = 0;
        this.yHeroi = 4;

        // objeto do tipo tabuleiro para gerar os eventos
        implementacaoTabuleiro = new Tabuleiro(nArmadilhas, nMonstros, nElixir);
        implementacaoTabuleiro.preencheTabuleiro();

        // botoes é a matriz de botões do tabuleiro
        botoes = new JButton[implementacaoTabuleiro.getTabuleiro().length][implementacaoTabuleiro.getTabuleiro()[0].length];

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
        add(textoSuperior, BorderLayout.NORTH);

        // painel inferior para informar ataque, defesa, vida, elixir e dicas
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        textoAtaque = new JLabel("Ataque: " + heroi.getAtaque(), SwingConstants.CENTER);
        textoDefesa = new JLabel("Defesa: " + heroi.getDefesa(), SwingConstants.CENTER);
        textoVida = new JLabel("Vida: " + heroi.getVidaAtual(), SwingConstants.CENTER);
        textoElixir = new JLabel("Quantidade de Elixir: " + heroi.getQuantidadeElixir(), SwingConstants.CENTER);
        textoDicas = new JLabel("Dicas restantes: " + heroi.getDicas(), SwingConstants.CENTER);

        painelInferior.add(textoAtaque);
        painelInferior.add(textoDefesa);
        painelInferior.add(textoVida);
        painelInferior.add(textoElixir);
        painelInferior.add(textoDicas);

        add(painelInferior, BorderLayout.SOUTH);
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
                        botoes[i][j].setText("AF"); // armadilha fixa
                        break;
                    case 3:
                        botoes[i][j].setText("AA"); // armadilha aleatoria
                        break;
                    case 4:
                        botoes[i][j].setText("M"); // monstro normal
                        break;
                    case 5:
                        botoes[i][j].setText("C"); // chefão
                        break;
                    case 6:
                        botoes[i][j].setText("E"); // elixir
                        break;
                }
            }
        }
    }
    
    private void atualizarStatus() {
        textoAtaque.setText("Ataque: " + heroi.getAtaque());
        textoDefesa.setText("Defesa: " + heroi.getDefesa());
        textoVida.setText("Vida: " + heroi.getVidaAtual());
        textoElixir.setText("Quantidade de Elixir: " + heroi.getQuantidadeElixir());
        textoDicas.setText("Dicas restantes: " + heroi.getDicas());
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
                    af.modificaVida(heroi);
                    if(heroi.getVivo() == false){
                        JOptionPane.showMessageDialog(null, "Você perdeu!");
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
                    af.modificaVida(heroi);
                    if(heroi.getVivo() == false){
                        JOptionPane.showMessageDialog(null, "Você perdeu!");
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
                    Monstro comum = new MonstroNormal();
                    Batalha b1 = new Batalha(heroi,comum);
                    b1.iniciar();
                    if(heroi.getVivo() == false){
                        JOptionPane.showMessageDialog(null, "Você perdeu!");
                        dispose();
                    }else{
                        int atributo =  (int)(Math.random() * 3);
                        int valor = (int)(Math.random() * 3) + 2;
                        if(atributo == 0){
                            heroi.setAtaque(heroi.getAtaque()+valor);
                            JOptionPane.showMessageDialog(null, "Você venceu a batalha! Seu ataque aumentou " + valor + " pontos.");
                        }
                        if(atributo == 1){
                            heroi.setDefesa(heroi.getDefesa()+valor);
                            JOptionPane.showMessageDialog(null, "Você venceu a batalha! Seu defesa aumentou " + valor + " pontos.");
                        }
                        if(atributo == 2){
                            heroi.setVidaTotal(heroi.getVidaTotal()+valor);
                            heroi.setVidaAtual(heroi.getVidaAtual()+valor);
                            JOptionPane.showMessageDialog(null, "Você venceu a batalha! Sua vida aumentou " + valor + " pontos.");
                        }
                    }            
                    atualizarStatus();
                }
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 5) { // batalha contra chefão
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();
                    Monstro boss = new Chefao();
                    Batalha b1 = new Batalha(heroi,boss);
                    b1.iniciar();
                    if(heroi.getVivo() == false){
                        JOptionPane.showMessageDialog(null, "Você perdeu o jogo!");
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Você venceu o jogo!");
                        dispose();
                    }
                }
                if (implementacaoTabuleiro.getEvento(linha, coluna) == 6) { // elixir
                    implementacaoTabuleiro.setEvento(1, linha, coluna);
                    implementacaoTabuleiro.setEvento(0, xHeroi, yHeroi);

                    xHeroi = linha;
                    yHeroi = coluna;

                    atualizarInterface();
                    if(heroi.getQuantidadeMaxElixir() == heroi.getQuantidadeElixir()){
                        JOptionPane.showMessageDialog(null, "Você encontrou um elixir, mas você não tem espaço para carregar outro elixir.");
                    }else{
                        heroi.incrementaElixir();
                        JOptionPane.showMessageDialog(null, "Você encontrou um elixir! Agora, você tem " + heroi.getQuantidadeElixir() + " elixir.");
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
