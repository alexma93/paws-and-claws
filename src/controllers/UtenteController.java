package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.Store;
import models.Utente;

@ManagedBean
@SessionScoped
public class UtenteController {
	private Store store;
	private Utente utenteCorrente;
	
	private String nome;
	private String cognome;
	private String dataNascita;
	
	private String password;
	private String email;
	
	private String erroreLogin;
	
	public UtenteController() {
		this.store = new Store();
	}

	//UC0
	public String registraUtente() {
		if(store.checkEmail(email)) {
			Date data;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy"); 
			try {
				
				data = formatter.parse(dataNascita);
				this.store=null;
				this.utenteCorrente = new Utente(nome,cognome,data,email,password);
				return "index.xhtml";
			} catch (ParseException e) {
				return "registrazione.xhtml";
			}
		}
		else {
			// email già presente
			return "registrazione.xhtml";
		}
		
	}

	public void aggiungiIndirizzoUtente(String strada,String citta,String stato,String cap,String regione) {
		this.utenteCorrente.aggiungiIndirizzo(strada,citta,stato,cap,regione);
	}
	
	public void confermaRegistrazioneUtente() {
		this.store.confermaRegistrazioneUtente(this.utenteCorrente);
	}
	
	//UC0BIS
	public String loginUtente() {
		Utente u = this.store.getUtente(email);
		if (u==null) {
			this.setErroreLogin("Email non valida");
			
		}
		else if (u.checkPassword(password)) {
			this.utenteCorrente = u;
		}
		else {
			this.setErroreLogin("Password non valida");
		}
		return "index.xhtml";
	}
	

	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Utente getUtenteCorrente() {
		return utenteCorrente;
	}
	public void setUtenteCorrente(Utente utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
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
	public String getErroreLogin() {
		return erroreLogin;
	}
	public void setErroreLogin(String erroreLogin) {
		this.erroreLogin = erroreLogin;
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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
}
