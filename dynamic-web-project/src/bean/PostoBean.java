package bean;

public class PostoBean {

	private char lettera;
	private int numero;
	
	public PostoBean(char lettera, int numero) {
		this.lettera = lettera;
		this.numero = numero;
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
}
