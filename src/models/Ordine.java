package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Ordine {
	
	private Long id;
	private Double prezzoTotale;
	private Boolean evaso;
	private Date dataApertura;
	private Date dataChiusura;
	private Date dataEvasione;
	private List<RigaOrdine> righe;
	private Coupon coupon;
	private Integer codice;
	
	public Ordine() {
		this.prezzoTotale = 0.;
		this.evaso = false;
		this.righe = new LinkedList<RigaOrdine>();
		this.dataApertura = new Date();
		//TODO codice
	}

	public boolean contiene(Prodotto p) {
		boolean contiene = false;
		for(RigaOrdine r: righe)
			if(r.stessoProdotto(p))
				contiene = true;
		return contiene;
	}
	public void aggiungiProdotto(Prodotto prodotto, Integer quantita) {
		if(!this.contiene(prodotto))
			this.righe.add(new RigaOrdine(prodotto,quantita));
		else {
			this.modificaQuantita(prodotto,quantita);
		}
		this.prezzoTotale += prodotto.getPrezzoDiListino()*quantita;
	}

	private void modificaQuantita(Prodotto prodotto, Integer quantita) {
		for(RigaOrdine r:righe)
			if(r.stessoProdotto(prodotto))
				r.setQuantita(r.getQuantita()+quantita);
	}

	public void aggiungiCoupon(Coupon c) {
		this.coupon = c;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getPrezzoTotale() {
		return prezzoTotale;
	}
	
	public void setPrezzoTotale(Double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	public Boolean getEvaso() {
		return evaso;
	}
	
	public void setEvaso(Boolean evaso) {
		this.evaso = evaso;
	}
	
	public Date getDataApertura() {
		return dataApertura;
	}
	
	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}
	
	public Date getDataChiusura() {
		return dataChiusura;
	}
	
	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	
	public Date getDataEvasione() {
		return dataEvasione;
	}
	
	public void setDataEvasione(Date dataEvasione) {
		this.dataEvasione = dataEvasione;
	}
	
	public List<RigaOrdine> getRighe() {
		return righe;
	}
	
	public void setRighe(List<RigaOrdine> righe) {
		this.righe = righe;
	}
	
	public Coupon getCoupon() {
		return coupon;
	}
	
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
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
		Ordine other = (Ordine) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}


}
