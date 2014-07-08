package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VoloBean implements java.io.Serializable {

	private String codicevolo;
	private TrattaBean tratta;
	private Date datapartenza;
	private Date orapartenza;
	private String tipoaereo;
	private int capienza;
	private Set prenotaziones = new HashSet(0);
	private Set bigliettos = new HashSet(0);

	public VoloBean() {
	}

	public VoloBean(String codicevolo, TrattaBean tratta, Date datapartenza,
			Date orapartenza, String tipoaereo, int capienza) {
		this.codicevolo = codicevolo;
		this.tratta = tratta;
		this.datapartenza = datapartenza;
		this.orapartenza = orapartenza;
		this.tipoaereo = tipoaereo;
		this.capienza = capienza;
	}

	public VoloBean(String codicevolo, TrattaBean tratta, Date datapartenza,
			Date orapartenza, String tipoaereo, int capienza,
			Set prenotaziones, Set bigliettos) {
		this.codicevolo = codicevolo;
		this.tratta = tratta;
		this.datapartenza = datapartenza;
		this.orapartenza = orapartenza;
		this.tipoaereo = tipoaereo;
		this.capienza = capienza;
		this.prenotaziones = prenotaziones;
		this.bigliettos = bigliettos;
	}

	public String getCodicevolo() {
		return this.codicevolo;
	}

	public void setCodicevolo(String codicevolo) {
		this.codicevolo = codicevolo;
	}

	public TrattaBean getTratta() {
		return this.tratta;
	}

	public void setTratta(TrattaBean tratta) {
		this.tratta = tratta;
	}

	public Date getDatapartenza() {
		return this.datapartenza;
	}

	public void setDatapartenza(Date datapartenza) {
		this.datapartenza = datapartenza;
	}

	public Date getOrapartenza() {
		return this.orapartenza;
	}

	public void setOrapartenza(Date orapartenza) {
		this.orapartenza = orapartenza;
	}

	public String getTipoaereo() {
		return this.tipoaereo;
	}

	public void setTipoaereo(String tipoaereo) {
		this.tipoaereo = tipoaereo;
	}

	public int getCapienza() {
		return this.capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public Set getPrenotaziones() {
		return this.prenotaziones;
	}

	public void setPrenotaziones(Set prenotaziones) {
		this.prenotaziones = prenotaziones;
	}

	public Set getBigliettos() {
		return this.bigliettos;
	}

	public void setBigliettos(Set bigliettos) {
		this.bigliettos = bigliettos;
	}
	@Override
	public int hashCode() {
		return this.codicevolo.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof VoloBean) ) 
			return false;
		if( ((VoloBean) obj).getTratta().equals(this.getTratta()) && ((VoloBean) obj).getCodicevolo().equals(this.getCodicevolo()))
			return true;
		
		return false;
	}

}
