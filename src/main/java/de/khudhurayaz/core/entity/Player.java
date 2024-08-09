package de.khudhurayaz.core.entity;

import de.khudhurayaz.App;
import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.GameConstant;
import de.khudhurayaz.core.Position;
import de.khudhurayaz.core.sound.Sound;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Player Klasse.
 *
 * @author Ayaz Khudhur
 * @version 1.0.0.0
 * @since 30.07.2024
 */
public class Player extends Entity{

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    //Player Position
    public Position playerPosition;
    private int keys = 0;
    private int standCounter = 0;
    private boolean moving = false;
    private int pixelCounter = 0;

    public Player(){
        super("Khudhur");
        worldPosition = new Position(23*GameConstant.TILE_SIZE, 21*GameConstant.TILE_SIZE);
        speed = 2;
        direction = "down";
        playerPosition = new Position(
                (GameConstant.SCREEN_WIDTH/2)-(GameConstant.TILE_SIZE/2),
                (GameConstant.SCREEN_HEIGHT/2)-(GameConstant.TILE_SIZE/2),
                (GameConstant.TILE_SIZE),
                (GameConstant.TILE_SIZE));
        solidArea = new Rectangle(10, 10, 36, 46);
        solidAreaDefault = new Rectangle();
        solidAreaDefault.x = solidArea.x;
        solidAreaDefault.y = solidArea.y;
        getPlayerImage();
        entityLogger = Logger.getLogger(getClass().getSimpleName());
    }

    /*
        Load Player Image.
     */
    private void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(GameConstant.PLAYER_IMGAE + "walk/boy_right_2.png")));
        }catch (IOException e){
            entityLogger.log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public void update() {
        if (!moving){
            if (App.panel.kHandler.UP() ||
                    App.panel.kHandler.DOWN() ||
                    App.panel.kHandler.LEFT() ||
                    App.panel.kHandler.RIGHT()) {
                if (App.panel.kHandler.UP()) {
                    direction = "up";
                }
                if (App.panel.kHandler.DOWN()) {
                    direction = "down";
                }
                if (App.panel.kHandler.LEFT()) {
                    direction = "left";
                }
                if (App.panel.kHandler.RIGHT()) {
                    direction = "right";
                }

                moving = true;

                //Check collision Tile
                collisionOn = false;
                App.panel.collisionChecker.checkTile(this);

                //object collision
                pickUpObject(App.panel.collisionChecker.checkObject(this, true));
            }
            else{
                standCounter++;
                if (standCounter == 20){
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
        if (moving){
            if (!collisionOn){
                switch (direction){
                    case "up":
                        worldPosition.setY(worldPosition.getY() - speed);
                        break;
                    case "down":
                        worldPosition.setY(worldPosition.getY() + speed);
                        break;
                    case "left":
                        worldPosition.setX(worldPosition.getX() - speed);
                        break;
                    case "right":
                        worldPosition.setX(worldPosition.getX() + speed);
                        break;
                }
            }

            //Sprite Animation
            spriteCounter++;
            if (spriteCounter >= 30){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            pixelCounter += speed;

            if (pixelCounter == 48){
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    /*
        Aufheben oder collision auf ein Objekt.
     */
    private void pickUpObject(int index){
        if (index != 999 && index != -1) {
            String objectName = App.panel.objects[index].name;
            switch (objectName){
                case "Key":
                    if (App.panel.objects[index].name.equalsIgnoreCase("Key")) {
                        App.panel.UI().showMessage("You got a Key!");
                        App.panel.removeEntity(App.panel.objects[index], GamePanel.OBJECT_ENTITIES);
                        App.panel.objects[index] = null;
                        App.panel.playSoundEffect(Sound.COIN_SOUND);
                        keys++;
                    }
                    break;
                case "Door":
                    if (keys >= 1){
                        keys--;
                        App.panel.UI().showMessage("You opened the door!");
                        App.panel.playSoundEffect(Sound.UNLOCK_SOUND);
                        App.panel.removeEntity(App.panel.objects[index], GamePanel.OBJECT_ENTITIES);
                        App.panel.objects[index] = null;
                    }else{
                        App.panel.UI().showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    App.panel.UI().showMessage("Speed up!");
                    App.panel.playSoundEffect(Sound.COIN_SOUND);
                    speed = speed + 1;
                    App.panel.removeEntity(App.panel.objects[index], GamePanel.OBJECT_ENTITIES);
                    App.panel.objects[index] = null;
                    break;
                case "Chest":
                    App.panel.playSoundEffect(Sound.FAN_FARE_SOUND);
                    App.panel.removeEntity(App.panel.objects[index], GamePanel.OBJECT_ENTITIES);
                    App.panel.objects[index] = null;
                    speed = 2;
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString(name, playerPosition.getX() + ((playerPosition.getWidth()/2)-(name.length()*4)), playerPosition.getY() - 10);
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1) image = up1;
                else if (spriteNum == 2) image = up2;
                break;
            case "down":
                if (spriteNum == 1) image = down1;
                else if (spriteNum == 2) image = down2;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                else if (spriteNum == 2) image = left2;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                else if (spriteNum == 2) image = right2;
                break;
        }
        g.drawImage(image, playerPosition.getX(), playerPosition.getY(), playerPosition.getWidth(), playerPosition.getHeight(), null);
    }

    public int getKeys(){
        return keys;
    }
}
