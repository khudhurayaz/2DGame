package de.khudhurayaz.core.object;

import de.khudhurayaz.core.util.LoadImage;

import java.io.IOException;
import java.util.logging.Logger;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest() {
        super("Chest");
    }

    @Override
    public void init() {
        logger = Logger.getLogger(OBJ_Door.class.getSimpleName());
        try {
            setImage(LoadImage.loadImage("chest.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
