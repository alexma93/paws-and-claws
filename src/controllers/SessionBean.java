package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.Amministratore;
import models.Store;
import models.Utente;

@ManagedBean(name="sessione")
@SessionScoped
public class SessionBean {
	private Utente utente;
	private Amministratore amministratore;
	private Store store;
	
	
	public SessionBean(){
		this.store = new Store();
		this.utente = new Utente();
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

	
}
