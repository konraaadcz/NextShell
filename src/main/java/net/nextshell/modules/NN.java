package main.java.net.nextshell.modules;




import java.awt.*;
import java.net.URI;





public class NN {





    private static final String w = "https://github.com/konraaadcz/NextShell/releases";






    public static void showNotification() throws Exception {
        SystemTray d = SystemTray.getSystemTray();
        TrayIcon hate = new TrayIcon(new java.awt.image.BufferedImage(1,1,java.awt.image.BufferedImage.TYPE_INT_ARGB));




      


        hate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent p) {
                try { Desktop.getDesktop().browse(new URI(w)); } catch (Exception a) {}
            }
        });



      



        d.add(hate);
        hate.displayMessage("NextShell Terminal", "Install the latest version by clicking on this notification", TrayIcon.MessageType.INFO);
    }
}
