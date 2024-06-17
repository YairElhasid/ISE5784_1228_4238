package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import java.io.IOException;

/**
 * Testing ImageWriter class
 * @author Yair Elhasid and Sagiv Maoz
 */
public class ImageWriterTest {
    /** test image writer*/
    @Test
    void testImageWriter(){
        ImageWriter imageWriter = new ImageWriter("surprise",800,500);
        for(int i=0;i< imageWriter.getNx();i++) {
            for(int j=0;j< imageWriter.getNy();j++){
                imageWriter.writePixel(i,j, i % 50 == 0 || j % 50 == 0 ?
                        new Color(30,45,107) : new Color(76,82,56));
            }
        }
        imageWriter.writeToImage();
    }
}
