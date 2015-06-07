package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import models.Prodotto;

//@Stateless(name="pFacade")
public class ProdottoFacade {
	
  //  @PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Prodotto createProduct(String nome, String codice, Float prezzoDiListino, String descrizione) {
		Prodotto product = new Prodotto(codice,descrizione,nome,prezzoDiListino,null);
		em.persist(product);
		return product;  
	}
}