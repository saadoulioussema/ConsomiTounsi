package tn.esprit.spring.sevice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.IPaymentService;

@Service
public class PaymentService implements IPaymentService{

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void createStripeCustomer(Long idUser) {
		Stripe.apiKey = "sk_test_CvsodfpwIsa4ZRQkFlA2B5Kr00wZ35cc9S";
		User user = userRepository.findById(idUser).get();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("description", "My First Test Customer (created for API docs)");
		params.put("email", user.getEmail());
		try {
		Customer customer = Customer.create(params);
		} catch (StripeException e) {

			throw new RuntimeException(e);
		}

	}

	@Override
	public String retrieveStripeCustomer(String idCus) {
//		Stripe.apiKey = "sk_test_CvsodfpwIsa4ZRQkFlA2B5Kr00wZ35cc9S";
//		try {
//			Customer a = Customer.retrieve(idCus);
//			Gson gson = new GsonBuilder().create();
//			return gson.toJson(a);
//		} catch (StripeException e) {
//			throw new RuntimeException(e);
//		}
		return null;
	}

	@Override
	public String createCardForCustumorStripe(String customerId, String card, String expMonth, String expYear,
			String cvc) throws StripeException {
		Stripe.apiKey = "sk_test_CvsodfpwIsa4ZRQkFlA2B5Kr00wZ35cc9S";
		Customer customer = Customer.retrieve(customerId);
		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", card);
		cardParam.put("exp_month", expMonth);
		cardParam.put("exp_year", expYear);
		cardParam.put("cvc", cvc);
		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam);
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		customer.getSources().create(source);
		return token.getId();
	}

	@Override
	public void chargeCustomer(int amount) {
		Stripe.apiKey = "sk_test_CvsodfpwIsa4ZRQkFlA2B5Kr00wZ35cc9S";
		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount);
		params.put("currency", "usd");
		params.put("customer", "cus_HBu01stVoWvPXU");

		try {
			Charge charge = Charge.create(params);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
		
	}

}
