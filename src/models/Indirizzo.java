package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indirizzo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String stato;
	@Column
	private String regione;
	@Column
	private String citta;
	@Column
	private String strada;
	@Column
	private String cap;
	
	public Indirizzo() {
		
	}
	
	public Indirizzo(String strada, String citta, String stato, String cap,
			String regione) {
		this.strada = strada;
		this.citta = citta;
		this.regione = regione;
		this.stato = stato;
		this.cap = cap;
	}

	public Long getId() {
	        return id;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getRegione() {
		return regione;
	}
	
	public void setRegione(String regione) {
		this.regione = regione;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getStrada() {
		return strada;
	}
	
	public void setStrada(String strada) {
		this.strada = strada;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}

}
