package de.khudhurayaz.core.tile;

public enum ETiles {
    WATER_00("water00", 0), //Number 00
    WATER_01("water01", 1), //Number 01
    WATER_02("water02", 2), //Number 02
    WATER_03("water03", 3), //Number 03
    WATER_04("water04", 4), //Number 04
    WATER_05("water05", 5), //Number 05
    WATER_06("water06", 6), //Number 06
    WATER_07("water07", 7), //Number 07
    WATER_08("water08", 8), //Number 08
    WATER_09("water09", 9), //Number 09
    WATER_10("water10", 10), //Number 10
    WATER_11("water11", 11), //Number 11
    WATER_12("water12", 12), //Number 12
    WATER_13("water13", 13), //Number 13
    GRASS_00("grass00", 14), //Number 14
    GRASS_01("grass01", 15), //Number 15
    ROAD_00("road00", 16), //Number 16
    ROAD_01("road01", 17), //Number 17
    ROAD_02("road02", 18), //Number 18
    ROAD_03("road03", 19), //Number 19
    ROAD_04("road04", 20), //Number 20
    ROAD_05("road05", 21), //Number 21
    ROAD_06("road06", 22), //Number 22
    ROAD_07("road07", 23), //Number 23
    ROAD_08("road08", 24), //Number 24
    ROAD_09("road09", 25), //Number 25
    ROAD_10("road10", 26), //Number 26
    ROAD_11("road11", 27), //Number 27
    ROAD_12("road12", 28), //Number 28
    WALL("wall", 29), //Number 29
    EARTH("earth", 30), //Number 30
    FLOOR_01("floor01", 31), //Number 31
    HUT("hut", 32), //Number 32
    TABLE_01("table01", 33), //Number 33
    TREE("tree", 34), //Number 34
    ;

    private final String image_path;
    private int tile_num;
    ETiles(String image_path, int tile_num){
        this.image_path = image_path + ".png";
        this.tile_num = tile_num;
    }

    public int getTileNumber(){
        return tile_num;
    }
    public static int size(){
        return 34;
    }
    public String getImagePath(){
        return image_path;
    }
}

