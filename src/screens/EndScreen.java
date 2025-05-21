package src.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;

import src.main.State;

public class EndScreen extends AbstractScreen {

    public EndScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if (mouseOver(mx, my, 80, 420, 250, 100)) {
            screenManager.getGame().playSound(5);
            screenManager.setScreen(State.Game);
        }
        if (mouseOver(mx, my, 400, 420, 350, 100)) {
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

        Graphics2D g2d = (Graphics2D) g;

        drawBackGround(g2d, gameOverImg);

        g.setFont(lbsFnt100);
        g.setColor(Color.white);
        //g.drawString("Game Over", 200, 120);

        g.setFont(lbsFnt25);
        g.drawString("You lost with a score of: " + screenManager.getPlayingScreen().getScore(), 250, 180);
        g.drawString("and collected " + screenManager.getPlayingScreen().getApple() +" apples", 285, 210);

        g.setFont(lbsFnt50);
        drawButton(g,"Try Again",80, 420, 250, 100);

        drawButton(g,"Back to menu",400, 420, 350, 100);
    }
    
}
