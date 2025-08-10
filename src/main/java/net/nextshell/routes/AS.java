package main.java.net.nextshell.routes;

import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class AS {


    private Timer inactivityTimer;
    private final long INACTIVITY_TIMEOUT = 600000;
    private long lastInteractionTime;
    private JFrame terminalFrame;





    public AS(JFrame terminalFrame) {
        this.terminalFrame = terminalFrame;
        this.lastInteractionTime = System.currentTimeMillis();
        this.inactivityTimer = new Timer();
        startInactivityMonitor();
    }



    private void startInactivityMonitor() {


        inactivityTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (System.currentTimeMillis() - lastInteractionTime >= INACTIVITY_TIMEOUT) {
                    shutdownApp();
                }

            }
        }, 0, 1000);
    }


    public void resetInactivityTimer() {
        lastInteractionTime = System.currentTimeMillis();
    }

    private void shutdownApp() {



        System.out.println("Automatic shutdown....(by the system)");
        terminalFrame.dispose();
        System.exit(0);
    }



    public void registerActivityListener() {


        terminalFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                resetInactivityTimer();
            }
        });


        terminalFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                resetInactivityTimer();
            }
        });


    }
}
