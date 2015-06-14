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
	
	public void addFornitore(Prodotto p,String iva) {
		Query ricercaFornitore = this.em.createQuery("SELECT f FROM Fornitore f WHERE f.iva = :iva");
		ricercaFornitore.setParameter("iva", iva);
		try {
			Fornitore f = (Fornitore) ricercaFornitore.getSingleResult();
			p.getFornitori().add(f);
		} catch (Exception e) {
		}
	}

}