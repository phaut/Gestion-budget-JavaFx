package application;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class categorieXML {
	static org.jdom2.Document document;
	static Element racine;
	static Element courant =null;
	private static List<Element> Liste;
	static ObservableList<String> categorieListeDepense = FXCollections.observableArrayList();
	private static List<Element> ListeCat;
	static String Str;
	static Element element;
	private static List<Element> listeDepenses;
	private String Categorie;
	static ObservableList<String> categorieDepense = FXCollections.observableArrayList();

	public categorieXML(String categorie) {
		// TODO Auto-generated method stub
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();

		this.Categorie =categorie;
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			//Le parsing est terminé ;)
			//document= sxb.build(new File("/Users/philippehautot/eclipse-workspace/javafxEssaiList/src/xml/CatDepenses2.xml"));
			document= sxb.build(new  File("./src/xml/CatDepenses2.xml"));
			sxb.setIgnoringElementContentWhitespace(true);
		}
		catch(Exception e){}
		//On initialise un nouvel élément racine avec l'élément racine du document.
		racine =document.getRootElement();
		//System.out.println(racine.getName());
		//System.out.println("this.Categorie "+this.Categorie);
		//Str ="Autres dépenses";

		//listeDepenses=racine.getChildren("dépenses");
		listeDepenses=racine.getChildren(this.Categorie);
		retourDepenses();
		//	afficheALL();

		//	afficheFiltre();

	}
	private  void retourDepenses() {
		// TODO Auto-generated method stub
		categorieDepense.clear();
		for (Element elDepense:listeDepenses) {

			String nomCatDepense=elDepense.getChild("nomcats").getAttributeValue("classe");
			categorieDepense.add(nomCatDepense);

		}

	}
	public static void afficheALL(String str)
	{
		//System.out.println( " el.getTextTrim  ");
		categorieListeDepense.clear();
		//Str ="Autres dépenses";
		Str =str;

		//	Str ="Frais automobile";
		ElementFilter filterNom = new ElementFilter("nomcats");
		//listeDepenses=racine.getChildren("dépenses");

		Iterator<Element> iN = racine.getDescendants(filterNom);
		while (iN.hasNext()) {
			element = iN.next();

			if(Str.matches(element.getAttributeValue("classe"))) {

				Liste = element.getChildren("cat");

			}
		}

		for (Element el:Liste ) {
			categorieListeDepense.add(el.getTextTrim());
		}
		//************************************
	}
	public static synchronized final List<Element> getListeCat() {
		return ListeCat;
	}
	public static synchronized final void setListeCat(List<Element> listeCat) {
		ListeCat = listeCat;
	}
	public static synchronized final List<Element> getListeDepenses() {
		return listeDepenses;
	}
	public static synchronized final void setListeDepenses(List<Element> listeDepenses) {
		categorieXML.listeDepenses = listeDepenses;
	}
	public static synchronized final ObservableList<String> getCategorieDepense() {
		return categorieDepense;
	}
	public synchronized final static void setCategorieDepense(ObservableList<String> categorieDepense) {
		categorieXML.categorieDepense = categorieDepense;
	}
	public static synchronized final ObservableList<String> getCategorieListeDepense() {
		return categorieListeDepense;
	}
	public static synchronized final void setCategorieListeDepense(ObservableList<String> categorieListeDepense) {
		categorieXML.categorieListeDepense = categorieListeDepense;
	}

}
