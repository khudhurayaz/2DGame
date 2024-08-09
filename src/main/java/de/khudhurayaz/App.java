package de.khudhurayaz;

import de.khudhurayaz.core.Window;

public class App {
    public static GamePanel panel;
    public static Window window;

    public static void main(String[] args) {
        panel = new GamePanel();
        window = new Window(panel);
    }
}