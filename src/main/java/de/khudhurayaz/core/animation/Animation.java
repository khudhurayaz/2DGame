package de.khudhurayaz.core.animation;

import java.awt.image.BufferedImage;

public class Animation extends SpriteSheet{

    public Animation(String path, boolean animation) {
        super(path, animation);
    }

    public Animation(String path, int width, int height, int animationTick, boolean animation) {
        super(path, width, height, animationTick, animation);
    }

}
