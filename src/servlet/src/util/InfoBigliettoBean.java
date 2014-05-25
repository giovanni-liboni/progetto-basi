package util;

import java.sql.Date;
import java.sql.Time;

public class InfoBigliettoBean {

	String partenza, arrivo, codicevolo;
	Date datapartenza, dataemissione;
	Time orapartenza;
	float prezzo;
	
	public InfoBigliettoBean()
	{
		partenza = null;
		arrivo = null;
		datapartenza = null;
		prezzo = 0;
		orapartenza = null;
		dataemissione = null;
		codicevolo = null;
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

	public Date getDatapartenza() {
		return datapartenza;
	}

	public void setDatapartenza(Date datapartenza) {
		this.datapartenza = datapartenza;
	}

	public Date getDataemissione() {
		return dataemissione;
	}

	public void setDataemissione(Date dataemissione) {
		this.dataemissione = dataemissione;
	}

	public Time getOrapartenza() {
		return orapartenza;
	}

	public void setOrapartenza(Time orapartenza) {
		this.orapartenza = orapartenza;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getCodicevolo() {
		return codicevolo;
	}

	public void setCodicevolo(String codicevolo) {
		this.codicevolo = codicevolo;
	}
}
