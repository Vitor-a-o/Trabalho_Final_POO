package dungeonfighter;

import javax.swing.JFrame;

public class InterfaceJogo extends JFrame {
    
// public InterfaceJogo(){
//     super("Dungeon Fighter");
//     setSize(800, 600);
//     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     setVisible(true);
// }
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(5, 5, 3);
        tabuleiro.preencheTabuleiro();
        tabuleiro.printTabuleiro();

        // 
    }
}
