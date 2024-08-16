package de.khudhurayaz.core.entity;

import de.khudhurayaz.App;
import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.GameConstant;
import de.khudhurayaz.core.Position;
import de.khudhurayaz.core.animation.Animation;
import de.khudhurayaz.core.animation.AnimationBuilder;
import de.khudhurayaz.core.animation.AnimationComponent;
import de.khudhurayaz.core.sound.Sound;
import de.khudhurayaz.core.animation.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * Player Klasse.
 *
 * @author Ayaz Khudhur
 * @version 1.0.0.0
 * @since 30.07.2024
 */
public class Player extends Entity{

    //Player Position
    public Position playerPosition;
    private int keys = 0;
    private int standCounter = 0;
    private boolean moving = false;
    private int pixelCounter = 0;
    private Animation idleUp, idleDown, idleRight, idleLeft;
    private AnimationComponent animationComponent;

    public Player(){
        super("Khudhur");
        init();
        setupAnimationComponent();
        entityLogger = Logger.getLogger(getClass().getSimpleName());
    }

    private void init() {
        worldPosition = new Position(23*GameConstant.TILE_SIZE, 21*GameConstant.TILE_SIZE);
        speed = 2;
        direction = "down";
        playerPosition = new Position(
                (GameConstant.SCREEN_WIDTH/2)-(GameConstant.TILE_SIZE/2),
                (GameConstant.SCREEN_HEIGHT/2)-(GameConstant.TILE_SIZE/2),
                (GameConstant.TILE_SIZE),
                (GameConstant.TILE_SIZE));
        int solidWidth = 30;
        int solidHeight = 40;
        solidArea = new Rectangle(solidWidth/2, solidHeight/2, solidWidth, solidHeight);
        solidAreaDefault = new Rectangle();
        solidAreaDefault.x = solidArea.x;
        solidAreaDefault.y = solidArea.y;

        //Player Animation
        idleLeft = AnimationBuilder.buildAnimation("player/walk/left");
        idleRight = AnimationBuilder.buildAnimation("player/walk/right");
        idleUp = AnimationBuilder.buildAnimation("player/walk/up");
        idleDown = AnimationBuilder.buildAnimation("player/walk/down");
    }

    private void setupAnimationComponent(){
        animationComponent = new AnimationComponent(this, idleUp, idleDown, idleRight, idleLeft);
        animationComponent.setAnimationSpeed(30);
        animationComponent.name = getName() + " Animation";
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

            pixelCounter += speed;
            if (pixelCounter == 48){
                moving = false;
                pixelCounter = 0;
            }
        }else {
            standCounter++;
            if (standCounter == 30){
                animationComponent.setAnimationIndex(0);
                standCounter = 0;
            }
        }

        animationComponent.update();
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
        BufferedImage image = switch (direction) {
            case "up" -> animationComponent.getAnimationUp(idleUp.getAnimationIndex());
            case "down" -> animationComponent.getAnimationDown(idleDown.getAnimationIndex());
            case "left" -> animationComponent.getAnimationLeft(idleLeft.getAnimationIndex());
            case "right" -> animationComponent.getAnimationRight(idleRight.getAnimationIndex());
            default -> null;
        };
        if (image != null) {
            g.drawImage(image, playerPosition.getX(), playerPosition.getY(), GameConstant.TILE_SIZE, GameConstant.TILE_SIZE, null);
        }
    }

    public int getKeys(){
        return keys;
    }
}
