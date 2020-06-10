package tn.esprit.spring.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.IFactureService;

@Service
public class FactureService implements IFactureService{

	@Autowired
    private FactureRepository factureRepo;
	@Autowired
	private UserRepository userRepo;
	
	//////////***trouver la facture par panier id***////////////
	@Override
	public Facture findFactureByPanier(Panier p) {
			
		return factureRepo.findMyFactureJPQL(p);
	}


	@Override
	public List<Facture> findMyBills() {
		
		return factureRepo.getAllMyBills(userRepo.findById((long) 1).get());
	}


	@Override
	public void deleteMyBill(int factureId) {
		factureRepo.delete(factureRepo.findById(factureId).get());
		
	}

}
