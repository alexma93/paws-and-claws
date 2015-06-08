package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Coupon;
import models.Ordine;
import models.Prodotto;

@ManagedBean
@SessionScoped
public class OrdineController {
	private Ordine ordineCorrente;
	private Integer quantita;
	
	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;

	//UC2
	public String aggiungiProdottoOrdine() {
		if(ordineCorrente==null)
			ordineCorrente = new Ordine();
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Prodotto p = this.session.getStore().getProdotto(codice);
		this.ordineCorrente.aggiungiProdotto(p,quantita);
		return "index.xhtml";
	}

	public void aggiungiCoupon(String codice) {
		Coupon c = this.session.getStore().getSingoloCoupon(codice);
		if(c==null) {
			//TODO
		}
		else {
			this.ordineCorrente.aggiungiCoupon(c);
		}
	}

	public void terminaOrdine() {
		this.session.getUtente().confermaOrdine(this.ordineCorrente);
		this.session.getStore().confermaOrdine(this.ordineCorrente);
		this.ordineCorrente = null;
	}

	//UC6
	public void evadiOrdine(Integer codice) {
		this.session.getStore().evadi(codice);
	}

	public Ordine getOrdineCorrente() {
		return ordineCorrente;
	}

	public void setOrdineCorrente(Ordine ordineCorrente) {
		this.ordineCorrente = ordineCorrente;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
}
