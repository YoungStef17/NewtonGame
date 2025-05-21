package src.screens;

import java.awt.Graphics;
import java.awt.event.MouseListener;

import src.main.Game;
import src.main.State;

public class ScreenManager {

    private Game game;

    private MenuScreen menuScreen;
    private SelectLevelScreen selectLevelScreen;
    private EndScreen endScreen;
    private WinScreen winScreen;
    private HelpScreen helpScreen;
    private LoadingScreen loadingScreen;
    private PlayingScreen playingScreen;


    // Costruttore per inizializzare le schermate
    public ScreenManager(Game game) {
        this.game = game;

        this.menuScreen = new MenuScreen(this);
        this.selectLevelScreen = new SelectLevelScreen(this);
        this.endScreen = new EndScreen(this);
        this.winScreen = new WinScreen(this);
        this.helpScreen = new HelpScreen(this);
        this.loadingScreen = new LoadingScreen(this);
        this.playingScreen = null;
    }

    public void tick(){

    }
    
    public void draw(Graphics g){
            
        switch (game.gameState) {
            case Menu:
                menuScreen.draw(g);
                break;
            case SelectLevel:
                selectLevelScreen.draw(g);
                break;
            case End:
                endScreen.draw(g);
                break;
            case Win:
                winScreen.draw(g);
                break;
            case Help:
                helpScreen.draw(g);
                break;
            case LoadingScreen:
                loadingScreen.draw(g);
                break;
            case Game:
                playingScreen.draw(g);
            default:
                break;
        }
        
    }
public void setScreen(State newState) {

        // Gestisce il passaggio tra schermate
        if (game.gameState == State.Game && playingScreen != null) {
            playingScreen.stopGameLoop();  // Ferma il game loop se si passa da PlayerScreen
        }

        game.gameState = newState;  // Aggiorna lo stato del gioco

        // Inizializza il nuovo stato e avvia il game loop se necessario
        switch (newState) {
            case Game:
                if (playingScreen == null) {

                    playingScreen = new PlayingScreen(this);  // Crea la PlayerScreen se non esiste
                }
                playingScreen.setUpGame();

                playingScreen.startGameLoop();  // Avvia il game loop
                break;
            default:
                game.repaint();
                break;
        }
        
    }
    public void updateMouseListeners() {
        // Rimuovi tutti i mouse listener esistenti
        for (MouseListener listener : game.getMouseListeners()) {
            game.removeMouseListener(listener);
        }

        // Aggiungi il listener appropriato in base allo stato del gioco
        switch (game.gameState) {
            case Menu:
                game.addMouseListener(menuScreen);
                break;
            case SelectLevel:
                game.addMouseListener(selectLevelScreen);
                break;
            case End:
                game.addMouseListener(endScreen);
                break;
            case Win:
                game.addMouseListener(winScreen);
                break;
            case Help:
                game.addMouseListener(helpScreen);
                break;
            case LoadingScreen:
                game.addMouseListener(loadingScreen);
                break;
            default:
                break;
        }
    }

    public PlayingScreen getPlayingScreen(){
        return playingScreen;
    }

    public Game getGame(){
        return game;
    }
}
