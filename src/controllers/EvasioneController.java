package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import facades.OrdineFacade;
import models.Ordine;

@ManagedBean(name="evasione")
@SessionScoped
public class EvasioneController {
	private List<Ordine> ordiniNonEvasi;
	
	private List<Ordine> ordiniEvasi;
	
	private String errore;

	@EJB(beanName="oFacade")
	private OrdineFacade ordineFacade;
	
	
	public String getOrdiniNonEvasiDB() {
		this.ordiniNonEvasi = ordineFacade.getOrdiniNonEvasi();
		this.errore = null;
		return "evasioneOrdine.xhtml";
	}
	
	public String getOrdiniEvasiDB() {
		this.ordiniEvasi = ordineFacade.getOrdiniEvasi();
		return "ordiniEvasi.xhtml";
	}
	
	public String evadiOrdine() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Integer i = Integer.parseInt(codice);
		if(ordineFacade.evadiOrdine(i))
			return getOrdiniNonEvasiDB();
		else this.errore = "Prodotti non disponibili in magazzino!";
	
		return "evasioneOrdine.xhtml";
	}
	
	
	public List<Ordine> getOrdiniNonEvasi() {
		return ordiniNonEvasi;
	}

	public void setOrdiniNonEvasi(List<Ordine> ordiniNonEvasi) {
		this.ordiniNonEvasi = ordiniNonEvasi;
	}
	public OrdineFacade getOrdineFacade() {
		return ordineFacade;
	}
	public void setOrdineFacade(OrdineFacade ordineFacade) {
		this.ordineFacade = ordineFacade;
	}
	public String getErrore() {
		return errore;
	}
	public void setErrore(String errore) {
		this.errore = errore;
	}

	public List<Ordine> getOrdiniEvasi() {
		return ordiniEvasi;
	}

	public void setOrdiniEvasi(List<Ordine> ordiniEvasi) {
		this.ordiniEvasi = ordiniEvasi;
	}


}
