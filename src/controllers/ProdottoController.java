package controllers;

import java.awt.Image;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import models.Fornitore;
import models.Prodotto;
import models.Store;

@ManagedBean
@SessionScoped
public class ProdottoController {
	private Store store;
	private Prodotto prodottoCorrente;
	private List<Prodotto> prodotti;
	private String nome;
	private String descrizione;
	private String codice;
	private Float prezzoDiListino;
	private Integer size;
	private Part foto;
	private String fileContent;
	

	@ManagedProperty(value="#{sessione}")
	private SessionBean session;
	
	@EJB(beanName="pFacade")
	private ProdottoFacade prodottoFacade;

	private String createProduct() {
		this.prodottoCorrente = prodottoFacade.createProduct(nome, codice, prezzoDiListino, descrizione, foto);
		return "aggiungiProdotto.xhtml";
	}
	
	////////////////////////////////prova file////////////////////////////////
	public String upload() {
		try {
			setFileContent(new Scanner(foto.getInputStream())
			.useDelimiter("\\A").next());
		} catch (IOException e) {
			// Error handling
		}
		return "aggiungiProdotto.xhtml";
	}
	public String validateFile(FacesContext ctx,
			UIComponent comp,
			Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part)value;
		if (!"text/plain".equals(file.getContentType())) {
			msgs.add(new FacesMessage("not a text file"));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
		return "aggiungiProdotto.xhtml";
	}
	//////////////////////////////////////////////////////////////////////////
	
	//UC4
	public String inserisciNuovoProdotto() {
		if(this.prodotti==null){
			this.prodotti = new LinkedList<>();
		}
		Prodotto p = new Prodotto(codice,descrizione,nome,prezzoDiListino,null,foto);
		this.prodotti.add(p);
		this.size=prodotti.size();
		//String codice,String descrizione,String nome,Integer prezzo,Integer quantita)
//		if(this.store.checkCodiceProdotto(codice)) {
//		}
//		else {
//			this.prodottoCorrente = new Prodotto(codice,descrizione,nome,prezzoDiListino,null);
			//TODO
//		}
		this.createProduct();
		
		return "aggiungiProdotto.xhtml";
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
	
	public void inserisciFoto(Part immagine) {
		this.prodottoCorrente.setImmagine(immagine);
	}
	
	public String fineInserimento() {
		this.session.getStore().inserisciProdotto(this.prodottoCorrente);
		this.prodottoCorrente = null;
		return "index.xhtml";
	}
	
	//UC4 BIS
	public void selezionaProdotto(String codice) {
		this.prodottoCorrente = this.store.getProdotto(codice);
	}
	
	public void modificaPrezzo(Float prezzo) {
		this.prodottoCorrente.setPrezzoDiListino(prezzo);
	}
	
	public void modificaQuantita(Integer quantita) {
		this.prodottoCorrente.setQuantita(quantita);
	}
	
	public void applicaSconto(String descrizione,Integer percentuale) {
		this.prodottoCorrente.aggiungiSconto(descrizione,percentuale);
	}
	
	public void eliminaSconto() {
		this.prodottoCorrente.setSconto(null);
	}
	
	
	//UC7
	//si ripete selezionaProdotto
	public void inserisciRecensione(Integer stelle,String testo) {
		this.prodottoCorrente.inserisciRecensione(stelle,testo,this.session.getUtente());
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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

	public Part getFoto() {
		return foto;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	
	
	
}
