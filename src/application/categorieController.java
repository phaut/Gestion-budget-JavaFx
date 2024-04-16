package application;

import java.io.IOException;

import depenses.autreDepense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class categorieController {
	//ObservableList<String> categorieDepense = FXCollections.observableArrayList();
		String selectIndex="";
		static Stage categorieStage;
		static String choixCategorie;
		static String choixDepense ;

		public categorieController() {

			choixDepense="";
		}
		@FXML
		private Button btnRetour;

		@FXML
		void categorieRetour(ActionEvent event) {
			// categorie.add(item.getCatDepense());

			switch(categorieRetour) {

			case "categories":

				//categorie=ListesObservees.initCategorie(categorie);
				categorieLV.setItems(categorie);
				btnRetour.setVisible(false);
				/*	System.out.println("item ");
				for(String item:categorie) {
				System.out.println("item "+item);
				}*/
				break;
			case "depenses":

				//categorieXML catXMLdep =new categorieXML("dépenses");
				categorieDep =  categorieXML.getCategorieDepense();
				categorieLV.setItems(categorieDep);
				categorieRetour = "categories";

				break;

			case "revenus":

				//categorieXML catXMLrev =new categorieXML("revenus");
				categorieDep =  categorieXML.getCategorieDepense();
				categorieLV.setItems(categorieDep);
				categorieRetour = "categories";

				break;

			case "transferts":
				//categorieXML catXMLtra =new categorieXML("revenus");
				categorieDep =  categorieXML.getCategorieDepense();
				categorieLV.setItems(categorieDep);
				categorieRetour = "categories";


			}
		}

		@FXML
		private TreeView<String> categorieTreeView;

		@FXML
		private AnchorPane categorieAnchor;

		@FXML
		private ListView<String> categorieLV;


		public static ListView<String> categorieLVreference;



		@FXML
		private ListView<String> categorieEssai;

		ListView<String> categorieEssaiDepenses;
		ObservableList<String> categorie = FXCollections.observableArrayList();
		ObservableList<String> categorieDepensEssai = FXCollections.observableArrayList();
		ObservableList<String> categorieRevenu = FXCollections.observableArrayList();
		ObservableList<String> categorieTransfert = FXCollections.observableArrayList();

		ObservableList<String> categorieCatEssai = FXCollections.observableArrayList();

		//private Stage categorieStage;
		public static String categorieRetour ="";

		protected ObservableList<String> categorieDep;

		public void setCategorieStage(Stage categoriestage) {
			categorieController.categorieStage = categoriestage;
		}

		@FXML
		void categorieClique(MouseEvent arg0) {
			//System.out.println("clicked on " + categorieLV.getSelectionModel().getSelectedItem());
		}

		@FXML
		private void initialize() {

			//categorie.clear();
			categorieLVreference=categorieLV;

			ListesObservees Lsobs = new ListesObservees();
			categorie=ListesObservees.initCategorie(categorie);
			initialiseListeCategorie();
		}

		private void initialiseListeCategorie() {

			//System.out.println("init");

			btnRetour.setVisible(false);
			// TODO Auto-generated method stub
			//Affiche toutes les catégories initiales
			categorieLV.setItems(categorie);

			//ObservableList<String> categorieDepensEssai = FXCollections.observableArrayList();


			//$***********************************//$**********************************
			categorieLV.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					//if(categorieLV.getSelectionModel().getSelectedIndex() == 0){
					//	System.out.println("Selected index:  " + categorieLV.getSelectionModel().getSelectedItem() );

					selectIndex = categorieLV.getSelectionModel().getSelectedItem();
					//	System.out.print(categorieLV.getSelectionModel().getSelectedIndex());

					int cellule=categorieLV.getSelectionModel().getSelectedIndex();

					if(!(cellule == -1 )) {

						switch (selectIndex){

						//****************************************  Parent Dépenses dans CatDepenses2.xml ******************
						case "Dépenses":
							new categorieXML("dépenses");

							categorieDep =  categorieXML.getCategorieDepense();
							categorieLV.setItems(categorieDep);
							btnRetour.setVisible(true);
							categorieRetour = "categories";
							choixCategorie="dépenses";
							new autreDepense();
							break;
							//********************************	Enfants de Dépenses dans CatDepenses2.xml	****************
						case "Autres dépenses":
							autreDepense.choixDepense("Autres dépenses","depenses");
							break;
						case "Frais automobile":
							autreDepense.choixDepense("Frais automobile","depenses");
							break;
						case "Frais de vie":
							autreDepense.choixDepense("Frais de vie","depenses");
							break;
						case "Frais divers":
							autreDepense.choixDepense("Frais divers","depenses");
							break;
						case "Frais logement":
							autreDepense.choixDepense("Frais logement","depenses");
							break;
						case "Impôts":
							autreDepense.choixDepense("Impôts","depenses");
							break;
						case "Loisirs":
							autreDepense.choixDepense("Loisirs","depenses");
							break;
						case "Téléphonie":
							autreDepense.choixDepense("Téléphonie","depenses");
							break;
							//************************************************************************


							//****************************************  Parent Revenus dans CatDepenses2.xml ******************
						case "Revenus":
							new categorieXML("revenus");
							//categorieLV.setItems(categorieRevenu);
							categorieDep =  categorieXML.getCategorieDepense();
							categorieLV.setItems(categorieDep);
							categorieRetour = "categories";
							btnRetour.setVisible(true);
							choixCategorie="revenus";
							break;
							//********************************	Enfants de Dépenses dans CatDepenses2.xml	****************
						case "Autres revenus":
							autreDepense.choixDepense("Autres revenus","revenus");
							break;
						case "Remboursements":
							autreDepense.choixDepense("Remboursements","revenus");
							break;
						case "Revenu":
							autreDepense.choixDepense("Revenu","revenus");
							break;

							//******************************Parent Transferts dans CatDepenses2.xml ***************************

						case "Transferts":
							new categorieXML("transferts");
							//categorieLV.setItems(categorieTransfert);
							categorieDep =  categorieXML.getCategorieDepense();
							categorieLV.setItems(categorieDep);
							categorieRetour = "categories";
							btnRetour.setVisible(true);
							choixCategorie="transferts";
							break;

							//********************************	Enfants de Transferts dans CatDepenses2.xml	****************
						case "Tous Transferts":
							autreDepense.choixDepense("Tous Transferts","transferts");
							break;

							default:
							{
							//	System.out.print( "Nom catégorie "+selectIndex);
								modifBudgetController.referenceCategorieTF.setText(selectIndex);
								categorieStage.close();

							}

							//**************************************************

						}

					}
				}

			});
		}

		public static void showChoixCategorie() {

			try {
				// Load the fxml file and create a new stage for the popup dialog.
				FXMLLoader loader = new FXMLLoader();

				loader.setLocation(Main.class.getResource("/vues/categorie.fxml"));

				// FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/choixGrid.fxml"));

				AnchorPane page = (AnchorPane) loader.load();

				// Create the dialog Stage.
				 categorieStage = new Stage();

				categorieStage.setTitle("Catégories");
				//categorieStage.initModality(Modality.WINDOW_MODAL);
				categorieStage.initModality(Modality.APPLICATION_MODAL);
				//gridStage.initOwner(gridStage);
				Scene scene = new Scene(page);
				categorieStage.setScene(scene);

				// Set the operation into the controller.
				categorieController controller = loader.getController();
				controller.setCategorieStage(categorieStage);
				// controller.setOperation(tempOperation);

				// Set the dialog icon.
				//categorieStage.getIcons().add(new Image("file:resources/images/edit.png"));

				// Show the dialog and wait until the user closes it
				categorieStage.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();

			}

		}

		public static synchronized final String getChoixCategorie() {
			return choixCategorie;
		}

		public static synchronized final void setChoixCategorie(String choixCategorie) {
			categorieController.choixCategorie = choixCategorie;
		}

}
