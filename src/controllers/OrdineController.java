package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import facades.CouponFacade;
import facades.OrdineFacade;
import facades.ProdottoFacade;
import facades.UtenteFacade;
import models.Coupon;
import models.Prodotto;

@ManagedBean
@RequestScoped
public class OrdineController {
	private Integer quantita;
	private String codiceCoupon;
	private String erroreCoupon;
	private Integer size;
	
	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;
	
	@EJB(beanName="oFacade")
	private OrdineFacade ordineFacade;
	
	@EJB(beanName="couponFacade")
	private CouponFacade couponFacade;

	@EJB(beanName="uFacade")
	private UtenteFacade utenteFacade;

	
	public OrdineController() {
		this.quantita = 1;
	}
	
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
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public ProdottoFacade getProdottoFacade() {
		return prodottoFacade;
	}
	
	public void setProdottoFacade(ProdottoFacade prodottoFacade) {
		this.prodottoFacade = prodottoFacade;
	}
	
	public OrdineFacade getOrdineFacade() {
		return ordineFacade;
	}
	
	public void setOrdineFacade(OrdineFacade ordineFacade) {
		this.ordineFacade = ordineFacade;
	}
	
	public CouponFacade getCouponFacade() {
		return couponFacade;
	}
	
	public void setCouponFacade(CouponFacade couponFacade) {
		this.couponFacade = couponFacade;
	}
	
}
