package application;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.scene.Scene;
import javafx.scene.control.ListView;


public class ListviewXml {

	String filePath= "src/xml/CatDepenses2.xml";

	 public static ListView<String> loadDepensesListView(String filePath) throws Exception {
	        ListView<String> listView = new ListView<>();
	        ObservableList<String> depensesItems = FXCollections.observableArrayList();

	        // Charger le fichier XML
	        File file = new File(filePath);
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(file);

	        // Parcourir les éléments dépenses
	        NodeList depensesList = document.getElementsByTagName("dépenses");
	        for (int i = 0; i < depensesList.getLength(); i++) {
	            Node depensesNode = depensesList.item(i);
	            if (depensesNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element depensesElement = (Element) depensesNode;

	                // Parcourir les éléments cat
	                NodeList catList = depensesElement.getElementsByTagName("cat");
	                for (int j = 0; j < catList.getLength(); j++) {
	                    Node catNode = catList.item(j);
	                    if (catNode.getNodeType() == Node.ELEMENT_NODE) {
	                        Element catElement = (Element) catNode;
	                        // Ajouter le contenu de cat à la liste observable
	                        depensesItems.add(catElement.getTextContent());
	                    }
	                }
	            }
	        }

	        //************************************
	     // Parcourir les éléments transferts
	        NodeList depensesListT = document.getElementsByTagName("transferts");
	        for (int i = 0; i < depensesListT.getLength(); i++) {
	            Node transfertsNode = depensesListT.item(i);
	            if (transfertsNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element transfertsElement = (Element) transfertsNode;

	                // Parcourir les éléments cat
	                NodeList catList = transfertsElement.getElementsByTagName("cat");
	                for (int j = 0; j < catList.getLength(); j++) {
	                    Node catNode = catList.item(j);
	                    if (catNode.getNodeType() == Node.ELEMENT_NODE) {
	                        Element catElement = (Element) catNode;
	                        // Ajouter le contenu de cat à la liste observable
	                       depensesItems.add(catElement.getTextContent());
	                    }
	                }
	            }
	        }



	        //***********************************

	        // Initialiser la ListView avec les éléments de la liste observable
	        listView.setItems(depensesItems);

	     // Initialiser la ListView avec les éléments de la liste observable
	      //  listView.setItems(depensesItems);

	      //  System.out.println("Contenu de la ListView :");
	        for (String item : listView.getItems()) {
	         //   System.out.println(item);
	        }

	        return listView;
	    }
}
