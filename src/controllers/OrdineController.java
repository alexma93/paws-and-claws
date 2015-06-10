package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import models.Coupon;
import models.CouponFacade;
import models.OrdineFacade;
import models.Prodotto;
import models.ProdottoFacade;
import models.UtenteFacade;

@ManagedBean
@RequestScoped
public class OrdineController {
	private Integer quantita;
	private String codiceCoupon;
	private String erroreCoupon;
	
	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;
	
	@EJB(beanName="oFacade")
	private OrdineFacade ordineFacade;
	
	@EJB(beanName="couponFacade")
	private CouponFacade couponFacade;

	public OrdineController() {
		this.quantita = 1;
	}
	//UC2
	public String aggiungiProdottoOrdine() {
		if(session.getOrdineCorrente()==null)
			session.setOrdineCorrente(ordineFacade.createOrdine(session.getUtente()));
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Prodotto p = prodottoFacade.getProdotto(codice);
		ordineFacade.aggiungiProdotto(session.getOrdineCorrente(),p,quantita);
		this.quantita = 1;
		return "carrello.xhtml";
	}

	public String aggiungiCoupon() {
		Coupon c = couponFacade.getCoupon(codiceCoupon);
		if(c==null) {
			this.setErroreCoupon("coupon non valido");
		}
		else {
			ordineFacade.aggiungiCoupon(session.getOrdineCorrente(),c);
		}
		return "carrello.xhtml";
	}

	public String terminaOrdine() {
		
		ordineFacade.terminaOrdine(session.getOrdineCorrente());
		session.terminaOrdine();
		return "index.xhtml";
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
