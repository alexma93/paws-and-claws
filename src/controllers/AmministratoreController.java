package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import models.Amministratore;
import models.AmministratoreFacade;
 
@ManagedBean
@RequestScoped
public class AmministratoreController {
	
	private String email;
	private String password;
	private String erroreLogin;
	
	@ManagedProperty(value = "#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="aFacade")
	private AmministratoreFacade amministratoreFacade;
	
	//UCOTRIS login amministratore
	public String loginAmministratore() {
		Amministratore a = amministratoreFacade.getAmministratore(email);
		if (a==null) {
			this.setErroreLogin("Email non valida");
			return "loginAmministratore.xhtml";
		}
		else if (a.checkPassword(password)) {
			session.setAmministratore(a);
			session.scollegaUtente();
			return "index.xhtml";
		}
		else {
			this.setErroreLogin("Password non valida");
			return "loginAmministratore.xhtml";
		}
		
	}


	public String getErroreLogin() {
		return erroreLogin;
	}

	public void setErroreLogin(String erroreLogin) {
		this.erroreLogin = erroreLogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public SessionBean getSession() {
		return session;
	}


	public void setSession(SessionBean session) {
		this.session = session;
	}
}
