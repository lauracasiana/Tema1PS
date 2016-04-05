package com.teatrulnational.models;

import java.sql.Date;

public class Spectacol {

	private String titlu;
	private String regia;
	private String distributia;
	private Date dataPremierei;
	private int nrBilete;

	public Spectacol(String titlu, String regia, String distributia, Date data,
			int nr) {
		this.titlu = titlu;
		this.regia = regia;
		this.distributia = distributia;
		this.dataPremierei = data;
		this.nrBilete = nr;
	}

	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public String getRegia() {
		return regia;
	}

	public void setRegia(String regia) {
		this.regia = regia;
	}

	public String getDistributia() {
		return distributia;
	}

	public void setDistributia(String distributia) {
		this.distributia = distributia;
	}

	public Date getDataPremierei() {
		return dataPremierei;
	}

	public void setDataPremierei(Date dataPremierei) {
		this.dataPremierei = dataPremierei;
	}

	public int getNrBilete() {
		return nrBilete;
	}

	public void setNrBilete(int nrBilete) {
		this.nrBilete = nrBilete;
	}
	
	
}
