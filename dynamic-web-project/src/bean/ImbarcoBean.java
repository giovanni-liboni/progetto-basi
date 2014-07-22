package bean;

public class ImbarcoBean implements java.io.Serializable {

	private ImbarcoId id;
	private PostoBean posto;
	private BigliettoBean biglietto;
	private Boolean imbarcato;

	public ImbarcoBean() {
	}

	public ImbarcoBean(ImbarcoId id, PostoBean posto, BigliettoBean biglietto) {
		this.id = id;
		this.posto = posto;
		this.biglietto = biglietto;
	}

	public ImbarcoBean(ImbarcoId id, PostoBean posto, BigliettoBean biglietto,
			Boolean imbarcato) {
		this.id = id;
		this.posto = posto;
		this.biglietto = biglietto;
		this.imbarcato = imbarcato;
	}

	public ImbarcoId getId() {
		return this.id;
	}

	public void setId(ImbarcoId id) {
		this.id = id;
	}

	public PostoBean getPosto() {
		return this.posto;
	}

	public void setPosto(PostoBean posto) {
		this.posto = posto;
	}

	public BigliettoBean getBiglietto() {
		return this.biglietto;
	}

	public void setBiglietto(BigliettoBean biglietto) {
		this.biglietto = biglietto;
	}

	public Boolean getImbarcato() {
		return this.imbarcato;
	}

	public void setImbarcato(Boolean imbarcato) {
		this.imbarcato = imbarcato;
	}

}
