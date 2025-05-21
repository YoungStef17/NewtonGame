package src.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import src.level.Handler;
import src.screens.PlayingScreen;
import src.utils.Animation;
import src.utils.Texture;

public class Player extends GameEntity {

    private float playerWidth = 32, playerHeight=64;
    public static final float MAX_SPEED=10;
    public static final int APPLE_SCORE_VALUE = 1000;
    private static final int ANIMATION_OFFSET = 15;
    //private Handler handler;

    Texture tex = PlayingScreen.getInstance();
    private Animation playerRun;
    private Animation playerRunInverted;
    private Block block;
    private Apple apple;
    
    public Player(float x, float y, EntityId id, Handler handler, PlayingScreen playingScreen){
        super(x, y, id, handler, playingScreen);
        width=playerWidth;
        height=playerHeight;
        playerRun = new Animation(5,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6],tex.player[7]);
        playerRunInverted = new Animation(5,tex.playerInverted[0],tex.playerInverted[1],tex.playerInverted[2],tex.playerInverted[3],tex.playerInverted[4],tex.playerInverted[5],tex.playerInverted[6],tex.playerInverted[7]);

    }

    public void tick(ArrayList<GameEntity> entity) {
        //Aggiorna la posizione del giocatore in base alla sua velocità.
        x+=velX;
        y+=velY;        

        
        if(gravityInverted){
            velY+= -gravity; //inverti gravità --> sale
        }else 
            velY+=gravity; //gravità normale --> scende

        if(velY> MAX_SPEED){
            velY=MAX_SPEED; //limito la velocità
        } 
        else if (velY < -MAX_SPEED) {
            velY = -MAX_SPEED;
        }
        
        x+=ENTIY_SPEED;
        Collision(entity);
        if (gravityInverted) {
            playerRunInverted.runAnimation();
        }
        else playerRun.runAnimation();
    }

    private void Collision(ArrayList<GameEntity> entity){

        for(int i=0; i< handler.entity.size(); i++){
            GameEntity tempEntity= handler.entity.get(i);
            if(tempEntity.getId() == EntityId.Block){
                block = (Block) tempEntity;

                playerCollisionWithBlock();  
            }
            else if (tempEntity.getId() == EntityId.Apple) {
                apple = (Apple) tempEntity;
                eatApple();
            }
        }
    }

    public void playerCollisionWithBlock(){

        if (getBoundsTop().intersects(block.getBoundsBot())) {
            collisionWithBlockTop(block);
        }
        if (getBoundsBot().intersects(block.getBoundsTop())) { //intersects verifica se 2 rettangoli si intersecano
            collisionWithBlockBottom(block);
            if (block.type == 4) {
                playingScreen.gameOver();
            }
        }
        if (getBounds().intersects(block.getBounds())) {
            x=block.getX() -Block.BLK_SIZE;
            if(block.type == 5) playingScreen.win();
        }
        if (getBoundsLeft().intersects(block.getBounds())) {
            x=block.getX() +Block.BLK_SIZE;
        }
    }

    public void render(Graphics g) { 
        if (gravityInverted) {
            playerRunInverted.drawAnimation(g, (int) x-ANIMATION_OFFSET, (int) y);
        }else
        playerRun.drawAnimation(g, (int) x-ANIMATION_OFFSET, (int) y);
    }

    public void eatApple(){
        if (getBounds().intersects(apple.getBounds())|| getBoundsBot().intersects(apple.getBounds()) || getBoundsTop().intersects(apple.getBounds())) {
            PlayingScreen.score +=APPLE_SCORE_VALUE;
            PlayingScreen.nApple ++;
            playingScreen.getGame().playSound(1);
            handler.removeEntity(apple);
        }
    }
    //definisco i rettangoli di collisione in diverse direzioni (alto, basso, sinistra e destra) per il giocatore

    public Rectangle getBoundsBot() {

        return new Rectangle((int)(x+(width/2)-(width/2)/2), (int) (y+(height/2)) ,(int)width/2, (int)height/2 );
    } 
    

    public Rectangle getBoundsTop() {

        return new Rectangle((int)(x+(width/2)-(width/2)/2), (int) y,(int)width/2, (int)height/2 );
    }

    public Rectangle getBounds() {//right

        return new Rectangle((int)(x+width-5), (int) y+5,(int) 5, (int)height-10 );
    }

    public Rectangle getBoundsLeft() {

        return new Rectangle((int)x, (int) y+5,(int) 5, (int)height-10 );
    }
}
