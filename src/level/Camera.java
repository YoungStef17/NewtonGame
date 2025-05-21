package src.level;


public class Camera {

    public float x,y;
    public float velX;
    private static final float CAMERA_SPEED = 1.8f;

    public Camera(float x, float y, float velX){
        this.x=x;
        this.y=y;
        this.velX = velX;
    }

    public void tick(){
        x -= CAMERA_SPEED + velX; // a ogni frame la telecamera si sposta verso sx
        //- invece che + perchè quando sposti questa la telecamera in una direzione, gli oggetti dentro la finestra sembrano muoversi nella direzione opposta
        //x-=velX;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
}
