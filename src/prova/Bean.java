package prova;


import java.io.IOException;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import java.io.OutputStream;

@ManagedBean
@SessionScoped
public class Bean {
  private Part file;
  private String fileContent;
 


public void upload() {
    try {
      setFileContent(new Scanner(file.getInputStream())
          .useDelimiter("\\A").next());
    } catch (IOException e) {
      // Error handling
    }
  }
 

  public Part getFile() {
    return file;
  }
 
  public void setFile(Part file) {
    this.file = file;
  }

public String getFileContent() {
	return fileContent;
}

public void setFileContent(String fileContent) {
	this.fileContent = fileContent;
}
}