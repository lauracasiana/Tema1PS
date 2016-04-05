package com.teatrulnational.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.teatrulnational.database.UserDAO;
import com.teatrulnational.models.User;

public class UserManager {

	public User Login(String username, String password) throws Exception {
		UserDAO ud = new UserDAO();
		String newpwd = getMD5Password(password);
		User usr = ud.Login(username, newpwd);
		return usr;
	}

	public boolean addAngajat(User ang) {
		boolean ok;
		UserDAO ud = new UserDAO();
		String newpswd;
		try {
			newpswd = getMD5Password(ang.getParola());
			ang.setParola(newpswd);
		} catch (NoSuchAlgorithmException e) {
			ok = false;
		}

		ok = ud.addAngajat(ang);
		return ok;
	}

	public ArrayList<User> seeAllAngajati() {
		UserDAO ud = new UserDAO();
		ArrayList<User> usr = ud.seeAllAngajat();
		return usr;
	}

	private String getMD5Password(String pwd) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pwd.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}
}
