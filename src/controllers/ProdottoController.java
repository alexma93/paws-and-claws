package controllers;

import java.awt.Image;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.omnifaces.util.Utils;

import models.Fornitore;
import models.Prodotto;
import models.ProdottoFacade;
import models.Store;

@ManagedBean
@SessionScoped
public class ProdottoController {
	private String nome;
	private Float prezzoDiListino;
	private String descrizione;
	private String codice;
	private Integer quantita;
	private String specie;
	
	private Store store;
	private Prodotto prodottoCorrente;
	private List<Prodotto> prodotti;
	private Integer size;
	private Part foto;
	
    private Part file;
    private byte[] content;
	

	@ManagedProperty(value="#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;
	
	//UC4
	private String createProduct() {
		prodottoFacade.createProduct(prodottoCorrente.getNome(), prodottoCorrente.getPrezzoDiListino(), 
									 prodottoCorrente.getDescrizione(), prodottoCorrente.getCodice(),
									 prodottoCorrente.getQuantita(), prodottoCorrente.getSpecie(),
									 prodottoCorrente.getFoto());
		return "aggiungiProdotto.xhtml";
	}
	public String confermaProdotti() {
		for(Prodotto p : this.prodotti) {
			this.prodottoCorrente = p;
			this.createProduct();
		}
		this.prodotti = null;
		return "aggiungiProdotto.xhtml";
	}
	public String inserisciNuovoProdotto() {
		if(this.prodotti==null){
			this.prodotti = new LinkedList<>();
		}
//		if(this.store.checkCodiceProdotto(codice)) {
			Prodotto p = new Prodotto(nome, prezzoDiListino, descrizione, codice, quantita, specie, null, null, foto);
			this.prodotti.add(p);
			this.size=prodotti.size();
//		}
		this.nome = null;
		this.prezzoDiListino = null;
		this.descrizione = null;
		this.codice = null;
		this.quantita = null;
		try {
			content = Utils.toByteArray(file.getInputStream());
		} catch (IOException e) {
		}
		return "aggiungiProdotto.xhtml";
	}
	
	public void inserisciFornitore(String iva) {
		Fornitore f = this.store.getFornitore(iva);
		if(f==null) {
			//TODO
		}
		else {
			this.prodottoCorrente.addFornitore(f);
		}
	}
	
	public void inserisciFoto(Part immagine) {
		this.prodottoCorrente.setImmagine(immagine);
	}
	
	public String fineInserimento() {
		this.session.getStore().inserisciProdotto(this.prodottoCorrente);
		this.prodottoCorrente = null;
		return "index.xhtml";
	}
	
	//UC4 BIS
	public void selezionaProdotto(String codice) {
		this.prodottoCorrente = this.store.getProdotto(codice);
	}
	
	public void modificaPrezzo(Float prezzo) {
		this.prodottoCorrente.setPrezzoDiListino(prezzo);
	}
	
	public void modificaQuantita(Integer quantita) {
		this.prodottoCorrente.setQuantita(quantita);
	}
	
	public void applicaSconto(String descrizione,Integer percentuale) {
		this.prodottoCorrente.aggiungiSconto(descrizione,percentuale);
	}
	
	public void eliminaSconto() {
		this.prodottoCorrente.setSconto(null);
	}
	
	
	//UC7
	//si ripete selezionaProdotto
	public void inserisciRecensione(Integer stelle,String testo) {
		this.prodottoCorrente.inserisciRecensione(stelle,testo,this.session.getUtente());
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Prodotto getProdottoCorrente() {
		return prodottoCorrente;
	}

	public void setProdottoCorrente(Prodotto prodottoCorrente) {
		this.prodottoCorrente = prodottoCorrente;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}
 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Float getPrezzoDiListino() {
		return prezzoDiListino;
	}
 
	public void setPrezzoDiListino(Float prezzoDiListino) {
		this.prezzoDiListino = prezzoDiListino;
	}
//	public Map<String, Prodotto> getProdotti() {
//		return prodotti;
//	}
//
//	public void setProdotti(Map<String, Prodotto> prodotti) {
//		this.prodotti = prodotti;
//	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}


	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}

}
