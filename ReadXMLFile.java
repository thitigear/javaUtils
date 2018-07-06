package com.gear.main.utils;

import java.awt.Color;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {

    private static String app_name;
    private static String path = "E:\\Hobby\\programming\\java\\IdeaProject\\";
    private static String endPath = "\\src\\com\\gear\\res\\values";

    public ReadXMLFile(String app_name) {
        this.app_name = app_name;
    }

    public static String getStringByStringName(String tag_name){
        String text = "";
        try {
            /*Define var*/
            File fXmlFile = new File(String.format("%s%s%s\\string.xml",path,app_name,endPath));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); // resource
            NodeList nList = doc.getElementsByTagName("string");

            System.out.println("----------------------------");

            for (int index = 0; index < nList.getLength(); index++) {
                Node nNode = nList.item(index);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Node Name :" + nNode.getNodeName());
                    System.out.println("Node Attribute : " + eElement.getAttribute("name"));
                    System.out.println("Node Value :" + nNode.getTextContent());
                    if(eElement.getAttribute("name").equals(tag_name)){
                        text = nNode.getTextContent();
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static Color getResourceColorByColorName(String color_name){
        try {
            /*Define var*/
            File fXmlFile = new File(String.format("%s%s%s\\colors.xml",path,app_name,endPath));
            DocumentBuilderFactory colorFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = colorFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); // resource
            NodeList nList = doc.getElementsByTagName("color");

            System.out.println("----------------------------");

            for (int index = 0; index < nList.getLength(); index++) {
                Node nNode = nList.item(index);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Node Name :" + nNode.getNodeName());
                    System.out.println("Node Attribute : " + eElement.getAttribute("name"));
                    System.out.println("Node Value :" + nNode.getTextContent());
                    if(eElement.getAttribute("name").equals(color_name)){
                        return Color.decode(nNode.getTextContent());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
