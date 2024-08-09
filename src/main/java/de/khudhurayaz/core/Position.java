package de.khudhurayaz.core;

/**
 * Position of Entity.
 * @since 19.07.2024
 * @version 1
 * @author Ayaz Khudhur
 */
public class Position {
    private int x, y, width, height;

    public Position(){
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public Position(Position otherPosition){
        this.x = otherPosition.x;
        this.y = otherPosition.y;
        this.width = otherPosition.width;
        this.height = otherPosition.height;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
    }

    public Position(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
