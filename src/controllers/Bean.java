package controllers;

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
  private String image;
  private OutputStream outputStream;
 


public void upload() {
    try {
      setFileContent(new Scanner(file.getInputStream())
          .useDelimiter("\\A").next());
    } catch (IOException e) {
      // Error handling
    }
  }
 


  public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public OutputStream getOutputStream() {
	return outputStream;
}

public void setOutputStream(OutputStream outputStream) {
	this.outputStream = outputStream;
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