package facades;

import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Prodotto;
import comparator.ProdottoComparator;

@Stateless(name="pFacade")
public class ProdottoFacade {

	@PersistenceContext(unitName = "model-unit")
	private EntityManager em;

	public Prodotto createProduct(String nome, Float prezzoDiListino, String descrizione, String codice, Integer quantita,
			String specie, byte[] foto) {
		Prodotto p = new Prodotto(nome, prezzoDiListino, descrizione, codice, quantita, specie, 0, foto);
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

	public List<Prodotto> getProdotti(String specie) {
		TypedQuery<Prodotto> prodotti;
		if(specie!=null) {
			prodotti = this.em.createQuery("SELECT p FROM Prodotto p WHERE p.specie = :specie",Prodotto.class);
			prodotti.setParameter("specie", specie);
		}
		else
			prodotti = this.em.createQuery("SELECT p FROM Prodotto p",Prodotto.class);
		return prodotti.getResultList();
	}
	
	public List<Prodotto> ordinaCatalogo(List<Prodotto> catalogo,String ordine) {
		List<Prodotto> c = new ArrayList<>(catalogo);
		c.sort(new ProdottoComparator(ordine));;
		return c;
	}

	public List<Prodotto> ricercaCatalogo(List<Prodotto> catalogo,
			String specie, Integer voto, Integer prezzo) {
		TypedQuery<Prodotto> result;
		
		String s = "SELECT p FROM Prodotto p WHERE p.prezzoDiListino <= :prezzo";
		if(!specie.equals("tutte"))
			s = s.concat(" AND p.specie = :specie");
		if (voto!=0)
			s = s.concat(" AND p.votoMedio >= :voto");
		
		result = this.em.createQuery(s, Prodotto.class);
		result.setParameter("prezzo", prezzo);
		if(!specie.equals("tutte"))
			result.setParameter("specie", specie);
		if (voto!=0)
			result.setParameter("voto", voto);
		return result.getResultList();
	}

	public List<Prodotto> ricercaTestualeCatalogo(List<Prodotto> catalogo,
			String parola) {
		TypedQuery<Prodotto> result = this.em.createQuery("SELECT p FROM Prodotto p WHERE p.nome "
				+ "LIKE :parola",Prodotto.class);
		result.setParameter("parola", "%"+parola+"%");
		return result.getResultList();
	}
}