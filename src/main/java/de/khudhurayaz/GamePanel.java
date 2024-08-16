package de.khudhurayaz;

import de.khudhurayaz.core.AssetSetter;
import de.khudhurayaz.core.UI;
import de.khudhurayaz.core.entity.Entity;
import de.khudhurayaz.core.entity.Player;
import de.khudhurayaz.core.GameLoop;
import de.khudhurayaz.core.collision.CollisionChecker;
import de.khudhurayaz.core.event.KeyHandler;
import de.khudhurayaz.core.object.SuperObject;
import de.khudhurayaz.core.sound.Sound;
import de.khudhurayaz.core.tile.TileManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static de.khudhurayaz.core.GameConstant.*;

public class GamePanel extends JPanel {

    /** get TileManager Entity */
    public static final int TILE_MANAGER_ENTITIES = 0;
    /** get Object Entity */
    public static final int OBJECT_ENTITIES = 1;
    /** get all entity */
    public static final int ENTITIES = 2;

    //Entities
    private ArrayList<Entity> tileEntities;
    private ArrayList<Entity> objectEntites;
    private ArrayList<Entity> entities;
    private GameLoop gameLoop;
    private Sound backgroundSound, sound;
    private Player player;

    //Sound
    private boolean backgroundSoundFirstTime = true;

    //Event
    public KeyHandler kHandler;

    //Collision
    public CollisionChecker collisionChecker;

    //Super Objects
    public SuperObject[] objects;

    //Assets
    public AssetSetter assetSetter;

    //UI
    private UI ui;

    //Logger
    private static final Logger LOGGER_GAME_PANEL = Logger.getLogger(GamePanel.class.getSimpleName());

    /**
     * Game initialisierung.
     */
    public GamePanel(){
        init();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.addKeyListener(kHandler);
        this.setFocusable(true);
        setupGame();
    }

    /*
        Alle initialisierung werden hier initialisiert.
     */
    private void init() {
        tileEntities = new ArrayList<>();
        entities = new ArrayList<>();
        objectEntites = new ArrayList<>();
        kHandler = new KeyHandler();
        collisionChecker = new CollisionChecker();
        gameLoop = new GameLoop();
        sound = new Sound();
        backgroundSound = new Sound();
        objects = new SuperObject[10];
        assetSetter = new AssetSetter(this);
        assetSetter.setObject();
        for (SuperObject so : objects){
            if (so != null){
                so.init();
            }
        }

        ui = new UI(this);
        addingEntity();
    }

    //Nach der initialisierung können weitere setup vorgenommen werden.
    private void setupGame(){
        //Background Musik einschalten
        playBackgroundSound(Sound.BACKGROUND_SOUND);
    }

    /*
        Entity hinzufügen
     */
    private void addingEntity(){
        //Tiles
        tileEntities.add(new TileManager());

        //Objects
        for (SuperObject so : objects){
            if (so != null){
                objectEntites.add(so);
            }
        }

        //Player etc.
        player = new Player();
        entities.add(player);
        entities.add(ui);
    }

    public void addEntity(Entity entity, int entityNumber){
        if (entity == null) {
            LOGGER_GAME_PANEL.log(Level.WARNING, "Der Entity ist NULL oder wurde noch nicht initialisiert!");
            return;
        }

        //TileManager
        if (entityNumber == 0){
            tileEntities.add(entity);
        }

        //Objects
        if (entityNumber == 1){
            objectEntites.add(entity);
        }

        //All other Entity
        if (entityNumber == 2){
            entities.add(entity);
        }
    }

    /**
     * Entity zurückliefern.
     * @param classEntity Entity zurückliefern.
     * @param ent Entity Type
     * @return Gespeichertes Entity wird zurückgeliefert.
     */
    public Entity getEntity(Class<? extends Entity> classEntity, int ent){
        if (ent == 0){
            for (Entity e : tileEntities){
                if (e.getClass() == classEntity)
                    return e;
            }
        }
        if (ent == 1){
            for (Entity e : objectEntites){
                if (e.getClass() == classEntity)
                    return e;
            }
        }
        if (ent == 2){
            for (Entity e : entities){
                if (e.getClass() == classEntity)
                    return e;
            }
        }
        return null;
    }

    /**
     * Entity löschen.
     * @param entity Entity die gelöscht werden soll.
     * @param ent Entity Gruppe eingeben.
     */
    public void removeEntity(Entity entity, int ent){
        if (entity !=  null) {
            if (ent == TILE_MANAGER_ENTITIES) {
                if (tileEntities != null)
                    tileEntities.remove(entity);
            }
            if (ent == OBJECT_ENTITIES) {
                if (objectEntites != null)
                    objectEntites.remove(entity);
            }
            if (ent == ENTITIES) {
                if (entities != null)
                    entities.remove(entity);
            }
        }
    }

    /**
     * Alle Entity die Update methode geerbt haben
     * aufgerufen.
     */
    public void update(){
        for (Entity tile : tileEntities){
            if (tile != null)
                tile.update();
        }

        for (Entity object : objectEntites){
            if (object != null)
                object.update();
        }

        for (Entity entity : entities){
            if (entity != null){
                entity.update();
            }
        }


        //check key input
        if (kHandler.STOP_BACKGROUND_SOUND()){
            if (backgroundSoundFirstTime){
                backgroundSound.stop();
                System.out.println("Stopped!");
            }else{
                backgroundSound.setFile(Sound.BACKGROUND_SOUND);
                backgroundSound.play();
                backgroundSound.loop();
                System.out.println("Play again!");
            }
            backgroundSoundFirstTime = !backgroundSoundFirstTime;
        }

        if (kHandler.VOLUME_DOWN()){
            volumeDown();
        }

        if (kHandler.VOLUME_UP()){
            volumeUP();
        }
    }

    public void repaintScreen(){
        Graphics g = App.window.getStrategy().getDrawGraphics();
        paintComponent(g);
        g.dispose();
        App.window.getStrategy().show();
    }

    public void paintComponent(Graphics g) {
        //Clear
        g.fillRect(0,0,getWidth(), getHeight());

        for (Entity tile : tileEntities){
            if (tile != null)
                tile.draw((Graphics2D) g);
        }

        for (Entity object : objectEntites){
            if (object != null)
                object.draw((Graphics2D) g);
        }

        for (Entity entity : entities){
            if (entity != null){
                entity.draw((Graphics2D) g);
            }
        }

        g.setColor(Color.white);
    }

    public void playBackgroundSound(int i){
        backgroundSound.setFile(i);
        backgroundSound.play();
        backgroundSound.loop();
    }

    public void volumeUP(){
        float up = backgroundSound.getVolume() + 1;
        backgroundSound.setVolume(up);
        System.out.println("Volume: " + backgroundSound.getVolume());
    }

    public void volumeDown(){
        float down = backgroundSound.getVolume() - 1;
        backgroundSound.setVolume(down);
        System.out.println("Volume: " + backgroundSound.getVolume());
    }

    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }

    public void stopSoundEffect(){
        sound.stop();
    }

    public void start(){
        gameLoop.start();
    }

    public void stop() throws InterruptedException {
        gameLoop.stop();
    }

    public void pause(boolean pause){
        gameLoop.pause(pause);
    }

    public int getFPS(){
        return gameLoop.getFPS();
    }
    public int getUPS(){
        return gameLoop.getUPS();
    }

    public UI UI(){
        return ui;
    }

    public Player getPlayer(){
        return player;
    }
}
