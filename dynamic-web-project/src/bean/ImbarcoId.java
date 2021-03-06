package bean;

public class ImbarcoId implements java.io.Serializable {

	private String codicevolo;
	private String documento;
	private char lettera;
	private int numero;

	public ImbarcoId() {
	}

	public ImbarcoId(String codicevolo, String documento, char lettera,
			int numero) {
		this.codicevolo = codicevolo;
		this.documento = documento;
		this.lettera = lettera;
		this.numero = numero;
	}

	public String getCodicevolo() {
		return this.codicevolo;
	}

	public void setCodicevolo(String codicevolo) {
		this.codicevolo = codicevolo;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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
		if (!(other instanceof ImbarcoId))
			return false;
		ImbarcoId castOther = (ImbarcoId) other;

		return ((this.getCodicevolo() == castOther.getCodicevolo()) || (this
				.getCodicevolo() != null && castOther.getCodicevolo() != null && this
				.getCodicevolo().equals(castOther.getCodicevolo())))
				&& ((this.getDocumento() == castOther.getDocumento()) || (this
						.getDocumento() != null
						&& castOther.getDocumento() != null && this
						.getDocumento().equals(castOther.getDocumento())))
				&& (this.getLettera() == castOther.getLettera())
				&& (this.getNumero() == castOther.getNumero());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodicevolo() == null ? 0 : this.getCodicevolo()
						.hashCode());
		result = 37 * result
				+ (getDocumento() == null ? 0 : this.getDocumento().hashCode());
		result = 37 * result + this.getLettera();
		result = 37 * result + this.getNumero();
		return result;
	}

}
