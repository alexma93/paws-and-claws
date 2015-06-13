package controllers;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import facades.UtenteFacade;
import models.Utente;

@ManagedBean
@RequestScoped
public class UtenteController {
	private String nome;
	private String cognome;
	private Date dataNascita;

	private String password;
	private String email;

	private String errore;
	
	private String stato;
	private String regione;
	private String citta;
	private String strada;
	private String cap;

	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="uFacade")
	private UtenteFacade utenteFacade;
	
	//utente visualizzato a partire da un ordine
	private Utente utenteVisualizzato;
	

	public String registraUtente() {
		if(utenteFacade.checkEmail(email)) {
			session.setUtente(utenteFacade.createUtente(nome,cognome,dataNascita,email,password));
			return "registraIndirizzo.xhtml";
		}
		else {
			this.setErrore("email gi� presente");
			return "registrazione.xhtml";
		}
	}
	

	public String aggiungiIndirizzoUtente() {
		session.getUtente().aggiungiIndirizzo(strada,citta,stato,cap,regione);
		return "confermaRegistrazione.xhtml";
	}

	public String confermaRegistrazioneUtente() {
		utenteFacade.confermaUtente(session.getUtente());
		return "index.xhtml";
	}

	public String annullaRegistrazioneUtente() {
		session.setUtente(null);
		return "index.xhtml";
	}

	public String loginUtente() {
		Utente u = utenteFacade.getUtente(email);
		if (u==null) {
			this.setErrore("Email non valida");
		}
		else if (u.checkPassword(password)) {
			session.setUtente(u);
		}
		else {
			this.setErrore("Password non valida");
		}

		return "index.xhtml";
	}
	
	//UC5
	public String visualizzaUtente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String email = fc.getExternalContext().getRequestParameterMap().get("email");
		setUtenteVisualizzato(utenteFacade.getUtente(email));
		return "mostraUtente.xhtml";
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getErrore() {
		return errore;
	}
	public void setErrore(String errore) {
		this.errore = errore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getStrada() {
		return strada;
	}

	public void setStrada(String strada) {
		this.strada = strada;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public UtenteFacade getUtenteFacade() {
		return utenteFacade;
	}

	public void setUtenteFacade(UtenteFacade utenteFacade) {
		this.utenteFacade = utenteFacade;
	}

	public Utente getUtenteVisualizzato() {
		return utenteVisualizzato;
	}

	public void setUtenteVisualizzato(Utente utenteVisualizzato) {
		this.utenteVisualizzato = utenteVisualizzato;
	}


}
