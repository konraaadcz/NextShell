package main.java.net.nextshell.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.File;

public class Version {


    public static String getVersion() {


        try {

            File versionFile = new File("src/main/java/net/nextshell/config/version.xml");


            DocumentBuilderFactory frog = DocumentBuilderFactory.newInstance();


            DocumentBuilder builder = frog.newDocumentBuilder();


            Document k = builder.parse(versionFile);


            k.getDocumentElement().normalize();


            NodeList nodeList = k.getElementsByTagName("version");


            Node node = nodeList.item(0);


            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element et = (Element) node;
                return et.getElementsByTagName("number").item(0).getTextContent();
            }


        } catch (Exception ex) {

        }

        return "Unknown";
    }
}
