package controllers.customer.creditcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CreditCardService;
import services.CustomerService;
import domain.CreditCard;
import domain.Customer;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("creditCardCustomerDelete")
@RequestMapping("creditCard/customer")
public class DeleteController extends AbstractDeleteController<CreditCard, CreditCardService>{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public boolean authorize(CreditCard domainObject, UserAccount principal) {
		Customer customer;
		customer = customerService.findByCreditCard(domainObject.getId());
		Assert.notNull(customer);
		
		return customer.getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "creditCard/delete";
	}

	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
}
