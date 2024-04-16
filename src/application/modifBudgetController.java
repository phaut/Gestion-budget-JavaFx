package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javaFxPostgres.DataAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Operations;

public class modifBudgetController {

	int idRetour;
	//String Gcc;
	ListView<String> listView;
	static String montant;
	boolean present = false;
	@FXML
	private DatePicker dateField;

	@FXML
	private Label labelMontant;

	@FXML
	private ChoiceBox<String> etatCB;

	@FXML
	private  TextField montantField;

	public static TextField referenceMontantField;

	@FXML
	private TextField referenceField;

	public static TextField referenceCategorieTF;

	@FXML
	private TextField soldeField;
	public static TextField referenceSoldeTF;

	@FXML
	void handleOuvreFenetre() {

	}

	@FXML
	void handleCancel() {
		controller.ligneModif=false;
		dialogStage.close();
	}

	@FXML
	void handleOk(ActionEvent event) {

		ListviewXml listeViewXml = new ListviewXml();
		 try {
			 listView  = ListviewXml.loadDepensesListView("src/xml/CatDepenses2.xml");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LocalDate date = dateField.getValue();
		if (date == null || date.lengthOfYear() == 0) {
		}
		String format = date.format(DateTimeFormatter.ofPattern(" dd MMMM yyyy "));
		if (isInputValid()) {
			if (!controller.ligneModif) {
				if (!(DataAccessor.ligneIdMax()== null)) {
				 idRetour=DataAccessor.ligneIdMax();
				}
				int nbLignes=DataAccessor.nbLignesBase();
				operation.setId(idRetour+1);
				//controller.nouvelleLigne=false;
			}
			operation.setDate(format);
			operation.setLocaldate(date);
			operation.setEtat(etatCB.getValue());
			operation.setReference(referenceField.getText());

			System.out.println("operation Référence = "+operation.getReference());

			 if (listView.getItems().contains(operation.getReference())) {
		            present = true;

		        } else {
		            present = false;
		        }

				if ( present) {
				if (Double.valueOf(montantField.getText())>0) {
				operation.setMontant(Double.valueOf("-"+montantField.getText()));
				}
			}
			else {
				
				operation.setMontant(Double.parseDouble(montantField.getText()));
			}
			okClicked = true;
			controller.ligneModif=false;
			dialogStage.close();
		}
	}

	private Stage dialogStage;
	private Operations operation;
	private boolean okClicked = false;


	@FXML
	private void initialize() {

		//**************** PAS MODIFIER PAS EFFACER************************

		dateField.setValue(LocalDate.now());

		if (controller.ligneModif) {
			dateField.setValue(controller. dateChoisie);
		}

		dateField.setEditable(true);
		ObservableList<String> options = FXCollections.observableArrayList("Pointé","Non pointé","Rapproché");
		this.etatCB.setItems(options); // this statement adds all values in choiceBox
		montantField.setOnMouseClicked(e -> {
			showModifMontant();

		});
		//******************************************************************
		referenceField.setOnMouseClicked(evt -> {
			categorieController.showChoixCategorie();
		});
		referenceCategorieTF=referenceField;
		referenceMontantField = montantField;
		referenceSoldeTF = soldeField;
	}

	/**
	 * Appelle le clavier modifMontant.
	 *
	 * @param dialogStage
	 */

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

		// Set the dialog icon.
		this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
	}

	/**
	 * Sets the person to be edited in the dialog.
	 *
	 * @param person
	 */
	public void setOperation(Operations operation) {
		this.operation = operation;
		dateField.setAccessibleText(operation.getDate());
		etatCB.setValue(operation.getEtat());

		System.out.println("operation.getMontant() "+operation.getMontant());

		if (operation.getMontant()<0) {

		montantField.setText(String.valueOf(operation.getMontant()*(-1)));

		}

		else {

			montantField.setText(String.valueOf(operation.getMontant()));
		}

		referenceField.setText(operation.getReference());

	}
	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		LocalDate date = dateField.getValue();

		if (date == null || date.lengthOfYear() == 0) {
			errorMessage += "Date non valide!\n";
		}

		if ( etatCB.getValue() == null ||  etatCB.getValue().length() == 0) {
			errorMessage += "Etat non valide!\n";
		}
		if (montantField.getText() == null || montantField.getText().length() == 0) {
			errorMessage += "Montant non  valide!\n";
		}

		if (referenceField.getText() == null || referenceField.getText().length() == 0) {
			errorMessage += "Référence non valide!\n";
		} 
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Saisie incorrecte");
			alert.setHeaderText("SVP corrigez les champs invalides");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
	public void showModifMontant() {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Calculateur.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage montantStage = new Stage();
			montantStage.setTitle("Montant");
			montantStage.initModality(Modality.WINDOW_MODAL);
			//  montantStage.initOwner(primaryStage);
			montantStage.initOwner(dialogStage);
			Scene scene = new Scene(page);
			montantStage.setScene(scene);

			// Set the operation into the controller.
			CalculateurController controller = loader.getController();
			controller.setMontantStage(montantStage);
			montantStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
