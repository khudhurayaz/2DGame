package de.khudhurayaz.core.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean up, down, left, right;
    private boolean isDebug;
    private boolean f1, f2, f3;

    public boolean UP(){
        return up;
    }

    public boolean DOWN(){
        return down;
    }

    public boolean LEFT(){
        return left;
    }

    public boolean RIGHT(){
        return right;
    }

    public boolean IS_DEBUG(){
        return isDebug;
    }

    public boolean STOP_BACKGROUND_SOUND(){
        return f1;
    }

    public boolean VOLUME_DOWN(){
        return f2;
    }

    public boolean VOLUME_UP(){
        return f3;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
            case KeyEvent.VK_F11 -> isDebug = !isDebug;
            case KeyEvent.VK_F1 -> f1 = true;
            case KeyEvent.VK_F2 -> f2 = true;
            case KeyEvent.VK_F3 -> f3 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
            case KeyEvent.VK_F1 -> f1 = false;
            case KeyEvent.VK_F2 -> f2 = false;
            case KeyEvent.VK_F3 -> f3 = false;
        }
    }
}
