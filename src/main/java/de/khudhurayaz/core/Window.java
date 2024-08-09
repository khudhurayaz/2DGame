package de.khudhurayaz.core;

import de.khudhurayaz.GamePanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Window{

    private final JFrame frame;

    public Window(GamePanel panel){
        
        this.frame = new JFrame();
        this.frame.setTitle("Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.add(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                panel.pause(true);
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                panel.pause(false);
            }
        });

        panel.start();
    }
}
