package src.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import src.main.State;

public class WinScreen extends AbstractScreen {
    int score = 0;
    public WinScreen(ScreenManager screenManager){
        super(screenManager);
    }
    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if (mouseOver(mx, my, 400, 420, 350, 100)) {
            screenManager.getGame().playSound(5);
            screenManager.setScreen(State.Menu);
            return;
        }
    }
   
    public void tick(){

    }
    public void mouseReleased(MouseEvent e){
        
    }

    public void draw(Graphics g){

        drawBackGround(g, WinImg);

        g.setFont(lbsFnt25);
        g.setColor(Color.white);
        g.drawString("You won with a score of: " + screenManager.getPlayingScreen().getScore(), 250, 180);
        g.drawString("and collected " + screenManager.getPlayingScreen().getApple() +" apples", 285, 210);

        drawButton(g,"Back to menu",230, 450, 350, 100);
    }
}
