package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Bucket;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    boolean hasBucket = false;
    String mainHand = "Hands";     // TODO: make a list of all materials

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 22;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-up-2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-up-3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-up-4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-down-2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-down-3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-down-4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-left-2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-left-3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-left-4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-right-2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-right-3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/Player/character-right-4.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Use Item's Main Function
        if (keyH.spacePressed == true) {
            String objectName = mainHand;
            switch(objectName) {
                case "Bucket":
                    OBJ_Bucket bucket = new OBJ_Bucket(); // find a way to instantiate object for whole player
                    getWater(bucket);
                    break;
                case "Pickaxe":
                    break;
                case "Shovel":
                    break;
                default:
                    break;
            }
        }

        // TODO: Use Item's Alt Function

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch(direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }

    public void getWater(OBJ_Bucket bucket) {
        if (hasBucket == true) {
            if (bucket.hasWater == false && tileInFront == "water") {
                System.out.println("Fetching Water");
                bucket.hasWater = true;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;
            switch(objectName) {
                case "Bucket":
                    hasBucket = true;
                    gp.obj[index] = null;
                    mainHand = "Bucket";
                    break;
                case "Pickaxe":
                    gp.obj[index] = null;
                    break;
                case "Shovel":
                    gp.obj[index] = null;
                    break;
            }
        }
    }
    public void draw(Graphics2D g) {
//        g.setColor(Color.white);   // sets the color for drwaing objects
//        g.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                if (spriteNum == 4) {
                    image = up4;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                if (spriteNum == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                if (spriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                if (spriteNum == 4) {
                    image = right4;
                }
                break;
        }
        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
