package src.level;

import java.awt.Color;
import java.awt.image.BufferedImage;

import src.entities.Apple;
import src.entities.Block;
import src.entities.Enemy;
import src.entities.EntityId;
import src.entities.Player;
import src.screens.PlayingScreen;

public class LevelLoader {

    private Handler handler;
    private PlayingScreen playingScreen;
    private static final int PXL_SIZE = 32;

    public LevelLoader(PlayingScreen playingScreen, Handler handler){
        this.playingScreen = playingScreen;
        this.handler = handler;
    }

    public void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
            int h = image.getHeight();
    
            for(int xx =0; xx<w; xx++){
                for(int yy=0; yy < h; yy++){
                    int pixel = image.getRGB(xx, yy);
                    Color pixelColor = new Color(pixel);
    
                    if( pixelColor.equals(Color.white)){
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,0, EntityId.Block, handler,playingScreen)); //aggiunge blocchi leaf
                    }
                    if (pixelColor.equals(new Color(180,130,0))) /*brown*/ {
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,6, EntityId.Block, handler ,playingScreen)); //wooden block
                    }
                    if (pixelColor.equals(new Color(100,200,200))) /*light cyan*/ {
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,7, EntityId.Block, handler,playingScreen)); //aggiunge blocchi bridge
                    }
                    //------//
                    if( pixelColor.equals(Color.red)){
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,1, EntityId.Block, handler,playingScreen)); //aggiunge blocchi speedUp
                    }
                    if( pixelColor.equals(Color.blue)){
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,2, EntityId.Block, handler,playingScreen)); //aggiunge blocchi speedDown
                    }
                    if (pixelColor.equals(Color.yellow)) {
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,3, EntityId.Block, handler,playingScreen)); //aggiunge blocchi normalSpeed
                    }
                    //------//
                    if (pixelColor.equals(new Color(200,100,200))) /*purple*/ {
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,8, EntityId.InvisibleBlock, handler,playingScreen)); //aggiunge blocchi straight sign
                    }
                    if (pixelColor.equals(new Color(50,150,250))) /*light blue */{
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,9, EntityId.InvisibleBlock, handler,playingScreen)); //aggiunge blocchi up sign
                    }
                    if (pixelColor.equals(new Color(250,150,50))) /*orange*/{
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,10, EntityId.InvisibleBlock, handler,playingScreen)); //aggiunge blocchi down sign
                    }
                    //-------//
                    if (pixelColor.equals(Color.gray)) {
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,4, EntityId.Block, handler,playingScreen)); //aggiunge blocchi gameOver
                    }
                    if (pixelColor.equals(Color.magenta)) {
                        handler.addEntity(new Block(xx*PXL_SIZE, yy*PXL_SIZE,5, EntityId.Block, handler,playingScreen)); //aggiunge blocchi win
                    }
                    
                    
                    
                    if( pixelColor.equals(Color.lightGray)){
                        Player player = new Player(xx*PXL_SIZE, yy*PXL_SIZE,EntityId.Player, handler,playingScreen);
                        handler.addEntity(player);
                        playingScreen.setPlayer(player);
                    }
                    if( pixelColor.equals(Color.green)){
                        handler.addEntity(new Apple(xx*PXL_SIZE, yy*PXL_SIZE, EntityId.Apple, handler,playingScreen));
                    }
                    if( pixelColor.equals(Color.CYAN)){
                        Enemy enemy = new Enemy(xx*PXL_SIZE, yy*PXL_SIZE, EntityId.Enemy,handler,playingScreen);
                        handler.addEntity(enemy);
                        playingScreen.setEnemy(enemy);
                    }
                    //moltiplico per 32 perchè 1 pixel rappresenta un blocco 32x32
                }
            }
    }
}
