package src.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import src.level.LevelState;

import src.main.State;

public class SelectLevelScreen extends AbstractScreen{

    public static final int LVLBUTX = 80;
    public static final int LVLBUTY = 50;
    public static final int LVLBUTWIDTH = 600;
    public static final int LVLBUTHEIGHT = 100;
    public static final int LVLBUTOFFSET = 10;
    static final int HIGHSCOREOFFSETY = 40;
    static final int APPLESCOREOFFSETY = 75;
    static final int APPLEIMGOFFSETY = 20;
    static final int WINSCORE = 33900;
    private static final int TOTAL_APPLE = 20; 

    public SelectLevelScreen(ScreenManager screenManager){
        super(screenManager);
    }

    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if(mouseOver(mx, my, 80, 50, 600, 100)){
            screenManager.getGame().playSound(5);
            screenManager.getGame().levelState = LevelState.Level1;
            screenManager.setScreen(State.Game);
        }
        if(mouseOver(mx, my, 80, 160, 600, 100)){
            screenManager.getGame().playSound(5);
            screenManager.getGame().levelState = LevelState.Level2;
            screenManager.setScreen(State.Game);
        }
        if (mouseOver(mx, my, 400, 470, 350, 80)) {
            screenManager.getGame().playSound(5);
            screenManager.setScreen(State.Menu);
            return;
        }
    }
    public void mouseReleased(MouseEvent e){
        
    }
    
    public void tick(){

    }

    public void draw(Graphics g){

        drawBackGround(g, menuImg);

        for(int i=0; i<3; i++){
            drawLevelButton(g, String.valueOf(i+1), LVLBUTX, LVLBUTY + (i*(LVLBUTHEIGHT+LVLBUTOFFSET)), LVLBUTWIDTH, LVLBUTHEIGHT);
        }

        drawButton(g, "Back to menu", 400, 470, 350, 80);
    }

    public void drawLevelButton(Graphics g, String text, int x, int y, int width, int height) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(buttonImg, x, y, width, height, null);

        g.setFont(lbsFnt50);
        g.setColor(Color.white);
        g.drawString("Level "+text, x + (width / 15), y + (height / 2) +15);

        LevelState level = LevelState.valueOf("Level" + text); // Determina il livello selezionato
        int highScore = screenManager.getGame().getScoreManager().getHighScore(level);
        int appleScore = screenManager.getGame().getScoreManager().getAppleScore(level);
        g2d.drawImage(lvlUncompletedImg, x + (width *5/ 6), y+APPLEIMGOFFSETY, 50, 50, null);
        if(highScore>WINSCORE) g2d.drawImage(lvlCompletedImg, x + (width *5/ 6), y+APPLEIMGOFFSETY, 50, 50, null);
        if(appleScore==TOTAL_APPLE) g2d.drawImage(lvlPlatinizedImg, x + (width *5/ 6), y+APPLEIMGOFFSETY, 50, 50, null);
        

        g.setFont(lbsFnt25);
        g.drawString("Highscore: " + highScore, x + (width / 2), y+HIGHSCOREOFFSETY);
        g.drawString("Apples: "+ appleScore, x + (width / 2), y+APPLESCOREOFFSETY);
    }
}
