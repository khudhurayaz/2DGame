package de.khudhurayaz.core.entity;

import de.khudhurayaz.core.Position;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 *
 * @author Ayaz Khudhur
 * @version 1.0.0.0
 */
public abstract class Entity {
    protected String name;
    public Position worldPosition;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea, solidAreaDefault;
    public boolean collisionOn = false;
    public static Logger entityLogger;

    public Entity(String name){
        this.name = name;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2d);

    public String getName(){
        return name;
    }
}
