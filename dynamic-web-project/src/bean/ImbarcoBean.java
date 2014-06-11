package bean;

public class ImbarcoBean {

	private char lettera;
	private int numero;
	private String codicevolo;
	private String documento;
	
	public ImbarcoBean(char lettera, int numero, String codicevolo,
			String documento) {
		this.lettera = lettera;
		this.numero = numero;
		this.codicevolo = codicevolo;
		this.documento = documento;
	}
	public char getLettera() {
		return lettera;
	}
	public void setLettera(char lettera) {
		this.lettera = lettera;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
}
