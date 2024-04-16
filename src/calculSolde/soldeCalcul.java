package calculSolde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import application.Main;
import application.controller;
import javaFxPostgres.DataAccessor;

public class soldeCalcul {

	PreparedStatement pst = null;
	String sql;
	DataAccessor da = DataAccessor.getDataAccessor();
	Connection conn = da.RetourConnexion();
	private LocalDate nouvelledate ;

	public soldeCalcul(LocalDate nouvelledate) {

		this.nouvelledate = nouvelledate;

		generesolde();
	}

	public  void generesolde() {
		// TODO Auto-generated method stub

		//SELECT * FROM operations WHERE (localdate = '2023-03-07')  *******Dans pgadmin4

		Main.operationData.clear();
		String format = "*";

		try {

			/*	System.out.println("nouvelledate "+nouvelledate);
			sql = "SELECT * FROM operations WHERE localdate >= ? ";
			pst=conn.prepareStatement(sql);

			pst.setDate(1,java.sql.Date.valueOf( nouvelledate ));
			pst.execute();*/


			sql ="CREATE TABLE operations_tmp AS SELECT * FROM operations ORDER BY localdate ;"
					+ "	DROP TABLE operations;"
					+ "	ALTER TABLE operations_tmp RENAME TO operations;";


			pst=conn.prepareStatement(sql);

			pst.execute();


			/*sql = "SELECT * FROM operations WHERE localdate >= ? ";
			pst=conn.prepareStatement(sql);
			pst.setDate(1,java.sql.Date.valueOf( nouvelledate ));
			//pst.execute();
			pst.execute();


			ResultSet rs = pst.executeQuery();

			while (rs.next()){
				Main.operationData.add(new modele.Operations(rs.getString("date"),

						rs.getInt("montant"),
						rs.getString("etat"),
						rs.getString("reference"),
						rs.getInt("solde"),
						rs.getDate("localdate").toLocalDate()

						));
				//System.out.println("rs.getString date "+rs.getString("date"));
				//calculSolde();
			}*/



			//soldeCalcul.
			controller.MoisAnneeEnCoursInit( format);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
