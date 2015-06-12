package controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.Ordine;

@ManagedBean(name="evasione")
@SessionScoped
public class EvasioneController {
	private List<Ordine> ordiniNonEvasi;

	public List<Ordine> getOrdiniNonEvasi() {
		return ordiniNonEvasi;
	}

	public void setOrdiniNonEvasi(List<Ordine> ordiniNonEvasi) {
		this.ordiniNonEvasi = ordiniNonEvasi;
	}


}
