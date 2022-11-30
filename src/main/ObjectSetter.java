package main;

import object.*;

public class ObjectSetter {
    GamePanel gp;

    public ObjectSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Hands();

        gp.obj[1] = new OBJ_Pickaxe();
        gp.obj[1].worldX = 37 * gp.tileSize;
        gp.obj[1].worldY = 15 * gp.tileSize;

        gp.obj[2] = new OBJ_Shovel();
        gp.obj[2].worldX = 39 * gp.tileSize;
        gp.obj[2].worldY = 15 * gp.tileSize;

        gp.obj[3] = new OBJ_Bucket();
        gp.obj[3].worldX = 35 * gp.tileSize;
        gp.obj[3].worldY = 15 * gp.tileSize;
    }
}
