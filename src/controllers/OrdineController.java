package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import models.Coupon;
import models.Ordine;
import models.Prodotto;

@ManagedBean
@RequestScoped
public class OrdineController {
	private Integer quantita;
	private String codiceCoupon;
	private String erroreCoupon;
	
	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;

	public OrdineController() {
		this.quantita = 1;
	}
	//UC2
	public String aggiungiProdottoOrdine() {
		if(session.getOrdineCorrente()==null)
			session.setOrdineCorrente(new Ordine());
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Prodotto p = this.session.getStore().getProdotto(codice);
		session.getOrdineCorrente().aggiungiProdotto(p,quantita);
		this.quantita = 1;
		return "carrello.xhtml";
	}

	public String aggiungiCoupon() {
		Coupon c = session.getStore().getSingoloCoupon(codiceCoupon);
		if(c==null) {
			this.setErroreCoupon("coupon non valido");
		}
		else {
			session.getOrdineCorrente().aggiungiCoupon(c);
		}
		return "carrello.xhtml";
	}

	public void terminaOrdine() {
		session.terminaOrdine();
	}

	//UC6
	public void evadiOrdine(Integer codice) {
		this.session.getStore().evadi(codice);
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
	public String getCodiceCoupon() {
		return codiceCoupon;
	}
	public void setCodiceCoupon(String codiceCoupon) {
		this.codiceCoupon = codiceCoupon;
	}
	public String getErroreCoupon() {
		return erroreCoupon;
	}
	public void setErroreCoupon(String erroreCoupon) {
		this.erroreCoupon = erroreCoupon;
	}
}
