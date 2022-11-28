package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Pickaxe extends SuperObject{
    public OBJ_Pickaxe () {
        name = "Pickaxe";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/pickaxe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
