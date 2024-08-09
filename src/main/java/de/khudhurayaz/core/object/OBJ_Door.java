package de.khudhurayaz.core.object;

import de.khudhurayaz.core.LoadImage;

import java.io.IOException;
import java.util.logging.Logger;

public class OBJ_Door extends SuperObject{
    public OBJ_Door() {
        super("Door");
    }

    @Override
    public void init() {
        logger = Logger.getLogger(OBJ_Door.class.getSimpleName());
        collisionOn = true;
        try {
            setImage(LoadImage.loadImage("door.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setImage(LoadImage.rotateImage(getImage(), 90));
    }
}
