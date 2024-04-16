# budget
Programme Javafx utilisation javafx-sdk-21.0.1 à installer
Gestion d'un budget avec base PostgreSQL 15  et une base écrite avec pgAdmin4
public class DataAccessor {

	static int nbLignes;
	static Integer idRetour;
    private static ObservableList<Operations> observableList = FXCollections.observableArrayList();

	static List<modele.Operations> pl = new ArrayList<>();
	static PreparedStatement pst = null;

	private static DataAccessor da = new DataAccessor("jdbc:postgresql://localhost:",
			"postgres", "");

	private static Connection connection;

	public static DataAccessor getDataAccessor() {
		return da;

	}

 Modifier la ligne 13 et 14 pour avoir accès à la base sur localhost 
