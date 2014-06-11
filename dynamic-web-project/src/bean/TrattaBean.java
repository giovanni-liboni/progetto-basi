package bean;

public class TrattaBean {


	private String partenza;
	private String arrivo;
	private int durata;
	private float distanza;
	
	public TrattaBean(){}
	
	public TrattaBean(String partenza, String arrivo, int durata, float distanza) {
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.durata = durata;
		this.distanza = distanza;
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
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public float getDistanza() {
		return distanza;
	}
	public void setDistanza(float distanza) {
		this.distanza = distanza;
	}
}
