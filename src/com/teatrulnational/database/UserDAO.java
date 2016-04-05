package com.teatrulnational.database;

import java.sql.Connection;
import java.util.ArrayList;

import com.teatrulnational.models.User;

public class UserDAO {

	public User Login(String username, String password) throws Exception {
		User usr;
		Connect c = new Connect();
		Connection conn = c.getConn();

		try {

			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet res = st
					.executeQuery("SELECT * FROM user WHERE username='"
							+ username + "' AND parola='" + password + "'");
			String name = "";
			String usrname = "";
			String pwd = "";
			String rol = "";

			while (res.next()) {
				int id = res.getInt("idUser");
				name = res.getString("nume");
				usrname = res.getString("username");
				pwd = res.getString("parola");
				rol = res.getString("role");

			}
			usr = new User(name, usrname, pwd, rol);
			c.DisConect();
			return usr;
		} catch (Exception e) {
			c.DisConect();
			throw (e);
		}

	}

	public boolean addAngajat(User usr) {
		Connect c = new Connect();
		Connection conn = c.getConn();
		if (!usr.getRole().equals("angajat"))
			return false;
		try {
			String queri = "INSERT INTO `teatrul_national`.`user` (`nume`, `username`, `parola`, `role`) VALUES ('"
					+ usr.getNume()
					+ "', '"
					+ usr.getUsername()
					+ "', '"
					+ usr.getParola() + "', '" + usr.getRole() + "');";
			java.sql.Statement st = conn.createStatement();
			int ok = 1;
			java.sql.ResultSet res = st
					.executeQuery("SELECT * FROM user WHERE username='"
							+ usr.getUsername() + "'");
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

	public ArrayList<User> seeAllAngajat() {
		ArrayList<User> useri = new ArrayList<User>();

		Connect c = new Connect();
		Connection conn = c.getConn();

		try {

			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet res = st.executeQuery("SELECT * FROM user ");

			while (res.next()) {
				String name = "";
				String usrname = "";
				String pwd = "";
				String rol = "";
				int id = res.getInt("idUser");
				name = res.getString("nume");
				usrname = res.getString("username");
				pwd = res.getString("parola");
				rol = res.getString("role");
				User usr = new User(name, usrname, pwd, rol);
				useri.add(usr);

			}

			c.DisConect();

		} catch (Exception e) {
			c.DisConect();

		}
		return useri;
	}
}
