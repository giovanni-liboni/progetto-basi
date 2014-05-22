package did;

public class InsErogatoBean{

	
	int idInsegn;
	// dalla tabella INSEGN
    String nomeins,
           codiceins,
    // dalla tabella INSEROGATO
           annoaccademico,
           nomemodulo,
           discriminantemodulo,
           nomeunita,
           crediti,
           hamoduli,
           haunita,
           mutuato,
           modulo,
    // dalla tabella DISCRIMINANTE
           descrizione;
    
    public InsErogatoBean(){
    	nomeins = "";
        codiceins = "";
        annoaccademico = "";
        nomemodulo = "";
        discriminantemodulo= "";
        nomeunita= "";
        crediti= "";
        hamoduli= "";
        haunita= "";
        mutuato= "";
        descrizione = "";

    }

	public String getNomeins() {
		return nomeins;
	}

	public String getCodiceins() {
		return codiceins;
	}

	public String getAnnoaccademico() {
		return annoaccademico;
	}

	public String getNomemodulo() {
		return nomemodulo;
	}

	public String getDiscriminantemodulo() {
		return discriminantemodulo;
	}

	public String getNomeunita() {
		return nomeunita;
	}

	public String getCrediti() {
		return crediti;
	}

	public String getHamoduli() {
		return hamoduli;
	}

	public String getHaunita() {
		return haunita;
	}

	public String getMutuato() {
		return mutuato;
	}

	public String getDescrizione() {
		return descrizione;
	}
	

	public void setNomeins(String nomeins) {
		if (nomeins == null || nomeins.compareTo("null") == 0)
			this.nomeins = "";
		else	
			this.nomeins = nomeins;
	}

	public void setCodiceins(String codiceins) {
		if (codiceins == null || codiceins.compareTo("null") == 0)
			this.codiceins = "";
		else	
			this.codiceins = codiceins;
	}

	public void setAnnoaccademico(String annoaccademico) {
		if (annoaccademico == null || annoaccademico.compareTo("null") == 0)
			this.annoaccademico = "";
		else	
			this.annoaccademico = annoaccademico;
	}

	public void setNomemodulo(String nomemodulo) {
		if (nomemodulo == null || nomemodulo.compareTo("null") == 0)
			this.nomemodulo = "";
		else	
			this.nomemodulo = nomemodulo;
	}

	public void setDiscriminantemodulo(String discriminantemodulo) {
		if (discriminantemodulo == null || discriminantemodulo.compareTo("null") == 0)
			this.discriminantemodulo = "";
		else		
			this.discriminantemodulo = discriminantemodulo;
	}

	public void setNomeunita(String nomeunita) {
		if (nomeunita == null || nomeunita.compareTo("null") == 0)
			this.nomeunita = "";
		else
			this.nomeunita = nomeunita;
	}

	public void setCrediti(String crediti) {
		if (crediti == null || crediti.compareTo("null") == 0)
			this.crediti = "";
		else
			this.crediti = crediti;
	}

	public void setHamoduli(String hamoduli) {
		if (hamoduli == null || hamoduli.compareTo("null") == 0)
			this.hamoduli = "";
		else
			this.hamoduli = hamoduli;
	}

	public void setHaunita(String haunita) {
		if (haunita == null || haunita.compareTo("null") == 0)
			this.haunita = "";
		else
			this.haunita = haunita;
	}

	public void setMutuato(String mutuato) {
		if (mutuato == null || mutuato.compareTo("null") == 0)
			this.mutuato = "";
		else
			this.mutuato = mutuato;
	}

	public void setDescrizione(String descrizione) {
		if (descrizione == null || descrizione.compareTo("null") == 0)
			this.descrizione = "";
		else
			this.descrizione = descrizione;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public int getIdInsegn() {
		return idInsegn;
	}

	public void setIdInsegn(int idInsegn) {
		this.idInsegn = idInsegn;
	}
}
