package de.khudhurayaz.core;

import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.object.OBJ_Boots;
import de.khudhurayaz.core.object.OBJ_Chest;
import de.khudhurayaz.core.object.OBJ_Door;
import de.khudhurayaz.core.object.OBJ_Key;

public class AssetSetter {
    private GamePanel panel;
    private final int TILE_SIZE = GameConstant.TILE_SIZE;

    public AssetSetter(GamePanel pa){
        this.panel = pa;
    }

    public void setObject(){
        panel.objects[0] = new OBJ_Key();
        panel.objects[0].worldPosition = new Position(26*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[1] = new OBJ_Key();
        panel.objects[1].worldPosition = new Position(27*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[2] = new OBJ_Key();
        panel.objects[2].worldPosition = new Position(28*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[3] = new OBJ_Door();
        panel.objects[3].worldPosition = new Position(29*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[4] = new OBJ_Door();
        panel.objects[4].worldPosition = new Position(30*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[5] = new OBJ_Door();
        panel.objects[5].worldPosition = new Position(31*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[6] = new OBJ_Chest();
        panel.objects[6].worldPosition = new Position(35*TILE_SIZE, 15*TILE_SIZE);
        panel.objects[7] = new OBJ_Boots();
        panel.objects[7].worldPosition = new Position(25*TILE_SIZE, 15*TILE_SIZE);
    }
}
