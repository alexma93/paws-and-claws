package facades;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Fornitore;
import models.Prodotto;

@Stateless(name="fFacade")
public class FornitoreFacade {

	@PersistenceContext(unitName = "model-unit")
	private EntityManager em;
	
	public List<Fornitore> getFornitori() {
		TypedQuery<Fornitore> result = this.em.createNamedQuery("findAllFornitori", Fornitore.class);
		List<Fornitore> fornitori = result.getResultList();
		return fornitori;
	}
	
	public Fornitore getFornitore(String codice) {
		Query ricercaFornitore = this.em.createQuery("SELECT f FROM Fornitore f WHERE f.codice = :codice");
		ricercaFornitore.setParameter("codice", codice);
		try {
			Fornitore f = (Fornitore) ricercaFornitore.getSingleResult();
			return f;
		} catch (Exception e) {
			return null;
		}
	}

}