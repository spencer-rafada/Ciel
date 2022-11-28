package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Bucket extends SuperObject{
    public OBJ_Bucket () {
        name = "Bucket";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/bucket.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
