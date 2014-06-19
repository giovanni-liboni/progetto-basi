package bean;

import java.util.HashSet;
import java.util.Set;

public class PasseggeroBean implements java.io.Serializable {

	private String documento;
	private String login;
	private String password;
	private String nazionalita;
	private String nome;
	private String cognome;
	private Integer numvoli;
	private Integer miglia;
	private Boolean tessera;
	private Set prenotaziones = new HashSet(0);
	private Set bigliettos = new HashSet(0);

	public PasseggeroBean() {
	}

	public PasseggeroBean(String documento, String login, String password,
			String nazionalita, String nome, String cognome) {
		this.documento = documento;
		this.login = login;
		this.password = password;
		this.nazionalita = nazionalita;
		this.nome = nome;
		this.cognome = cognome;
	}

	public PasseggeroBean(String documento, String login, String password,
			String nazionalita, String nome, String cognome, Integer numvoli,
			Integer miglia, Boolean tessera, Set prenotaziones, Set bigliettos) {
		this.documento = documento;
		this.login = login;
		this.password = password;
		this.nazionalita = nazionalita;
		this.nome = nome;
		this.cognome = cognome;
		this.numvoli = numvoli;
		this.miglia = miglia;
		this.tessera = tessera;
		this.prenotaziones = prenotaziones;
		this.bigliettos = bigliettos;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNazionalita() {
		return this.nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getNumvoli() {
		return this.numvoli;
	}

	public void setNumvoli(Integer numvoli) {
		this.numvoli = numvoli;
	}

	public Integer getMiglia() {
		return this.miglia;
	}

	public void setMiglia(Integer miglia) {
		this.miglia = miglia;
	}

	public Boolean getTessera() {
		return this.tessera;
	}

	public void setTessera(Boolean tessera) {
		this.tessera = tessera;
	}

	public Set getPrenotaziones() {
		return this.prenotaziones;
	}

	public void setPrenotaziones(Set prenotaziones) {
		this.prenotaziones = prenotaziones;
	}

	public Set getBigliettos() {
		return this.bigliettos;
	}

	public void setBigliettos(Set bigliettos) {
		this.bigliettos = bigliettos;
	}

}
