package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceEntityService;
import domain.ServiceEntity;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listServiceEntityDashboardController")
@RequestMapping("dashboard/administrator/serviceEntity")
public class ListServiceEntityController extends AbstractListController<ServiceEntity, ServiceEntityService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/list/serviceEntity";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		Integer quantity = null;
		
		switch(option){
		case 2:
			quantity = service.quantityMostPopularServices();
			break;
		case 3:
			quantity = service.quantityLeastPopularServices();
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}
	
	@Override
	protected Page<ServiceEntity> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<ServiceEntity> result = null;
		Integer option = new Integer(context.get(0));
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		
		switch(option){
		case 2:
			result = service.findMostPopularServices(aux);
			break;
		case 3:
			result = service.findLeastPopularServices(aux);
			break;
		}
		
		return result;
	}

}
