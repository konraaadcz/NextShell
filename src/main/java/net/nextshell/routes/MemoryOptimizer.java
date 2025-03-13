package main.java.net.nextshell.routes;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryOptimizer {

    private Timer memoryOptimizationTimer;

    public MemoryOptimizer() {

        memoryOptimizationTimer = new Timer();
        memoryOptimizationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                optimizeMemoryUsage();
            }
        }, 0, 120000);
    }




    public void optimizeMemoryUsage() {

        System.gc();

    }
}
