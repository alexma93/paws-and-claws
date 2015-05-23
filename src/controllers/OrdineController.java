package controllers;

import models.Coupon;
import models.Ordine;
import models.Prodotto;
import models.Store;
import models.Utente;

public class OrdineController {
	private Ordine ordineCorrente;
	private Store store;
	private Utente utenteCorrente;
	
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
	
	//UC6
	public void evadiOrdine(Integer codice) {
		this.store.evadi(codice);
	}
}
