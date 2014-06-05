package util;

import java.sql.Date;
import java.sql.Time;

public class InfoPrenotazioneBean {

	String partenza, arrivo, documento, codicevolo;
	Date datarichiesta, datapartenza;
	Time orarichiesta, orapartenza;
	
	public InfoPrenotazioneBean(){
		partenza = null;
		arrivo = null;
		documento = null;
		codicevolo = null;
		datarichiesta = null;
		orarichiesta = null;
		datapartenza = null;
		orapartenza = null;
	}

	public String getPartenza() {
		return partenza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public String getArrivo() {
		return arrivo;
	}

	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCodicevolo() {
		return codicevolo;
	}

	public void setCodicevolo(String codicevolo) {
		this.codicevolo = codicevolo;
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

	public Date getDatapartenza() {
		return datapartenza;
	}

	public void setDatapartenza(Date datapartenza) {
		this.datapartenza = datapartenza;
	}

	public Time getOrapartenza() {
		return orapartenza;
	}

	public void setOrapartenza(Time orapartenza) {
		this.orapartenza = orapartenza;
	}
	
	
}
