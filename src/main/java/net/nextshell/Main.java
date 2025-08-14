package main.java.net.nextshell;





import javax.swing.SwingUtilities;
import main.java.net.nextshell.visual.Frame;
import main.java.net.nextshell.modules.MO;




public class Main {
    public static void main(String[] args) {


        

        SwingUtilities.invokeLater(() -> {
            

            new Thread(() -> {
                try {

                    
                    Thread.sleep(3500);
                } catch (InterruptedException f) {


                    
                    
                }

                SwingUtilities.invokeLater(() -> {




                    
                    Frame sziauram = new Frame();
                    sziauram.setVisible(true);

                    MO om = new MO();
                    om.optimizeMemoryUsage();





                    
                });

            }).start();

        });
    }
}
