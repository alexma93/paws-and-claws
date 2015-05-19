package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utente {
	private Long id;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private Date dataRegistrazione;
	private Indirizzo indirizzo;
	private List<Recensione> recensioni;
	private Map<Long,Ordine> ordini;
	
	public Utente() {
	}
	
	public Utente(String nome, String cognome, Date data, String email,
			String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = data;
		this.dataRegistrazione = new Date();
		this.email = email;
		this.password = password;
		this.recensioni = new ArrayList<Recensione>();
		this.ordini = new HashMap<Long,Ordine>();
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public void aggiungiIndirizzo(String strada, String citta, String stato,
			String cap, String regione) {
		this.indirizzo = new Indirizzo(strada,citta,stato,cap,regione);
		
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public void confermaOrdine(Ordine ordine) {
		this.ordini.put(ordine.getId(), ordine);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
}
