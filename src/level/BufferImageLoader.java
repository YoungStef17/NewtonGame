package src.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferImageLoader {
    
    private BufferedImage image;

    public BufferedImage loadImage(String path){

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();    
        }
        return image;
    }
}
