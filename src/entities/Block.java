package src.entities;

import java.util.ArrayList;

import src.level.Handler;
import src.screens.PlayingScreen;
import src.utils.Texture;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameEntity{

    public static final int BLK_SIZE = 32;
    private static final int BLK_BOUNDS_VERTICAL = 10;

    Texture tex = PlayingScreen.getInstance();
    public int type;
    
    public Block(float x, float y, int type, EntityId id, Handler handler, PlayingScreen playingScreen){
        super(x, y, id, handler, playingScreen);
        this.type = type;
    }

    public  void tick(ArrayList <GameEntity> entity){

    }

    public  void render(Graphics g){
        if (type == 0) {
        g.drawImage(tex.block[0], (int) x, (int) y, null);
        }
        if (type == 1) {
            g.drawImage(tex.block[1], (int) x, (int) y, null);
        }
        if (type == 2) {
            g.drawImage(tex.block[2], (int) x, (int) y, null);
        }
        if (type == 3) {
            g.drawImage(tex.block[3], (int) x, (int) y, null);
        }
        if (type == 4) {
            g.drawImage(tex.block[4], (int) x, (int) y, null);
        }
        if (type == 5) {
            g.drawImage(tex.block[5], (int) x, (int) y, null);
        }
        if (type == 6) {
            g.drawImage(tex.block[6], (int) x, (int) y, null);
        }
        if (type == 7) {
            g.drawImage(tex.block[7], (int) x, (int) y, null);
        }
        if (type == 8) {
            g.drawImage(tex.block[8], (int) x, (int) y, null);
        }
        if (type == 9) {
            g.drawImage(tex.block[9], (int) x, (int) y, null);
        }
        if (type == 10) {
            g.drawImage(tex.block[10], (int) x, (int) y, null);
        }
    }

    public Rectangle getBoundsTop(){//top
        return new Rectangle((int) x, (int) y, BLK_SIZE,BLK_BOUNDS_VERTICAL);
    }
    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) (y), BLK_SIZE, BLK_SIZE);
    }

    public Rectangle getBoundsBot(){
        return new Rectangle((int) x, (int) (y+22), BLK_SIZE,BLK_BOUNDS_VERTICAL);
    }
}
