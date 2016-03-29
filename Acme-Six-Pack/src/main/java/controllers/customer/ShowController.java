package controllers.customer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CustomerService;
import domain.Customer;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller
@RequestMapping("profile/customer")
public class ShowController extends AbstractShowController<Customer, CustomerService> {

	@Override
	public boolean authorize(Customer domainObject, UserAccount principal) {
		
		return domainObject.getUserAccount().equals(principal);
	}
	
	@Override
	public Customer getObject(final Map<String, String> pathVariables, final Customer entity, List<String> context){
		return service.findByPrincipal();
	}
	
	
	@Override
	protected String view() {
		return "profile/show";
	}

}
