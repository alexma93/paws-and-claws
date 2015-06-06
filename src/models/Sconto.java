package models;

public class Sconto {
	private Long id;
	private String descrizione;
	private Integer percentuale;
	
	public Sconto(String descrizione,Integer percentuale) {
		this.descrizione = descrizione;
		this.percentuale = percentuale;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Integer getPercentuale() {
		return percentuale;
	}
	
	public void setPercentuale(Integer percentuale) {
		this.percentuale = percentuale;
	}
	

}
