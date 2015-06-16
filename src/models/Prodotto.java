package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;






import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Prodotto p")
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
	
	@ManyToMany
	private List<Fornitore> fornitori;
	
	@Column
	private Integer votoMedio;
	
	@Column
	private byte[] foto;

	public Prodotto(){}
	
	public Prodotto(String nome, Float prezzoDiListino, String descrizione,
			String codice, Integer quantita, String specie,
			Integer votoMedio, byte[] foto) {
		this.nome = nome;
		this.prezzoDiListino = prezzoDiListino;
		this.descrizione = descrizione;
		this.codice = codice;
		this.quantita = quantita;
		this.specie = specie;
		this.votoMedio = votoMedio;
		this.foto = foto;
		this.dataInserimento = new Date();
		this.fornitori = new ArrayList<>();
		this.votoMedio = votoMedio;
	}

	public void addFornitore(Fornitore f) {
		this.fornitori.add(f);
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
	
	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public List<Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Integer getVotoMedio() {
		return votoMedio;
	}

	public void setVotoMedio(Integer votoMedio) {
		this.votoMedio = votoMedio;
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
	
}
