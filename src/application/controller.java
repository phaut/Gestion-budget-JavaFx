package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import calculSolde.soldeCalcul;
import javaFxPostgres.DataAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import modele.Operations;


public class controller implements Initializable {

	ListView<Operations> list = new ListView<>();
	List<Operations> listEssai =null;
	String LVperiodeStr;
	static String format;
	static String moment;
	Boolean btnD;
	Boolean btnG;
	static Integer incMois = 0;
	static Double soldeInitial;
	Integer soldeBanqueST;
	static LocalDate moisSuivant;
	static LocalDate current_date;
	static int idInt;
	public static LocalDate dateChoisie;
	static double montantSolde = 0;
	static double soldeMontantPointe =0;
	static double somme = 0.0;
	static double sommeSolde= 0.0;
	static double montant= 0.0;
	LocalDate nouvelledate;
	static int siZeopData;
	SpinnerValueFactory<Integer> valueFactoryInt;
	ObservableList<String> months = FXCollections.observableArrayList(//
			"janvier", "fevrier", "mars", "avril", //
			"mai", "juin", "juillet", "aout", //
			"septembre", "octobre", "novembre", "decembre");

	ObservableList<String> periode =FXCollections.observableArrayList("Jour","Mois","Trimestre",
			"Semestre","Année","01 Janvier à aujourd'hui","Toutes les dates","Autres...");


	static PreparedStatement pst = null;
	static PreparedStatement pst1 = null;
	static String sql;

	@FXML
	private TextField dernierJourDuMoisTF;

	@FXML
	private TableColumn<Operations,Integer> idCol;

	@FXML
	private TableColumn<Operations,Boolean> etatBoolCol;
	@FXML
	private MenuItem Apropos;

	@FXML
	private MenuItem NouveauF;

	@FXML
	private MenuItem OuvreF;

	@FXML
	private MenuItem SauvegardeCommeF;

	@FXML
	private MenuItem SauvegardeF;

	@FXML
	private MenuItem SortieF;

	@FXML
	private Button flecheDbtn;

	@FXML
	private Button flecheGbtn;

	@FXML
	private Button periodeBtn;

	@FXML
	private  TextField periodeTF;

	public static TextField periodeTFreference;

	public static TextField   dernierJourDuMoisTFreference;

	@FXML
	private TableColumn<Operations, LocalDate> locaDateColonne;

	@FXML
	private TextField soldeBanqueTF;

	public static TextField soldebanqueTFreference;

	//--------------------------------

	@FXML
	void handleSoldeInitial(ActionEvent event) {

		soldeInitial=Double.valueOf(soldeEnBanqueTF.getText());

	}
	@FXML
	void handleApropos(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Operations Bancaires");
		alert.setHeaderText("A propos..");
		alert.setContentText("Auteur : Ph Hautot");

		alert.showAndWait();
	}

	@FXML
	void handleChangePeriodeBtn(ActionEvent event) {
		LVPeriode.setVisible(true);
		btnFermeLV.setVisible(true);
	}
	@FXML
	void handleChangeperiodeTF(ActionEvent event) {

	}

	@FXML
	void handleSoldeInitialTC(InputMethodEvent event) {

	}

	@FXML
	void handleChangePeriodeMC(MouseEvent event) {

		if (MouseEvent.MOUSE_CLICKED != null) {
			LVPeriode.setVisible(true);
			btnFermeLV.setVisible(true);
		}
	}

	@FXML
	void handleNouveauF(ActionEvent event) {
	}

	@FXML
	void handleOuvreF() {

	}

	@FXML
	void handleSauvegardeCommeF() {

	}

	@FXML
	void handleSauvegardeF() {

	}

	@FXML
	void handleSortieF(ActionEvent event) {
		System.exit(0);
	}
	//--------------------------------


	@FXML
	private TextField soldeEnBanqueTF;

	@FXML
	private TextField soldePointeTF;

