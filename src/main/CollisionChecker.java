package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker (GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile (Entity entity) {
        // compute the whole world entity to get to the solid area
        int leftX = entity.worldX + entity.solidArea.x;
        int rightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int topY = entity.worldY + entity.solidArea.y;
        int bottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int leftCol = leftX / gp.tileSize;
        int rightCol = rightX / gp.tileSize;
        int topRow = topY / gp.tileSize;
        int bottomRow = bottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                topRow = (topY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
