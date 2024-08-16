package de.khudhurayaz.core.collision;

import de.khudhurayaz.App;
import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.entity.Entity;
import de.khudhurayaz.core.GameConstant;
import de.khudhurayaz.core.tile.TileManager;

/**
 * <h1>CollisionChecker</h1>
 *
 * <h3>checkTile - Methode</h3>
 * <p>
 *      Wenn der Spieler rand das Spieles oder welches Objekt das collision an hat.
 *      Wird mit <code>checkTile(Entity entity)</code>  geprüft
 *      und der entity collision auf wahr gesetzt
 * </p>
 *
 * @version 1.0.0.0
 * @author Ayaz Khudhur
 */
public class CollisionChecker {

    /**
     * Mit dieser Methode wird geprüft, ob der Spieler auf ein collisions Objekt trifft oder nicht.
     * @param entity Player Entity wird erwartet.
     */
    public void checkTile(Entity entity){
        GamePanel panel = App.panel;
        TileManager tileManager = (TileManager) panel.getEntity(TileManager.class, GamePanel.TILE_MANAGER_ENTITIES);

        //Coordinate
        float entityLeftWorldX = entity.worldPosition.getX() + entity.solidArea.x;
        float entityRightWorldX = entity.worldPosition.getX() + entity.solidArea.x + entity.solidArea.width;
        float entityTopWorldY = entity.worldPosition.getY() + entity.solidArea.y;
        float entityBottomWorldY = entity.worldPosition.getY() + entity.solidArea.y + entity.solidArea.height;
        //Spalte
        float entityLeftCol = entityLeftWorldX / GameConstant.TILE_SIZE;
        float entityRightCol = entityRightWorldX / GameConstant.TILE_SIZE;
        //Zeile
        float entityTopRow = entityTopWorldY / GameConstant.TILE_SIZE;
        float entityBottomRow = entityBottomWorldY / GameConstant.TILE_SIZE;

        float tileNum1, tileNum2;
        switch (entity.direction){
            case "up":
                entityTopRow = ((entityTopWorldY - entity.speed) - entity.solidArea.width/2f) / GameConstant.TILE_SIZE;
                tileNum1 = tileManager.mapTileNumber[(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = tileManager.mapTileNumber[(int)entityRightCol][(int)entityTopRow];
                if(tileManager.tiles[(int)tileNum1].collision || tileManager.tiles[(int)tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = ((entityBottomWorldY - entity.speed)  + entity.solidArea.height/2f) / GameConstant.TILE_SIZE;
                tileNum1 = tileManager.mapTileNumber[(int)entityLeftCol][(int)entityBottomRow];
                tileNum2 = tileManager.mapTileNumber[(int)entityRightCol][(int)entityBottomRow];
                if(tileManager.tiles[(int)tileNum1].collision || tileManager.tiles[(int)tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = ((entityLeftWorldX - entity.speed / 2f) - entity.solidArea.width/2f) / GameConstant.TILE_SIZE;
                tileNum1 = tileManager.mapTileNumber[(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = tileManager.mapTileNumber[(int)entityLeftCol][(int)entityBottomRow];
                if(tileManager.tiles[(int)tileNum1].collision || tileManager.tiles[(int)tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = ((entityRightWorldX + entity.speed/2f) + entity.solidArea.width/2f) / GameConstant.TILE_SIZE;
                tileNum1 = tileManager.mapTileNumber[(int)entityRightCol][(int)entityTopRow];
                tileNum2 = tileManager.mapTileNumber[(int)entityRightCol][(int)entityBottomRow];
                if(tileManager.tiles[(int)tileNum1].collision || tileManager.tiles[(int)tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    /**
     * CheckObject
     * @param e Entity welches auf ein Objekt zutrifft.
     * @param player Ob es der Spieler ist.
     * @return der index von jeweiligem Objekt wird zurückgeworfen.
     */
    public int checkObject(Entity e, boolean player){
        GamePanel panel = App.panel;
        int index = 999;

        for (int i = 0; i < panel.objects.length; i++) {
            if (panel.objects[i] != null) {
                //Get entity solid position
                e.solidArea.x = e.worldPosition.getX() + e.solidArea.x;
                e.solidArea.y = e.worldPosition.getY() + e.solidArea.y;

                //Get the object solid area position
                panel.objects[i].solidArea.x = panel.objects[i].worldPosition.getX() + panel.objects[i].solidArea.x;
                panel.objects[i].solidArea.y = panel.objects[i].worldPosition.getY() + panel.objects[i].solidArea.y;

                switch (e.direction){
                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(panel.objects[i].solidArea)){
                            if (panel.objects[i].collisionOn){
                                e.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(panel.objects[i].solidArea)){
                            if (panel.objects[i].collisionOn){
                                e.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(panel.objects[i].solidArea)){
                            if (panel.objects[i].collisionOn){
                                e.collisionOn = true;
                            }

                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(panel.objects[i].solidArea)){
                            if (panel.objects[i].collisionOn){
                                e.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                }
                e.solidArea.x = e.solidAreaDefault.x;
                e.solidArea.y = e.solidAreaDefault.y;
                panel.objects[i].solidArea.x = panel.objects[i].solidAreaDefault.x;
                panel.objects[i].solidArea.y = panel.objects[i].solidAreaDefault.y;
            }
        }
        return index;
    }
}
