package de.khudhurayaz.core;


import de.khudhurayaz.App;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameLoop implements Runnable{

    private static final int FPS_SET = 64;
    private static final int UPS_SET = 200;
    private int fps, update;
    private boolean running;
    private Thread gameThread;
    private boolean pause = false;
    private static final Logger GL_LOGGER = Logger.getLogger(GameLoop.class.getSimpleName());

    public void start(){
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public void stop() throws InterruptedException {
        gameThread.join();
        running = false;
    }

    public void pause(boolean pause){
        if (!pause){
            running = false;
            GL_LOGGER.log(Level.INFO, "Spiel wird pausiert!");
            try {
                stop();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }else{
            if (this.pause){
                GL_LOGGER.log(Level.INFO, "Spiel wird fortgesetzt!");
                if (!gameThread.isAlive()){
                    start();
                }
            }
            this.pause = true;
        }
    }

    private void gameLoop(){
        //Frame time and update time
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        double deltaU = 0; //delta update
        double deltaF = 0; //delta frames
        int frames = 0; // Frames
        int updates = 0; // Updates
        long lastCheck = 0;

        while (running){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                App.panel.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                App.panel.repaintScreen();
                frames++;
                deltaF--;
            }

            if (System.nanoTime() - lastCheck >= 1_000_000_000){
                lastCheck = System.nanoTime();
                fps = frames;
                update = updates;
                //GL_LOGGER.log(Level.INFO, "FRAMES: " + frames + ", UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public int getFPS(){
        return fps;
    }

    public int getUPS() {
        return update;
    }

    public boolean isRunning(){
        return running;
    }

    @Override
    public void run() {
        gameLoop();
    }
}