	private static TextField soldeMontantPointeTFreference;


	@FXML
	private Button btnModifie;

	@FXML
	private Button btnNouveau;

	@FXML
	private Button btnSupprime;

	@FXML
	private TableColumn<Operations, String> dateColonne;

	@FXML
	private TableColumn<Operations, String> etatColonne;

	@FXML
	private TableColumn<Operations,Double> montantColonne;

	public static TableColumn<Operations,Double> montantColonneReference;

	public  boolean nouvelleLigne;
	public static boolean ligneModif;


	@FXML
	private TableView<Operations> operationTable;

	@FXML
	private TableColumn<Operations, String> referenceColonne;


	@FXML
	private TableColumn<Operations, Double> soldeColonne1;

	@FXML
	private Button changeDateBTN;

	@FXML
	private TableColumn<ImageView,Operations> etatImage;

	@FXML
	void handleChangePeriodeDbtn(ActionEvent event) {
		btnD=true;
		btnG=false;
		changePeriodeDG();

	}
	@FXML
	void handleChangePeriodeGbtn(ActionEvent event) {
		btnD=false;
		btnG=true;
		changePeriodeDG();

	}

	private void changePeriodeDG() {
		// TODO Auto-generated method stub
		current_date = LocalDate.now();
		if (!(moment==null)) {

			switch (moment) {
			case "Jour":
			{
				format =current_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
			}
			break;
			case  "Année":
			{
				String Annee = periodeTF.getText();
				int annee = Integer.parseInt(Annee);
				if (btnD) {
					annee++;
				}
				else if(btnG) {
					annee--;
				}
				Annee = Integer.toString(annee);
				periodeTF.setText(Annee);
				format=Annee;

				int anneeFin = Integer.parseInt(format);
		        // Créer un objet YearMonth avec l'année donnée
		        YearMonth yearMonth = YearMonth.of(anneeFin, 12); // Décembre de l'année donnée
		        // Obtenir le dernier jour du mois (décembre)
		        int dernierJour = yearMonth.lengthOfMonth();
		        String jourDernier = dernierJour+"/12/"+format;

				/****************MODIF 26/03/2024****************/
				AffichedernierJourDuMois(jourDernier); //En cours
				/************************************************/


			}
			break;
			case "Mois":
			{
				LocalDate dateDuJour = current_date;
				if (btnD) {
					incMois++;
					moisSuivant = dateDuJour.plusMonths(incMois);
					String moisFormat = moisSuivant.format(DateTimeFormatter.ofPattern(" MMMM yyyy "));
					//System.out.println(" moisFormat "+ moisFormat);
					periodeTF.setText(moisFormat);
					format=moisFormat;

					//********************* MODIFICATION 26 MARS 2024*************************************************
					// Obtenir la date actuelle
					LocalDate dateActuelle = LocalDate.now();

					// Ajouter un mois pour obtenir le mois suivant
					LocalDate dateMoisSuivant = dateActuelle.plusMonths(incMois);

					// Obtenir le dernier jour du mois suivant
					LocalDate dernierJourMoisSuivant = dateMoisSuivant.withDayOfMonth(dateMoisSuivant.lengthOfMonth());

					// Formater la date au format souhaité (jour/mois/année)
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dernierJourFormate = dernierJourMoisSuivant.format(formatter1);

					dernierJourDuMoisTFreference = dernierJourDuMoisTF;
					dernierJourDuMoisTFreference.setText("Au " +dernierJourFormate);

					//**************************************************

				}
				else if(btnG) {
					incMois--;
					moisSuivant = dateDuJour.plusMonths(incMois);
					// moisSuivant = dateDuJour.minusMonths(1);
					String moisFormat = moisSuivant.format(DateTimeFormatter.ofPattern(" MMMM yyyy "));
					periodeTF.setText(moisFormat);
					format=moisFormat;
					//********************* MODIFICATION 26 MARS 2024*************************************************
					// Obtenir la date actuelle
					LocalDate dateActuelle = LocalDate.now();

					// Ajouter un mois pour obtenir le mois suivant
					LocalDate dateMoisSuivant = dateActuelle.plusMonths(incMois);

					// Obtenir le dernier jour du mois suivant
					LocalDate dernierJourMoisSuivant = dateMoisSuivant.withDayOfMonth(dateMoisSuivant.lengthOfMonth());

					// Formater la date au format souhaité (jour/mois/année)
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dernierJourFormate = dernierJourMoisSuivant.format(formatter1);
					dernierJourDuMoisTFreference.setText("Au " +dernierJourFormate);
					//**************************************************
				}
			}
			break;

			}
			Integer taille = Main.operationData.size();
			setFormat();
		}
	}

