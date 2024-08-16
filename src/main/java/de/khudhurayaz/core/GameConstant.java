package de.khudhurayaz.core;

public abstract class GameConstant {
    //SCREEN SETTING
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 4;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    //Output
    public static final String OUTPUT_PATH = "src/main/resources";
    //Image settings
    public static final String IMAGE_PATH = "/images/";
    public static final String OBJECT_PATH = IMAGE_PATH + "Object/";
    public static final String PLAYER_IMGAE = IMAGE_PATH + "player/";
    public static final String TILES_IMAGE = IMAGE_PATH + "tiles/";

    //Map setting
    public static final String MAP_PATH = "/map/";
    public static final String LEVEL_PATH = MAP_PATH + "level/";

    //Sound
    public static final String SOUND_PATH = "/sounds/";

    //World Settings
    public static final int MAX_WORLD_COLUMN = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

}
