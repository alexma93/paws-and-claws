package models;

import java.util.Date;

import javax.ejb.Stateless;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="uFacade")
public class UtenteFacade {

	@PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Utente createUtente(String nome, String cognome, Date data, String email,
			String password) {
		Utente utente = new Utente(nome,cognome,data,email,password);
		return utente;  
	}

	public void confermaUtente(Utente u) {
		em.persist(u);
	}

	public boolean checkEmail(String email) {
		Query ricercaUtente = this.em.createQuery("SELECT u FROM Utente u WHERE u.email = :email");
		ricercaUtente.setParameter("email", email);
		try {
			ricercaUtente.getSingleResult();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public Utente getUtente(String email) {
		Query ricercaUtente = this.em.createQuery("SELECT u FROM Utente u WHERE u.email = :email");
		ricercaUtente.setParameter("email", email);
		try {
			Utente u = (Utente) ricercaUtente.getSingleResult();
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	public void confermaOrdine(Utente utente, Ordine ordine) {
		//utente.getOrdini().put(ordine.getCodice(), ordine);
	}
}