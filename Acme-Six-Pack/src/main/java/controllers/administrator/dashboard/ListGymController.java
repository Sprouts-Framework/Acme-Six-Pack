package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;
import domain.Gym;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listGymDashboardController")
@RequestMapping("dashboard/administrator/gym")
public class ListGymController extends AbstractListController<Gym, GymService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/list/gym";
	}
	
	@Override
	protected Page<Gym> getPage(Pageable page, String searchCriteria, List<String> context) {
		Integer option = new Integer(context.get(0));
	
		switch(option){
		case 0:
			return service.findMostPopularGyms(page);
		case 1:
			return service.findLeastPopularGyms(page);
		case 8:
			return service.findGymsThatHaveMoreComments(page);
		default:
			return null;
		}
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		Integer quantity = null;

	
		switch(option){
		case 0:
			quantity = service.quantityMostPopularGyms();
			break;
		case 1:
			quantity = service.quantityLeastPopularGyms();
			break;
		case 8:
			quantity = service.quantityGymsWithMoreComments();
			break;
		default:
			break;
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}
	
	
	
}
