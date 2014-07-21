package bean;

// Generated 20-lug-2014 1.26.43 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tratta generated by hbm2java
 */
public class Tratta implements java.io.Serializable {

	private TrattaId id;
	private int durata;
	private BigDecimal distanza;
	private Set volos = new HashSet(0);

	public Tratta() {
	}

	public Tratta(TrattaId id, int durata, BigDecimal distanza) {
		this.id = id;
		this.durata = durata;
		this.distanza = distanza;
	}

	public Tratta(TrattaId id, int durata, BigDecimal distanza, Set volos) {
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

	public BigDecimal getDistanza() {
		return this.distanza;
	}

	public void setDistanza(BigDecimal distanza) {
		this.distanza = distanza;
	}

	public Set getVolos() {
		return this.volos;
	}

	public void setVolos(Set volos) {
		this.volos = volos;
	}

}
