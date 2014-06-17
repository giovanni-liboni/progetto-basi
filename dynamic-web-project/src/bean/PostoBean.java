package bean;

import java.util.HashSet;
import java.util.Set;

public class PostoBean implements java.io.Serializable {

	private PostoId id;
	private Set imbarcos = new HashSet(0);

	public PostoBean() {
	}

	public PostoBean(PostoId id) {
		this.id = id;
	}

	public PostoBean(PostoId id, Set imbarcos) {
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
