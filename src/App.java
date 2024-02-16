import java.awt.Color;

import javax.swing.JFrame;
/**
 * App.java 
 *
 * @version     1.0.0
 * @university  University Forman Christian College
 * @course      CSCS 290 (Java)
 * @project     Snake Game
 * @category    OOP Based Game Using Java Swing
 * @author      Hamza Tarar
 */

public class App {
    public static void main(String[] args) throws Exception {

        //Creating Frame
        JFrame frame = new JFrame("Snake Game", null);
        frame.setBounds(10, 10, 905, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        //Adding Game Panel and setting background 
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        //Making frame Visible (by default it is not visible )
        frame.setVisible(true);
    } 
}
