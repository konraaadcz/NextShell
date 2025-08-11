

package main.java.net.nextshell;



import javax.swing.SwingUtilities;

import main.java.net.nextshell.visual.Frame;
import main.java.net.nextshell.visual.LS;
import main.java.net.nextshell.modules.MO;




public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LS nemsziauram = new LS();


            nemsziauram.showSplash();

            new Thread(() -> {


                try {

                    Thread.sleep(13000);


                } catch (InterruptedException f) {
                    
                    
                }



                SwingUtilities.invokeLater(() -> {

                    nemsziauram.end();

                    Frame sziauram = new Frame();
                    sziauram.setVisible(true);

                    MO om = new MO();
                    om.optimizeMemoryUsage();


                });


            }).start();


        });
    }
}
