package tn.esprit.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.sevice.interfece.IPanierService;
import tn.esprit.spring.sevice.interfece.IPaymentService;

@RestController
public class PanierController {
	
	@Autowired
	IPanierService panierservice;
	@Autowired
	IPaymentService paymentService;

	
	/*@PostMapping(value="/addProdLineToMyChart/{idprodline}")
	public void addProdLineToMyChart( @PathVariable("idprodline") int prodlineId) {
		panierservice.addProdLineToMyChart(prodlineId);
		

	}*/
	
	@GetMapping("/getProChart/{idpanier}")
	 @ResponseBody
	 public List<Product_Line> getProChart(@PathVariable("idpanier")int idpanier){
		 List<Product_Line> res = panierservice.MyChartProdLine(idpanier);
			for(int i=0;i<res.size();i++) {
				System.out.println(res.get(i).toString());
			}

		 return panierservice.MyChartProdLine(idpanier);
	 }
	 
	 @GetMapping("/getAllPaniers")
	 @ResponseBody
	 public List<Panier> getAllPaniers(){
		
		 return panierservice.getPaniers();
	 }
	 
	 @PutMapping("/validateMyBasket/{idUser}")
	 public void validateMyBasket(@PathVariable ("idUser") int idUser) {
		 panierservice.ValidateMyChart(idUser);
		 
		 
	}
	 
	 @GetMapping("/getAllValidProdLines")
	 @ResponseBody
	 public Map<String,Integer> getAllValidProdLines(){
		
		 return panierservice.getAllProdLinesOfValidChart();
	 }
	 
	 
	/* @PutMapping("/desaffectProdLineFromChart/{idprodLine}")
	 public void desaffectProdLineFromChart(@PathVariable ("idprodLine") int idprodLine) {
		 panierservice.desaffectProdLineFromChart(idprodLine);;
		 
		 
	}
	 */
	 @GetMapping("/getMyChartProdLines")
	 @ResponseBody
	 public List<Product_Line> ProdLinesOfMyChart(){
		
		 return panierservice.ProdLinesOfMyChart();
	 }
	 
	 
	 /*@PutMapping("/desaffectProdLine/{idpl}")
	 public void desaffectProdLine(@PathVariable ("idpl") int idpl) {
		 panierservice.desaffectProdLineFromChart(idpl);
		 
		 
	}*/
}







