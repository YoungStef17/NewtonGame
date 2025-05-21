package src.main;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

import src.level.LevelState;
import src.screens.ScreenManager;
import src.utils.Sound;
    
    
public class Game extends JPanel{
    
    public static int JPANEL_HEIGHT,JPANEL_WIDTH;
    
    private ScreenManager screenManager;

    public State gameState = State.LoadingScreen;
    public LevelState levelState;

    private State previousState;
    public Sound music = new Sound();
    public static Sound soundEffects = new Sound();
    public static Boolean musicOn= true;
    public static Boolean soundEffectsOn = true;
    public ScoreManager scoreManager;


    public Game(){
        setDoubleBuffered(true);
        screenManager = new ScreenManager(this);
        scoreManager = new ScoreManager("src/highscore.txt");

    }
    
    private void tick(){ //metodo che è responsabile dell'aggiornamento della logica del gioco (es. movimento dei personaggi, calcolo delle collisioni, ecc.)

        if (previousState != gameState) {
            screenManager.updateMouseListeners(); // Aggiorna i listener solo se lo stato è cambiato
            previousState = gameState; // Aggiorna lo stato precedente
        }
        screenManager.tick();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        JPANEL_WIDTH= getWidth();
        JPANEL_HEIGHT = getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, JPANEL_WIDTH, JPANEL_HEIGHT);
        
        tick();  
        screenManager.draw(g);   
    }
    
    public void playMusic(int ind) {
        if (musicOn) {
            music.setFile(ind);
            music.play();
            music.loop();
        }
    }
    public void stopMusic() {
        if (musicOn) {
            music.stop();
        }
    }

    public void playSound(int ind) {
        if (soundEffectsOn) {
            soundEffects.setFile(ind);
            soundEffects.play();
        }
    }
    public ScreenManager getScreenManager(){
        return screenManager;
    }

    public ScoreManager getScoreManager(){
        return scoreManager;
    }

    public static void main(String[] args) {
        new Window(800, 600, "Newton",new Game());
    }
}
    