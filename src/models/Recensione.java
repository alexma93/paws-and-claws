package models;

import java.util.Date;

public class Recensione {
	private Long id;
	private Integer stelle;
	private String testo;
	private Prodotto prodotto;
	private Utente utente;
	private Date dataInserimento;
	
	public Recensione(Integer stelle, String testo, Utente utente,
			Prodotto prodotto) {
		this.stelle = stelle;
		this.testo = testo;
		this.utente = utente;
		this.prodotto = prodotto;
		this.dataInserimento = new Date();
		utente.aggiungiRecensione(this);
	}

	public Long getId() {
		return id;
	}
	
	public Integer getStelle() {
		return stelle;
	}
	
	public void setStelle(Integer stelle) {
		this.stelle = stelle;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prodotto == null) ? 0 : prodotto.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
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
		Recensione other = (Recensione) obj;
		if (prodotto == null) {
			if (other.prodotto != null)
				return false;
		} else if (!prodotto.equals(other.prodotto))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}


}
