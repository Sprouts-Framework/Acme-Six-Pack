package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Customer;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;
import es.us.lsi.dp.services.CustomerService;

@Controller("listCustomerDashboardController")
@RequestMapping("dashboard/administrator/customer")
public class ListCustomerController extends AbstractListController<Customer, CustomerService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/list/actor";
	}
	
	@Override
	protected Page<Customer> getPage(Pageable page, String searchCriteria, List<String> context) {
		Integer option = new Integer(context.get(0));
	
		switch(option){
		case 4:
			return service.findCustomersWhoHavePaidMoreFees(page);
		case 5:
			return service.findCustomersWhoHavePaidLessFees(page);
		default:
			return null;
		}
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		Integer quantity = null;
		
		switch(option){
		case 4:
			quantity = service.quantityCustomersWhoHavePaidMoreFees();
			break;
		case 5:
			quantity = service.quantityCustomersWhoHavePaidLessFees();
			break;
		default:
			break;
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}
	
	
	
}
