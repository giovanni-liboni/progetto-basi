package bean;

public class PasseggeroBean {



	String nome, cognome, documento, nazionalita,login;
	int numvoli, miglia;
	boolean tessera;
	
	public PasseggeroBean()
	{
		nome = null;
		cognome = null;
		documento = null;
		nazionalita = null;
		login = null;
		numvoli = 0;
		miglia = 0;
	}
	public PasseggeroBean(String nome, String cognome, String documento,
			String nazionalita, String login, int numvoli, int miglia,
			boolean tessera) {
		this.nome = nome;
		this.cognome = cognome;
		this.documento = documento;
		this.nazionalita = nazionalita;
		this.login = login;
		this.numvoli = numvoli;
		this.miglia = miglia;
		this.tessera = tessera;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public int getNumvoli() {
		return numvoli;
	}

	public void setNumvoli(int numvoli) {
		this.numvoli = numvoli;
	}

	public int getMiglia() {
		return miglia;
	}

	public void setMiglia(int miglia) {
		this.miglia = miglia;
	}

	public boolean isTessera() {
		return tessera;
	}

	public void setTessera(boolean tessera) {
		this.tessera = tessera;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
