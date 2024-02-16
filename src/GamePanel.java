
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JComboBox;

/**
 * GamePanel.java
 *
 * @version     1.0.0
 * @university  Forman Christian College
 * @course      CSCS 290 (Java)
 * @project     Snake Game
 * @category    OOP Based Game Using Java Swing
 * @author      Hamza Tarar
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    
    //loading Images

    private ScoringSystem scoreSys = new ScoringSystem();
    private Snake snake = new Snake();
    private Apple apple = new Apple(snake);
    private Sounds sounds = new Sounds();
    private Images images = new Images();

    private String playerName;
    private Timer timer;
    private int delay = 100;
    private boolean gameOver = false;
    private JComboBox<String> difficultyComboBox;


    GamePanel(){
        playerName = showLoginDialog();
       handleDifficultyChange();

        if (playerName == null || playerName.isEmpty()) {
            System.exit(0);}

        //Enabling Key listener
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay, this);
        timer.start();
        sounds.playMusic();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        //Title border
        g.drawRect(24, 10, 851, 55);
        //Setting title Image 
        images.snaketitle.paintIcon(this, g, 25, 11);
        

        //Main Game border
         g.drawRect(24, 74, 851, 576 );
        //Creating Center Black Rectangle
        g.setColor(Color.black);
        g.fillRect(25, 75,850,575);



        //Initizling Game
        if (scoreSys.isStart()){

            //Head Position in frame
            snake.SnakeXlength[0] = 100;
            snake.SnakeYlength[0] = 100;   //Head of snake is made of 100 pixels
            
            //Body Position in Frame
            snake.SnakeXlength[1] = 75;
            snake.SnakeYlength[1] = 100;
            snake.SnakeXlength[2] = 50;
            snake.SnakeYlength[2] = 100;

        }

        //Drawing Head icon
        ImageIcon headIcon = snake.getHeadIcon();
        headIcon.paintIcon(this, g, snake.SnakeXlength[0],snake.SnakeYlength[0]);
        
        //Drawing Body
        for(int i=1;i<snake.lengthOfSnake;i++){
            images.snakeimage.paintIcon(this, g, snake.SnakeXlength[i], snake.SnakeYlength[i]);
        }

        //Drawing Apple
        images.appleimage.paintIcon(this, g, apple.Xcord,apple.Ycord );

        if(gameOver){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD, 50));
            g.drawString("Game Over", 300, 300);

            g.setFont(new Font("Arial",Font.PLAIN, 20));
            g.drawString("Press SPACE to restart", 320, 350);

            g.setFont(new Font("Arial",Font.PLAIN, 15));
            g.drawString("Your Score is " + scoreSys.getScore(), 320, 400);


        }

        //Drawings Stats
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN, 14));
        g.drawString("Score: "+ scoreSys.score, 750, 30);
        g.drawString("Length: "+ snake.lengthOfSnake, 750, 50);


        g.drawString("High Score: ", 50, 45);
        int y = 25;
        int i = 1;
        for (String score : scoreSys.highScore){
            g.drawString(i + ". " + score, 150, y);
            y += 15;
            i++;
        }
        

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN, 14));
        g.drawString("v-1.0 Developed By Hamza Tarar", 330, 675);

        //Delete all the painted things
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        //Changing position of body by assigning position to next one
        for(int i=snake.lengthOfSnake-1;i>0;i--){
            snake.SnakeXlength[i] = snake.SnakeXlength[i-1];
            snake.SnakeYlength[i] = snake.SnakeYlength[i-1];
        }


        //Changing position of head
        if(snake.isLeft()){
            snake.SnakeXlength[0] -= 25;
        }
        if(snake.isRight()){
            snake.SnakeXlength[0] += 25;
        }
        if(snake.isUp()){
            snake.SnakeYlength[0] -= 25;
        }
        if(snake.isDown()){
            snake.SnakeYlength[0] += 25;
        }

        //Conditions to make snake from other side when it reaches the wall
        //Right and left
        if(snake.SnakeXlength[0]>850)snake.SnakeXlength[0]=25;
        if(snake.SnakeXlength[0]<25)snake.SnakeXlength[0]=850;
        //Up and down
        if(snake.SnakeYlength[0]>625)snake.SnakeYlength[0]=75;
        if(snake.SnakeYlength[0]<75)snake.SnakeYlength[0]=625;


        CollisionWithApple();
        CollisionWithBody();
        //Paint updated frame
        repaint(); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT && !snake.isRight()){
            snake.updateSnakeHeadDir("left");
            scoreSys.incrMoves();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT && !snake.isLeft()){
            snake.updateSnakeHeadDir("right");
            scoreSys.incrMoves();
        }
        if (e.getKeyCode()==KeyEvent.VK_UP && !snake.isDown()){
            snake.updateSnakeHeadDir("up");
            scoreSys.incrMoves();
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN && !snake.isUp()){
            snake.updateSnakeHeadDir("down");
            scoreSys.incrMoves();
        }

        if (e.getKeyCode()==KeyEvent.VK_SPACE && gameOver){
            restartGame();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void CollisionWithApple(){
        if(snake.SnakeXlength[0]==apple.Xcord && snake.SnakeYlength[0] == apple.Ycord){
            sounds.playEatSound();
            apple.newApple(snake);
            snake.lengthOfSnake++;
            scoreSys.incrScore();
        }
    }

    public void CollisionWithBody(){
        for(int i=snake.lengthOfSnake-1;i>0;i--){
            if(snake.SnakeXlength[i]== snake.SnakeXlength[0] && snake.SnakeYlength[i] == snake.SnakeYlength[0]){
                timer.stop();
                gameOver =true;
            }
        }
    }


    public void restartGame(){
        scoreSys.updateHighScores(playerName);
        gameOver=false;
        scoreSys.resetScore();
        snake.lengthOfSnake =3;
        snake.updateSnakeHeadDir("right");
        timer.start();
        repaint();
    }

    private String showLoginDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
    
        // Name input components
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField textField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(textField);
    
        // Difficulty ComboBox
        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel difficultyLabel = new JLabel("Select difficulty level:       ");
        String[] difficultyLevels = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<>(difficultyLevels);
        difficultyComboBox.setSelectedIndex(1); // Set the default difficulty to "Medium"
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyComboBox);
    
        panel.add(namePanel);
        panel.add(difficultyPanel);
    
        JLabel infoLabel = new JLabel("Enter your name and select a difficulty level to start the game");
        panel.add(infoLabel);

        // Custom PNG icon
        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, images.customicon);

    
    
        if (result == JOptionPane.OK_OPTION) {
            playerName = textField.getText();
            return playerName;
        } else {
            return null;
        }
    }
    


private void handleDifficultyChange() {
    int selectedDifficulty = difficultyComboBox.getSelectedIndex();
    switch (selectedDifficulty) {
        case 0: // Easy
            delay = 200; 
            break;
        case 1: // Medium
            delay = 100; 
            break;
        case 2: // Hard
            delay = 50; 
    }

}


}
