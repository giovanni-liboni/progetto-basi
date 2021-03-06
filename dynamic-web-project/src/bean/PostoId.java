package bean;

public class PostoId implements java.io.Serializable {

	private char lettera;
	private int numero;

	public PostoId() {
	}

	public PostoId(char lettera, int numero) {
		this.lettera = lettera;
		this.numero = numero;
	}

	public char getLettera() {
		return this.lettera;
	}

	public void setLettera(char lettera) {
		this.lettera = lettera;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PostoId))
			return false;
		PostoId castOther = (PostoId) other;

		return (this.getLettera() == castOther.getLettera())
				&& (this.getNumero() == castOther.getNumero());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getLettera();
		result = 37 * result + this.getNumero();
		return result;
	}

}
