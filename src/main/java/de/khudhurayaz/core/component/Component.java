package de.khudhurayaz.core.component;

import de.khudhurayaz.core.entity.Entity;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Component extends Entity {

    private Entity entity;
    private static final Logger COMPONENT_LOGGER = Logger.getLogger(Component.class.getSimpleName());

    public Component(Entity entity) {
        this.entity = entity;
        COMPONENT_LOGGER.log(
                Level.INFO,
                "A new Animation Component "
                + "for {" + entity.getName() + "} "
                + "has been added!"
        );
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
