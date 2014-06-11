package bean;

import java.sql.Date;
import java.sql.Time;

public class PrenotazioneBean {



	int id;
	String codicevolo, documento;
	Date datarichiesta;
	Time orarichiesta;
	
	public PrenotazioneBean(){
		id=0;
		codicevolo = null;
		documento = null;
		datarichiesta = null;
		orarichiesta = null;
	}
	public PrenotazioneBean(int id, String codicevolo, String documento,
			Date datarichiesta, Time orarichiesta) {
		this.id = id;
		this.codicevolo = codicevolo;
		this.documento = documento;
		this.datarichiesta = datarichiesta;
		this.orarichiesta = orarichiesta;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodicevolo() {
		return codicevolo;
	}

	public void setCodicevolo(String codicevolo) {
		this.codicevolo = codicevolo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getDatarichiesta() {
		return datarichiesta;
	}

	public void setDatarichiesta(Date datarichiesta) {
		this.datarichiesta = datarichiesta;
	}

	public Time getOrarichiesta() {
		return orarichiesta;
	}

	public void setOrarichiesta(Time orarichiesta) {
		this.orarichiesta = orarichiesta;
	}
}
