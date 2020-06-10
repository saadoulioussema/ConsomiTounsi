package tn.esprit.spring.sevice.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Date;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.Product_LineRepository;
import tn.esprit.spring.sevice.interfece.IProduct_LineService;

@Service
public class Product_LineServiceImpl implements IProduct_LineService{

	
	@Autowired
	Product_LineRepository prodLineRepository;
	@Autowired
	ProductRepository produitRepository;
	
	private static final Logger logger = LogManager.getLogger(Product_LineServiceImpl.class);
	
	@Override
	public Product_Line ajouterProduct_Line(long prodId, int quantity) {
		
	LocalDateTime localDateTime = LocalDateTime.now();
		Product p = produitRepository.findById(prodId).get();
		p.setQuantity(p.getQuantity()-quantity);
		produitRepository.save(p);
		Product_Line prodLine = new Product_Line();
		prodLine.setProduit(p);
		prodLine.setDateprodline(Date.from( localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		//prodLine.setDateprodline(LocalDateTime.now().);
		int price = prodLineRepository.getPriceProdJPQL(prodId);
		prodLine.setQuantité(quantity);
		prodLine.setPrix(price*quantity);
		prodLineRepository.save(prodLine);
		return prodLine;
}


	@Override
	public void deleteProdLine(int prodlineId) {
		Product_Line pl = prodLineRepository.findById(prodlineId).get();
		prodLineRepository.delete(pl);
		
	}


	@Override
	public List<Product_Line> getProdLines() {
		List<Product_Line> list = prodLineRepository.findAll();
		return list;
	}


	@Override
	public void updateProdLineById(int idpl, int quantity) {
		Product_Line pl = prodLineRepository.findById(idpl).get();
		float prix1 = ((pl.getPrix())/(pl.getQuantité()))*quantity;
		prodLineRepository.mettreAjourProductLineByIdJPQL(quantity, prix1, idpl);
		
	}


	@Override
	public int getLastProd() {
		return prodLineRepository.getLastProdLineJPQL();
	}




}
