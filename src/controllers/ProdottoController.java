package controllers;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.Part;

import org.omnifaces.util.Utils;

import facades.FornitoreFacade;
import facades.ProdottoFacade;
import models.Fornitore;
import models.Prodotto;

@ManagedBean
@SessionScoped
public class ProdottoController {
	private String nome;
	private Float prezzoDiListino;
	private String descrizione;
	private String codice;
	private Integer quantita;
	private String specie;
	
	private List<Fornitore> fornitori;
	private List<String> fornitoriProdotto;
	
	private Prodotto prodottoCorrente;
	private List<Prodotto> prodotti;
	private Integer size;
	
    private Part file;
    private byte[] content;
	

	@ManagedProperty(value="#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;
	
	@EJB(beanName="fFacade")
	private FornitoreFacade fornitoreFacade;

	//UC4
	private String createProduct() {
		prodottoFacade.createProduct(prodottoCorrente);
		return "aggiungiProdotto.xhtml";
	}
	public String confermaProdotti() {
		for(Prodotto p : this.prodotti) {
			this.prodottoCorrente = p;
			this.createProduct();
		}
		this.prodotti = null;
		return "aggiungiProdotto.xhtml";
	}
	public String inserisciNuovoProdotto() {
		if(this.prodotti==null){
			this.prodotti = new LinkedList<>();
		}
		try {
			content = Utils.toByteArray(file.getInputStream());
		} catch (IOException e) {
		}
		if(prodottoFacade.getProdotto(codice)==null) {
			Prodotto p = new Prodotto(nome, prezzoDiListino, descrizione, codice, quantita, specie, 0, content);
			
			for(String codice : this.fornitoriProdotto) {
				Fornitore f = fornitoreFacade.getFornitore(codice);
				p.addFornitore(f);
			}
			this.prodotti.add(p);
			this.size=prodotti.size();
			this.nome = null;
			this.prezzoDiListino = null;
			this.descrizione = null;
			this.codice = null;
			this.quantita = null;
			this.file = null;
			this.specie = "seleziona";
		} else {
			this.codice = null;
			return "aggiungiProdotto.xhtml";
		}
		this.fornitoriProdotto = null;
		
		return "aggiungiProdotto.xhtml";
	}
	public String getFornitoriDB() {
		this.fornitori = fornitoreFacade.getFornitori();
		this.fornitoriProdotto = null;
		
		return "aggiungiProdotto.xhtml";
	}
	
	public void inserisciFornitore(String iva) {
//		//Fornitore f = this.store.getFornitore(iva);
//		//if(f==null) {
//			//TODO
//		}
//		else {
//		//	this.prodottoCorrente.addFornitore(f);
//		}
	}
	
	public void inserisciFoto(byte[] immagine) {
		this.prodottoCorrente.setFoto(immagine);
	}
	
	public String fineInserimento() {
	//	this.session.getStore().inserisciProdotto(this.prodottoCorrente);
		this.prodottoCorrente = null;
		return "index.xhtml";
	}

	//UC7
	//si ripete selezionaProdotto
	public void inserisciRecensione(Integer stelle,String testo) {
		this.prodottoCorrente.inserisciRecensione(stelle,testo,this.session.getUtente());
	}

	public Prodotto getProdottoCorrente() {
		return prodottoCorrente;
	}

	public void setProdottoCorrente(Prodotto prodottoCorrente) {
		this.prodottoCorrente = prodottoCorrente;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}
 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Float getPrezzoDiListino() {
		return prezzoDiListino;
	}
 
	public void setPrezzoDiListino(Float prezzoDiListino) {
		this.prezzoDiListino = prezzoDiListino;
	}
//	public Map<String, Prodotto> getProdotti() {
//		return prodotti;
//	}
//
//	public void setProdotti(Map<String, Prodotto> prodotti) {
//		this.prodotti = prodotti;
//	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}


	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public Part getFile() {
		return file;
	}
	
	public void setFile(Part file) {
		this.file = file;
	}
	
	public ProdottoFacade getProdottoFacade() {
		return prodottoFacade;
	}
	
	public void setProdottoFacade(ProdottoFacade prodottoFacade) {
		this.prodottoFacade = prodottoFacade;
	}
	public List<Fornitore> getFornitori() {
		return fornitori;
	}
	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}
	public List<String> getFornitoriProdotto() {
		return fornitoriProdotto;
	}
	public void setFornitoriProdotto(List<String> fornitoriProdotti) {
		this.fornitoriProdotto = fornitoriProdotti;
	}
	public FornitoreFacade getFornitoreFacade() {
		return fornitoreFacade;
	}
	public void setFornitoreFacade(FornitoreFacade fornitoreFacade) {
		this.fornitoreFacade = fornitoreFacade;
	}

}
