package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import facades.UtenteFacade;
import models.Amministratore;
import models.Ordine;
import models.Utente;

@ManagedBean(name="sessione")
@SessionScoped
public class SessionBean {
	private Utente utente;
	private Amministratore amministratore;
	/*è inserito qui e non in OrdineController perchè se cambiassi utente 
	 * durante la stessa sessione al pc rimarrebbe lo stesso ordine */
	private Ordine ordineCorrente;
	
	@EJB(beanName="uFacade")
	private UtenteFacade utenteFacade;
	
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

	public Ordine getOrdineCorrente() {
		return ordineCorrente;
	}

	public void setOrdineCorrente(Ordine ordineCorrente) {
		this.ordineCorrente = ordineCorrente;
	}
}
