package tn.esprit.spring.frontcontroller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.GeneratePdfReport;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.sevice.interfece.IFactureService;
import tn.esprit.spring.sevice.interfece.IPanierService;
import tn.esprit.spring.sevice.interfece.IProductService;
import tn.esprit.spring.sevice.interfece.IProduct_LineService;
import tn.esprit.spring.sevice.interfece.IUserService;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.entity.User;

@Scope(value = "session")
@Controller(value = "PanierJSFController")
@ELBeanName(value = "PanierJSFController")
@Join(path = "/products", to = "/productsList.jsf")
public class PanierJSFController {

	@Autowired
	IPanierService panierService;
	
	private Panier monpanier;
	private int myquantity;
	private List<Product_Line> myprodlines;
	private int mypanierId;
	private List<Product> myproducts;
	private int panierId;
	private User userconnected;
	private float sommeDesAchats;
	private Panier yetValidatedCart;
	private List<Facture> myBills;
	private int billsTotal=0;
	private float prixTotalAPayer;
	
	@Autowired
	IFactureService factureService;
	@Autowired
	IProductService productService;
	@Autowired
	IProduct_LineService prodlineService;
	@Autowired
	IUserService userService;
	
	
	
	
	
	public int getPanierId() {
		 panierId = panierService.findMyChart().getIdPanier();
		 return panierId;
	}




	public void setPanierId(int panierId) {
		this.panierId = panierId;
	}




	public float getThePriceOfBill(Facture f) {
		prixTotalAPayer = f.getFraisDeliv()+f.getPanierDetail().getPrixApayer();
		return prixTotalAPayer;
	}
	
	
	
	
	public List<Facture> getMyBills() {
		 myBills = factureService.findMyBills();
		 return myBills;
	}

	public void setMyBills(List<Facture> myBills) {
		this.myBills = myBills;
	}

	

	
	

	public int getBillsTotal() {
		for(Facture fac : myBills) {
			billsTotal+=fac.getPanierDetail().getQuantiteTotale();
		}
			return billsTotal;
	}
	




	public void setMybillsquantity(int mybillsquantity) {
		this.billsTotal = mybillsquantity;
	}




	public Panier getYetValidatedCart() {
		 yetValidatedCart = panierService.findMyChart();
		 return yetValidatedCart;
	}

	public void setYetValidatedCart(Panier yetValidatedCart) {
		this.yetValidatedCart = yetValidatedCart;
	}

	public float getSommeDesAchats() {
		sommeDesAchats= panierService.SommeDeMesAchats(userconnected);
		return sommeDesAchats;
	}

	public void setSommeDesAchats(float sommeDesAchats) {
		this.sommeDesAchats = sommeDesAchats;
	}

	public User getUserconnected() {
		userconnected = userService.findbyid((long) 1);
		return userconnected;
	}

	public void setUserconnected(User userconnected) {
		this.userconnected = userconnected;
	}

	public List<Product> getMyproducts() {
		myproducts= productService.getProducts();
		return myproducts;
	}

	public void setMyproducts(List<Product> myproducts) {
		this.myproducts = myproducts;
	}

	public int getMypanierId() {
		mypanierId = panierService.findMyChart().getIdPanier();
		return mypanierId;
	}

	public void setMypanierId(int mypanierId) {
		this.mypanierId = mypanierId;
	}

	public int getMyquantity() {
		myquantity = panierService.findMyChart().getQuantiteTotale();
		return myquantity;
	}

	public void setMyquantity(int myquantity) {
		this.myquantity = myquantity;
	}

	public Panier getMonpanier() {
		monpanier=  panierService.findMyChart();
		return monpanier;
	}

	public void setMonpanier(Panier monpanier) {
		this.monpanier = monpanier;
	}

	public List<Product_Line> getMyprodlines() {
		myprodlines =  panierService.ProdLinesOfMyChart();
		return myprodlines;
	}

	public void setMyprodlines(List<Product_Line> myprodlines) {
		this.myprodlines = myprodlines;
	}
	
	public void deleteProdLine(int prodlineId) {
		panierService.desaffectProdLineFromChart(prodlineId);
}
	
	public int prodlinesLength() {
		return monpanier.getQuantiteTotale();
	}
	
	
	
	public String validateMonPanier() {
		String navigateTo = "null";
		panierService.ValidateMyChart(userconnected.getId().byteValue() );
		navigateTo = "/productsList.xhtml?faces-redirect=true";
		return navigateTo;
	}
	
	public String addToMyCart(long prodId,int quantity) {
		String navigateTo = "null";
		Product_Line p = prodlineService.ajouterProduct_Line(prodId , quantity);
		//Product_Line p = new Product_Line(produitRepo.findById(prodId).get(),quantity);
		panierService.addProdLineToMyChart(p);
		navigateTo = "/myChartProdLines.xhtml?faces-redirect=true";
		return navigateTo;
	}
	
	public String ToMyCart() {
		String navigateTo = "/myCart.xhtml?faces-redirect=true";
		return navigateTo;
	}
	
	public void deleteMyBill(int factureId ) {
		factureService.deleteMyBill(factureId);
	}
	
	public void generateFacturePdf(Panier panierId) {
		Facture f = factureService.findFactureByPanier(panierId);
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
	}
	
}