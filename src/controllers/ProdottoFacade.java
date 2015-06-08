package controllers;

import javax.ejb.Stateless;

import java.awt.Image;
import java.sql.Blob;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

import models.Prodotto;

@Stateless(name="pFacade")
public class ProdottoFacade {
	
    @PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Prodotto createProduct(String nome, String codice, Float prezzoDiListino, String descrizione,Part foto) {
		Prodotto product = new Prodotto(codice,descrizione,nome,prezzoDiListino,null,foto);
		em.persist(product);
		return product;  
	}
}