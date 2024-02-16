import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ScoringSystem.java
 *
 * @version     1.0.0
 * @university  Forman Christian College
 * @course      CSCS 290 (Java)
 * @project     Snake Game
 * @category    OOP Based Game Using Java Swing
 * @author      Hamza Tarar
 */

public class ScoringSystem {
    
    protected int moves;
    protected int score;
    protected String[] highScore;
    protected ArrayList<Integer> highScore_list = new ArrayList<>();

    public ScoringSystem() {
        this.moves = 0;
        this.score = 0;
        readHighScores();
    }

    public int getMoves() {
        return moves;
    }

    public void incrMoves() {
        this.moves += 1;
    }
    public int getScore() {
        return score;
    }

    public void incrScore() {
        this.score += 1;
    }

    public void readHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader("SnakeGame/src/assets/highscore/highscore.txt"))) {
            String line;
            List<String> highScoreList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                highScoreList.add(line);
            }
            highScore = highScoreList.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHighScores(String playerName) {
    // Load the current high scores
    readHighScores();
    System.out.print(playerName + " " + score);
    // Compare the current score with the high scores
    if (this.score > Integer.parseInt(highScore[0].split(" ")[1])) {
        // Update the high scores
        highScore[0] = playerName + " " + score;
    }
    else if (this.score > Integer.parseInt(highScore[1].split(" ")[1])) {
        // Update the high scores
        highScore[1] = playerName + " " + score;
    }
    else if (this.score > Integer.parseInt(highScore[2].split(" ")[1])) {
        // Update the high scores
        highScore[2] = playerName + " " + score;
    }
        // Save the updated high scores to the file
        try (FileWriter writer = new FileWriter("SnakeGame/src/assets/highscore/highscore.txt")) {
            for (String line : highScore) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
}




    protected boolean isStart(){
        return this.moves==0;
    }

    protected void resetScore(){
        this.moves = 0;
        this.score = 0;
    }
    
}