	@FXML
	void btnChangePeriode(ActionEvent event) {
		LVPeriode.setVisible(true);
		btnFermeLV.setVisible(true);

	}
	@FXML
	private ListView<String> LVPeriode;

	@FXML
	void ChangePeriode(MouseEvent arg0) {

		LVperiodeStr= LVPeriode.getSelectionModel().getSelectedItem();
		changePeriode(LVperiodeStr);
	}

	private void changePeriode(String lVperiodeStr) {
		// TODO Auto-generated method stub

		LocalDate current_date = LocalDate.now();

		switch (LVperiodeStr) {

		case "Jour":
		{
			moment="Jour";
			format =current_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

			/****************MODIF 26/03/2024****************/
			//AffichedernierJourDuMois(); //En cours
			/************************************************/

		}
		break;
		case  "Année":
		{
			moment="Année";
			format =current_date.format(DateTimeFormatter.ofPattern("yyyy"));
			int annee = Integer.parseInt(format);
	        // Créer un objet YearMonth avec l'année donnée
	        YearMonth yearMonth = YearMonth.of(annee, 12); // Décembre de l'année donnée
	        // Obtenir le dernier jour du mois (décembre)
	        int dernierJour = yearMonth.lengthOfMonth();

	        // Afficher le dernier jour de l'année
	       // System.out.println("Le dernier jour de l'année " + format + " est le " + dernierJour + " décembre.");
	        String dernierJourAnnee =  dernierJour + "/12/"+format;
	        /****************MODIF 26/03/2024****************/
			AffichedernierJourDuMois(dernierJourAnnee); //En cours
			/************************************************/
	    }

		break;
		case "Mois":
		{
			moment="Mois";

			// Convertir la date en objet YearMonth
			format =current_date.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
	        YearMonth yearMonth = YearMonth.parse(format, DateTimeFormatter.ofPattern("MMMM yyyy"));
	        // Obtenir le dernier jour du mois
	        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
	        // Formater le dernier jour du mois
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String lastDayOfMonthStr = lastDayOfMonth.format(formatter);
			/****************MODIF 26/03/2024****************/
			AffichedernierJourDuMois(lastDayOfMonthStr); //En cours
			/************************************************/

		}
		break;

		case "Toutes les dates":
		{
			moment="Toutes les dates";
			format ="*";
			/****************MODIF 26/03/2024****************/

			try {

				String derniereDate = "";
				DataAccessor da = DataAccessor.getDataAccessor();
				Connection conn = da.RetourConnexion();

				// Construire la chaîne de requête SQL pour sélectionner toutes les dates de la table, triées par date
				String sql = "SELECT MAX(localdate) AS derniere_date FROM operations";
				pst = conn.prepareStatement(sql);

				// Exécuter la requête et traiter les résultats
				ResultSet rs = pst.executeQuery();
				// Traiter les résultats ici

				if (rs.next()) {
	                Date date = rs.getDate("derniere_date");
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                derniereDate = sdf.format(date);


	            }
				dernierJourDuMoisTFreference = dernierJourDuMoisTF;
				dernierJourDuMoisTFreference.setText("Au " + derniereDate);
			} catch (Exception e) {
				e.printStackTrace();
			}


		//	AffichedernierJourDuMois(); //En cours
			/************************************************/


		}
		}
		setFormat();
	}
	private void AffichedernierJourDuMois(String jourDernier) {

		dernierJourDuMoisTFreference = dernierJourDuMoisTF;
		dernierJourDuMoisTFreference.setText("Au " +jourDernier);

	}
	private void setFormat() {
		// TODO Auto-generated method stub
		periodeTF.setText(format);
		Main.operationData.clear();
		MoisAnneeEnCoursInit(format);
		LVPeriode.setVisible(false);
		btnFermeLV.setVisible(false);
		operationTable.refresh();
		operationTable.requestFocus();
		operationTable.getSelectionModel().select(0);
		operationTable.getFocusModel().focus(0);

	}
	@FXML
	private TextField SpinnerTF;

