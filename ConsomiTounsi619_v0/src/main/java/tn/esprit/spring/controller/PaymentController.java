package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import tn.esprit.spring.sevice.interfece.IPaymentService;

@RestController
public class PaymentController {

	@Autowired
	IPaymentService paymentService;
	
	//Create a stripe customer
	@PostMapping(value="/AddCustomer/{idUser}")
    public void createCustomer(@PathVariable("idUser")Long idUser)  {
             paymentService.createStripeCustomer(idUser);
    }
		
	
	@GetMapping("/retrieveCustomer/{idCus}")
	public String retrieveCustomer(@PathVariable("idCus")String idCus) {
		return paymentService.retrieveStripeCustomer(idCus);
	}
	
	
	@PostMapping(value="/createCardCustumorStripe/{customerId}/{card}/{expMonth}/{expYear}/{cvc}")
	@ResponseBody
    public String createCardCustumorStripe(@PathVariable("customerId")String customerId,@PathVariable("card")String card,@PathVariable("expMonth")String expMonth,@PathVariable("expYear")String expYear,@PathVariable("cvc")String cvc) throws StripeException  {
		return paymentService.createCardForCustumorStripe(customerId, card, expMonth, expYear, cvc);
    }
	
	
	@PostMapping(value="/chargeCustomer/{amount}")
	public void chargeCustomer(@PathVariable("amount")int amount) {
		paymentService.chargeCustomer(amount);
	}
		
	
	
}
