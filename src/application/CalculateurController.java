package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CalculateurController {
	long nbPoint = 0 ;
	char ch ='.';
	@FXML
    private Button cinq;

    @FXML
    private Button deux;

    @FXML
    private Button huit;

    @FXML
    private Button neuf;

    @FXML
    private Button quatre;

    @FXML
    private Button sept;

    @FXML
    private Button six;

    @FXML
    private Button trois;

    @FXML
    private Button un;

    @FXML
    private Button zero;

    @FXML
    private Button boutonPoint;

    @FXML
    private Button boutonVirgule;

    @FXML
    private TextField affiche;

    @FXML
    private Button clear;

    @FXML
    private Button Annule;

	private Stage MontantStage;

	@FXML
    void handleBoutonOk(ActionEvent event) {

		//if (categorieController.getChoixCategorie()=="dépenses") {
		modifBudgetController.referenceMontantField.setText(affiche.getText());
		//}

		MontantStage.close();

    }

    @FXML
    void handleAnnule(ActionEvent event) {

    	MontantStage.close();

    }

    @FXML
    void handleBoutonAction(ActionEvent event) {
    	 if
    	(event.getSource() == un) {
    		affiche.setText(affiche.getText() + "1");
    		//System.out.println( " un= ");
    	} else if (event.getSource() == deux) {
    		affiche.setText(affiche.getText() + "2");
    	}else if (event.getSource() == trois) {
    		affiche.setText(affiche.getText() + "3");
    	}else if (event.getSource() == quatre) {
    		affiche.setText(affiche.getText() + "4");
    	}else if (event.getSource() == cinq) {
    		affiche.setText(affiche.getText() + "5");
    	}else if (event.getSource() == six) {
    		affiche.setText(affiche.getText() + "6");
    	}else if (event.getSource() == sept) {
    		affiche.setText(affiche.getText() + "7");
    	}else if (event.getSource() == huit) {
    		affiche.setText(affiche.getText() + "8");
    	}else if (event.getSource() == neuf) {
    		affiche.setText(affiche.getText() + "9");
    	}else if (event.getSource() == zero) {
    		affiche.setText(affiche.getText() + "0");

    	}else if (event.getSource() == clear) {
    		affiche.setText("");
    	}else if (event.getSource() == boutonPoint) {
    		if (!(nbPoint==1)){
    			affiche.setText(affiche.getText() + ".");
    			String afficheMontant=affiche.getText();
    			nbPoint = countOccurrence(afficheMontant,  ch);
    			//System.out.println( " nb point = "+String.valueOf(nbPoint));
    		}
    	}

}


	public void setMontantStage(Stage montantStage) {
		// TODO Auto-generated method stub
		 this.MontantStage = montantStage;

	}

	private static int countOccurrenc(String str, char ch) {
	    int counter = 0;
	    for (int i = 0; i < str.length(); i++)
	    {
	        if (str.charAt(i) == ch) {
	            counter++;
	        }
	    }

	    return counter;
	}

	private static long countOccurrence(String str, char ch) {
	    return str.chars()
	            .filter(c -> c == ch)
	            .count();
	}

	private static int countOccurrences(String str, char ch) {

	    Matcher matcher = Pattern.compile(String.valueOf(ch))
	                            .matcher(str);

	    int counter = 0;
	    while (matcher.find()) {
	        counter++;
	    }

	    return counter;
	}

}
