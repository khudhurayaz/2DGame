package de.khudhurayaz.core.animation;

public class AnimationBuilder {

    public static Animation buildAnimation(String path) {
        return new Animation(path, true);
    }

    public static Animation buildAnimation(String path, boolean loop) {
        return new Animation(path, loop);
    }

    public static Animation buildAnimation(String path, int width, int height, int animationTick, boolean animation) {
        return new Animation(path, width, height, animationTick, animation);
    }

}
