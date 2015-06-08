package controllers;

import java.awt.Image;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import models.Fornitore;
import models.Prodotto;
import models.Specie;
import models.Store;

@ManagedBean
public class ProdottoController {
	@ManagedProperty(value="#{param.id}")
	private Store store;
	private Prodotto prodottoCorrente;
	private Map<String,Prodotto> prodotti;
	private String nome;
	private String descrizione;
	private String codice;
	private Float prezzoDiListino;
	

	@ManagedProperty(value="#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;

	public String createProduct() {
		this.prodottoCorrente = prodottoFacade.createProduct(nome, codice, prezzoDiListino, descrizione);
		return "aggiungiProdotto.xhtml";
	}
	
	//UC4
	public String inserisciNuovoProdotto() {
		if(this.prodotti==null){
			this.prodotti = new HashMap<>();
		}
		Prodotto p = new Prodotto(codice,descrizione,nome,prezzoDiListino,null);
		this.prodotti.put(p.getCodice(), p);
		//String codice,String descrizione,String nome,Integer prezzo,Integer quantita)
//		if(this.store.checkCodiceProdotto(codice)) {
//		}
//		else {
//			this.prodottoCorrente = new Prodotto(codice,descrizione,nome,prezzoDiListino,null);
			//TODO
//		}
		createProduct();
		
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
	
	public void inserisciFoto(Image immagine) {
		this.prodottoCorrente.setImmagine(immagine);
	}
	
	public void impostaSpecie(String specie) {
		Specie s = this.store.getSpecie(specie);
		if(s==null) {
			//TODO
		}
		else {
			this.prodottoCorrente.setSpecie(s);
		}
	}
	
	public void impostaTaglia(String taglia) {
		this.prodottoCorrente.setTaglia(taglia);
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
	public Map<String, Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(Map<String, Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	
}
