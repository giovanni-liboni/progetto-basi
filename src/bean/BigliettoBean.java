package bean;

import java.sql.Date;

public class BigliettoBean {


	int id, id_prenotazione;
	String codicevolo, documento;
	float prezzo;
	Date dataemissione;
	
	public BigliettoBean() {
		id = 0;
		id_prenotazione = 0;
		codicevolo=null;
		documento=null;
		prezzo=0;
		dataemissione=null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_prenotazione() {
		return id_prenotazione;
	}
	public void setId_prenotazione(int id_prenotazione) {
		this.id_prenotazione = id_prenotazione;
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
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public Date getDataemissione() {
		return dataemissione;
	}
	public void setDataemissione(Date dataemissione) {
		this.dataemissione = dataemissione;
	}
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BigliettoBean other = (BigliettoBean) obj;
        if ((this.id == -1) ? (other.id != -1) : 
                 !(this.id==other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != -1 ? Integer.toString(this.id).hashCode() : 0);
        return hash;
    }
}
