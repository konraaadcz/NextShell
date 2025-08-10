package main.java.net.nextshell;

import main.java.net.nextshell.frame.Frame;
import main.java.net.nextshell.routes.MO;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Frame sziauram = new Frame();
            sziauram.setVisible(true);


            MO om = new MO();
            om.optimizeMemoryUsage();;
        });
    }
}