/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author tifui
 */
public class FavoriteList {

    List<SongFile> files;

    public FavoriteList() {
        files = new ArrayList<>();
    }

    public List<SongFile> getFiles() {
        return files;
    }

    public void parseXML(File inputFile) throws SAXException, IOException, ParserConfigurationException {

        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        NodeList nList = doc.getElementsByTagName("song");
        files.clear();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                files.add(new SongFile(element.getAttribute("path")));
            }
        }
    }

    public void generateXML(File file) throws TransformerException, ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("AudioLibrary");
        doc.appendChild(rootElement);

        // staff elements
        Element list = doc.createElement("List");
        rootElement.appendChild(list);

        for (File curentFile : files) {
            Element song = doc.createElement("song");
            song.appendChild(doc.createTextNode(curentFile.getName()));
            song.setAttribute("path", curentFile.getAbsolutePath());
            list.appendChild(song);
        }
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);

    }

    @Override
    public String toString() {
        return "Favorite List";
    }

    public void addSongFile(File file) {
        SongFile song = new SongFile(file.getAbsolutePath());
        files.add(song);
    }

    public class SongFile extends File {

        public SongFile(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }
}
