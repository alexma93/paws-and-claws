package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//prezzo senza coupon
	@Column
	private Double prezzoTotale;
	@Column
	private Boolean evaso;
	@Temporal (TemporalType.DATE)
	private Date dataApertura;
	@Temporal (TemporalType.DATE)
	private Date dataChiusura;
	@Temporal (TemporalType.DATE)
	private Date dataEvasione;
	
	@Column
	private Integer codice;
	@ManyToOne
	private Utente utente;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name = "ordine_id")
	private List<RigaOrdine> righe;
	@OneToOne
	private Coupon coupon;
	
	public Ordine(Utente utente) {
		this.prezzoTotale = 0.;
		this.evaso = false;
		this.righe = new LinkedList<RigaOrdine>();
		this.dataApertura = new Date();
		this.utente = utente;
		//il codice alla fine
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

	public void modificaQuantita(Prodotto prodotto, Integer quantita) {
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
	

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
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
