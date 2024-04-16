package application;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Operations;

public class Main extends Application {
	private static Stage primaryStage;
	private static int siZeopData;
	private static int odSolde;
	private Connection connection;
	public Blob  photocbFaux;
	public static ObservableList<Operations> operationData = FXCollections.observableArrayList();
	public Main() throws SQLException {

		ImageView emp1photo = new ImageView(new Image (this.getClass().getResourceAsStream("cbFaux.png")));

		//InputStream essai = photocbFaux.getBinaryStream(); //= new Blob(new Image (this.getClass().getResourceAsStream("cbFaux.png")));
		File file = new File("cbFaux.png");

		// System.out.println("Main extends Application" );
		// operationData.add(new Operations("23 04 1952", 234,"Soldé","ok",123));
		operationData.add(new Operations(photocbFaux, "23 04 1953", 235,"Non Soldé","not ok",154, null, false, 0));

	}
	public ObservableList<Operations> getOperationData() {
		return operationData;
	}
	@Override
	public  void start(Stage primaryStage) {

		//ImageView emp1photo = new ImageView(new Image (this.getClass().getResourceAsStream("cbFaux.png")));

		try {
			Main.primaryStage = primaryStage;
			Main.primaryStage.setTitle("Opérations Bancaires");
			Main.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			// Parent root = FXMLLoader.load(getClass().getResource("/vue/budget.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/budget.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			controller controller = loader.getController();
			Scene scene = new Scene(root,950,700);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			controller.setMainApp(this);

			primaryStage.show();

		//	DataAccessor.MAJbase();


		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {

		launch(args);
	}


	//public boolean showOperationEditDialog(Operations tempOperation) {
	public boolean showOperationEditDialog(Operations tempOperation) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/modifBudgetDialogue.fxml"));

			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Opération bancaire");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the operation into the controller.
			modifBudgetController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			// controller.setOperation(tempOperation);
			controller.setOperation(tempOperation);

			// Set the dialog icon.
			dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();


			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static int getOdSolde() {
		return odSolde;
	}
	public static void setOdSolde(int odSolde) {
		Main.odSolde = odSolde;
	}
}