	@FXML
	void HandleChangePeriode(InputMethodEvent event) {

		LVPeriode.getSelectionModel().getSelectedItem();
	}
	@FXML
	private Button btnFermeLV;

	@FXML
	void handleBtnFermeLV(ActionEvent event) {
		LVPeriode.setVisible(false);
		btnFermeLV.setVisible(false);
	}

	@SuppressWarnings("unused")
	@FXML
	void btnModifie(ActionEvent event) {
		Operations selectedOperation = operationTable.getSelectionModel().getSelectedItem();
		Operations tempOperation = new Operations();
		//dateChoisie = selectedOperation.getLocaldate();
		ligneModif=true;
		if (selectedOperation != null) {
			dateChoisie = selectedOperation.getLocaldate();
			boolean okClicked = mainApp.showOperationEditDialog(selectedOperation);
			if (okClicked) {
				nouvelledate = dateChoisie;
				siZeopData= mainApp.getOperationData().size();
				int idSelected=selectedOperation.getId();
				DataAccessor.MAJmodifBase(idSelected,selectedOperation);

				//************Ajout MODIF 25/03/2024**********************
				calculSolde();
				//***********************************

				//**********MODIF 10/04/2024  A REMETTRE ? A RETIRER ? ******************

				modifNouveau(nouvelledate);

				//**********************************************************

				ligneModif=false;
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Pas de sélection");
			alert.setHeaderText("Aucune ligne sélectionnée");
			alert.setContentText("Sélectionnez une ligne.");
			alert.showAndWait();
		}
	}

	private void modifNouveau(LocalDate nouvelledate) {
		// TODO Auto-generated method stub
		ligneModif=false;
		new soldeCalcul(nouvelledate);
		operationTable.refresh();
		DataAccessor.MAJbase();
		LocalDate current_date = LocalDate.now();
		//************* MODIFICATION 03/04/2024 *******************************
		format = "*";
		moment = "Toutes les dates";
		MoisAnneeEnCoursInit (format);
		//************************ Ajouté **********************************************
		format =current_date.format(DateTimeFormatter.ofPattern(" MMMM yyyy "));
		moment = "Mois";
		MoisAnneeEnCoursInit (format);
		incMois=0;
	}
	@FXML
	void btnNouveau(ActionEvent event) {
		Operations tempOperation = new Operations();
		boolean okClicked = mainApp.showOperationEditDialog(tempOperation);
		if (okClicked) {
			ligneModif=false;
			mainApp.getOperationData().add(tempOperation);
			nouvelledate = tempOperation.getLocaldate();
			siZeopData= mainApp.getOperationData().size();
			DataAccessor.MAJbaseNouveau(tempOperation);
			modifNouveau(nouvelledate);
		}
	}

	@FXML
	void btnSupprime(ActionEvent event) {
		int selectedIndex = operationTable.getSelectionModel().getSelectedIndex();

		Integer idValeur=operationTable.getSelectionModel().getSelectedItem().getId();

		if (selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprime une ligne");
			alert.setHeaderText("Confirmer ou non la suppression.");
			alert.setContentText("Voulez vous supprimer cette ligne?");

			siZeopData= mainApp.getOperationData().size();

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.operationData.remove(selectedIndex);
				operationTable.refresh();

				siZeopData= mainApp.getOperationData().size();

				//*************Ajout MODIF 25/03/2024*******

				calculSolde();

				//*****************************************
				DataAccessor.supprimeLigne(idValeur);
				modifNouveau(nouvelledate);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Aucune opération sélectionnée");
			alert.setContentText("Séléctionnez une opération SVP.");
			alert.showAndWait();
		}
	}

	// Reference to the main application.
	private Main mainApp;
	private TextField textField;
	public controller() {
	}
	public static  void MoisAnneeEnCoursInit(String format) {
		// TODO Auto-generated method stub
		LocalDate current_date = LocalDate.now();
		periodeTFreference.setText(format);
		Main.operationData.clear();  //Effacer la table


		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();

			if (format =="*") {
				periodeTFreference. setText("Toutes les dates");

				sql = "SELECT * FROM operations ORDER BY localdate ASC";

				pst=conn.prepareStatement(sql);
				//********** Modification 3/04/2024 *******
				DataAccessor. MAJsolde();
				//*****************************************

				incMois=0;
			}

			else {
				sql = "SELECT * FROM operations WHERE date LIKE ? ORDER BY localdate ";
				pst=conn.prepareStatement(sql);
				pst.setString(1, "%" + format + "%");
			}

			ResultSet rs = pst.executeQuery();

			while (rs.next()){
				Main.operationData.add(new modele.Operations(
						rs.getBlob("etatimage"),rs.getString("date"),
						//rs.getInt("montant"),
						rs.getDouble("montant"),
						rs.getString("etat"),
						rs.getString("reference"),
						rs.getDouble("solde"),
						rs.getDate("localdate").toLocalDate(),
						rs.getBoolean("etatBool"),
						rs.getInt("id")
						));
			}

			calculSolde(); //Calcul de la colonne solde

		} catch (Exception e) {
			e.printStackTrace();
		}

		//**************** POUR ESSAI *************************
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		//****************************************************
		Integer taille = Main.operationData.size();

	}

