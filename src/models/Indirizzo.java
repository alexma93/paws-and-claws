package models;

public class Indirizzo {
	private Long id;
	private String stato;
	private String regione;
	private String citta;
	private String strada;
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
