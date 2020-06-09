package tn.esprit.spring.sevice.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.FidelityCard;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.PaymentType;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.FidelityCardRepository;
import tn.esprit.spring.repository.PanierRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.Product_LineRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.IPanierService;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.entity.User;

@Service
public class PanierServiceImpl implements IPanierService{
	
	@Autowired
	PanierRepository panierRepository;
	@Autowired
	Product_LineRepository prodlineRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	FidelityCardRepository fidelityCardRepo;
	@Autowired
	FactureRepository factureRepo;
	@Autowired
	ProductRepository prodRepository;
	
	
	private static final Logger logger = LogManager.getLogger(PanierServiceImpl.class);

	
	//ajouter une ligne de commande à mon panier
	@Override
	public void addProdLineToMyChart(Product_Line prodlineManagedEntity) {
		 boolean isHere=true;
		User userManagedEntity = userRepository.findById((long) 1).get();
		LocalDateTime localDateTime = LocalDateTime.now();
		if (panierRepository.findMyChartJPQL(userManagedEntity).isEmpty()) {
			List<Product_Line> result = new ArrayList<>();
			result.add(prodlineManagedEntity);
			Panier p1 = new Panier(result, userManagedEntity, prodlineManagedEntity.getPrix(),
					Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()), false,
					prodlineManagedEntity.getQuantité(), prodlineManagedEntity.getPrix());
			
			panierRepository.save(p1);
			isHere=false;
		} else {
			
			Panier p = panierRepository.findMyChartJPQL(userManagedEntity).get(0);
			//nouveau code controle de saisie
			for(Product_Line pl : p.getProductlines()) {
				if(pl.getProduit().getName().equals(prodlineManagedEntity.getProduit().getName())) {
					pl.setPrix(pl.getPrix()+prodlineManagedEntity.getPrix());
					pl.setQuantité(pl.getQuantité()+prodlineManagedEntity.getQuantité());
					p.setPrixApayer(p.getPrixApayer() + prodlineManagedEntity.getPrix());
					p.setPrixTotal(p.getPrixTotal() + prodlineManagedEntity.getPrix());
					p.setQuantiteTotale(p.getQuantiteTotale() + prodlineManagedEntity.getQuantité());
					prodlineRepository.save(pl);
					panierRepository.save(p);
					isHere=false;
					
				}
			}
					if(isHere==true) {
						p.getProductlines().add(prodlineManagedEntity);
						p.setPrixApayer(p.getPrixApayer() + prodlineManagedEntity.getPrix());
						p.setPrixTotal(p.getPrixTotal() + prodlineManagedEntity.getPrix());
						p.setQuantiteTotale(p.getQuantiteTotale() + prodlineManagedEntity.getQuantité());
						panierRepository.save(p);
					}
					
				
			
			
			}
				
			
			
		

	}



	//trouber les lignes de commandes par panier id
	@Override
	public List<Product_Line> MyChartProdLine(int panierid) {
	
		return panierRepository.findById(panierid).get().getProductlines();
	}


	//lister tous les paniers
	@Override
	public List<Panier> getPaniers() {
		
		return (List<Panier>) panierRepository.findAll();
	}


	//valider mon panier//
	@Override
	public void ValidateMyChart(int userId) {
		Panier p = panierRepository.findMyChartJPQL(userRepository.findById((long) userId).get()).get(0);
		FidelityCard fc = fidelityCardRepo.getFidelityCardByUser(userRepository.findById(Long.valueOf(1)).get());
		int points = fc.getPoints();
		if (points >= 50 && points <= 100) {
			p.setRemise(p.getPrixTotal() / 50);

		} else if (points > 100 && points <= 150) {
			p.setRemise(p.getPrixTotal() / 20);
		} else if (points > 150 && points <= 200) {
			p.setRemise(p.getPrixTotal() / 14);
		} else if (points > 200 && points <= 300) {
			p.setRemise(p.getPrixTotal() / 10);
		} else if (points > 300 && points <= 500) {
			p.setRemise(p.getPrixTotal() / 8);
		} else if (points > 300 && points <= 500){
			p.setRemise(p.getPrixTotal() / 6);
		}
		else if (points==0){
			p.setRemise(0);
		}
		else {
			p.setRemise(p.getPrixTotal() / 5);
		}
		p.setTypePaiement(PaymentType.cash);
		p.setValid(true);
		p.setPrixApayer(p.getPrixTotal() - p.getRemise());
		fc.setPoints(fc.getPoints() + (int) p.getPrixTotal()/2);
		fidelityCardRepo.save(fc);
		panierRepository.save(p);
		Facture f = new Facture(p, 5);
		factureRepo.save(f);
		
		
	}



	//trouver panier par id
	@Override
	public Panier findPanier(int panierId) {
		Panier p = panierRepository.findById(panierId).get();
		return p;
	}



	//lister les categories et les quantitées achetées à chacune(statistiques)
	@Override
	public Map<String,Integer> getAllProdLinesOfValidChart() {
		List<String> text = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for(Product_Line pl:panierRepository.getAllProdLinesOfValidChart()) {
			
			if(!(text.contains(pl.getProduit().getCategory().getName()))) {
				text.add(pl.getProduit().getCategory().getName());
			}
		}
		for(String word : text) {
			for(Product_Line pl:panierRepository.getAllProdLinesOfValidChart()) {
				if(pl.getProduit().getCategory().getName().equals(word)) {
				 Integer times = map.get(word);
			        if(times == null) {
			           map.put(word, pl.getQuantité());
			        } else {
			           map.put(word, times +pl.getQuantité());
			        }
			}
		}
			}
				return map;
		
	}


	//supprimer ligne de commande de mon panier
	@Override
	@Transactional	
	public void desaffectProdLineFromChart(int PlId) {
		Panier p = panierRepository.findMyChartJPQL(userRepository.findById((long) 1).get()).get(0);

		int plNb = p.getProductlines().size();
		for (int index = 0; index < plNb; index++) {
			if (p.getProductlines().get(index).getPlId()==PlId) {
				Product_Line pl = p.getProductlines().get(index);

				p.setPrixApayer(p.getPrixApayer() - pl.getPrix());
				p.setPrixTotal(p.getPrixTotal() - pl.getPrix());
				p.setQuantiteTotale(p.getQuantiteTotale() - pl.getQuantité());
				Product pr = prodRepository.findById(pl.getProduit().getBarCode()).get();
				pr.setQuantity(pr.getQuantity() + pl.getQuantité());
				prodRepository.save(pr);
				panierRepository.save(p);
				p.getProductlines().remove(index);
				plNb--;

				prodlineRepository.deleteById(PlId);

				//break;//a revoir
			}
		}
		
	}


	//lister les lignes de commandes de mon panier
	@Override
	public List<Product_Line> ProdLinesOfMyChart() {
		
		User userManagedEntity = userRepository.findById(Long.valueOf(1)).get();
		return panierRepository.findMyChartProdLines(userManagedEntity);
	}


	//trouver mon panier non encore validé
	@Override
	public Panier findMyChart() {
		
		User userManagedEntity = userRepository.findById((long) 1).get();
		return panierRepository.findMyChartJPQL(userManagedEntity).get(0);
	}


	//vider mon panier
	@Override
	public void viderMonPanier() {
		
		Panier p = panierRepository.findMyChartJPQL(userRepository.findById((long) 1).get()).get(0);
		p.getProductlines().clear();
		p.setPrixApayer(0f);
		p.setPrixTotal(0f);
		p.setQuantiteTotale(0);
	p.setRemise(0f);
		panierRepository.save(p);
}


	//touver mon dernier panier validé
	@Override
	public Panier findMyLastCart() {
		List<Panier> mycarts = panierRepository.findMyValidChartJPQL(userRepository.findById((long) 1).get());
		return mycarts.get(mycarts.size()-1);
	}

	//somme des mes achats
	@Override
	public float SommeDeMesAchats(User userId) {
		float somme=0f;
		for(Panier pa : panierRepository.findMyValidChartJPQL(userId)) {
			somme+=pa.getPrixApayer();
		}
		return somme;
	}


	
	
	

}
