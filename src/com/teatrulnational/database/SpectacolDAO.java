package com.teatrulnational.database;

import java.sql.Connection;

import com.teatrulnational.models.Spectacol;

public class SpectacolDAO {

	public boolean addSpectacol(Spectacol spec) {
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {
			String queri = "INSERT INTO `teatrul_national`.`spectacol` (`titlu`, `regia`, `distributia`, `datapremierei`, `nrbilete`) VALUES ('"
					+ spec.getTitlu()
					+ "', '"
					+ spec.getRegia()
					+ "', '"
					+ spec.getDistributia()
					+ "', '"
					+ spec.getDataPremierei()
					+ "', '" + spec.getNrBilete() + "');";
			java.sql.Statement st = conn.createStatement();
			int ok = 1;
			java.sql.ResultSet res = st
					.executeQuery("SELECT * FROM spectacol WHERE titlu='"
							+ spec.getTitlu() + "'");
			while (res.next()) {
				ok = 0;
			}
			if (ok == 1) {
				st.executeUpdate(queri);
				c.DisConect();
				return true;
			}
			return false;
		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteSpectacol(String titlu) {
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {
			String queri = "DELETE FROM `teatrul_national`.`spectacol` WHERE `titlu`='"
					+ titlu + "';";
			java.sql.Statement st = conn.createStatement();

			st.executeUpdate(queri);
			c.DisConect();
			return true;

		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateSpectacol(Spectacol spec) {
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {
			int id = getID(spec);
			String queri = "UPDATE `teatrul_national`.`spectacol` SET `titlu`='"
					+ spec.getTitlu()
					+ "', `regia`='"
					+ spec.getRegia()
					+ "', `distributia`='"
					+ spec.getDistributia()
					+ "', datapremierei='"
					+ spec.getDataPremierei()
					+ "', `nrbilete`='"
					+ spec.getNrBilete()
					+ "' WHERE `idspectacol`='" + id + "';";
			java.sql.Statement st = conn.createStatement();

			st.executeUpdate(queri);
			c.DisConect();
			return true;

		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();
			return false;
		}
	}

	private int getID(Spectacol spec) {
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {
			String queri = "SELECT * FROM `teatrul_national`.`spectacol` WHERE `titlu`='"
					+ spec.getTitlu() + "';";
			java.sql.Statement st = conn.createStatement();

			java.sql.ResultSet res = st.executeQuery(queri);
			while(res.next()){
				return res.getInt("idspectacol");
			}
			c.DisConect();
			

		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();
			
		}
		return -1;
	}
}
