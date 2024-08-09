package de.khudhurayaz.core;

import de.khudhurayaz.core.tile.ETiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LoadImage {

    public static BufferedImage rotateImage(BufferedImage originalImage, double angle){
        double width = originalImage.getWidth();
        double height = originalImage.getHeight();

        BufferedImage rotatedImage = new BufferedImage((int) width, (int) height, originalImage.getType());

        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform transform = new AffineTransform();

        transform.rotate(Math.toRadians(angle), width /2, height/2);

        g2d.setTransform(transform);
        g2d.drawImage(originalImage, 0,0,null);
        g2d.dispose();
        return rotatedImage;
    }

    public static BufferedImage loadImage(String path) throws IOException{
        return ImageIO.read(Objects.requireNonNull(LoadImage.class.getResourceAsStream(GameConstant.OBJECT_PATH + path)));
    }

    public static BufferedImage loadImage(ETiles eTiles) throws IOException {
        return ImageIO.read(Objects.requireNonNull(LoadImage.class.getResourceAsStream(GameConstant.TILES_IMAGE + eTiles.getImagePath())));
    }

    public static ETiles getTile(int i){
        return switch (i) {
            case 0 -> ETiles.WATER_00;
            case 1 -> ETiles.WATER_01;
            case 2 -> ETiles.WATER_02;
            case 3 -> ETiles.WATER_03;
            case 4 -> ETiles.WATER_04;
            case 5 -> ETiles.WATER_05;
            case 6 -> ETiles.WATER_06;
            case 7 -> ETiles.WATER_07;
            case 8 -> ETiles.WATER_08;
            case 9 -> ETiles.WATER_09;
            case 10 -> ETiles.WATER_10;
            case 11 -> ETiles.WATER_11;
            case 12 -> ETiles.WATER_12;
            case 13 -> ETiles.WATER_13;
            case 14 -> ETiles.GRASS_00;
            case 15 -> ETiles.GRASS_01;
            case 16 -> ETiles.ROAD_00;
            case 17 -> ETiles.ROAD_01;
            case 18 -> ETiles.ROAD_02;
            case 19 -> ETiles.ROAD_03;
            case 20 -> ETiles.ROAD_04;
            case 21 -> ETiles.ROAD_05;
            case 22 -> ETiles.ROAD_06;
            case 23 -> ETiles.ROAD_07;
            case 24 -> ETiles.ROAD_08;
            case 25 -> ETiles.ROAD_09;
            case 26 -> ETiles.ROAD_10;
            case 27 -> ETiles.ROAD_11;
            case 28 -> ETiles.ROAD_12;
            case 29 -> ETiles.WALL;
            case 30 -> ETiles.EARTH;
            case 31 -> ETiles.FLOOR_01;
            case 32 -> ETiles.HUT;
            case 33 -> ETiles.TABLE_01;
            case 34 -> ETiles.TREE;
            default -> null;
        };
    }
}
