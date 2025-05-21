package src.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import src.level.BufferImageLoader;
import src.main.Game;
import src.utils.Sound;

public abstract class AbstractScreen extends MouseAdapter {
    protected ScreenManager screenManager;
    protected BufferedImage level, menuImg, buttonImg, titleButtonImg, signImg, titleImg, gameOverImg, WinImg;
    protected BufferedImage lvlUncompletedImg, lvlCompletedImg, lvlPlatinizedImg;
    protected Sound music = new Sound();
    protected Sound soundEffects = new Sound();
    protected boolean audioOn = true;
    protected Font lbsFnt100, lbsFnt75, lbsFnt60, lbsFnt50, lbsFnt40, lbsFnt25, fnt15;
    protected Font fnt = new Font("aerial", 1, 20);

    public AbstractScreen(ScreenManager screenManager){
        this.screenManager = screenManager;
         
        BufferImageLoader loader = new BufferImageLoader();

        menuImg = loader.loadImage("/src/resource/images/menuBackground.png");
        buttonImg = loader.loadImage("/src/resource/images/button.png");
        titleButtonImg = loader.loadImage("/src/resource/images/titleButton.png");
        signImg = loader.loadImage("/src/resource/images/sign.png");
        
        titleImg = loader.loadImage("/src/resource/images/iconTitle.png");
        gameOverImg = loader.loadImage("/src/resource/images/bgGameOver.png");
        WinImg = loader.loadImage("/src/resource/images/bgWin.png");

        lvlUncompletedImg = loader.loadImage("/src/resource/images/levelUncompleted.png");
        lvlCompletedImg = loader.loadImage("/src/resource/images/levelCompleted.png");
        lvlPlatinizedImg = loader.loadImage("/src/resource/images/levelPlatinized.png");

        // Carico il font Lobster
        try {
            
            Font lobsterFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resource/font/Lobster.ttf"));
            lbsFnt100 = lobsterFont.deriveFont(Font.PLAIN, 100);
            lbsFnt75 = lobsterFont.deriveFont(Font.PLAIN, 75);
            lbsFnt60 = lobsterFont.deriveFont(Font.PLAIN, 60);
            lbsFnt50 = lobsterFont.deriveFont(Font.PLAIN, 50);
            lbsFnt40 = lobsterFont.deriveFont(Font.PLAIN, 40);
            lbsFnt25 = lobsterFont.deriveFont(Font.PLAIN, 25);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(lobsterFont);

        } catch (IOException | FontFormatException e) {
        }
    }

    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);

    protected boolean mouseOver(int mx, int my, int x, int y, int width, int height){ // vedo se con il mouse sto sopra al pulsante
        if (mx > x && mx < x +width) {
            if (my > y && my < y+ height) {
                return true;
            } else
                return false;
        } else
            return false;

    }
    
    
    public boolean isAudioOn(){
        return audioOn;
    }

    public void drawButton(Graphics g, String text, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
    
        // Disegno il pulsante con l'immagine
        g2d.drawImage(buttonImg, x, y, width, height, null);
    
        // Imposto il font e il colore del testo
        g.setFont(lbsFnt40);
        g.setColor(Color.white);
    
        // Ottengo il FontMetrics per calcolare le dimensioni del testo
        FontMetrics fm = g.getFontMetrics();
    
        // Calcolo la larghezza e l'altezza del testo
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent() - fm.getDescent();
    
        // Calcolo la posizione per centrare il testo
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + textHeight) / 2;
    
        // Disegno il testo centrato
        g.drawString(text, textX, textY);
    }

    public void drawTitleButton(Graphics g, String text, int x, int y, int width, int height, Font fnt) {
        Graphics2D g2d = (Graphics2D) g;
    
        // Disegno il pulsante con l'immagine
        g2d.drawImage(titleButtonImg, x, y, width, height, null);
    
        // Imposto il font e il colore del testo
        g.setFont(fnt);
        g.setColor(Color.white);
    
        // Ottengo il FontMetrics per calcolare le dimensioni del testo
        FontMetrics fm = g.getFontMetrics();
    
        // Calcolo la larghezza e l'altezza del testo
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent() - fm.getDescent();
    
        // Calcolo la posizione per centrare il testo
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + textHeight) / 2;
    
        // Disegno il testo centrato
        g.drawString(text, textX, textY);
    }

    public void drawBackGround(Graphics g, BufferedImage bgImage) {
        Graphics2D g2d = (Graphics2D) g;

        int windowWidth = Game.JPANEL_WIDTH;
        int windowHeight = Game.JPANEL_HEIGHT;

        g2d.drawImage(bgImage, 0, 0, windowWidth, windowHeight, null);
    }
}

