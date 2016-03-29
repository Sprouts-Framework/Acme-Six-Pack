package controllers.home.serviceOfGym;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymService;
import domain.ServiceOfGym;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("serviceOfGymListController")
@RequestMapping("home/gym/serviceOfGym")
public class ListController extends AbstractListController<ServiceOfGym, ServiceOfGymService> {

	@Override
	protected String view() {
		return "gym/display";
	}
	
	@Override
	protected Page<ServiceOfGym> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<ServiceOfGym> result;
		
		int gymId = Integer.valueOf(context.get(0));
		result = service.findServiceOfGymByGymId(gymId, page);
		Assert.notNull(result);
		
		return result;
	}
	

}
