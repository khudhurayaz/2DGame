package de.khudhurayaz.core;


import de.khudhurayaz.App;

public class GameLoop implements Runnable{

    private final int FPS_SET = 64;
    private final int UPS_SET = 200;
    private int fps, update;
    private boolean running;
    private Thread gameThread;

    public void start(){
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public void stop() throws InterruptedException {
        gameThread.join();
        running = false;
    }

    private boolean firstPause = false;
    public void pause(boolean pause){
        if (!pause){
            running = false;
            System.out.println("Das Spiel ist pausiert!");
            try {
                stop();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }else{
            if (firstPause){
                System.out.println("Das Spiel wird fortgesetzt!");
                if (!gameThread.isAlive()){
                    start();
                }
            }
            firstPause = true;
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
                App.panel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                fps = frames;
                update = updates;
                //System.out.println("FRAMES: " + frames + ", UPS: " + updates);
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
