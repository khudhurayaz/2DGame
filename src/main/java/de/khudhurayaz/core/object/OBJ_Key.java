package de.khudhurayaz.core.object;

import de.khudhurayaz.core.LoadImage;

import java.io.IOException;
import java.util.logging.Logger;

public class OBJ_Key extends SuperObject{

    public OBJ_Key() {
        super("Key");
    }

    @Override
    public void init() {
        logger = Logger.getLogger(OBJ_Key.class.getSimpleName());
        try {
            setImage(LoadImage.loadImage("key.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
