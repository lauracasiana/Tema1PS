package com.teatrulnational.database;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.teatrulnational.models.Bilet;
import com.teatrulnational.models.Spectacol;

public class TicketDAO {

	public boolean addTicket(Bilet b) {
		Connect c = new Connect();
		Connection conn = c.getConn();
		if (checkValidity(b) == true) {
			try {
				int idspec = getID(b);
				String queri = "INSERT INTO `teatrul_national`.`bilet` (`idSpectacol`, `rand`, `numar`) VALUES ('"
						+ idspec
						+ "', '"
						+ b.getRand()
						+ "', '"
						+ b.getNumar()
						+ "');";
				java.sql.Statement st = conn.createStatement();
				st.executeUpdate(queri);

				int nrBileteNou = getNrBilete(b) - b.getNumar();
				String queri2 = "UPDATE `teatrul_national`.`spectacol` SET `nrbilete`='"
						+ nrBileteNou
						+ "' WHERE `idspectacol`='"
						+ idspec
						+ "';";
				java.sql.Statement st2 = conn.createStatement();
				st2.executeUpdate(queri2);

				c.DisConect();

				return true;

			} catch (Exception e) {
				c.DisConect();
				e.printStackTrace();
				return false;
			}
		} else
			return false;
	}

	private int getNrBilete(Bilet b) {
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {
			String queri = "SELECT * FROM `teatrul_national`.`spectacol` WHERE `titlu`='"
					+ b.getSpectacol().getTitlu() + "';";
			java.sql.Statement st = conn.createStatement();

			java.sql.ResultSet res = st.executeQuery(queri);
			while (res.next()) {
				return res.getInt("nrbilete");
			}
			c.DisConect();

		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();

		}
		return -1;
	}

	private int getID(Bilet b) {
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {
			String queri = "SELECT * FROM `teatrul_national`.`spectacol` WHERE `titlu`='"
					+ b.getSpectacol().getTitlu() + "';";
			java.sql.Statement st = conn.createStatement();

			java.sql.ResultSet res = st.executeQuery(queri);
			while (res.next()) {
				return res.getInt("idspectacol");
			}
			c.DisConect();

		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();

		}
		return -1;
	}

	private boolean checkValidity(Bilet b) {

		Connect c = new Connect();
		Connection conn = c.getConn();
		int bilete = 0;
		try {
			String queri = "SELECT * FROM `teatrul_national`.`spectacol` WHERE `titlu`='"
					+ b.getSpectacol().getTitlu() + "';";
			java.sql.Statement st = conn.createStatement();

			java.sql.ResultSet res = st.executeQuery(queri);

			while (res.next()) {
				bilete = res.getInt("nrbilete");
			}
			c.DisConect();

		} catch (Exception e) {
			c.DisConect();
			e.printStackTrace();

		}
		if (b.getNumar() <= bilete)
			return true;
		else
			return false;
	}

	public ArrayList<Bilet> seeAllTickets() {
		ArrayList<Bilet> tickets = new ArrayList<Bilet>();
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {

			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet res = st.executeQuery("SELECT * FROM bilet ");

			while (res.next()) {
				int rand;
				int numar;

				int id = res.getInt("idbilet");
				Spectacol spec = getSpectacol(res.getInt("idspectacol"));
				rand = res.getInt("rand");
				numar = res.getInt("numar");
				Bilet b = new Bilet(spec, rand, numar);
				tickets.add(b);

			}

			c.DisConect();

		} catch (Exception e) {
			c.DisConect();

		}
		return tickets;
	}

	private Spectacol getSpectacol(int int1) {
		Spectacol spec;
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {

			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet res = st
					.executeQuery("SELECT * FROM spectacol WHERE `idspectacol`='"
							+ int1 + "';");

			while (res.next()) {
				String titlu = "";
				String regia = "";
				String distributia = "";
				Date data;
				int nr;

				titlu = res.getString("titlu");
				regia = res.getString("regia");
				distributia = res.getString("distributia");
				data = res.getDate("datapremierei");
				nr = res.getInt("nrbilete");
				spec = new Spectacol(titlu, regia, distributia, data, nr);
				return spec;

			}

			c.DisConect();

		} catch (Exception e) {
			c.DisConect();
			return null;
		}
		return null;
	}
}
