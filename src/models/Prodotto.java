package models;

import java.awt.Image;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prodotto {
	private Long id;
	private String nome;
	private String descrizione;
	private Integer prezzoDiListino;
	private String codice;
	private Date dataInserimento;
	private Integer quantita;
	private String taglia;
	private Specie specie;
	private Sconto sconto;
	private Map<String,Fornitore> fornitori;
	private List<Recensione> recensioni;
	private Image foto;
	
	public Specie getSpecie() {
		return specie;
	}

	public void setSpecie(Specie specie) {
		this.specie = specie;
	}

	public Map<String, Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(Map<String, Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}

	public Prodotto(String codice, String descrizione, String nome,
			Integer prezzo, Integer quantita) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.nome = nome;
		this.prezzoDiListino = prezzo;
		this.dataInserimento = new Date();
		this.quantita = quantita;
		this.fornitori = new HashMap<String,Fornitore>();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Integer getPrezzoDiListino() {
		return prezzoDiListino;
	}
	
	public void setPrezzoDiListino(Integer prezzoDiListino) {
		this.prezzoDiListino = prezzoDiListino;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public Date getDataInserimento() {
		return dataInserimento;
	}
	
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	public Sconto getSconto() {
		return sconto;
	}

	public void setSconto(Sconto sconto) {
		this.sconto = sconto;
	}

	public String getTaglia() {
		return taglia;
	}
	
	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public void addFornitore(Fornitore f) {
		this.fornitori.put(f.getIva(),f);
	}

	public void setImmagine(Image immagine) {
		this.foto = immagine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		Prodotto other = (Prodotto) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	public void aggiungiSconto(String descrizione, Integer percentuale) {
		this.sconto = new Sconto(descrizione,percentuale);
	}

	public void inserisciRecensione(Integer stelle, String testo,
			Utente utente) {
		Recensione r = new Recensione(stelle,testo,utente,this);
		this.recensioni.add(r);
		
	}
	
}
