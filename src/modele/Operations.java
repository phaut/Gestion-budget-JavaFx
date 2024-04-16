package modele;

import java.sql.Blob;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;



public class Operations {


	private ImageView photo;

	private final SimpleStringProperty date;
	// private final  SimpleIntegerProperty montant;
	private final  SimpleDoubleProperty montant;

	private final  StringProperty etat;
	private final StringProperty reference;
	//private final SimpleIntegerProperty solde;
	private final  SimpleDoubleProperty solde;


	// private final SimpleBooleanProperty etatBool;

	private final BooleanProperty etatBool;
	private final  SimpleIntegerProperty id;


	//  private final LocalDate localDate;

	private final ObjectProperty<LocalDate>localDate ;


	/**
	 * Default constructor.
	 */
	public Operations() {
		this(null,null, 0,null,null,0,null,false,0);
	}

	public Operations(Blob photo,String Date,double Montant,String Etat,String Reference,double Solde,LocalDate Localdate,boolean EtatBool,int Id) {

		this.photo = (ImageView) photo;
		this.date = new SimpleStringProperty(Date);
		// this.date = new SimpleObjectProperty<>(Date);;

		// this.montant = new  SimpleIntegerProperty(Montant);
		this.montant = new  SimpleDoubleProperty(Montant);

		this.etat = new SimpleStringProperty(Etat);
		this.reference = new SimpleStringProperty(Reference);
		//this.solde = new SimpleIntegerProperty(Solde);
		this.solde = new SimpleDoubleProperty(Solde);

		this.localDate = new SimpleObjectProperty<>(Localdate);
		this.etatBool=new SimpleBooleanProperty(EtatBool);
		// this.etatBool = new BooleanProperty(EtatBool);

		this.id = new SimpleIntegerProperty(Id);
	}

	//********************************
	public ImageView getPhoto() {
		return photo;
	}

	public void setPhoto(ImageView photo) {
		this.photo=photo;
	}

	//********************************

	//*********************************
	/* public  Boolean  getEtatBool() {
		return etatBool.get();
	}*/
	public boolean isEtatBool() {
		return etatBool.get();
	}
	public void setEtatBool(Boolean etatBool) {
		this.etatBool.set(etatBool);
	}
	public BooleanProperty etatBoolProperty() {
		return etatBool;
	}


	//*********************************

	//*********************************

	public LocalDate getLocaldate() {
		return localDate.get();
	}
	public void setLocaldate(LocalDate Localdate) {
		this.localDate.set(Localdate);
	}

	public ObjectProperty<LocalDate> LocaldateProperty() {
		return localDate;
	}

	//*********************************

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date.set(date);
	}

	public StringProperty dateProperty() {
		return date;
	}
	//*********************************

	public double getMontant() {
		return montant.get();
	}

	public void setMontant(double montant) {
		this.montant.set(montant);
	}

	public DoubleProperty montantProperty() {
		return montant;
	}
	//*********************************
	public String getEtat() {
		return etat.get();
	}

	public void setEtat(String etat) {
		this.etat.set(etat);
	}

	public StringProperty etatProperty() {
		return etat;
	}
	//*********************************
	public String getReference() {
		return reference.get();
	}

	public void setReference(String reference) {
		this.reference.set(reference);
	}

	public StringProperty referenceProperty() {
		return reference;
	}
	//*********************************
	public double getSolde() {
		return solde.get();
	}

	public void setSolde(double solde) {
		this.solde.set(solde);
	}

	public DoubleProperty soldeProperty() {
		return solde;
	}
	//*********************************

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		// TODO Auto-generated method stub
		return id;
	}


}
