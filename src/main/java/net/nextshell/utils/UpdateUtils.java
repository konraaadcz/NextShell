package main.java.net.nextshell.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.File;

public class UpdateUtils {
    public static String getUpdates() {
        StringBuilder updates = new StringBuilder();
        try {
            File updatesFile = new File("src/main/java/net/nextshell/config/updates.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(updatesFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("update");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String date = element.getElementsByTagName("date").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    updates.append(date).append(": ").append(description).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updates.toString();
    }
}
