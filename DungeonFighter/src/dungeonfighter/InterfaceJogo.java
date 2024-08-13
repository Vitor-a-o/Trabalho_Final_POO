package dungeonfighter;

import javax.swing.JFrame;
import javax.swing.JButton; 
import javax.swing.JPanel; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

public class InterfaceJogo extends JFrame {
    
    public InterfaceJogo(){
        super("Dungeon Fighter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void escolherHeroi(InterfaceJogo i){
        JButton warriorButton = new JButton("Warrior");
        warriorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle warrior selection
            }
        });
        
        JButton mageButton = new JButton("Mage");
        mageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle mage selection
            }
        });
        
        JButton archerButton = new JButton("Archer");
        archerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle archer selection
            }
        });
        
        // Add the buttons to the interface
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(warriorButton);
        buttonPanel.add(mageButton);
        buttonPanel.add(archerButton);
        add(buttonPanel);
    }
    
}
