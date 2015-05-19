package models;

import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Portale {
	private Utente utenteCorrente;
	private Ordine ordineCorrente;
	private Amministratore amministratoreCorrente;
	private Store store;
	private Prodotto prodottoCorrente;
	
	//UC0
	public void registrami(String nome, String cognome, String dataNascita, String email, String password) {
		if(store.checkEmail(email)) {
			Date data;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
			try {
				data = formatter.parse(dataNascita);
				this.utenteCorrente = new Utente(nome,cognome,data,email,password);
			} catch (ParseException e) {
				// TODO eccezione
				e.printStackTrace();
			}
		}
		else {
			// TODO email gia' presente
		}
	}
	
	public void aggiungiIndirizzoUtente(String strada,String citta,String stato,String cap,String regione) {
		this.utenteCorrente.aggiungiIndirizzo(strada,citta,stato,cap,regione);
	}
	
	public void confermaRegistrazioneUtente() {
		this.store.confermaRegistrazioneUtente(this.utenteCorrente);
	}
	
	//UC0BIS
	public void loginUtente(String email,String password) {
		Utente u = this.store.getUtente(email);
		if (u==null) {
			//TODO
		}
		else if (u.checkPassword(password)) {
			this.utenteCorrente = u;
		}
		else {
			//TODO
		}
	}
	
	//UCOTRIS login amministratore
	public void loginAmministratore(String email,String password) {
		Amministratore a = this.store.getAmministratore(email);
		if (a==null) {
			//TODO
		}
		else if (a.checkPassword(password)) {
			this.amministratoreCorrente = a;
		}
		else {
			//TODO
		}
	}
	
	//UC2
	public void creaOrdine() {
		this.ordineCorrente = new Ordine();
	}
	
	public void aggiungiProdottoOrdine(String codice,Integer quantita) {
		Prodotto p = this.store.getProdotto(codice);
		this.ordineCorrente.aggiungiProdotto(p,quantita);
	}
	
	public void aggiungiCoupon(String codice) {
		Coupon c = this.store.getSingoloCoupon(codice);
		if(c==null) {
			//TODO
		}
		else {
			this.ordineCorrente.aggiungiCoupon(c);
		}
	}
	
	public void terminaOrdine() {
		this.utenteCorrente.confermaOrdine(this.ordineCorrente);
		this.store.confermaOrdine(this.ordineCorrente);
		this.ordineCorrente = null;
	}
	
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
}
