package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import tn.esprit.spring.GeneratePdfReport;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.sevice.interfece.IFactureService;
import tn.esprit.spring.sevice.interfece.IPanierService;

@RestController
public class FactureController {

	
	@Autowired
    private IFactureService factureService;
	@Autowired
	private IPanierService panierService;
	
	@Autowired
	ServletContext context;
	
	@GetMapping(value = "/pdfreportFacture/{idPanier}")
    public String FactureReport(@PathVariable("idPanier") int idPanier) {
		 Panier p  = panierService.findPanier(idPanier);
         Facture f = factureService.findFactureByPanier(p);
         ByteArrayInputStream bis = GeneratePdfReport.FactureReport(f);
         HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FactureReport.pdf");
         int size = bis.available();
        char[] theChars = new char[size];
        byte[] bytes = new byte[size];
        bis.read(bytes, 0, size);
        for (int i = 0; i < size;)
          theChars[i] = (char) (bytes[i++] & 0xff);
   
        File convertFile = new File("./uploads\\FactureReport.pdf");
		try {
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(bytes);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return new String(theChars);
       
    }
	
	
 
	
}
