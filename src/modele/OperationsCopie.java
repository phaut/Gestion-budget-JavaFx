package modele;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OperationsCopie {

	// private final SimpleStringProperty date;
	 private ObjectProperty <LocalDate>date;
	    private final  SimpleIntegerProperty montant;
	    private final  StringProperty etat;
	    private final StringProperty reference;
	    private final SimpleIntegerProperty solde;

	  /*  public OperationsCopie() {
	        this(null, 0,null,null,0);
	    }*/

	    public OperationsCopie(LocalDate Date,int Montant,String Etat,String Reference,int Solde) {
			// this.date = new SimpleStringProperty(Date);
			 this.date = new SimpleObjectProperty<>(Date);

			 this.montant = new  SimpleIntegerProperty(Montant);
			 this.etat = new SimpleStringProperty(Etat);
			 this.reference = new SimpleStringProperty(Reference);
			 this.solde = new SimpleIntegerProperty(Solde);

		}

	    public LocalDate getDate() {
	        return date.get();
	    }

	    public void setDate(LocalDate date) {
	        this.date.set(date);
	    }

	    public ObjectProperty dateProperty() {
	        return date;
	    }

	    public int getMontant() {
	        return montant.get();
	    }

	    public void setMontant(int montant) {

	        this.montant.set(montant);
	    }

	    public IntegerProperty montantProperty() {
	        return montant;
	    }

	    public String getEtat() {
	        return etat.get();
	    }

	    public void setEtat(String etat) {
	        this.etat.set(etat);
	    }

	    public StringProperty etatProperty() {
	        return etat;
	    }

	    public String getReference() {
	        return reference.get();
	    }

	    public void setReference(String reference) {
	        this.reference.set(reference);
	    }

	    public StringProperty referenceProperty() {
	        return reference;
	    }

	    public int getSolde() {
	        return solde.get();
	    }

	    public void setSolde(int solde) {
	        this.solde.set(solde);
	    }

	    public IntegerProperty soldeProperty() {
	        return solde;
	    }


}
