package bean;

public class BigliettoId implements java.io.Serializable {

	private String codicevolo;
	private String documento;

	public BigliettoId() {
	}

	public BigliettoId(String codicevolo, String documento) {
		this.codicevolo = codicevolo;
		this.documento = documento;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BigliettoId))
			return false;
		BigliettoId castOther = (BigliettoId) other;

		return ((this.getCodicevolo() == castOther.getCodicevolo()) || (this
				.getCodicevolo() != null && castOther.getCodicevolo() != null && this
				.getCodicevolo().equals(castOther.getCodicevolo())))
				&& ((this.getDocumento() == castOther.getDocumento()) || (this
						.getDocumento() != null
						&& castOther.getDocumento() != null && this
						.getDocumento().equals(castOther.getDocumento())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodicevolo() == null ? 0 : this.getCodicevolo()
						.hashCode());
		result = 37 * result
				+ (getDocumento() == null ? 0 : this.getDocumento().hashCode());
		return result;
	}

}
