package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteLoader {
    public  static BufferedImage[] loadSprites(String folderPath){

            File folder = new File(folderPath);
            if (!folder.exists() || !folder.isDirectory()){
                System.out.println("Invalid folder path: "+ folderPath);
                return new BufferedImage[0];
            }
            File[] files = folder.listFiles(((dir, name) -> name.toLowerCase().endsWith(".png")));
            if (files==null || files.length== 0){
                System.out.println("No PNG files found in folder: " + folderPath);
                return new BufferedImage[0];
            }
            BufferedImage[] sprites = new BufferedImage[files.length];
            try {
                for (int i= 0; i< files.length; i++){
                    sprites[i] = ImageIO.read(files[i]);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return sprites;
    }
}
