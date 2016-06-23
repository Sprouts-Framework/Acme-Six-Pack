package controllers.customer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import domain.Customer;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.CustomerService;

@Controller
@RequestMapping("profile/customer")
public class ShowController extends AbstractShowController<Customer, CustomerService> implements AddsToModel{

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

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		if(!context.isEmpty()){
		String error = context.get(0);
		objects.put("error", error);
		}
	}

}