	public static void calculSolde() {

		 DecimalFormat df = new DecimalFormat("#.##");

		switch(moment) {
		case "Jour":{

		}break;

		case "Mois":{
			try {

				 montantSolde=0;
				 soldeMontantPointe= 0;
				DataAccessor da = DataAccessor.getDataAccessor();
				Connection conn = da.RetourConnexion();
				format = format.trim();

				// Convertir le format de mois "avril 2024" en format de date
				SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.FRANCE);
				Date date = sdf.parse(format);

				// Construire la chaîne de requête SQL pour sélectionner les montants pour les mois avant avril et avril inclus, et trier par date
				String sql = "SELECT id, montant,etatbool FROM operations WHERE DATE_TRUNC('month', CAST(localdate AS DATE)) <= DATE_TRUNC('month', CAST(? AS DATE)) ORDER BY localdate";
				pst = conn.prepareStatement(sql);

				// Utiliser setDate() pour définir le paramètre de date dans la requête
				pst.setDate(1, new java.sql.Date(date.getTime()));

				// Exécuter la requête et traiter les résultats
				ResultSet rs = pst.executeQuery();
				// Traiter les résultats ici

				while (rs.next()) {
					// Récupérer et imprimer l'ID et le montant
					int id = rs.getInt("id");
					 montant = rs.getDouble("montant");
					boolean soldePointe = rs.getBoolean("etatbool");
					//System.out.println("ID: " + id + ", Montant: " + montant);
					 montantSolde = montantSolde + montant ;
					 if (soldePointe) {
						 soldeMontantPointe =  soldeMontantPointe +montant;

					 }
				}

				String formattedValueMontant = df.format(montantSolde);
		        String formattedValuesoldeMontantPointe = df.format(soldeMontantPointe);

				soldebanqueTFreference.setText(String.valueOf( formattedValueMontant));
				soldeMontantPointeTFreference.setText(String.valueOf( formattedValuesoldeMontantPointe));




			} catch (Exception e) {
				e.printStackTrace();
			}
		}break;

		case "Année":{
			 montantSolde=0.0;

			try {
				DataAccessor da = DataAccessor.getDataAccessor();
				Connection conn = da.RetourConnexion();
				format = format.trim();
				String dateFinAnnee = format + "-12-31";

				String sql = "SELECT  SUM(montant) AS total FROM operations WHERE localdate <= ?";
				pst = conn.prepareStatement(sql);
				// Construire la date de fin de l'année donnée
	            pst.setDate(1, java.sql.Date.valueOf(dateFinAnnee));

	            ResultSet resultSet = pst.executeQuery();
	            if (resultSet.next()) {
	                somme = resultSet.getDouble("total");
	            }

	            String sql1 = "SELECT SUM(montant) AS total FROM operations WHERE localdate <= ? AND etatbool = true";
	            pst1 = conn.prepareStatement(sql1);
	            pst1.setDate(1, java.sql.Date.valueOf(dateFinAnnee));

	            ResultSet rSet = pst1.executeQuery();
	            if (rSet.next()) {
	                sommeSolde = rSet.getDouble("total");
	              //  System.out.println("Somme des montants avec etatbool = true : " + sommeSolde);
	            }


				//*********************************************
				 // Formatage de la valeur double avec deux chiffres après la virgule
		        String formattedValueSomme = df.format(somme);
		        String formattedValueSommeSolde = df.format(sommeSolde);
		        // Assigner la valeur formatée au TextField
		        soldebanqueTFreference.setText(formattedValueSomme);
		        soldeMontantPointeTFreference.setText(formattedValueSommeSolde);
				//********************************************


			} catch (Exception e) {
				e.printStackTrace();
			}
		}break;

		case "Toutes les dates":{
			 montantSolde=0.0;
			try {
				DataAccessor da = DataAccessor.getDataAccessor();
				Connection conn = da.RetourConnexion();

				// Construire la chaîne de requête SQL pour sélectionner toutes les dates de la table, triées par date
				String sql = "SELECT id, montant FROM operations ORDER BY localdate";
				pst = conn.prepareStatement(sql);

				// Exécuter la requête et traiter les résultats
				ResultSet rs = pst.executeQuery();
				// Traiter les résultats ici

				while (rs.next()) {
					// Récupérer et imprimer l'ID et le montant
					int id = rs.getInt("id");
					int montant = rs.getInt("montant");
					 //System.out.println("ID: " + id + ", Montant: " + montant);
					 montantSolde = montantSolde + montant ;
				}
				soldebanqueTFreference.setText(String.valueOf( montantSolde));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}break;


		}

		if ((format=="*")) {

		}
		else {

		}

	}
	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		operationTable.setItems(mainApp.getOperationData());
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		operationTable.setOnSort((event) -> {

		});
		soldeMontantPointeTFreference = soldePointeTF;
		Main.operationData.clear();
		operationTable.setItems(null);
		soldeEnBanqueTF.setText("0");
		soldeInitial=Double.valueOf(soldeEnBanqueTF.getText());
		LVPeriode.setItems(periode);

