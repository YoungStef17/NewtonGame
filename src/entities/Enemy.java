package src.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import src.level.Handler;
import src.screens.PlayingScreen;
import src.utils.Animation;
import src.utils.Texture;

public class Enemy extends GameEntity{
    
    private Queue<Action> actionQueue = new LinkedList<>();
    Texture tex = PlayingScreen.getInstance();
    private Animation enemyAttacking;
    private Player player;
    private Block block;
    private static final float ENEMY_WIDTH =64;
    private static final float ENEMY_HEIGHT = 64;
    private static final int TIME_DELAY = 1000;

    private static class Action {
        long timestamp; // Time when the action was performed
        boolean gravityInverted; // Gravity state at the time of the action

        Action(long timestamp, boolean gravityInverted) {
            this.timestamp = timestamp;
            this.gravityInverted = gravityInverted;
        }
    }


    public Enemy(float x, float y,  EntityId id,Handler handler, PlayingScreen playingScreen) {
        super(x, y, id, handler, playingScreen);
        width=ENEMY_WIDTH;
        height=ENEMY_HEIGHT;
        enemyAttacking = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2],tex.enemy[3],tex.enemy[4],tex.enemy[5],tex.enemy[6],tex.enemy[7],tex.enemy[8]);
    }
    
    public void tick(ArrayList<GameEntity> entity) {
        enemyAttacking.runAnimation();

        x+=velX;
        y+=velY;        
        
        if (!actionQueue.isEmpty()) {
            Action oldestAction = actionQueue.peek();
            if (System.currentTimeMillis() - oldestAction.timestamp >= TIME_DELAY) {
                // 1 seconds have passed, perform the oldest action
                setGravityInverted(oldestAction.gravityInverted);
                actionQueue.poll(); // Remove the action from the queue
            }
        }

        if(gravityInverted){
            velY+= -gravity; //inverti gravità --> sale
        }
        
        else velY+=gravity; //gravità normale --> scende

        if(velY> Player.MAX_SPEED){
            velY=Player.MAX_SPEED; //limito la velocità
        } 

        else if (velY < -Player.MAX_SPEED) {
            velY = -Player.MAX_SPEED;
        }
        
        x+=ENTIY_SPEED;
        Collision(entity);
    }
    
    public void addAction(boolean gravityInverted) {
        long currentTime = System.currentTimeMillis();
        actionQueue.add(new Action(currentTime, gravityInverted));
    }
    
    private void Collision(ArrayList<GameEntity> entity){

        for(int i=0; i< handler.entity.size(); i++){
            GameEntity tempEntity= handler.entity.get(i);
            if(tempEntity.getId() == EntityId.Block){
                block = (Block) tempEntity;
                enemyCollisionWithBlock();
                
            }else if (tempEntity.getId() == EntityId.Player) {
                player = (Player) tempEntity;
                if (intersectPlayer()) {
                    playingScreen.gameOver();
                }
            }
        }
    }

    public void enemyCollisionWithBlock(){
        if (getBoundsTop().intersects(block.getBoundsBot())) {
            collisionWithBlockTop(block);
        }

        if (getBoundsBot().intersects(block.getBoundsTop())) { 
            collisionWithBlockBottom(block);
        }
        
        //Right
        if (getBounds().intersects(block.getBounds())) {
            x=block.getX() -ENEMY_WIDTH;
        }
        //left
        if (getBoundsLeft().intersects(block.getBounds())) {
            x=block.getX() +ENEMY_WIDTH;
        }
    }

    public void render(Graphics g) {

        enemyAttacking.drawAnimation(g, (int) x, (int) y);
    }

    public boolean intersectPlayer(){
        return getBounds().intersects(player.getBounds()) || getBoundsTop().intersects(player.getBoundsBot()) || getBoundsBot().intersects(player.getBoundsTop())|| getBoundsLeft().intersects(player.getBounds());
    }

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

