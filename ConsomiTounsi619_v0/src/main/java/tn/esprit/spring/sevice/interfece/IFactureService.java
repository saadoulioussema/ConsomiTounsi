package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Panier;

public interface IFactureService {
	
	public Facture findFactureByPanier(Panier p);
	public List<Facture> findMyBills();
	public void deleteMyBill(int factureId);

}