		LocalDate current_date = LocalDate.now();

		//***************** MODIFICATION 27/03/2024*******
		format = "*";
		moment="Toutes les dates";
		// ********************************************************

		montantColonneReference=montantColonne;
		periodeTFreference=periodeTF;
		soldebanqueTFreference=soldeBanqueTF;
		MoisAnneeEnCoursInit (format);

		//*****************MODIF 26 Mars 2024 ***************************
		format =current_date.format(DateTimeFormatter.ofPattern(" MMMM yyyy "));
		moment="Mois";
		MoisAnneeEnCoursInit (format);
		//*****************MODIF 26 Mars 2024 ***************************

		// Convertir la date en objet YearMonth
        YearMonth yearMonth = YearMonth.parse(format, DateTimeFormatter.ofPattern("MMMM yyyy"));

        // Obtenir le dernier jour du mois
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        // Formater le dernier jour du mois
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String lastDayOfMonthStr = lastDayOfMonth.format(formatter);
			AffichedernierJourDuMois(lastDayOfMonthStr);  //En cours


		//****************************************************************


		etatBoolCol.setCellValueFactory(cellData -> cellData.getValue().etatBoolProperty());
		etatBoolCol.setCellFactory(column -> {
			CheckBoxTableCell<Operations, Boolean> cell = new CheckBoxTableCell<Operations, Boolean>() {
				@Override
				public void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					if (!isEmpty()) {
						// Ajouter un gestionnaire d'événements pour détecter les modifications de la case à cocher
						CheckBox checkBox = (CheckBox) this.getGraphic();
						if (checkBox != null) {
							checkBox.setOnAction(event -> {
								Operations operation = getTableView().getItems().get(getIndex());
								int rowId = operation.getId(); // Récupérer l'ID de la ligne
								operation.setEtatBool(checkBox.isSelected());

								// Mettre à jour la base de données avec la nouvelle valeur
								DataAccessor.updateEtatBool(operation.getId(), checkBox.isSelected());
								SoldePointé(operation,rowId,checkBox.isSelected());
							});
						}
					}
				}

				private void SoldePointé(Operations operation, int rowId, boolean selected) {

					if (selected) {

						calculSolde();
					}

					else {

						calculSolde();

					}

				}
			};
			cell.setAlignment(Pos.CENTER); // Aligner la case à cocher au centre de la cellule
			return cell;
		});
		//*************************************************************************
		etatImage.setCellValueFactory(new PropertyValueFactory<>("photo"));
		dateColonne.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		etatColonne.setCellValueFactory(cellData ->cellData.getValue().etatProperty());
		referenceColonne.setCellValueFactory(cellData ->cellData.getValue().referenceProperty());
		montantColonne.setCellValueFactory(cellData -> cellData.getValue().montantProperty().asObject());

		idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		locaDateColonne.setCellValueFactory(cellData ->cellData.getValue(). LocaldateProperty());
		soldeColonne1.setCellValueFactory(cellData -> cellData.getValue().soldeProperty().asObject());
		// Activer le tri pour la colonne
		locaDateColonne.setSortable(true);
		operationTable.getSortOrder().add(locaDateColonne);
		locaDateColonne.setSortType(TableColumn.SortType.ASCENDING);
		operationTable.sort();
		//******************************************************************

		//montantColonne.setCellFactory(new Callback<TableColumn<Operations,Integer>, TableCell<Operations,Integer>>() {
			montantColonne.setCellFactory(new Callback<TableColumn<Operations,Double>, TableCell<Operations,Double>>() {
			@Override
			public TableCell<Operations, Double> call(TableColumn<Operations, Double> param) {
				return new TableCell<Operations, Double>() {

					@Override
					public void updateItem(Double item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							// Ajout de la classe CSS "montant-cell" à la cellule
		                    getStyleClass().add("montant-cell");

							if (String.valueOf(item).charAt(0) =='-'){
								this.setTextFill(Color.RED);
								//System.out.println("montant couleur clicked");
							}
							else{
								this.setTextFill(Color.GREEN);
							}
							setText(String.valueOf(item));
						}
					}
				};
			}
		});



		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		locaDateColonne.setCellFactory(tc -> new TableCell<Operations, LocalDate>() {
			@Override
			protected void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (empty) {
					setText(null);
				} else {
					setText(formatter1.format(date));
				}
			}
		});
	}
}
