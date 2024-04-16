package enumeration;

public enum Categories {
	Dépenses("Dépenses"),
	Revenus("Revenus"),
	Transferts("Transferts");

	 private String categDepense;

    Categories(String CatDepense) {
        this.categDepense=CatDepense;
    }

    public String getCatDepense() {
        return categDepense;
    }

}
