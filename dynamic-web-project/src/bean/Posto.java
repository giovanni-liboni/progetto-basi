package bean;

// Generated 20-lug-2014 1.26.43 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Posto generated by hbm2java
 */
public class Posto implements java.io.Serializable {

	private PostoId id;
	private Set imbarcos = new HashSet(0);

	public Posto() {
	}

	public Posto(PostoId id) {
		this.id = id;
	}

	public Posto(PostoId id, Set imbarcos) {
		this.id = id;
		this.imbarcos = imbarcos;
	}

	public PostoId getId() {
		return this.id;
	}

	public void setId(PostoId id) {
		this.id = id;
	}

	public Set getImbarcos() {
		return this.imbarcos;
	}

	public void setImbarcos(Set imbarcos) {
		this.imbarcos = imbarcos;
	}

}