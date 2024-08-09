package de.khudhurayaz.core;

import de.khudhurayaz.App;
import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.entity.Entity;
import de.khudhurayaz.core.entity.Player;
import de.khudhurayaz.core.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI extends Entity {

    private String message;
    private boolean messageOn;
    private int messageCounter;
    private GamePanel panel;
    private BufferedImage key;
    private Player player;
    private Font font_30 = new Font("Arial", Font.PLAIN, 30);

    public UI(GamePanel panel) {
        super("UI");
        this.panel = panel;
        OBJ_Key objKey = new OBJ_Key();
        objKey.init();
        key = objKey.getImage();
        player = panel.getPlayer();
    }

    @Override
    public void update() {

    }

    public void showMessage(String msg){
        this.message = msg;
        this.messageOn = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(font_30);
        g2d.setColor(Color.WHITE);
        g2d.drawString("FPS: " + panel.getFPS(), panel.getWidth() - 250, 50);
        g2d.drawString("UPS: " + panel.getUPS(), panel.getWidth() - 140, 50);
        if (player != null){
            g2d.drawImage(key, GameConstant.TILE_SIZE/2, GameConstant.TILE_SIZE/2, GameConstant.TILE_SIZE/2, GameConstant.TILE_SIZE/2, null);
            g2d.drawString(" x " + player.getKeys(), 60, GameConstant.TILE_SIZE);
        }

        if (messageOn){
            g2d.drawString(message, 50, 150);

            messageCounter++;
            if (messageCounter >= 120){
                messageCounter = 0;
                messageOn = false;
            }
        }

        //check if pressed F1
        if (App.panel.kHandler.IS_DEBUG()){
            g2d.setColor(Color.red);
            g2d.drawRect(
                    player.solidArea.x + player.playerPosition.getX(),
                    player.solidArea.y + player.playerPosition.getY(),
                    player.solidArea.width, player.solidArea.height);
        }
    }
}
