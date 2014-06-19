package bean;

import java.util.HashSet;
import java.util.Set;

public class TrattaBean implements java.io.Serializable {

	private TrattaId id;
	private int durata;
	private double distanza;
	private Set volos = new HashSet(0);

	public TrattaBean() {
	}

	public TrattaBean(TrattaId id, int durata, double distanza) {
		this.id = id;
		this.durata = durata;
		this.distanza = distanza;
	}

	public TrattaBean(TrattaId id, int durata, double distanza, Set volos) {
		this.id = id;
		this.durata = durata;
		this.distanza = distanza;
		this.volos = volos;
	}

	public TrattaId getId() {
		return this.id;
	}

	public void setId(TrattaId id) {
		this.id = id;
	}

	public int getDurata() {
		return this.durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public double getDistanza() {
		return this.distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}

	public Set getVolos() {
		return this.volos;
	}

	public void setVolos(Set volos) {
		this.volos = volos;
	}

}
