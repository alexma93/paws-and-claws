package prova;

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

import models.Utente;

@Stateless(name="provaFacade")
public class ProvaFacade {

	@PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Prova createProduct(byte[] c) {
		Prova p = new Prova();
		p.setContent(c);
		em.persist(p);
		return p;  
	}

	public Prova getP() {
		Query r = this.em.createQuery("SELECT p FROM Prova p WHERE p.nome = :email");
		r.setParameter("email", "pippo");
		try {
			Prova p = (Prova) r.getSingleResult();
			return p;
		} catch (Exception e) {
			return null;
		}
		
	}

}