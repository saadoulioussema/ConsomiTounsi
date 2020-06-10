package tn.esprit.spring.sevice.interfece;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

public interface IPaymentService {

	public void createStripeCustomer(Long idUser);
	public String retrieveStripeCustomer(String idCus);
	public String createCardForCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc) throws StripeException;
	public void chargeCustomer(int amount);
}
