package controllers.customer.creditcard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CreditCardService;
import domain.CreditCard;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("creditCardCustomerCreate")
@RequestMapping("creditCard/customer")
public class CreateController extends AbstractCreateController<CreditCard, CreditCardService> {

	@Override
	public boolean authorize(CreditCard domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "creditCard/create";
	}
	
	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
}
