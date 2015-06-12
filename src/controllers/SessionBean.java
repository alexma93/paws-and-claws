package controllers;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.Amministratore;
import models.Ordine;
import models.Store;
import models.Utente;
import models.UtenteFacade;

@ManagedBean(name="sessione")
@SessionScoped
public class SessionBean {
	private Utente utente;
	private Amministratore amministratore;
	private Store store;
	/*è inserito qui e non in OrdineController perchè se cambio utente 
	 * durante la stessa sessione rimarrebbe lo stesso ordine*/
	private Ordine ordineCorrente;
	
	@EJB(beanName="uFacade")
	private UtenteFacade utenteFacade;
	
	public SessionBean(){
		this.store = new Store();
	}
	
	public void terminaOrdine() {
		utenteFacade.confermaOrdine(this.utente,this.ordineCorrente);
		this.ordineCorrente = null;
	}
	
	public String scollegaUtente() {
		this.utente = null;
		this.ordineCorrente = null;
		return "index.xhtml";
	}
	
	public String scollegaAmministratore() {
		this.amministratore = null;
		return "index.xhtml";
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
