package de.khudhurayaz.core.object;

import de.khudhurayaz.App;
import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.GameConstant;
import de.khudhurayaz.core.entity.Entity;
import de.khudhurayaz.core.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public abstract class SuperObject extends Entity{

    private BufferedImage image;
    protected Logger logger = Logger.getLogger(SuperObject.class.getSimpleName());

    public SuperObject(String name) {
        super(name);
        solidArea = new Rectangle(0,0,48,48);
        solidAreaDefault = new Rectangle(0,0,0,0);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public abstract void init();

    @Override
    public void draw(Graphics2D g2d) {
        Player player = (Player) App.panel.getEntity(Player.class, GamePanel.ENTITIES);
        int screenX = worldPosition.getX() - player.worldPosition.getX() + player.playerPosition.getX();
        int screenY = worldPosition.getY() - player.worldPosition.getY() + player.playerPosition.getY();

        if (worldPosition.getX() + GameConstant.TILE_SIZE > player.worldPosition.getX() - player.playerPosition.getX() &&
            worldPosition.getX() - GameConstant.TILE_SIZE < player.worldPosition.getX() + player.playerPosition.getX() &&
            worldPosition.getY() + GameConstant.TILE_SIZE > player.worldPosition.getY() - player.playerPosition.getY() &&
            worldPosition.getY() - GameConstant.TILE_SIZE < player.worldPosition.getY() + player.playerPosition.getY()){

            g2d.drawImage(getImage(), screenX, screenY, GameConstant.TILE_SIZE, GameConstant.TILE_SIZE, null);
        }
    }

    @Override
    public void update(){}
}
