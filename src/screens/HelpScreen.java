package src.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import src.main.Game;
import src.main.State;

public class HelpScreen extends AbstractScreen{

    public HelpScreen(ScreenManager screenManager){
        super(screenManager);
    }

    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if (mouseOver(mx, my, 400, 470, 350, 80)) {
            screenManager.getGame().playSound(5);
            screenManager.setScreen(State.Menu);
                return;
        }
        if (mouseOver(mx, my, 200,320, 360, 80)) {
            screenManager.getGame().playSound(5);
            setAudio();
        }
    }
    
    public void setAudio(){
        audioOn = !isAudioOn();
        if (!audioOn) {
            Game.musicOn = false;
            Game.soundEffectsOn = false;
        }else{
            Game.musicOn = true;
            Game.soundEffectsOn = true;  
        }  
        screenManager.getGame().repaint();
    }
    
    public void tick(){

    }
    public void mouseReleased(MouseEvent e){
        
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        drawBackGround(g, menuImg);

        g2d.drawImage(signImg, 200, 60, 370, 220, null);

        drawTitleButton(g, "HELP", 180, 40, 400, 100, lbsFnt60);

        g.setFont(lbsFnt25);
        g.drawString("Use space to change your gravity", 215, 255);

        drawButton(g,"Back to menu", 400, 470,350,80);

        if (audioOn) {
            drawButton(g,"Audio: ON", 200, 320,360,80);
        }else 
            drawButton(g,"Audio: OFF", 200, 320,360,80);
    }
            
        
}

