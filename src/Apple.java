import java.util.Random;
/**
 * Apple.java
 *
 * @version     1.0.0
 * @university  Forman Christian College
 * @course      CSCS 290 (Java)
 * @project     Snake Game
 * @category    OOP Based Game Using Java Swing
 * @author      Hamza Tarar
 */

public class Apple {
    

    protected int[] x_cord = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    protected int[] y_cord = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    Random random = new Random();
    protected int Xcord ,Ycord;
        

    public Apple(Snake snake) {
        newApple(snake);
    }


    protected void newApple(Snake snake){
        Xcord = x_cord[random.nextInt(34)];
        Ycord = y_cord[random.nextInt(23)];

        //Check to avoid the situation when apple appears on snake
        for(int i=snake.lengthOfSnake-1;i>0;i--){
            if(snake.SnakeXlength[i]== Xcord && snake.SnakeYlength[i] == Ycord){
                newApple(snake);
            }
        }
    }
}
