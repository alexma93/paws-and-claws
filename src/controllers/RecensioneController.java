package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import facades.ProdottoFacade;
import models.Prodotto;

@ManagedBean
@SessionScoped
public class RecensioneController {
	
	private Integer stelle;
	private String testo;
	private String codiceProdotto;
	private Prodotto prodotto;
	
	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;
	
	public String iniziaRecensione() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Prodotto p = prodottoFacade.getProdotto(codice);
		this.prodotto = p;
		return "inserisciRecensione.xhtml";
	}
	public String inserisciNuovoProdotto() {

		return "index.xhtml";
	}
	
	public Integer getStelle() {
		return stelle;
	}
	
	public void setStelle(Integer stelle) {
		this.stelle = stelle;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public String getCodiceProdotto() {
		return codiceProdotto;
	}
	
	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	public SessionBean getSession() {
		return session;
	}
	
	public void setSession(SessionBean session) {
		this.session = session;
	}
	
	public ProdottoFacade getProdottoFacade() {
		return prodottoFacade;
	}
	
	public void setProdottoFacade(ProdottoFacade prodottoFacade) {
		this.prodottoFacade = prodottoFacade;
	}
	
}
