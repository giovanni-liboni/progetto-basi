package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PrenotazioneBean implements java.io.Serializable {

	private int id;
	private VoloBean volo;
	private PasseggeroBean passeggero;
	private Date datarichiesta;
	private Date orarichiesta;
	private Set bigliettos = new HashSet(0);

	public PrenotazioneBean() {
	}

	public PrenotazioneBean(int id, VoloBean volo, PasseggeroBean passeggero) {
		this.id = id;
		this.volo = volo;
		this.passeggero = passeggero;
	}

	public PrenotazioneBean(int id, VoloBean volo, PasseggeroBean passeggero,
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
