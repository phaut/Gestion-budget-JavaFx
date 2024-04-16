package javaFxPostgres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Operations;


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


	public DataAccessor(String  dBUrl , String usr , String pass) {
		// TODO Auto-generated constructor stub

		try {
			connection = DriverManager.getConnection(dBUrl, usr, pass);
			System.out.println("Opened database successfully");


		}catch(SQLException e) {System.out.println(e);}


	}
	public Connection RetourConnexion(){
		return connection;
	}


	/**
	 * Retourne le nombre de lignes
	 * de la table operations
	 */

	public static  int nbLignesBase(){

		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();

			PreparedStatement ps =conn.prepareStatement("SELECT count(*) AS nbLignes FROM operations");

			ResultSet rs = ps.executeQuery();
			 nbLignes=0;
			rs.next();
			nbLignes = rs.getInt("nbLignes");

		}catch (Exception e) {
			e.printStackTrace();
		}
		return nbLignes;

	}

	/**
	 * Retourne l'id le plus grand
	 * de la table operations
	 */

	public static Integer ligneIdMax() {

		ObservableList<modele.Operations> list = FXCollections.observableArrayList();

		try {

			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();
			String sql ="SELECT * FROM operations ORDER BY id DESC LIMIT 1 ";
			pst=conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()){
				list.add(new modele.Operations(rs.getBlob("etatimage"),rs.getString("date"),
						//rs.getInt("montant"),
						rs.getDouble("montant"),
						rs.getString("etat"),
						rs.getString("reference"),
						//rs.getInt("solde"),
						rs.getDouble("solde"),
						rs.getDate("localdate").toLocalDate(),
						rs.getBoolean("etatBool"),
						rs.getInt("id")

						));
		
				idRetour=rs.getInt("id");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return idRetour;

	}
	//********************MODIFICATION 27/03/2024************************************************

	public static void updateEtatBool(int id, boolean newValue) {
        try {
            // Établir la connexion à la base de données
          //  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_donnees", "votre_utilisateur", "votre_mot_de_passe");

            // Préparer la requête de mise à jour
            String sql = "UPDATE operations SET etatBool = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, newValue);
            statement.setInt(2, id);

            // Exécuter la requête de mise à jour
            statement.executeUpdate();

            // Fermer la connexion à la base de données
          //  connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/**
	 *Modifie la table "operations"
	 *aprés modification des données
	 *
	 */

	public static void MAJmodifBase(int idSelected, Operations selectedOperation) {
		String date= selectedOperation.getDate();
		String etat =selectedOperation.getEtat();
		String reference =selectedOperation.getReference();
		Double montant =selectedOperation.getMontant();

		LocalDate localdate= selectedOperation.getLocaldate();
		Boolean etatbool = selectedOperation.isEtatBool();
		Double solde =selectedOperation.getSolde();
		Integer id =selectedOperation.getId();

		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();
			String sql = "UPDATE  operations SET date=?,etat=?,reference=?,solde=?,montant=?,localdate=?,etatbool=? WHERE id =?";

			pst=conn.prepareStatement(sql);
			pst.setString(1,date);
			pst.setString(2,etat);
			pst.setString(3,reference);
			pst.setDouble(4, solde);
			pst.setDouble(5, montant);
			pst.setDate(6, Date.valueOf(localdate));
			pst.setBoolean(7, etatbool);
			pst.setInt(8, idSelected);
			 pst.execute();



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 *Modifie la table "operations"
	 *aprés modification de la colonne etatBool
	 *
	 */

	public static void MAJbaseEtatBool(int idInt, Boolean newValue) {

		Boolean newval =newValue;
		Integer id=idInt;

		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();
			String sql = "UPDATE  operations SET etatbool= ? WHERE id =?";

			pst=conn.prepareStatement(sql);

			pst.setBoolean(1, newval  );
			pst.setInt(2, id );
			//ResultSet rs = ps.executeQuery();
			pst.execute();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *Supprime la ligne de la table "operations"
	 *dont  l'id  est passé en argument
	 *
	 */

	public static void supprimeLigne(int idInt) {

		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();
			String sql = "DELETE FROM  operations  WHERE id =?";

			pst=conn.prepareStatement(sql);

			pst.setInt(1, idInt  );
			pst.execute();

		}catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 *Modifie la table "operations"
	 *
	 *
	 */

	public static void MAJbase() {
		// TODO Auto-generated method stub

		int longueur=Main.operationData.size();
		try {

			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();

			PreparedStatement ps =conn.prepareStatement("DELETE FROM operations");
			ps.execute();

			for (int i = 0;i<longueur;i++) {
				//String value =
				String value1=Main.operationData.get(i).getDate();
				String value2=Main.operationData.get(i).getEtat();
				String value3=Main.operationData.get(i).getReference();
				Double value4=Main.operationData.get(i).getSolde();
				//Integer value5=Main.operationData.get(i).getMontant();
				Double value5=Main.operationData.get(i).getMontant();
				LocalDate value6=Main.operationData.get(i).getLocaldate();
				Integer value7=Main.operationData.get(i).getId();
				Boolean value8 = Main.operationData.get(i).isEtatBool();

				String sql = "insert into operations (date,etat,reference,solde,montant,localdate,id,etatbool)" +
						"values(?,?,?,?,?,?,?,?)";


				pst = conn.prepareStatement(sql);
				pst.setString(1,value1);
				pst.setString(2,value2);
				pst.setString(3,value3);
				pst.setDouble(4,value4);
				//pst.setInt(5,value5);
				pst.setDouble(5,value5);

				pst.setDate(6, Date.valueOf(value6));
				pst.setInt(7,(value7));
				pst.setBoolean(8,value8);

				pst.execute();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *Modifie la table "operations"
	 *aprés insertion d'une nouvelle ligne de données
	 *
	 */

	public static void MAJbaseNouveau(Operations tempOperation){

		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();
			String sql = "insert into operations (date,etat,reference,solde,montant,localdate,etatbool,id)" +
					"values(?,?,?,?,?,?,?,?)";


			String value1= tempOperation.getDate();
			String value2=tempOperation.getEtat();
			String value3=tempOperation.getReference();
			Double value4=tempOperation.getSolde();
			Double value5=tempOperation.getMontant();

			LocalDate value6= tempOperation.getLocaldate();
			boolean value7=tempOperation.isEtatBool();
			Integer value8=tempOperation.getId();

			pst = conn.prepareStatement(sql);
			pst.setString(1,value1);
			pst.setString(2,value2);
			pst.setString(3,value3);
			pst.setDouble(4,value4);
			pst.setDouble(5,value5);

			pst.setDate(6, Date.valueOf(value6));
			pst.setBoolean(7,value7);
			pst.setInt(8,value8);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void MAJsolde() {

		try {
			DataAccessor da = DataAccessor.getDataAccessor();
			Connection conn = da.RetourConnexion();

			String sql =
				    "WITH CalculatedBalance AS (" +
				    "   SELECT " +
				    "       id," +
				    "       localdate," +
				    "       montant," +
				    "       CAST(SUM(montant) OVER (ORDER BY localdate, id) AS NUMERIC(12, 2))  AS solde_cumulatif " +
				    "   FROM " +
				    "       operations " +
				    ") " +
				    "UPDATE operations o " +
				    "SET solde = cb.solde_cumulatif " +
				    "FROM CalculatedBalance cb " +
				    "WHERE o.id = cb.id;";


			pst = conn.prepareStatement(sql);
			pst.execute();

			String sql1 = "SELECT * FROM operations ORDER BY localdate ASC, solde ASC;";
			pst = conn.prepareStatement(sql1);
			pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
