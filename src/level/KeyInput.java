package src.level;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.entities.Enemy;
import src.entities.EntityId;
import src.entities.GameEntity;
import src.entities.Player;

public class KeyInput extends KeyAdapter{

    Handler handler;
    private Player player;

    public KeyInput(Handler handler){
        this.handler=handler;
    }

    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode(); //ogni comando ha una key
        for(int i =0 ; i<handler.entity.size(); i++){
            GameEntity tempEntity= handler.entity.get(i);

            if (tempEntity.getId() == EntityId.Player) {
                player = (Player) tempEntity;

                if (key == KeyEvent.VK_SPACE && player.canSwitchGravity ) {
                    player.setGravityInverted(!tempEntity.isGravityInverted());
                    player.canSwitchGravity = false;
                    tempEntity.getPlayingScreen().getGame().playSound(6);
                    actionRecorder();
                }
            }

            if(key== KeyEvent.VK_ESCAPE){
                System.exit(1);   
            }
            
        }
    }

    public void actionRecorder(){
        for (GameEntity entity : handler.entity) {
            if (entity.getId() == EntityId.Enemy) {
                ((Enemy) entity).addAction(player.isGravityInverted());
            }
        }
    }
}