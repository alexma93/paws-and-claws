package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RigaOrdine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Float prezzoUnitario;
	@Column
	private Integer quantita;
	@Column
	private Prodotto prodotto;
	
	public RigaOrdine() {
	}
	
	public RigaOrdine(Prodotto prodotto, Integer quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.prezzoUnitario = prodotto.getPrezzoDiListino();
	}
	
	public boolean stessoProdotto(Prodotto p) {
		return this.prodotto.getCodice().equals(p.getCodice());
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(Float prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	
	
}
