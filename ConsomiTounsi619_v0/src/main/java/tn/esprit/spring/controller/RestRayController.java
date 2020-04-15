
package tn.esprit.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Ray;
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

	
	
}
