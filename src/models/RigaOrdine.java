package models;

public class RigaOrdine {

	private Long id;
	private Integer prezzoUnitario;
	private Integer quantita;
	private Prodotto prodotto;
	
	public RigaOrdine(Prodotto prodotto, Integer quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.prezzoUnitario = prodotto.getPrezzoDiListino();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(Integer prezzoUnitario) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prodotto == null) ? 0 : prodotto.hashCode());
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
		RigaOrdine other = (RigaOrdine) obj;
		if (prodotto == null) {
			if (other.prodotto != null)
				return false;
		} else if (!prodotto.equals(other.prodotto))
			return false;
		return true;
	}
	
	
}
