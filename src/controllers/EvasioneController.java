package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Ordine;
import models.OrdineFacade;

@ManagedBean(name="evasione")
@SessionScoped
public class EvasioneController {
	private List<Ordine> ordiniNonEvasi;

	@EJB(beanName="oFacade")
	private OrdineFacade ordineFacade;
	
	public OrdineFacade getOrdineFacade() {
		return ordineFacade;
	}
	public void setOrdineFacade(OrdineFacade ordineFacade) {
		this.ordineFacade = ordineFacade;
	}
	
	//UC6
	public String getOrdiniNonEvasiDB() {
		List<Ordine> ordiniNonEvasi = ordineFacade.getOrdiniNonEvasi();
		this.setOrdiniNonEvasi(ordiniNonEvasi);
		return "evasioneOrdine.xhtml";
	}
	private void setOrdiniNonEvasi() {
		List<Ordine> ordiniNonEvasi = ordineFacade.getOrdiniNonEvasi();
		this.setOrdiniNonEvasi(ordiniNonEvasi);
	}
	public String evadiOrdine() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("codice");
		Integer i = Integer.parseInt(codice);
		ordineFacade.evadiOrdine(i);
		setOrdiniNonEvasi();
		
		return "evasioneOrdine.xhtml";
	}
	
	
	public List<Ordine> getOrdiniNonEvasi() {
		return ordiniNonEvasi;
	}

	public void setOrdiniNonEvasi(List<Ordine> ordiniNonEvasi) {
		this.ordiniNonEvasi = ordiniNonEvasi;
	}


}
