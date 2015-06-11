package prova;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import models.UtenteFacade;

import org.omnifaces.util.Utils;

@ManagedBean
@SessionScoped // Can be @ViewScoped, but caution should be taken with byte[] property. You don't want to save it in session.
public class UploadImageBean {

    private Part file;
    private byte[] content;
    
    private Prova p;
   
	@EJB(beanName="provaFacade")
	private ProvaFacade f;
	
    public String vai() throws IOException {
    	content =  Utils.toByteArray(file.getInputStream());
    	//f.createProduct(content);
    	this.p = f.getP();
    	return "prova2.xhtml";
    }

    public ProvaFacade getF() {
		return f;
	}
	public void setF(ProvaFacade f) {
		this.f = f;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public byte[] getContent() {
        return content;
    }

	public Prova getP() {
		return p;
	}

	public void setP(Prova p) {
		this.p = p;
	}

}