package application;

import enumeration.Categories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListesObservees {

	static ObservableList<String> categorie = FXCollections.observableArrayList();

	public ListesObservees() {
		super();
		ListesObservees.categorie = categorie;

		//	initCategorie(categorie);
	}

	public static ObservableList<String> initCategorie(ObservableList<String> categorie2) {

				categorie.clear();

				for (Categories item:Categories.values()) {
					//System.out.println( "item = "+item.getCatDepense());
					categorie.add(item.getCatDepense());



				}
				return categorie;

	}


}
