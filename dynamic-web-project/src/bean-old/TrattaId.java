package bean;

public class TrattaId implements java.io.Serializable {

	private String partenza;
	private String arrivo;

	public TrattaId() {
	}

	public TrattaId(String partenza, String arrivo) {
		this.partenza = partenza;
		this.arrivo = arrivo;
	}

	public String getPartenza() {
		return this.partenza;
	}

	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	public String getArrivo() {
		return this.arrivo;
	}

	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TrattaId))
			return false;
		TrattaId castOther = (TrattaId) other;

		return ((this.getPartenza() == castOther.getPartenza()) || (this
				.getPartenza() != null && castOther.getPartenza() != null && this
				.getPartenza().equals(castOther.getPartenza())))
				&& ((this.getArrivo() == castOther.getArrivo()) || (this
						.getArrivo() != null && castOther.getArrivo() != null && this
						.getArrivo().equals(castOther.getArrivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPartenza() == null ? 0 : this.getPartenza().hashCode());
		result = 37 * result
				+ (getArrivo() == null ? 0 : this.getArrivo().hashCode());
		return result;
	}

}
