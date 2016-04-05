package com.teatrulnational.models;

public class User {

	private String nume;
	private String username;
	private String parola;
	private String role;

	public User(String nume, String username, String parola, String role) {
		this.nume = nume;
		this.username = username;
		this.parola = parola;
		this.role = role;
	}

	public String getNume() {
		return nume;
	}

	public String getUsername() {
		return username;
	}

	public String getParola() {
		return parola;
	}

	public String getRole() {
		return role;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

}
