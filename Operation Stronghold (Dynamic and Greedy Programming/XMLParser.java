import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {

    public static Map<String, Malware> parse(String filename) {

        HashMap<String,Malware> malwareDBx= new HashMap<>();
        DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dBfactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse(new File(filename));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("row");

        for (int i = 0; i < nList.getLength(); i++)
        {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String hash = element.getElementsByTagName("hash").item(0).getTextContent();
                String title = element.getElementsByTagName("title").item(0).getTextContent();
                int level = Integer.parseInt(element.getElementsByTagName("level").item(0).getTextContent());
                Malware malware = new Malware(title,level,hash);
                malwareDBx.put(hash,malware);
            }
        }
        return malwareDBx;
    }
}
