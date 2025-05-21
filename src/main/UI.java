package src.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import src.screens.PlayingScreen;

public class UI {
    
    private PlayingScreen playingScreen;
    public boolean messageOn = false;
    public String message = "";

    private int messageCounter = 0;
    Font Footlight_MT_Light_32,Footlight_MT_Light_83;

    public UI(PlayingScreen playingScreen){
        this.playingScreen = playingScreen;
        Footlight_MT_Light_32 = new Font("Footlight MT Light", Font.PLAIN, 32);
        Footlight_MT_Light_83 = new Font("Footlight MT Light", Font.LAYOUT_LEFT_TO_RIGHT, 83);
    }

    public void draw(Graphics2D g){
        
        if (playingScreen.getGame().gameState == State.Game) {
            playScreen(g);
        }
        if (playingScreen.getGame().gameState == State.Win) {
            gameFinischedScreen(g);
        }
        
    }

    public void playScreen(Graphics2D g){
        
        g.setFont(Footlight_MT_Light_32);
        g.setColor(Color.white);
        g.drawString(playingScreen.getApple()+"/20", 32, 70);
        g.drawString(""+ playingScreen.getScore(), 32, 40);
        if (messageOn) {
            g.setFont(Footlight_MT_Light_83);
            g.setColor(Color.white);
            g.drawString(message, 60, 320);
            messageCounter++;
            if (messageCounter >100) {
                messageCounter=0;
                messageOn = false;
            }
        }
    }

    public void gameFinischedScreen(Graphics2D g2){
        
        g2.setFont(Footlight_MT_Light_32);
        g2.setColor(Color.yellow);
        String msg = "YOU WON!";
        g2.drawString(msg, 200,200);

    }

    public void showMessage(String msg){
       
        messageCounter = 0;
        message = msg;
        messageOn = true;
    }
}