package src.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import src.level.BufferImageLoader;
import src.main.Game;
import src.main.State;

public class LoadingScreen extends AbstractScreen {
    
    private BufferedImage loadingImage;
    private long loadingStartTime = 0;
    private boolean isTimerStarted= false;
    private static final int LOADING_TIME = 5000;

    
    public LoadingScreen(ScreenManager screenManager) {
        super(screenManager);
        // Carica l'immagine di caricamento
        BufferImageLoader loader = new BufferImageLoader();
        loadingImage = loader.loadImage("/src/resource/images/iconLogo.png"); // Percorso dell'immagine
    }

    public void mousePressed(MouseEvent e){

    }
    public void tick(){

    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Ottieni le dimensioni della finestra
        int windowWidth = Game.JPANEL_WIDTH;
        int windowHeight = Game.JPANEL_HEIGHT;
        
        // Disegna l'immagine ridimensionata per adattarsi alla finestra
        
        g2d.drawImage(loadingImage, 0, 0, windowWidth, windowHeight, null);
        g.setColor(Color.lightGray);
        g.setFont(fnt);
        g2d.drawString("THEORIZING GRAVITATION...", 260,495 );
        timeLoading();
    }
    
    public void timeLoading(){
        
        if (!isTimerStarted) {
            // Inizia il timer solo una volta
            loadingStartTime = System.currentTimeMillis();
            isTimerStarted = true;
        }
    
        // Calcola il tempo trascorso
        long elapsedTime = System.currentTimeMillis() - loadingStartTime;
    
        if (elapsedTime > LOADING_TIME) {  // Aspetta 5 secondi (5000 ms)
            // Una volta trascorsi i 5 secondi, cambia lo stato del gioco
            screenManager.setScreen(State.Menu);
            System.out.println("Tempo trascorso: vai al menu.");
        }
        screenManager.getGame().repaint();
    }
    public void mouseReleased(MouseEvent e){
    }
}
