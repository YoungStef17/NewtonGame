package src.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import src.entities.Enemy;
import src.entities.EntityId;
import src.entities.GameEntity;
import src.entities.Player;
import src.level.BufferImageLoader;
import src.level.Camera;
import src.level.Handler;
import src.level.KeyInput;
import src.level.LevelLoader;
import src.main.Game;
import src.main.State;
import src.main.UI;
import src.utils.Texture;

public class PlayingScreen extends AbstractScreen implements Runnable{
    private boolean running = false;
    private Thread thread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 100;
    public static int score = 0;
    public static int nApple = 0;

    public static Texture tex ;
    public Handler handler;
    public Camera cam;
    private Player player;
    private Enemy enemy;
    
    public UI userInterface = new UI(this);
    public BufferedImage level, backgroundImg;
    
    private LevelLoader levelLoader;
    public BufferImageLoader loader;

    public PlayingScreen(ScreenManager screenManager){
        super(screenManager);
        loader = new BufferImageLoader();
        init();

    }

    public synchronized void startGameLoop(){
        if(running) //se è true il metodo ritorna immediatamente e non fa nulla (il gioco è già in esecuzione).
            return;
        running=true;
        thread = new Thread(this); // crea un nuovo thread che eseguirà il codice del metodo run() della tua classe Game(this si riferisce a game poichè thread accetta oggetto runnable e game implemetna runnable).
        thread.start(); //chiama il metodo run
    }

    public void init(){

        backgroundImg = loader.loadImage("/src/resource/images/background.png");
        tex = new Texture();
    
        cam = new Camera(0,0,0);
    
        handler = new Handler(cam);

        levelLoader = new LevelLoader(this, handler);
    
        screenManager.getGame().addKeyListener(new KeyInput(handler));

    }

    public void run(){
        screenManager.getGame().setFocusable(true);
        screenManager.getGame().requestFocus();
        
        double timePerUpdate =1000000000/UPS_SET;
        double timePerFrame =1000000000/FPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (running) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime)/ timePerUpdate;
            deltaF += (currentTime - previousTime)/ timePerFrame;
            previousTime = currentTime;

            if (deltaU >=1) {
                tick();
                update++;
                deltaU--;
            }
            if (deltaF >=1) {
                screenManager.getGame().repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames+ " UPS: "+ update);
                frames = 0;
                update = 0;
            }
        }
    }
    
    public void stopGameLoop(){
        running = false;
    }
    
    public void tick() {
        handler.tick();
    
        cam.tick();    
        score =(int) player.getX();
        
        for (int i = 0; i < handler.entity.size(); i++) {
            GameEntity tempEntity = handler.entity.get(i);
    
            if (tempEntity.getId() == EntityId.Player) {

                if (playerOutOfBounds()) {
                    gameOver();
                }
            }
            if (tempEntity.getId() == EntityId.Enemy) {
                if (enemyOutOfBounds() ) {
                    respawnEnemyIfOutOfBounds();
                }   
            }
        }  
    }

    public void gameOver(){
        screenManager.getGame().stopMusic();
        screenManager.getGame().playSound(7);
        System.out.println("end");
        screenManager.setScreen(State.End);
        resetGame();
    }

    public void win(){
        screenManager.getGame().stopMusic();
        screenManager.getGame().playSound(4);
        screenManager.setScreen(State.Win);
        resetGame();
    }

    public void respawnEnemyIfOutOfBounds(){
        enemy.x=player.x-256;
        enemy.y = player.y;
        enemy.setVelX(player.getVelX());
    }

    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;//trasformo g in g2d perchè graphics2d fornisce funzioni + avanzate come la traslazione
        
        g2d.translate(cam.getX(), cam.getY());//begin of cam
        //translate() sposta l'intera area di disegno in base alle coordinate della telecamera.
        for(int xx = 0; xx < (backgroundImg.getWidth()*33) ; xx+=backgroundImg.getWidth()) g.drawImage(backgroundImg, xx, 0 , screenManager.getGame());
        
        handler.render(g);
    
        g2d.translate(-cam.getX(), -cam.getY());//end of cam
        //Se non usassi g2d.translate(-cam.getX(), -cam.getY()), la traslazione si accumulerebbe ad ogni ciclo di rendering, ciò che disegnerei nei cicli successivi sarebbe ulteriormente spostato di una certa quantità rispetto alla posizione corrente
        userInterface.draw(g2d);
        //g.dispose();
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }

    public void selectLevel(){
        switch (screenManager.getGame().levelState) {
            case Level1:
                level = loader.loadImage("/src/resource/levels/levelOne.png");
                break;
            case Level2:
                level = loader.loadImage("/src/resource/levels/level.png");
                break;
            case Level3:
            
                break;
            default:
                break;
        }
        levelLoader.loadImageLevel(level);
    }

    public void setUpGame(){
        score=0;
        nApple =0;

        selectLevel();

        screenManager.getGame().playMusic(0);
        userInterface.showMessage("Use space to jump");
    }

    public void resetGame(){
        handler.clearEntity();

        cam.setX(0);
        cam.setY(0);
        cam.velX=0;

        screenManager.getGame().getScoreManager().updateHighScore(screenManager.getGame().levelState,score,nApple);
    }

    public boolean playerOutOfBounds(){
        if (player.getY() > (cam.getY() + Game.JPANEL_HEIGHT+128) || player.getY() < cam.getY()-128 || player.getX() < (-1 *cam.getX()-64)) {
            return true;
        }else   
            return false;
    }
    public boolean enemyOutOfBounds(){
        if (enemy.getY() > (cam.getY() + Game.JPANEL_HEIGHT+256) || enemy.getY() < cam.getY()-256 || enemy.getX() < (-1 *cam.getX()-256)) {
            return true;
        }else   
            return false;
    }
    
        
    public static Texture getInstance(){
        return tex;
    }
    

    public int getScore(){
        return score;
    }
    
    public int getApple(){
        return nApple;
    }

    public Game getGame(){
        return screenManager.getGame();
    }
    public Handler getHandler(){
        return handler;
    }
    public void mousePressed(MouseEvent e) {
        
    }
    
    public void mouseReleased(MouseEvent e) {
        
    }
}
