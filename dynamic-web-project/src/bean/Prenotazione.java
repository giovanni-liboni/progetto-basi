package bean;

// Generated 20-lug-2014 1.15.12 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Prenotazione generated by hbm2java
 */
public class Prenotazione implements java.io.Serializable {

	private int id;
	private Volo volo;
	private Passeggero passeggero;
	private Date datarichiesta;
	private Date orarichiesta;
	private Set bigliettos = new HashSet(0);

	public Prenotazione() {
	}

	public Prenotazione(int id, Volo volo, Passeggero passeggero) {
		this.id = id;
		this.volo = volo;
		this.passeggero = passeggero;
	}

	public Prenotazione(int id, Volo volo, Passeggero passeggero,
			Date datarichiesta, Date orarichiesta, Set bigliettos) {
		this.id = id;
		this.volo = volo;
		this.passeggero = passeggero;
		this.datarichiesta = datarichiesta;
		this.orarichiesta = orarichiesta;
		this.bigliettos = bigliettos;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Volo getVolo() {
		return this.volo;
	}

	public void setVolo(Volo volo) {
		this.volo = volo;
	}

	public Passeggero getPasseggero() {
		return this.passeggero;
	}

	public void setPasseggero(Passeggero passeggero) {
		this.passeggero = passeggero;
	}

	public Date getDatarichiesta() {
		return this.datarichiesta;
	}

	public void setDatarichiesta(Date datarichiesta) {
		this.datarichiesta = datarichiesta;
	}

	public Date getOrarichiesta() {
		return this.orarichiesta;
	}

	public void setOrarichiesta(Date orarichiesta) {
		this.orarichiesta = orarichiesta;
	}

	public Set getBigliettos() {
		return this.bigliettos;
	}

	public void setBigliettos(Set bigliettos) {
		this.bigliettos = bigliettos;
	}

}
