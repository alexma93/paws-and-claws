package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
	private Map<String,Utente> utenti;
	private Map<String,Prodotto> catalogo;
	private Map<String,Coupon> coupon;
	private Map<Integer,Ordine> ordiniNonEvasi;
	private Map<Integer,Ordine> ordiniEvasi;
	private Map<String,Amministratore> amministratori;
	private Map<String,Fornitore> fornitori;
	private Map<String,Specie> specie;
	private List<Prodotto> prodottiNuovi;
	
	public Store() {
		this.utenti = new HashMap<String,Utente>();
		this.catalogo = new HashMap<String,Prodotto>();
		this.coupon = new HashMap<String,Coupon>();
		this.ordiniEvasi = new HashMap<Integer,Ordine>();
		this.ordiniNonEvasi = new HashMap<Integer,Ordine>();
		this.amministratori = new HashMap<String,Amministratore>();
		this.fornitori = new HashMap<String,Fornitore>();
		this.specie = new HashMap<String,Specie>();
		this.prodottiNuovi = new ArrayList<Prodotto>(50);
	}
	public boolean checkEmail(String email) {
		return !this.utenti.containsKey(email);
	}

	public void confermaRegistrazioneUtente(Utente utenteCorrente) {
		this.utenti.put(utenteCorrente.getEmail(), utenteCorrente);
		
	}

	public Utente getUtente(String email) {
		return this.utenti.get(email);
	}

	public Prodotto getProdotto(String codice) {
		return this.catalogo.get(codice);
	}

	public Coupon getSingoloCoupon(String codice) {
		return this.coupon.get(codice);
	}
	
	public void confermaOrdine(Ordine ordine) {
		ordine.setCodice(this.ordiniEvasi.size()+this.ordiniNonEvasi.size());
		this.ordiniNonEvasi.put(ordine.getCodice(), ordine);
	}

	public Amministratore getAmministratore(String email) {
		return this.amministratori.get(email);
	}

	public boolean checkCodiceProdotto(String codice) {
		return this.catalogo.containsKey(codice);
	}

	public Fornitore getFornitore(String iva) {
		return this.fornitori.get(iva);
	}

	public Specie getSpecie(String specie) {
		return this.specie.get(specie);
	}

	public void inserisciProdotto(Prodotto prodotto) {
		this.prodottiNuovi.remove(this.prodottiNuovi.get(0));
		this.prodottiNuovi.add(prodotto);
		this.catalogo.put(prodotto.getCodice(), prodotto);
	}
	
}
