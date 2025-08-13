package main.java.net.nextshell.modules;





import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;





public class CL {



  



    private static final String LFP = "src/main/java/net/nextshell/data/log.txt";
    private static final DateTimeFormatter r = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");




  


    static {



      

        try {
            Path lf = Paths.get(LFP);
            if (!Files.exists(lf.getParent())) {
                Files.createDirectories(lf.getParent());
            }


          



            if (!Files.exists(lf)) {
                Files.createFile(lf);
            }


          

        } catch (IOException j) {




          
        }
    }


  


    public static void logCommand(String command) {
        String tmp = LocalDateTime.now().format(r);
        String ll = tmp + " - " + command + System.lineSeparator();




      

        try (FileWriter fw = new FileWriter(LFP, true)) {
            fw.write(ll);
            




          
        } catch (IOException j) {




        }
    }
}
