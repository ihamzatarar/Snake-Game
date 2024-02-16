import javax.swing.ImageIcon;

/**
 * Snake.java
 *
 * @version     1.0.0
 * @university  Forman Christian College
 * @course      CSCS 290 (Java)
 * @project     Snake Game
 * @category    OOP Based Game Using Java Swing
 * @author      Hamza Tarar
 */

public class Snake {   

    protected String snakeHeadDir = "right";


    protected int[] SnakeXlength = new int[750];
    protected int[] SnakeYlength = new int[750];

    protected int lengthOfSnake = 3;
    protected Images images = new Images();


    protected ImageIcon getHeadIcon(){

        if (this.isRight()){
            return images.rightmouth;
        }
        else if (this.isLeft()){
            return images.leftmouth;
        }
        else if (this.isUp()){
            return images.upmouth;
        }
        else if (this.isDown()){
            return images.downmouth;
        }
        else{
            return null;
        }
    }

    protected void updateSnakeHeadDir(String direction){
        this.snakeHeadDir = direction;
    }

    protected boolean isRight(){
        return this.snakeHeadDir == "right";
    }
    protected boolean isLeft(){
        return this.snakeHeadDir == "left";
    }
    protected boolean isUp(){
        return this.snakeHeadDir == "up";
    }
    protected boolean isDown(){
        return this.snakeHeadDir == "down";
    }
    
}
