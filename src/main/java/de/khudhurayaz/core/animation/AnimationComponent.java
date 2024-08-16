package de.khudhurayaz.core.animation;

import de.khudhurayaz.core.component.Component;
import de.khudhurayaz.core.entity.Entity;

import java.awt.image.BufferedImage;

public class AnimationComponent extends Component {

    private Animation idle, idleUp, idleDown, idleLeft, idleRight;

    public AnimationComponent(Entity entity, Animation idleUp, Animation idleDown, Animation idleRight, Animation idleLeft) {
        super(entity);
        this.idleUp = idleUp;
        this.idleDown = idleDown;
        this.idleRight = idleRight;
        this.idleLeft = idleLeft;
    }

    public AnimationComponent(Entity entity, Animation idle) {
        super(entity);
        this.idle = idle;
    }

    public BufferedImage getAnimationUp(int index) {
        return idleUp.getAnimation(index);
    }

    public BufferedImage getAnimationDown(int index) {
        return idleDown.getAnimation(index);
    }

    public BufferedImage getAnimationLeft(int index) {
        return idleLeft.getAnimation(index);
    }

    public BufferedImage getAnimationRight(int index) {
        return idleRight.getAnimation(index);
    }

    public BufferedImage getAnimation(int index) {
        return idle.getAnimation(index);
    }

    public void setAnimationLeft(Animation idleLeft) {
        this.idleLeft = idleLeft;
    }

    public void setAnimationIndex(int index) {
        if (idle != null) {
            idle.setAnimationIndex(index);
        }
        if (idleLeft != null) {
            idleLeft.setAnimationIndex(index);
        }
        if (idleRight != null) {
            idleRight.setAnimationIndex(index);
        }
        if (idleDown != null) {
            idleDown.setAnimationIndex(index);
        }
        if (idleUp != null) {
            idleUp.setAnimationIndex(index);
        }
    }

    public void setAnimationSpeed(int speed) {
        if (idle != null) {
            idle.setSpeed(speed);
        }
        if (idleLeft != null) {
            idleLeft.setSpeed(speed);
        }
        if (idleRight != null) {
            idleRight.setSpeed(speed);
        }
        if (idleDown != null) {
            idleDown.setSpeed(speed);
        }
        if (idleUp != null) {
            idleUp.setSpeed(speed);
        }
    }

    @Override
    public void update() {
        if (idleUp != null) {
            idleUp.update();
        }
        if (idleDown != null) {
            idleDown.update();
        }
        if (idleLeft != null) {
            idleLeft.update();
        }
        if (idleRight != null) {
            idleRight.update();
        }
        if (idle != null) {
            idle.update();
        }

    }
}
