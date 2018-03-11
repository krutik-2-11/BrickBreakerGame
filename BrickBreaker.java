
package brickbreaker;

//import Gameplay.Gameplay;
import java.awt.Color;
import javax.swing.JFrame;


public class BrickBreaker extends JFrame{

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.add(gameplay);
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick Breaker");
        
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
