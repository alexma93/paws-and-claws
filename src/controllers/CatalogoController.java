package controllers;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import models.Prodotto;
import models.Store;

@ManagedBean
@SessionScoped
public class CatalogoController {
	private Map<String,Prodotto> catalogoCorrente;
	private Integer size;
	
	public CatalogoController() {
		this.catalogoCorrente = (new Store()).getCatalogo();
		this.size = this.catalogoCorrente.size();
	}

	public Prodotto getProdotto() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String codice = fc.getExternalContext().getRequestParameterMap().get("prodotto");
		return this.catalogoCorrente.get(codice);
	}
	public Map<String, Prodotto> getCatalogoCorrente() {
		return catalogoCorrente;
	}
	public void setCatalogoCorrente(Map<String, Prodotto> catalogoCorrente) {
		this.catalogoCorrente = catalogoCorrente;
	}


	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}
	
}
