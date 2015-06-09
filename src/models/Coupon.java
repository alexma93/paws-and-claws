package models;

public class Coupon {
	private Long id;
	private String codice;
	private Integer percentuale;
	
	public Coupon() {
		
	}
	public Coupon(String codice,Integer percentuale) {
		this.codice = codice;
		this.percentuale = percentuale;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public Integer getPercentuale() {
		return percentuale;
	}
	
	public void setPercentuale(Integer percentuale) {
		this.percentuale = percentuale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}
	
	
}
