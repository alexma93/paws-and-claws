package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import facades.ProdottoFacade;
import models.Prodotto;

@ManagedBean
@SessionScoped
public class CatalogoController {
	
	//indica la grandezza del catalogo e serve per la paginazione
	private Integer size;
	private List<Prodotto> catalogo;
	
	private String specie;
	private Integer voto;
	private Integer prezzo; 
	
	private String parola;

	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;
	
	
	public String inizializza() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String specie = fc.getExternalContext().getRequestParameterMap().get("specie");
		
		this.catalogo = prodottoFacade.getProdotti(specie);

		this.size = this.catalogo.size();
		
		this.specie = "tutte";
		this.voto = 0;
		this.prezzo = 200;
		return "catalogo.xhtml";
	}

	public String ordina() {
		FacesContext fc = FacesContext.getCurrentInstance();
		//ordine inteso come metodo di ordinamento
		String ordine = fc.getExternalContext().getRequestParameterMap().get("ordine");
		this.catalogo = prodottoFacade.ordinaCatalogo(this.catalogo, ordine);
		return "catalogo.xhtml";
	}

	public String ricerca() {
		this.catalogo = prodottoFacade.ricercaCatalogo(this.catalogo,specie,voto,prezzo);
		return "catalogo.xhtml";
	}
	
	public String ricercaTestuale() {
		this.catalogo = prodottoFacade.ricercaTestualeCatalogo(this.catalogo,parola);
		this.parola = null;
		return "catalogo.xhtml";
	}

	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Prodotto> getCatalogo() {
		return catalogo;
	}


	public void setCatalogo(List<Prodotto> catalogo) {
		this.catalogo = catalogo;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}
	
	
	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public ProdottoFacade getProdottoFacade() {
		return prodottoFacade;
	}

	public void setProdottoFacade(ProdottoFacade prodottoFacade) {
		this.prodottoFacade = prodottoFacade;
	}


}
