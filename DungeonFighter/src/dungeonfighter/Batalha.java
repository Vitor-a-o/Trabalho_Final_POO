package dungeonfighter;

import java.awt.Color;
import java.util.Random;
import java.lang.Math;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Batalha extends JFrame {
    private Heroi heroi;
    private Monstro monstro;
    private Random random;
    private boolean usouEspecial;
    private int turnosEspecial;
    // controla quantos turnos ainda tem pra usar o especial
    // 2 para o guerreiro, 1 para o barbaro
    private boolean batalhaAtiva;
    private JLabel vidaMonstro;
    private JLabel ataqueMonstro;
    private JLabel defesaMonstro;
    private JLabel vida;
    private JLabel ataque;
    private JLabel defesa;
    
    public Batalha(Heroi heroi, Monstro monstro) {
        this.heroi = heroi;
        this.monstro = monstro;
        this.random = new Random();
        this.usouEspecial = false;
        this.batalhaAtiva = true;
        
        setTitle("Batalha");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLayout(new GridBagLayout());
        
        FundoBatalha painelFundo = new FundoBatalha("fundo.png"); // Substitua pelo caminho da imagem de fundo
        setContentPane(painelFundo);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // textos informativos do herói:
        JPanel painelTextos = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = -2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        ataque = addTextLabel(painelTextos, "Ataque: " + heroi.getAtaque(), gbc);
        ataque.setForeground(Color.WHITE);
        gbc.gridy++;
        defesa = addTextLabel(painelTextos, "Defesa: " + heroi.getDefesa(), gbc);
        defesa.setForeground(Color.WHITE);
        gbc.gridy++;
        vida = addTextLabel(painelTextos, "Vida: " + heroi.getVidaAtual(), gbc);
        vida.setForeground(Color.WHITE);
        add(painelTextos, gbc);
        painelTextos.setOpaque(false);

       // painel da imagem do herói e do nome:
        BufferedImage imagemHeroi;
            if (heroi instanceof Barbaro) {
                imagemHeroi = carregarImagem("/src/barbaro.png");
            } else if (heroi instanceof Guerreiro) {
                imagemHeroi = carregarImagem("/src/guerreiro.png");
            } else if (heroi instanceof Paladino) {
                imagemHeroi = carregarImagem("/src/paladino.png");
            } else {
                imagemHeroi = carregarImagem("/src/heroi.png");
            }
        JPanel painelHeroi = criarPainelImagem(imagemHeroi, heroi.getNome());
        painelHeroi.setOpaque(false);

        // adicionando o painel à interface:
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        add(painelHeroi, gbc);


        // textos informativos do monstro:
        JPanel painelTextosMonstro = new JPanel(new GridBagLayout());
        gbc.gridx = 2;
        gbc.gridy = -2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        ataqueMonstro = addTextLabel(painelTextosMonstro, "Ataque: " + monstro.getAtaque(), gbc);
        ataqueMonstro.setForeground(Color.WHITE);
        gbc.gridy++;
        defesaMonstro = addTextLabel(painelTextosMonstro, "Defesa: " + monstro.getDefesa(), gbc);
        defesaMonstro.setForeground(Color.WHITE);
        gbc.gridy++;
        vidaMonstro = addTextLabel(painelTextosMonstro, "Vida: " + monstro.getVidaAtual(), gbc);
        vidaMonstro.setForeground(Color.WHITE);
        add(painelTextosMonstro, gbc);
        painelTextosMonstro.setOpaque(false);

        // painel da imagem do monstro e do nome:
        BufferedImage imagemMonstro = carregarImagem(monstro instanceof Chefao ? "/src/chefao.png" : "/src/monstro.png");
        JPanel painelMonstro = criarPainelImagem(imagemMonstro, monstro instanceof Chefao ? "Chefão" : "Esqueleto");
        painelMonstro.setOpaque(false);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        add(painelMonstro, gbc);

        // painel dos botões:
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(painelBotoes, gbc);
        painelBotoes.setOpaque(false);

        // botões de ação:
        BotaoArredondado atacar = adicionarBotao(painelBotoes, "Atacar", gbc, 0);
        BotaoArredondado usarHabilidade = adicionarBotao(painelBotoes, "Habilidade especial", gbc, 1);
        BotaoArredondado usarElixir = adicionarBotao(painelBotoes, "Usar elixir", gbc, 2);

        setVisible(true);
        
        atacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turno(heroi, monstro);
                verificarFimBatalha();
                atualizarStatus();
                if (monstro.getVivo()){
                    JOptionPane.showMessageDialog(null, "É a vez do inimigo de atacar.");
                    turno(monstro, heroi);
                    atualizarStatus();
                }
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
                        JOptionPane.showMessageDialog(null, "Habilidade Especial: Postura Defensiva \nSua defesa aumentará 50% por duas rodadas.");
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
                    if (monstro.getVivo()) {
                        JOptionPane.showMessageDialog(null, "É a vez do inimigo de atacar.");
                        turno(monstro, heroi);
                        atualizarStatus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Você já usou a habilidade especial nessa batalha.");
                }
            }
        });

        usarElixir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(heroi.getQuantidadeElixir() > 0){
                    Elixir elixir = heroi.getBolsaDeElixir()[heroi.getQuantidadeElixir() - 1];
                    elixir.modificaVida(heroi);
                    vida.setText("Vida: " + heroi.getVidaAtual());
                    if(monstro.getVivo()){
                        JOptionPane.showMessageDialog(null, "É a vez do inimigo de atacar.");
                        turno(monstro, heroi);
                        atualizarStatus();
                    } 
                }
                else{
                    JOptionPane.showMessageDialog(null, "Você não tem nenhum elixir para usar.");
                }
            }
        });
    }

    private JLabel addTextLabel(JPanel painel, String texto, GridBagConstraints gbc) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        painel.add(label, gbc);
        return label;
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

    private JPanel criarPainelImagem(BufferedImage imagem, String titulo) {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        JLabel labelImagem = new JLabel(new ImageIcon(imagem.getScaledInstance(300, 400, Image.SCALE_SMOOTH)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(labelImagem, gbc);

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy++;
        painel.add(labelTitulo, gbc);

        return painel;
    }

    private BotaoArredondado adicionarBotao(JPanel painel, String texto, GridBagConstraints gbc, int y) {
        BotaoArredondado botao = new BotaoArredondado(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = y;
        painel.add(botao, gbc);
        return botao;
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