package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Store {
	private Map<String,Utente> utenti;
	private Map<String,Prodotto> catalogo;
	private Map<String,Coupon> coupon;
	private Map<Integer,Ordine> ordiniNonEvasi;
	private Map<Integer,Ordine> ordiniEvasi;
	private Map<String,Amministratore> amministratori;
	private Map<String,Fornitore> fornitori;
	private List<String> specie;
	private List<Prodotto> prodottiNuovi;
	
	public Store() {
		this.utenti = new HashMap<String,Utente>();
		this.catalogo = new TreeMap<String,Prodotto>();
		this.coupon = new HashMap<String,Coupon>();
		this.ordiniEvasi = new HashMap<Integer,Ordine>();
		this.ordiniNonEvasi = new HashMap<Integer,Ordine>();
		this.amministratori = new HashMap<String,Amministratore>();
		this.fornitori = new HashMap<String,Fornitore>();
		this.specie = new ArrayList<String>();
		this.prodottiNuovi = new ArrayList<Prodotto>(50);
		butta();
	}  

	public void butta() {
		confermaRegistrazioneUtente(new Utente("daniele","petrillo",new Date(),"a@b.it","pass"));
		this.amministratori.put("alexma", new Amministratore("alexma","casetta"));
		Prodotto p = new Prodotto("000","gialla","pannocchia",5F,6,null);
		this.inserisciProdotto(p);
		p.setSpecie("gatto");
		this.inserisciProdotto(new Prodotto("001","lungo","guinzaglio",6F,20,null));
		this.inserisciProdotto(new Prodotto("003","piccola","ciotola",1F,30,null));
		this.inserisciProdotto(new Prodotto("002","sonoro","osso di gomma",0.5F,100,null));
		for(int i=0; i<51;i++)
		this.inserisciProdotto(new Prodotto("004"+i,"appetitose","crocchette",9.5F,6,null));
		Coupon c = new Coupon("abc",20);
		this.coupon.put("abc", c);
	}
	public boolean checkEmail(String email) {
		return !this.utenti.containsKey(email);
	}

	public void confermaRegistrazioneUtente(Utente utenteCorrente) {
		this.utenti.put(utenteCorrente.getEmail(), utenteCorrente);
		
	}

	
	public void confermaOrdine(Ordine ordine) {
		//TODO
		ordine.setCodice(this.ordiniEvasi.size()+this.ordiniNonEvasi.size());
		this.ordiniNonEvasi.put(ordine.getCodice(), ordine);
	}

	public boolean checkCodiceProdotto(String codice) {
		return this.catalogo.containsKey(codice);
	}	
	
	public void inserisciProdotto(Prodotto prodotto) {
		if(this.prodottiNuovi.size()>=50)
			this.prodottiNuovi.remove(this.prodottiNuovi.get(0));
		this.prodottiNuovi.add(prodotto);
		this.catalogo.put(prodotto.getCodice(), prodotto);
	}
	public void evadi(Integer codice) {
		Ordine o = this.ordiniNonEvasi.get(codice);
		this.ordiniNonEvasi.remove(codice);
		this.ordiniEvasi.put(codice, o);
		o.setEvaso(true);
	}

	public Amministratore getAmministratore(String email) {
		return this.amministratori.get(email);
	}
	
	public Fornitore getFornitore(String iva) {
		return this.fornitori.get(iva);
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

	//getter e setter
	public Map<String, Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Map<String, Utente> utenti) {
		this.utenti = utenti;
	}

	public Map<String, Prodotto> getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Map<String, Prodotto> catalogo) {
		this.catalogo = catalogo;
	}

	public Map<String, Coupon> getCoupon() {
		return coupon;
	}

	public void setCoupon(Map<String, Coupon> coupon) {
		this.coupon = coupon;
	}

	public Map<Integer, Ordine> getOrdiniNonEvasi() {
		return ordiniNonEvasi;
	}

	public void setOrdiniNonEvasi(Map<Integer, Ordine> ordiniNonEvasi) {
		this.ordiniNonEvasi = ordiniNonEvasi;
	}

	public Map<Integer, Ordine> getOrdiniEvasi() {
		return ordiniEvasi;
	}

	public void setOrdiniEvasi(Map<Integer, Ordine> ordiniEvasi) {
		this.ordiniEvasi = ordiniEvasi;
	}

	public Map<String, Amministratore> getAmministratori() {
		return amministratori;
	}

	public void setAmministratori(Map<String, Amministratore> amministratori) {
		this.amministratori = amministratori;
	}

	public Map<String, Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(Map<String, Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public List<Prodotto> getProdottiNuovi() {
		return prodottiNuovi;
	}

	public void setProdottiNuovi(List<Prodotto> prodottiNuovi) {
		this.prodottiNuovi = prodottiNuovi;
	}

	public List<String> getSpecie() {
		return specie;
	}

	public void setSpecie(List<String> specie) {
		this.specie = specie;
	}
	
}
