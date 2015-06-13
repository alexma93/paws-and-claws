package comparator;

import java.util.Comparator;

import models.Prodotto;

public class ProdottoComparator implements Comparator<Prodotto> {
	private String ordinamento;
	
	public ProdottoComparator(String ordinamento) {
		this.setOrdinamento(ordinamento);
	}
	
	public int compare(Prodotto p1,Prodotto p2)  {
		if(ordinamento.equals("nome"))
			return p1.getNome().compareTo(p2.getNome());
		else if (ordinamento.equals("dataInserimento"))
			return p1.getDataInserimento().compareTo(p2.getDataInserimento());
		else if (ordinamento.equals("prezzoDiListino"))
			return p1.getPrezzoDiListino().compareTo(p2.getPrezzoDiListino());
		else if (ordinamento.equals("votoMedio"))
			return p1.getVotoMedio().compareTo(p2.getVotoMedio());
		else return 1;
	}

	public String getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(String ordinamento) {
		this.ordinamento = ordinamento;
	}
}
