package com.example.l902;

import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TeatteriLista {
    private int check = 0;
    private List<String> teatterilista = null;
    private static TeatteriLista tl= new TeatteriLista();
    public static TeatteriLista getInstance(){
        return tl;
    }
    public List lueXML() {

        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String teatteritUrl = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(teatteritUrl);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");
            List<Teatteri> nimilista = new ArrayList<>();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("Element" + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.print("ID on: ");
                    System.out.println(element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.print("Teatterin nimi on: ");
                    System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());
                    Teatteri theater = new Teatteri(element.getElementsByTagName("ID").item(0).getTextContent(), element.getElementsByTagName("Name").item(0).getTextContent());
                    nimilista.add(theater);
                }
            }

            List<String> teatterilista = new ArrayList<>();
            String nimi;
            check = 0;
            for (int j = 0; j < nimilista.size(); j++) {
                check++;
                nimi = nimilista.get(j).getName();
                if ((check > 2) && (nimilista.get(j).getName().contains(":"))) {
                    teatterilista.add(nimi);
                }
            }
            return teatterilista;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("*********tehty*******");
        }
        return teatterilista;
    }
}
