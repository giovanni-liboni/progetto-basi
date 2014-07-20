package bean;

// Generated 20-lug-2014 1.26.43 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Biglietto generated by hbm2java
 */
public class Biglietto implements java.io.Serializable {

	private BigliettoId id;
	private Prenotazione prenotazione;
	private Volo volo;
	private Passeggero passeggero;
	private int id_1;
	private Date dataemissione;
	private BigDecimal prezzo;
	private Set imbarcos = new HashSet(0);

	public Biglietto() {
	}

	public Biglietto(BigliettoId id, Prenotazione prenotazione, Volo volo,
			Passeggero passeggero, int id_1, Date dataemissione,
			BigDecimal prezzo) {
		this.id = id;
		this.prenotazione = prenotazione;
		this.volo = volo;
		this.passeggero = passeggero;
		this.id_1 = id_1;
		this.dataemissione = dataemissione;
		this.prezzo = prezzo;
	}

	public Biglietto(BigliettoId id, Prenotazione prenotazione, Volo volo,
			Passeggero passeggero, int id_1, Date dataemissione,
			BigDecimal prezzo, Set imbarcos) {
		this.id = id;
		this.prenotazione = prenotazione;
		this.volo = volo;
		this.passeggero = passeggero;
		this.id_1 = id_1;
		this.dataemissione = dataemissione;
		this.prezzo = prezzo;
		this.imbarcos = imbarcos;
	}

	public BigliettoId getId() {
		return this.id;
	}

	public void setId(BigliettoId id) {
		this.id = id;
	}

	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
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

	public int getId_1() {
		return this.id_1;
	}

	public void setId_1(int id_1) {
		this.id_1 = id_1;
	}

	public Date getDataemissione() {
		return this.dataemissione;
	}

	public void setDataemissione(Date dataemissione) {
		this.dataemissione = dataemissione;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public Set getImbarcos() {
		return this.imbarcos;
	}

	public void setImbarcos(Set imbarcos) {
		this.imbarcos = imbarcos;
	}

}
