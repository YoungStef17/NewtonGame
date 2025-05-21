package src.screens;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;

import src.main.State;

public class MenuScreen extends AbstractScreen {

    public MenuScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        // Play button
        if (mouseOver(mx, my, 280, 180, 200, 100)) {
            screenManager.getGame().playSound(5);
            screenManager.setScreen(State.SelectLevel);
        }

        // Command button
        if (mouseOver(mx, my, 280, 300, 200, 100)) {
            screenManager.getGame().playSound(5);
            screenManager.setScreen(State.Help);
        }

        // Quit button
        if (mouseOver(mx, my, 280, 420, 200, 100)) {
            screenManager.getGame().playSound(5);
            System.exit(1);
        }
    }

    public void mouseReleased(MouseEvent e) {
        // Può essere lasciato vuoto se non necessario
    }

    public void tick() {
        // Aggiorna logica se necessario (vuoto per ora)
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        drawBackGround(g, menuImg);

        // Disegna titolo del menu
        drawTitleButton(g, "", 180, 40, 400, 100, lbsFnt75);
        g2d.drawImage(titleImg, 210, 52, 330, 70, null);

        // Disegna i pulsanti
        drawButton(g, "PLAY", 200, 180, 360, 80);
        drawButton(g, "HELP", 200, 300, 360, 80);
        drawButton(g, "QUIT", 200, 420, 360, 80);
    }

}