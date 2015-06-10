package models;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="oFacade")
public class OrdineFacade {

	 @PersistenceContext(unitName = "model-unit")
		private EntityManager em;
	 
	 public Ordine createOrdine(Utente utente) {
		 Ordine o = new Ordine(utente);
		 return o;
	 }
	 
	 public void terminaOrdine(Ordine o) {
		 Query codiceOrdine = this.em.createQuery("SELECT COUNT(o) FROM Ordine o");
		 Integer codice = ((Long) codiceOrdine.getSingleResult()).intValue();
		 o.setCodice(codice);
		 o.setDataChiusura(new Date());
		 em.persist(o);
	 }

	public void aggiungiProdotto(Ordine ordine, Prodotto prodotto,
			Integer quantita) {
		if(!ordine.contiene(prodotto))
			ordine.getRighe().add(new RigaOrdine(prodotto,quantita));
		else {
			ordine.modificaQuantita(prodotto,quantita);
		}
		ordine.setPrezzoTotale(ordine.getPrezzoTotale() + prodotto.getPrezzoDiListino()*quantita);
	}

	public void aggiungiCoupon(Ordine ordine, Coupon coupon) {
		ordine.setCoupon(coupon);
	}
}
