package controllers;

import java.awt.Image;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import models.Fornitore;
import models.Prodotto;
import models.Specie;
import models.Store;

@ManagedBean
@SessionScoped
public class ProdottoController {
	private Store store;
	private Prodotto prodottoCorrente;
	
	@ManagedProperty(value="#{sessione}")
	private SessionBean session;

	//UC4
	public void inserisciNuovoProdotto(String codice,String descrizione,String nome,
			Integer prezzo,Integer quantita) {
		if(this.store.checkCodiceProdotto(codice)) {
			this.prodottoCorrente = new Prodotto(codice,descrizione,nome,prezzo,quantita);
		}
		else {
			//TODO
		}
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
	
	public void fineInserimento() {
		this.store.inserisciProdotto(this.prodottoCorrente);
		this.prodottoCorrente = null;
	}
	
	//UC4 BIS
	public void selezionaProdotto(String codice) {
		this.prodottoCorrente = this.store.getProdotto(codice);
	}
	
	public void modificaPrezzo(Integer prezzo) {
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
	
}
