package controllers;

import models.Amministratore;
import models.Store;

public class AmministratoreController {
	private Store store;
	private Amministratore amministratoreCorrente;
	
	private String erroreLogin;
	
	//UCOTRIS login amministratore
	public String loginAmministratore(String email,String password) {
		Amministratore a = this.store.getAmministratore(email);
		if (a==null) {
			this.setErroreLogin("Email non valida");
		}
		else if (a.checkPassword(password)) {
			this.amministratoreCorrente = a;
		}
		else {
			this.setErroreLogin("Password non valida");
		}
		return "index.xhtml";
	}
	
	public Amministratore getAmministratoreCorrente() {
		return amministratoreCorrente;
	}
	public void setAmministratoreCorrente(Amministratore amministratoreCorrente) {
		this.amministratoreCorrente = amministratoreCorrente;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getErroreLogin() {
		return erroreLogin;
	}

	public void setErroreLogin(String erroreLogin) {
		this.erroreLogin = erroreLogin;
	}
}
