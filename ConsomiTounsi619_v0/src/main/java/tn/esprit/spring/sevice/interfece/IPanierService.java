package tn.esprit.spring.sevice.interfece;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.entity.User;

public interface IPanierService {
	
	public void addProdLineToMyChart(Product_Line prodlineManagedEntity);
	public List<Product_Line> MyChartProdLine(int panierid);
	public List<Panier> getPaniers();
	public void ValidateMyChart(int userId);
	public Panier findPanier(int panierId);
	public Map<String,Integer> getAllProdLinesOfValidChart();
	public void desaffectProdLineFromChart(int PlId);
	public List<Product_Line> ProdLinesOfMyChart();
	public Panier findMyChart();
	public void viderMonPanier();
	public Panier findMyLastCart();
	public float SommeDeMesAchats(User userId);
}
