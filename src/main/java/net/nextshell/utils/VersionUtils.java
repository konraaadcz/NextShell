package main.java.net.nextshell.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.File;

public class VersionUtils {
    public static String getVersion() {
        try {
            File versionFile = new File("src/main/java/net/nextshell/config/version.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(versionFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("version");
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getElementsByTagName("number").item(0).getTextContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}
