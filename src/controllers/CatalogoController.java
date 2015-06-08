package controllers;

import java.util.ArrayList;
import java.util.List;
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
	private Prodotto prodottoCorrente;
	private List<Prodotto> catalogo;
	
	public CatalogoController() {
		this.catalogoCorrente = (new Store()).getCatalogo();
		this.size = this.catalogoCorrente.size();
		this.catalogo = new ArrayList<>(this.catalogoCorrente.values());
	}

	
//	public String visualizzaProdotto() {
//		/*FacesContext fc = FacesContext.getCurrentInstance();
//		String codice = fc.getExternalContext().getRequestParameterMap().get("prodotto");
//		this.prodottoCorrente = this.catalogoCorrente.get(codice);*/
//		return "index.xhtml";
//	}
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


	public Prodotto getProdottoCorrente() {
		return prodottoCorrente;
	}


	public void setProdottoCorrente(Prodotto prodottoCorrente) {
		this.prodottoCorrente = prodottoCorrente;
	}


	public List<Prodotto> getCatalogo() {
		return catalogo;
	}


	public void setCatalogo(List<Prodotto> catalogo) {
		this.catalogo = catalogo;
	}


}
