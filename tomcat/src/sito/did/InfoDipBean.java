package did;

public class InfoDipBean {

	String nome, cognome;
	
	public InfoDipBean()
	{
		nome = null;
		cognome = null;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
