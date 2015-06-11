package models;

import java.awt.Image;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.Part;

import org.primefaces.component.link.Link;

@Entity
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p")
public class Prodotto {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Float prezzoDiListino;
	
	@Column(length = 2000)
	private String descrizione;
	
	@Column(nullable = false)
	private String codice;
	
	@Temporal (TemporalType.DATE)
	private Date dataInserimento;
	
	@Column
	private Integer quantita;
	
	@Column
	private String specie;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Sconto sconto;
	
	@ManyToMany
	private Map<String,Fornitore> fornitori;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name = "recensione_id")
	private List<Recensione> recensioni;
	
	@Column
	private Integer votoMedio;
	
	@Column
	private byte[] foto;

	public Prodotto(){}
	
	public Prodotto(String nome, Float prezzoDiListino, String descrizione,
			String codice, Integer quantita, String specie, Sconto sconto,
			Integer votoMedio, byte[] foto) {
		this.nome = nome;
		this.prezzoDiListino = prezzoDiListino;
		this.descrizione = descrizione;
		this.codice = codice;
		this.quantita = quantita;
		this.specie = specie;
		this.sconto = sconto;
		this.votoMedio = votoMedio;
		this.foto = foto;
		this.dataInserimento = new Date();
		this.fornitori = new HashMap<String,Fornitore>();
		this.recensioni = new LinkedList<>();
		this.votoMedio = 0;
	}

	public Prodotto(String codice, String descrizione, String nome,
			Float prezzo, Integer quantita, byte[] foto) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.nome = nome;
		this.prezzoDiListino = prezzo;
		this.dataInserimento = new Date();
		this.quantita = quantita;
		this.fornitori = new HashMap<String,Fornitore>();
		this.votoMedio = 0;
		this.foto = foto;
	}

	public void aggiungiSconto(String descrizione, Integer percentuale) {
		this.sconto = new Sconto(descrizione,percentuale);
	}

	public void inserisciRecensione(Integer stelle, String testo,
			Utente utente) {
		Recensione r = new Recensione(stelle,testo,utente,this);
		this.recensioni.add(r);
		
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
	
	public Float getPrezzoDiListino() {
		return prezzoDiListino;
	}
	
	public void setPrezzoDiListino(Float prezzoDiListino) {
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

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public void addFornitore(Fornitore f) {
		this.fornitori.put(f.getIva(),f);
	}

	public void setImmagine(byte[] immagine) {
		this.foto = immagine;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public Map<String, Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(Map<String, Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public Integer getVotoMedio() {
		return votoMedio;
	}

	public void setVotoMedio(Integer votoMedio) {
		this.votoMedio = votoMedio;
	}
	
}
