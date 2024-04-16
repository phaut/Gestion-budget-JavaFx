package depenses;

import application.categorieXML;
import javafx.collections.ObservableList;

public class autreDepense {

	public autreDepense() {


	}

	public static void choixDepense(String str, String strCategorie) {

		ObservableList<String> categorieDep;
		categorieXML.afficheALL(str);
		categorieDep =categorieXML.getCategorieListeDepense();
		application.categorieController.categorieLVreference.setItems(categorieDep);
		application.categorieController.categorieRetour = strCategorie;

	}

}
