package src.entities;

import java.util.ArrayList;

import src.level.Handler;
import src.screens.PlayingScreen;

import java.awt.Graphics;
import java.awt.Rectangle;

//è una classe base per tutti gli oggetti del gioco
public abstract class GameEntity {

    public float x,y;
    protected float velX=0,velY=0;
    protected EntityId id; 
    protected boolean gravityInverted=true;
    public boolean canSwitchGravity = false; 
    protected PlayingScreen playingScreen;
    protected Handler handler;
    protected float gravity = 0.1f;
    protected float width , height;
    
    protected static final int ENTIY_SPEED = 2;
    protected static final float SPEED_UP = 0.7f;
    protected static final float SPEED_DOWN = -0.7f;

    public GameEntity(float x, float y, EntityId id,Handler handler, PlayingScreen playingScreen){
        this.x = x;
        this.y=y;
        this.id = id;
        this.handler = handler;
        this.playingScreen = playingScreen;
    }

    public abstract void tick(ArrayList <GameEntity> entity);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract Rectangle getBoundsBot();
    public abstract Rectangle getBoundsTop();

    public void collisionWithBlockBottom(Block block){
        y=block.getY() - height;
        velY = 0;
        canSwitchGravity=true;
        collisionWithSpecialBlock(block);
    }

    public void collisionWithBlockTop(Block block){
        y=block.getY() +Block.BLK_SIZE;
        velY = 0;
        canSwitchGravity=true;
        collisionWithSpecialBlock(block);
    }

    public void collisionWithSpecialBlock(Block block){
        if (block.type == 1) {
            if (velX != SPEED_UP) {
                playingScreen.getGame().playSound(3);
            }
            velX = SPEED_UP;
            handler.cam.velX = SPEED_UP;
        }
        if (block.type == 2) {
            if(velX != SPEED_DOWN) {
                playingScreen.getGame().playSound(2);
            }
            velX = SPEED_DOWN;
            //handler.cam.velX = (float) -0.2;
        }
        if (block.type == 3) {
            velX=0;
            handler.cam.velX=0;
        }
    }

    public  float getX(){
        return x;
    }
    public  float getY(){
        return y;
    }
    public  void setX(float x){
        this.x = x;
    }
    public  void setY(float y){
        this.y = y;
    }

    public  float getVelX(){
        return velX;
    }
    public  float getVelY(){
        return velY;
    }
    public  void setVelX(float velX){
        this.velX=velX;
    }
    public  void setVelY(float velY){
        this.velY=velY;
    }

    public boolean isGravityInverted(){
        return gravityInverted;
    }

    public void setGravityInverted(boolean gravityInverted){
        this.gravityInverted=gravityInverted;
    }

    public  EntityId getId(){
        return id;
    }

    public PlayingScreen getPlayingScreen(){
        return playingScreen;
    }
}