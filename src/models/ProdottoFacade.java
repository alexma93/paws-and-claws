package models;

import javax.ejb.Stateless;

import java.awt.Image;
import java.sql.Blob;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;

@Stateless(name="pFacade")
public class ProdottoFacade {
	
    @PersistenceContext(unitName = "model-unit")
	private EntityManager em;
    
	public Prodotto createProduct(String nome, Float prezzoDiListino, String descrizione, String codice, Integer quantita,
									String specie, byte[] foto) {
		Prodotto p = new Prodotto(nome, prezzoDiListino, descrizione, codice, quantita, specie, null, null, foto);
		em.persist(p);
		return p;  
	}

	public Prodotto getProdotto(String codice) {
		Query ricercaProdotto = this.em.createQuery("SELECT p FROM Prodotto p WHERE p.codice = :codice");
		ricercaProdotto.setParameter("codice", codice);
		try {
			Prodotto p = (Prodotto) ricercaProdotto.getSingleResult();
			return p;
		} catch (Exception e) {
			return null;
		}
	}
}