package src.level;

import java.awt.Graphics;
import java.util.ArrayList;

import src.entities.Block;
import src.entities.EntityId;
import src.entities.GameEntity;
import src.main.Game;

public class Handler {

    public ArrayList<GameEntity> entity = new ArrayList<GameEntity>(); //contiene tutti gli oggetti di gioco attivi.
    private static final int BLK_REMOVING_RANGE = 100;
    public Camera cam;
    GameEntity tempEntity;

    public Handler(Camera cam) {  // Passa la camera nel costruttore
        this.cam = cam;
    }
 
    public void tick(){
        
        for(int i =0; i< entity.size(); i++){
            tempEntity = entity.get(i);
            
            tempEntity.tick(entity);
        }
    }
 
    public void render (Graphics g){
        
        for(int i = 0; i < entity.size(); i++){
            tempEntity = entity.get(i);
            
            if (blockOutOfCam()) {
                ;
            } 
            else {
                // Controllo se l'oggetto è fuori dalla visuale della camera (e.g. a sinistra dello schermo)
                if (tempEntity.getX() < (-cam.getX() - BLK_REMOVING_RANGE) && tempEntity.getId()!=EntityId.Enemy) { 
                    removeEntity(tempEntity); // Rimuove l'oggetto se è fuori dalla visuale
                    i--; // Decremento l'indice per evitare errori di salto durante la rimozione
                } 
                else tempEntity.render(g); // Renderizza l'oggetto se è ancora visibile
            }
        }
    }

    public boolean blockOutOfCam(){
        return (tempEntity.x > -cam.getX() + Game.JPANEL_WIDTH) || (tempEntity.x < -cam.getX() - Block.BLK_SIZE) || (tempEntity.y > -cam.getY() + Game.JPANEL_HEIGHT) || (tempEntity.y < -cam.getY() - Block.BLK_SIZE);
    }

    public void addEntity(GameEntity entity){
        this.entity.add(entity);
    }

    public void removeEntity( GameEntity entity){
        this.entity.remove(entity);
    }

    public void clearEntity(){
        entity.clear();
    }
}
