package de.khudhurayaz.core.tile;

import de.khudhurayaz.App;
import de.khudhurayaz.GamePanel;
import de.khudhurayaz.core.entity.Entity;
import de.khudhurayaz.core.entity.Player;
import de.khudhurayaz.core.GameConstant;
import de.khudhurayaz.core.LoadImage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends Entity {

    public Tile[] tiles;
    public int[][] mapTileNumber;
    private String map = GameConstant.MAP_PATH + "worldMap.txt";

    public TileManager(){
        super("TileManager");
        tiles = new Tile[40];
        mapTileNumber = new int[GameConstant.MAX_WORLD_COLUMN][GameConstant.MAX_WORLD_ROW];
        getTilesImage();
        loadMap();
    }

    public void getTilesImage(){
        try {
            //Water 00 - 13
            for (int i = 0; i <= ETiles.size(); i++) {
                tiles[i] = new Tile();
                if (LoadImage.getTile(i) != null){
                    tiles[i].eTiles = LoadImage.getTile(i);
                    tiles[i].tileImage = LoadImage.loadImage(LoadImage.getTile(i));
                }
            }
            for (int i = 0; i <= ETiles.size(); i++) {
                setCollision(i, "water", "wall", "tree");
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void setCollision(int i, String...param){
        for (String s : param){
            if (s.equalsIgnoreCase("water") && tiles[i].eTiles == ETiles.WATER_00){
                for (int j = 0; j <= 13; j++) {
                    tiles[j].collision = true;
                }
            } else if (s.equalsIgnoreCase("wall") && tiles[i].eTiles == ETiles.WALL){
                tiles[i].collision = true;
            } else if (s.equalsIgnoreCase("tree") && tiles[i].eTiles == ETiles.TREE) {
                tiles[i].collision = true;
            }
        }

        if (tiles[i].eTiles == ETiles.WATER_00){
            tiles[i].collision = true;
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < GameConstant.MAX_WORLD_COLUMN && row < GameConstant.MAX_WORLD_ROW){
                String line = reader.readLine();

                while(col < GameConstant.MAX_WORLD_COLUMN){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }

                if (col == GameConstant.MAX_WORLD_COLUMN){
                    col = 0;
                    row++;
                }
            }

            reader.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2d) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < GameConstant.MAX_WORLD_COLUMN && worldRow < GameConstant.MAX_WORLD_ROW){

            int tileNum = mapTileNumber[worldCol][worldRow];
            Player player = (Player) App.panel.getEntity(Player.class, GamePanel.ENTITIES);

            int worldX = worldCol * GameConstant.TILE_SIZE;
            int worldY = worldRow * GameConstant.TILE_SIZE;
            int screenX = worldX - player.worldPosition.getX() + player.playerPosition.getX();
            int screenY = worldY - player.worldPosition.getY() + player.playerPosition.getY();

            if (
                worldX + GameConstant.TILE_SIZE > player.worldPosition.getX() - player.playerPosition.getX() &&
                worldX - GameConstant.TILE_SIZE < player.worldPosition.getX() + player.playerPosition.getX() &&
                worldY + GameConstant.TILE_SIZE > player.worldPosition.getY() - player.playerPosition.getY() &&
                worldY - GameConstant.TILE_SIZE < player.worldPosition.getY() + player.playerPosition.getY()){
                g2d.drawImage(tiles[tileNum].tileImage, screenX, screenY, GameConstant.TILE_SIZE, GameConstant.TILE_SIZE, null);
            }

            worldCol++;

            if (worldCol == GameConstant.MAX_WORLD_COLUMN){
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
