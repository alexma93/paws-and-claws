package models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="couponFacade")
public class CouponFacade {

	@PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Coupon getCoupon(String codice) {
		Query ricercaCoupon = this.em.createQuery("SELECT c FROM Coupon c WHERE c.codice = :codice");
		ricercaCoupon.setParameter("codice", codice);
		try {
			Coupon c = (Coupon) ricercaCoupon.getSingleResult();
			return c;
		} catch (Exception e) {
			return null;
		}
	}
	
}
