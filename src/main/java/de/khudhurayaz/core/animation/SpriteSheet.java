package de.khudhurayaz.core.animation;

import de.khudhurayaz.core.GameConstant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    private int animationTick, animationIndex, speed = 30, width, height;
    private int aniTick, aniSpeed = 13, aniIndex;
    private final String path;
    private BufferedImage[] images;
    private boolean animation;

    public SpriteSheet(String path, boolean animation) {
        this.path = path;
        this.animation = animation;
        this.width = 0;
        this.height = 0;
        this.aniTick = 0;
        init();
    }

    public SpriteSheet(String path, int width, int height, int animationTick, boolean animation) {
        this.path = path;
        this.width = width;
        this.height = height;
        this.animation = animation;
        this.aniTick = animationTick;
        init();
    }

    private void init(){
        loadDirection();
    }

    private BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    private void loadDirection(){
        File[] files = new File(GameConstant.OUTPUT_PATH + GameConstant.IMAGE_PATH + path).listFiles();

        if (files != null) {
            images = new BufferedImage[files.length];
            for (int i = 0; i < files.length; i++) {
                try {
                    images[i] = ImageIO.read(files[i].getAbsoluteFile());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void update() {
        aniTick++;
        if (aniTick >= getSpeed()) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= size()) {
                aniIndex = 0;
            }
        }
    }

    public BufferedImage getAnimation(int index) {
        if (index >= size()) return null;
        return images[index];
    }

    public BufferedImage[] getFrames() {
        return images;
    }

    public int size(){
        return images.length;
    }



    public BufferedImage get(int index) {
        if (index > images.length) {
            return null;
        }
        return images[index];
    }
    public int getAnimationIndex() {
        return aniIndex;
    }
    public void setAnimationIndex(int animationIndex) {
        this.aniIndex = animationIndex;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
