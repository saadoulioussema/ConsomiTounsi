package tn.esprit.spring.sevice.interfece;
import java.util.List;

import tn.esprit.spring.entity.Product_Line;;
public interface IProduct_LineService {

	public Product_Line ajouterProduct_Line(long prodId,int quantity);
	public void deleteProdLine(int prodlineId);
	public List<Product_Line> getProdLines();
	public void updateProdLineById(int idpl,int quantity);
	public int getLastProd();
	
}
 