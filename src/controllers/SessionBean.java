package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.Amministratore;
import models.Ordine;
import models.Store;
import models.Utente;

@ManagedBean(name="sessione")
@SessionScoped
public class SessionBean {
	private Utente utente;
	private Amministratore amministratore;
	private Store store;
	/*� inserito qui e non in OrdineController perch� se cambio utente 
	 * durante la stessa sessione rimarrebbe lo stesso ordine*/
	private Ordine ordineCorrente;
	
	public SessionBean(){
		this.store = new Store();
		
		/* TODO togliere*/
		this.utente = new Utente();
	}
	
	public void terminaOrdine() {
		store.confermaOrdine(this.ordineCorrente);
		utente.confermaOrdine(this.ordineCorrente);
		this.ordineCorrente = null;
	}
	
	public String scollegaUtente() {
		this.utente = null;
		return "index.xhtml";
	}
	
	public String scollegaAmministratore() {
		this.amministratore = null;
		return "index.xhtml";
	}
	

	public void confermaRegistrazioneUtente() {
		this.store.confermaRegistrazioneUtente(this.utente);
		
	}
	
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Amministratore getAmministratore() {
		return amministratore;
	}

	public void setAmministratore(Amministratore amministratore) {
		this.amministratore = amministratore;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	

	public Ordine getOrdineCorrente() {
		return ordineCorrente;
	}

	public void setOrdineCorrente(Ordine ordineCorrente) {
		this.ordineCorrente = ordineCorrente;
	}


	
}
