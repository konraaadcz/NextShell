package main.java.net.nextshell.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.File;

public class Update {
    
    public static String getUpdates() {
        
        StringBuilder updates = new StringBuilder();

        
        try {
            
            
            File updatesFile = new File("src/main/java/net/nextshell/config/updates.xml");


            DocumentBuilderFactory frog = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = frog.newDocumentBuilder();

            Document m = builder.parse(updatesFile);

            m.getDocumentElement().normalize();

            NodeList ns = m.getElementsByTagName("update");
            


            for (int i = 0; i < ns.getLength(); i++) {


                Node node = ns.item(i);




                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element et = (Element) node;
                    String te = et.getElementsByTagName("date").item(0).getTextContent();
                    String d = et.getElementsByTagName("description").item(0).getTextContent();
                    updates.append(te).append(": ").append(d).append("\n");


                }
            }
        } catch (Exception xe) {

        }
        return updates.toString();
    }
}
