package src.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import src.level.LevelState;

public class ScoreManager {
    private int highScoreLevelOne;
    private int appleScoreLevelOne;
    
    private int highScoreLevelTwo;
    private int appleScoreLevelTwo;
    
    private int highScoreLevelThree;
    private int appleScoreLevelThree;
    private String filePath;

    public ScoreManager(String filePath){
        this.filePath = filePath;
        loadScores(); // Carica sia highScore che appleScore
        
    }

    public void saveScores(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            
            writer.write(highScoreLevelOne + "," + appleScoreLevelOne);
            writer.newLine();
            writer.write(highScoreLevelTwo + "," + appleScoreLevelTwo);
            writer.newLine();
            writer.write(highScoreLevelThree + "," + appleScoreLevelThree);
        }
        catch (IOException e){
            System.out.println("Errore durante il salvataggio");
        }
    }

    public void loadScores(){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            if ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); //uso uno split per suddividere la riga
                highScoreLevelOne = Integer.parseInt(parts[0]); //converte la stringa in numero
                appleScoreLevelOne = Integer.parseInt(parts[1]);
            }
            
            if ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                highScoreLevelTwo = Integer.parseInt(parts[0]);
                appleScoreLevelTwo = Integer.parseInt(parts[1]);
            }
            
            if ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                highScoreLevelThree = Integer.parseInt(parts[0]);
                appleScoreLevelThree = Integer.parseInt(parts[1]);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore durante il caricamento");
        }
    }

    public void updateHighScore(LevelState level,int score,int apples){
        switch (level) {
            case Level1:
                highScoreLevelOne = Math.max(score, highScoreLevelOne);
                appleScoreLevelOne = Math.max(apples, appleScoreLevelOne);
                break;
            case Level2:
                highScoreLevelTwo = Math.max(score, highScoreLevelTwo);
                appleScoreLevelTwo = Math.max(apples, appleScoreLevelTwo);
                break;
            case Level3:
                highScoreLevelThree = Math.max(score, highScoreLevelThree);
                appleScoreLevelThree = Math.max(apples, appleScoreLevelThree);
                break;
        }
        saveScores();
    }

    public int getHighScore(LevelState level){
        switch (level) {
            case Level1: return highScoreLevelOne;
            case Level2: return highScoreLevelTwo;
            case Level3: return highScoreLevelThree;
            default: return 0;
        }    }

    public int getAppleScore(LevelState level){
        switch (level) {
            case Level1: return appleScoreLevelOne;
            case Level2: return appleScoreLevelTwo;
            case Level3: return appleScoreLevelThree;
            default: return 0;
        }
    }
}
