package bean;

import java.sql.Date;
import java.sql.Time;

public class VoloBean {



	String partenza, arrivo, tipoaereo, codicevolo;
	Date datapartenza;
	Time orapartenza;
	int capienza, durata,distanza;
	
	public VoloBean()
	{
		partenza = null;
		arrivo = null;
		tipoaereo = null;
		codicevolo = null;
		capienza = 0;
		datapartenza = null;
		orapartenza = null;
		distanza = 0;
		durata = 0;
	}
	public VoloBean(String partenza, String arrivo, String tipoaereo,
			String codicevolo, Date datapartenza, Time orapartenza,
			int capienza, int durata, int distanza) {
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.tipoaereo = tipoaereo;
		this.codicevolo = codicevolo;
		this.datapartenza = datapartenza;
		this.orapartenza = orapartenza;
		this.capienza = capienza;
		this.durata = durata;
		this.distanza = distanza;
	}
	public String getPartenza() {
		return partenza;
	}

	public String getArrivo() {
		return arrivo;
	}

	public String getTipoaereo() {
		return tipoaereo;
	}

	public String getCodicevolo() {
		return codicevolo;
	}

	public Date getDatapartenza() {
		return datapartenza;
	}

	public Time getOrapartenza() {
		return orapartenza;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}

	public void setTipoaereo(String tipoaereo) {
		this.tipoaereo = tipoaereo;
	}

	public void setCodicevolo(String codicevolo) {
		this.codicevolo = codicevolo;
	}

	public void setDatapartenza(Date datapartenza) {
		this.datapartenza = datapartenza;
	}

	public void setOrapartenza(Time orapartenza) {
		this.orapartenza = orapartenza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public int getDistanza() {
		return distanza;
	}

	public void setDistanza(int distanza) {
		this.distanza = distanza;
	}
}
