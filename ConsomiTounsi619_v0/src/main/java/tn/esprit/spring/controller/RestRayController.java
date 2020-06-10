
package tn.esprit.spring.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.net.MalformedURLException;
import java.nio.file.Path;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Notif;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Ray;
import tn.esprit.spring.sevice.interfece.IProductService;
import tn.esprit.spring.sevice.interfece.IRayInfoService;

/**
 * 
 * Cette classe implémente les resources REST qui permettent de gérer l'identité d'un client.
 * http://websystique.com/spring-boot/spring-boot-rest-api-example/
 * 
 * @author Walid YAICH
 *
 */

@RestController
public class RestRayController {
	
	@Autowired
	private IRayInfoService rayInfoService;
	
	@Autowired
	IProductService iProductService;
	
	//private Logger logger = LoggerFactory.getLogger(IdentityController.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass()); // A faire pour toute les classes

	
	/**
	 * Retourner le client s'il existe dans la base
	 * @param clientId
	 * @return Client client
	 */
    @RequestMapping(value = "/ray/{rayId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)////
    public ResponseEntity<Ray> getRay(@PathVariable("rayId") Long rayId) {////
    	logger.debug("Invocation de la resource : GET /ray/{rayId}");
    	Ray ray = rayInfoService.getRayById(rayId);
        if (ray == null) {
        	logger.info("Impossible de récupérer le rayon");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ray, HttpStatus.OK);
    }
    
    
    /**
     * Ajouter un client
     * @param client
     */
	@RequestMapping(value = "/ray/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addRay(@RequestBody Ray ray){
    	logger.debug("Invocation de la resource : POST /ray/");
    	ray.setUser(UserController.USERCONNECTED);
    	
    	rayInfoService.addRay(ray);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	
	/**
	 * Mettre a jour les informations d'un client.
	 * @param clientId
	 * @param client
	 */
	@RequestMapping(value = "/ray/{rayId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateRay(@PathVariable("rayId") Long rayId, @RequestBody Ray ray){
    	logger.debug("Invocation de la resource : PUT /ray/");
    	rayInfoService.updateRayInfoById(ray, rayId);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	/**
	 * Supprimer un client
	 * @param clientId
	 */
	@RequestMapping(value = "/ray/{rayId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRay(@PathVariable("rayId") Long rayId){////
    	logger.debug("Invocation de la resource : DELETE /client/");
    	rayInfoService.deleteRay(new Ray(rayId));
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	/**
	 * @return le nombre total des clients dans la base
	 * @throws JSONException
	 */
//	@RequestMapping(value = "/ray/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> countRay() throws JSONException{
//	     JSONObject responseJSON = new JSONObject();
//	     responseJSON.put("nombre de clients", rayInfoService.countRays());
//	     return new ResponseEntity<>(responseJSON.toString(), HttpStatus.OK);
//    }
	@RequestMapping(value = "/ray/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> countRay(){
	    String ch=" ";
	   ch="nombre de rayons est "+rayInfoService.countRays();
	     return new ResponseEntity<>(ch, HttpStatus.OK);
  }

	
	//fonctionalités avancées
	
	//afficher all rayons
	@RequestMapping(value = "/rays", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ray>> getAllRays(){
    	logger.debug("Invocation de la resource : GET /product");
    	List<Ray> rays = rayInfoService.getAllRays();
    	if(rays.isEmpty()){
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<>(rays, HttpStatus.OK);
    }
	
	//nombre de rayons (category)
	@RequestMapping(value = "/raycat/{categoryray}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String> countRaycategoty(@PathVariable("categoryray") Category categoryray){
		    String ch=" ";
		   ch="nombre de rayons de cette category est "+rayInfoService.countRaysbyCategory(categoryray);
		     return new ResponseEntity<>(ch, HttpStatus.OK);
	  }
	
	//afficher rayons par category(category)
	@RequestMapping(value = "/rayscat/{categoryray}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ray>> getAllRayscat(@PathVariable("categoryray") Category categoryray){
    	logger.debug("Invocation de la resource : GET /product");
    	List<Ray> rays = (List<Ray>) rayInfoService.getRayByCat(categoryray);
    	if(rays.isEmpty()){
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<>(rays, HttpStatus.OK);
    }
	
	// calculer le nombre de produit par rayon(idray)
	@RequestMapping(value = "/numberproductbyray/{idray}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String> countProductsInRays(@PathVariable("idray") Long idray){
		    String ch=" ";
		   ch="nombre de produit par rayon de cet id est "+rayInfoService.countProductsInRays(idray);
		     return new ResponseEntity<>(ch, HttpStatus.OK);
	  }
	
	
	//afficher all rayons vide
		@RequestMapping(value = "/raysvide", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Ray>> getAllRaysvide(){
	    	logger.debug("Invocation de la resource : GET /product");
	    	List<Ray> rays = rayInfoService.getAllRays();
	    	List<Ray> 	result=  new ArrayList<>();
	    	int nbrays=rays.size();	
			Ray ray=new Ray();	
			
	    	if(rays.isEmpty()){
	        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	}
	    	for(int index = 0; index < nbrays; index++){
				ray=rays.get(index);
				if(rayInfoService.countProductsInRays(ray.getId())==0){
	    		result.add(ray);}
	    	}
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    }
		
		
		//afficher all rayons pleins
				@RequestMapping(value = "/raysplein", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			    public ResponseEntity<List<Ray>> getAllRaysplein(){
			    	logger.debug("Invocation de la resource : GET /product");
			    	List<Ray> rays = rayInfoService.getAllRays();
			    	List<Ray> 	result=  new ArrayList<>();
			    	int nbrays=rays.size();	
					Ray ray=new Ray();	
					
			    	if(rays.isEmpty()){
			        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    	}
			    		for(int index = 0; index < nbrays; index++){
						ray=rays.get(index);
						if(rayInfoService.countProductsInRays(ray.getId())==ray.getCapacity()){
			    		result.add(ray);}
			    	}
			    	return new ResponseEntity<>(result, HttpStatus.OK);
			    }
				
				//afficher liste de date expiration d'un id_rayon passé en paramètre
				@RequestMapping(value = "/raysdate/{idray}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			    public ResponseEntity<List<Date>> getListDate(@PathVariable("idray") Long idray){
			    	logger.debug("Invocation de la resource : GET /product");
			    	Ray ray = rayInfoService.getRayById(idray);
			    	List<Product> products1= ray.getProducts(); 
			    	List<Date> 	result=  new ArrayList<>();
			    
					Product product=new Product();	
					for(int index = 0; index < products1.size(); index++){
						product=products1.get(index);
			    		result.add(product.getExprdate());			    	
			    	}
			    	return new ResponseEntity<>(result, HttpStatus.OK);
			    }
		
				
				//ajouter un produit dans un rayon
				@RequestMapping(value = "/addProductparrayon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
			    public ResponseEntity<Void> addProducttt(@RequestBody Product product){
				
					if (!iProductService.existsById(product.getBarCode())) {
					//	product.setCategory(validCategory(product.getCategory()));
						
					//Category categoryproduct= (Category)Enum.Parse(typeof(Category), product.getCategory().getName());  
					//Category categoryproduct=product.getCategory().getName();
					//Enum to String using Enum.valueOf()
					//	Category categoryproduct = Category.valueOf(Category.class,  product.getCategory().getName());
				       
				        //Enum to String using Currency.valueOf()
						Category categoryproduct = Category.valueOf( product.getCategory().getName());
						List<Ray> rays =  rayInfoService.getRayByCat(categoryproduct);
						Ray ray=rays.get(0);
						if(rayInfoService.countProductsInRays(ray.getId())<ray.getCapacity())
						{
							rayInfoService.addProductAndAssignToRay(product, ray.getId());
						}
						else {
							throw new IllegalArgumentException("produit non ajouté! rayon est de capacité maximal");
						}
						
				return new ResponseEntity<>(HttpStatus.OK);
					}else {
						throw new IllegalArgumentException("Product already exist");
					}
				}

				
				
				//afficher Produit ayant date expiré
				@RequestMapping(value = "/productexprdate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			    public ResponseEntity<List<Product>> getProductexpr(){
			    	logger.debug("Invocation de la resource : GET /product");
			    	List<Product> product =  rayInfoService.getProductExprdate();
			    	if(product.isEmpty()){
			        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    	}
			    	return new ResponseEntity<>(product, HttpStatus.OK);
			    }
				
				
				//supprimer les produits de date expiré des rayon
				@RequestMapping(value = "/deleteexpr", method = RequestMethod.DELETE)
			    public ResponseEntity<Void> deleteexpr(){
			    	logger.debug("Invocation de la resourceee : DELETE /client/");
			    	List<Product> products =  rayInfoService.getProductExprdate();
			    
			    	for(int index = 0; index < products.size(); index++){
						Product product=products.get(index);
						
						rayInfoService.deleteNotif(product.getNotif()); 
						
						iProductService.removeProduct(product.getBarCode());
			    	}
			    	return new ResponseEntity<>(HttpStatus.OK);
			    }
				
				
				//afficher des notifs de user connecté
				@RequestMapping(value = "/addProductparrayon", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
			    public ResponseEntity<List<Notif>> affnotif(){
				
					List<Notif> notif=rayInfoService.myNotifications();
						
					if(notif.isEmpty()){
						throw new IllegalArgumentException("pas de produit de date expirée");}
						else {
							return new ResponseEntity<>(notif, HttpStatus.OK);
						}	
				}
				
				//creation de notif dans la base 
				@RequestMapping(value = "/createnotif/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
			    public ResponseEntity<Void> createnotif(){
			    	logger.debug("Invocation de la resource : POST /ray/");
			    	
			    	List<Product> products =  rayInfoService.getProductExprdate();
			    	for(int index = 0; index < products.size(); index++){
						Product product=products.get(index);
						rayInfoService.notifyuser(product,product.getRay());
			    	}
			    	
			    	
			    	return new ResponseEntity<>(HttpStatus.CREATED);
			    }
				
				//upload video <-> ajouter push 3al serveur
				@RequestMapping(value="/video", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
				public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
					File convertFile = new File("./uploads\\"+file.getOriginalFilename());
					convertFile.createNewFile();
					FileOutputStream fout = new FileOutputStream(convertFile);
					
					
					
					fout.write(file.getBytes());
					fout.close();
					return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
				}
				//download video
				@RequestMapping(value="/download3/{filename}", method=RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
				public ResponseEntity<Resource> downloadFile3(@PathVariable("filename") String filename) {
					File ff = new File("./uploads\\"+filename);
					
					 Path pathToFile =ff.toPath();
				        UrlResource resource = null;
				        try {
				            resource = new UrlResource(pathToFile.toUri());
				        } catch (MalformedURLException e) {
				            throw new RuntimeException(e);
				        }
				        return new ResponseEntity<>(resource, HttpStatus.OK);
				        }
				
				
				//affichege video sur place
				@RequestMapping(value="/download", method=RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
				public ResponseEntity<MultipartFile> downloadFile(@RequestParam("file") MultipartFile file) {
					//File ff = new File("./uploads\\"+filename);
					
//					 Path pathToFile =file.toPath();
//				        UrlResource resource = null;
//				        try {
//				            resource = new UrlResource(pathToFile.toUri());
//				        } catch (MalformedURLException e) {
//				            throw new RuntimeException(e);
//				        }
				        return new ResponseEntity<>(file, HttpStatus.OK);
				        }
				
				
				
				//download video22
				@RequestMapping(value="/download2", method=RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
				public ResponseEntity<Object> downloadFile2(@RequestParam("file") MultipartFile  file1) throws IOException {
					FileWriter filewriter =  null;
					try {
					 filewriter = new FileWriter(file1.getName());
						filewriter.write(file1.toString());
						filewriter.flush();
						
						File file = new File(file1.getName());
					
					InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
					headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
					headers.add("Pragma", "no-cache");
					headers.add("Expires", "0");
					
					ResponseEntity<Object> responseEntity = ResponseEntity.ok()
							.headers(headers)
							.contentLength(file.length())
							.contentType(MediaType
							.parseMediaType("application/txt")).body(resource);
					return responseEntity;
					} catch (Exception e ) {
	return new ResponseEntity<>("error occurredt", HttpStatus.INTERNAL_SERVER_ERROR);	
} finally {
	if(filewriter !=null)
		filewriter.close();

}
				
				}				
					
				

}
