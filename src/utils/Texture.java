package src.utils;

import java.awt.image.BufferedImage;

import src.level.BufferImageLoader;

public class Texture {
    
    
    SpriteSheet bs1, bs2, bs3, bs4,ps,es,as;

    private BufferedImage block_sheet1 = null;
    private BufferedImage block_sheet2 = null;
    private BufferedImage block_sheet3 = null;
    private BufferedImage block_sheet4 = null;
    private BufferedImage player_sheet = null;
    private BufferedImage enemy_sheet = null;
    private BufferedImage apple_sheet = null;



    public BufferedImage[] block = new BufferedImage[11];
    public BufferedImage[] player = new BufferedImage[8];
    public BufferedImage[] playerInverted = new BufferedImage[8];
    public BufferedImage[] enemy = new BufferedImage[9];
    public BufferedImage[] apple = new BufferedImage[10];


    public Texture(){
        BufferImageLoader loader = new BufferImageLoader();
        try {
            block_sheet1 = loader.loadImage("/src/resource/images/spriteBlock1.png");
            block_sheet2 = loader.loadImage("/src/resource/images/spriteBlock2.png");
            block_sheet3 = loader.loadImage("/src/resource/images/spriteBlock3.png");
            block_sheet4 = loader.loadImage("/src/resource/images/spriteBlock4.png");
            player_sheet = loader.loadImage("/src/resource/images/spritePlayer.png");
            enemy_sheet = loader.loadImage("/src/resource/images/spriteEnemy.png");
            apple_sheet = loader.loadImage("/src/resource/images/spriteApple.png");

        } catch (Exception e) {
            e.printStackTrace();
       }

        bs1 = new SpriteSheet(block_sheet1);
        bs2 = new SpriteSheet(block_sheet2);
        bs3 = new SpriteSheet(block_sheet3);
        bs4 = new SpriteSheet(block_sheet4);
        ps = new SpriteSheet(player_sheet);
        es = new SpriteSheet(enemy_sheet);
        as = new SpriteSheet(apple_sheet);
        getTextures();
    }

    private void getTextures(){

        block[0] = bs1.grabImage(5, 10, 32, 32);//leaf block
        block[1] = bs1.grabImage(21, 9, 32, 32);//speed block
        block[2] = bs1.grabImage(13, 9, 32, 32);//slow block
        block[3] = bs1.grabImage(5, 10, 32, 32);//reset vel block
        block[4] = bs4.grabImage(1, 1, 32, 32);//die block
        block[5] = bs3.grabImage(7, 13, 32, 32);//win block
        block[6] = bs1.grabImage(17, 12, 32, 32);//bark block
        block[7] = bs4.grabImage(2, 1, 32, 32);//upbridge block
        block[8] = bs4.grabImage(3, 1, 32, 32);//straight sign block
        block[9] = bs4.grabImage(4, 1, 32, 32);//up sign block
        block[10] = bs4.grabImage(5, 1, 32, 32);//down sign block
        
        

        
        for(int i = 0; i <8 ; i++){
            player[i] = ps.grabImage(1+i, 1, 64, 64);

        }
        for(int i = 0; i <8 ; i++){
            playerInverted[i] = ps.grabImage(1+i, 2, 64, 64);
        }
        for(int j=0;j<3;j++){
            for(int i=0;i<3;i++){
               enemy[j*3+i] = es.grabImage(i+1, j+1, 64, 64);  
            }
        }

        for(int j=0;j<2;j++){
            for(int i=0;i<5;i++){
               apple[j*5+i] = as.grabImage(i+1, j+1, 32, 32);               
           }
        }
    
    }
}
