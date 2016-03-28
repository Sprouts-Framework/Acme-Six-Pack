package controllers.customer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CustomerService;
import domain.Customer;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("customerControllerUpdate")
@RequestMapping("profile/customer")
public class UpdateController extends AbstractUpdateController<Customer, CustomerService> {

	@Override
	public boolean authorize(Customer domainObject, UserAccount principal) {
		return domainObject.getUserAccount().equals(principal);
	}

	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
	
	@Override
	protected String view() {
		return "profile/update";
	}
	
	@Override
	public Customer getObject(Map<String, String> pathVariables, Customer entity, List<String> context) {
		Customer result;
		result = service.findByPrincipal();
		return result;
	}

}
