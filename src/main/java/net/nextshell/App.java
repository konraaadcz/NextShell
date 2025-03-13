package main.java.net.nextshell;

import main.java.net.nextshell.frame.TerminalFrame;
import main.java.net.nextshell.routes.MemoryOptimizer;
import javax.swing.*;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TerminalFrame fr = new TerminalFrame();
            fr.setVisible(true);
            MemoryOptimizer o = new MemoryOptimizer();
            o.optimizeMemoryUsage();;
        });
    }
}