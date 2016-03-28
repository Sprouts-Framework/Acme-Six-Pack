package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymService;
import domain.ServiceOfGym;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listServiceOfGymDashboardController")
@RequestMapping("dashboard/administrator/serviceOfGym")
public class ListServiceOfGymController extends AbstractListController<ServiceOfGym, ServiceOfGymService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/list/serviceOfGym";
	}
	
	@Override
	protected Page<ServiceOfGym> getPage(Pageable page, String searchCriteria, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		switch(option){
		case 9:
			return service.findServicesThatHaveMoreComments(page);
		default:
			return null;
		}
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		Integer quantity = null;

	
		switch(option){
		case 9:
			quantity = service.quantityServicesThatHaveMoreComments();
			break;
		default:
			break;
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}
	
}
