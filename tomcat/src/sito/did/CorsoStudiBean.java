package did;
/**
* Bean per la tabella (campi principali) CorsoStudi
*/

public class CorsoStudiBean {
	//Definizione dei campi del Bean (variabili private: accessibili solo tramite i metodi get e set)
	private int id;
	private String NomeCorsoStudi;
	private String Codice;
	private String Abbreviazione;
	private int Durataanni;
	private String Sede;
	private String Informativa;
	private int id_tipocs;
	private int numeroInsAttivi;
	private int creditiTot;

	//Definizione del costruttore del Bean
    public CorsoStudiBean() {
		id = 1;
		NomeCorsoStudi = null; 
		Codice = null;
		Abbreviazione = null;
		Durataanni = 3;
		Sede = null;
		Informativa = null;
		id_tipocs = 0;
		numeroInsAttivi = 0;
		creditiTot = 0;
    }

    //metodi set
    public void setId(int i) {
		id = i;
    }

	public void setNomeCorsoStudi(String s) {
		NomeCorsoStudi = s;
    }

	public void setCodice(String s) {
		Codice = s;
	}

	public void setAbbreviazione(String s) {
		Abbreviazione = s;
	}

	public void setDurataanni(int d) {
		Durataanni = d;
	}
	
	public void setSede(String s) {
		Sede = s;
	}
	
	public void setInformativa(String s) {
		Informativa = s;
	}
	

    //metodi get
	public int getId() {
		return id;
	}

	public String getNomeCorsoStudi() {
		return NomeCorsoStudi;
	}

	public String getCodice() {
		return Codice;
	}

	public String getAbbreviazione() {
		return Abbreviazione;
	}

	public int getDurataanni() {
		return Durataanni;
	}
	
	public String getSede() {
		return Sede;
	}
	
	public String getInformativa() {
		return Informativa;
	}

	public int getId_tipocs() {
		return id_tipocs;
	}

	public int getNumeroInsAttivi() {
		return numeroInsAttivi;
	}

	public int getCreditiTot() {
		return creditiTot;
	}

	public void setId_tipocs(int id_tipocs) {
		this.id_tipocs = id_tipocs;
	}

	public void setNumeroInsAttivi(int numeroInsAttivi) {
		this.numeroInsAttivi = numeroInsAttivi;
	}

	public void setCreditiTot(int creditiTot) {
		this.creditiTot = creditiTot;
	}
}
