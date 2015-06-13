package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.Amministratore;

@Stateless(name="aFacade")
public class AmministratoreFacade {

	
	@PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Amministratore getAmministratore(String email) {
		Query ricercaAmministratore = this.em.createQuery("SELECT a FROM Amministratore a WHERE a.email = :email");
		ricercaAmministratore.setParameter("email", email);
		try {
			Amministratore a = (Amministratore) ricercaAmministratore.getSingleResult();
			return a;
		} catch (Exception e) {
			return null;
		}
	}
	
}
