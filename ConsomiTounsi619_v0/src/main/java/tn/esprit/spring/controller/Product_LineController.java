package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.sevice.interfece.IPanierService;
import tn.esprit.spring.sevice.interfece.IProduct_LineService;


@RestController
public class Product_LineController {

	
	@Autowired
	IProduct_LineService iprodlineservice;
	
	//controle saisie
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	IPanierService panierService;
	
	@PostMapping(value="/ajouterProdLine/{idprod}/{quant}")
	public void ajouterProduct_Line( @PathVariable("idprod") long prodId, @PathVariable("quant") int quantity) {
//		boolean isHere=true;
//		//controle de saisie
//		//Product_line pl = new Product_Line()
//		List<Product_Line> mylist = panierService.ProdLinesOfMyChart();
//		for(Product_Line pl: mylist) {
//			if(pl.getProduit().getBarCode()==prodId && pl.getQuantité()==quantity) {
//				panierService.addProdLineToMyChart(new Product_Line(prodRepo.findById(prodId).get(),quantity) );
//				
//				isHere=false;
//			}
//		}
//				//methode sans controle
//		if(isHere==true) {		
		Product_Line p = iprodlineservice.ajouterProduct_Line(prodId, quantity);
		panierService.addProdLineToMyChart(p);

	//}
	}
	
	@DeleteMapping(value="/deleteProdLine/{idprodline}")
	public void deleteProduct_Line( @PathVariable("idprodline") int prodlineId) {
		iprodlineservice.deleteProdLine(prodlineId);
		
}
	
	
		 @GetMapping("/getAllProdLines")
		 public  List<Product_Line> getAllProdLines(){

			 List<Product_Line> list = iprodlineservice.getProdLines();
			 return list;
		 }
		 
	 
	@PutMapping(value = "/mettreAjourProdLineByIdJPQL/{id}/{newquantity}") 
 	@ResponseBody
	public void mettreAjourProdLineByIdJPQL(@PathVariable("newquantity") int quantity, @PathVariable("id") int plId) {	
	iprodlineservice.updateProdLineById(plId, quantity);
		
	}
	
	
}
