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
        int tileInFront;

        switch(entity.direction) {
            case "up":
                topRow = (topY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][topRow];
                tileInFront = gp.tileM.mapTileNum[leftCol][topRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(entity.collisionOn == true) {
                    entity.tileInFront = gp.tileM.tile[tileInFront].name;
                } else {
                    entity.tileInFront = null;
                }
                break;
            case "down":
                bottomRow = (bottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                tileInFront = gp.tileM.mapTileNum[leftCol][bottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(entity.collisionOn == true) {
                    entity.tileInFront = gp.tileM.tile[tileInFront].name;
                } else {
                    entity.tileInFront = null;
                }
                break;
            case "left":
                leftCol = (leftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                tileInFront = gp.tileM.mapTileNum[leftCol][topRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(entity.collisionOn == true) {
                    entity.tileInFront = gp.tileM.tile[tileInFront].name;
                } else {
                    entity.tileInFront = null;
                }
                break;
            case "right":
                rightCol = (rightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                tileInFront = gp.tileM.mapTileNum[rightCol][topRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(entity.collisionOn == true) {
                    entity.tileInFront = gp.tileM.tile[tileInFront].name;
                } else {
                    entity.tileInFront = null;
                }
                break;
        }
        // System.out.println(entity.tileInFront);
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if(gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (isPlayer == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if(gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (isPlayer == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                                if(gp.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (isPlayer == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
