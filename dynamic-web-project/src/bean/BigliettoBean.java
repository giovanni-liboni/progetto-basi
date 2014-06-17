package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BigliettoBean implements java.io.Serializable {

	private BigliettoId id;
	private PrenotazioneBean prenotazione;
	private VoloBean volo;
	private PasseggeroBean passeggero;
	private int id_1;
	private Date dataemissione;
	private double prezzo;
	private Set imbarcos = new HashSet(0);

	public BigliettoBean() {
	}

	public BigliettoBean(BigliettoId id, PrenotazioneBean prenotazione, VoloBean volo,
			PasseggeroBean passeggero, int id_1, Date dataemissione, double prezzo) {
		this.id = id;
		this.prenotazione = prenotazione;
		this.volo = volo;
		this.passeggero = passeggero;
		this.id_1 = id_1;
		this.dataemissione = dataemissione;
		this.prezzo = prezzo;
	}

	public BigliettoBean(BigliettoId id, PrenotazioneBean prenotazione, VoloBean volo,
			PasseggeroBean passeggero, int id_1, Date dataemissione, double prezzo,
			Set imbarcos) {
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

	public PrenotazioneBean getPrenotazione() {
		return this.prenotazione;
	}

	public void setPrenotazione(PrenotazioneBean prenotazione) {
		this.prenotazione = prenotazione;
	}

	public VoloBean getVolo() {
		return this.volo;
	}

	public void setVolo(VoloBean volo) {
		this.volo = volo;
	}

	public PasseggeroBean getPasseggero() {
		return this.passeggero;
	}

	public void setPasseggero(PasseggeroBean passeggero) {
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

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Set getImbarcos() {
		return this.imbarcos;
	}

	public void setImbarcos(Set imbarcos) {
		this.imbarcos = imbarcos;
	}

}
