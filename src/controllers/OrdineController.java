package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Coupon;
import models.CouponFacade;
import models.Ordine;
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
	private List<Ordine> ordiniNonEvasi;
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
	public String getOrdiniNonEvasiDB() {
		this.ordiniNonEvasi = ordineFacade.getOrdiniNonEvasi();
		return "evasioneOrdine.xhtml";
	}
	public String evadiOrdine() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Integer i = Integer.parseInt(codice);
		ordineFacade.evadiOrdine(i);
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
	public List<Ordine> getOrdiniNonEvasi() {
		return ordiniNonEvasi;
	}
	public void setOrdiniNonEvasi(List<Ordine> ordiniNonEvasi) {
		this.ordiniNonEvasi = ordiniNonEvasi;
	}
	
	
}
