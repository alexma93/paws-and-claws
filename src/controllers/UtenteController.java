package controllers;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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

	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;

	public UtenteController() {
	}

	//UC0
	public String registraUtente() {
		if(session.getStore().checkEmail(email)) {
			session.setUtente(new Utente(nome,cognome,dataNascita,email,password));
			return "index.xhtml";
		}
		else {
			this.setErrore("email già presente");
			return "registrazione.xhtml";
		}

	}

	public void aggiungiIndirizzoUtente(String strada,String citta,String stato,String cap,String regione) {
		session.getUtente().aggiungiIndirizzo(strada,citta,stato,cap,regione);
	}

	public void confermaRegistrazioneUtente() {
		session.confermaRegistrazioneUtente();
	}

	//UC0BIS
	public String loginUtente() {
		Utente u = session.getStore().getUtente(email);
		if (u==null) {
			this.setErrore("Email non valida");
			return "login.xhtml";
		}
		else if (u.checkPassword(password)) {
			session.setUtente(u);
			return "login.xhtml";
		}
		else {
			this.setErrore("Password non valida");
		}
		return "index.xhtml";
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


}
