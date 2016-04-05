package com.teatrulnational.models;

public class Bilet {

	private Spectacol spectacol;
	private int rand;
	private int numar;

	public Bilet(Spectacol spec, int rand, int nr) {
		this.spectacol = spec;
		this.rand = rand;
		this.numar = nr;
	}

	public Spectacol getSpectacol() {
		return spectacol;
	}

	public void setSpectacol(Spectacol spectacol) {
		this.spectacol = spectacol;
	}

	public int getRand() {
		return rand;
	}

	public void setRand(int rand) {
		this.rand = rand;
	}

	public int getNumar() {
		return numar;
	}

	public void setNumar(int numar) {
		this.numar = numar;
	}

}
