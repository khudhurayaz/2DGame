package de.khudhurayaz.core.object;

import de.khudhurayaz.core.util.LoadImage;

import java.io.IOException;
import java.util.logging.Logger;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots() {
        super("Boots");
    }

    @Override
    public void init() {
        logger = Logger.getLogger(OBJ_Boots.class.getSimpleName());
        try {
            setImage(LoadImage.loadImage("boots.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
