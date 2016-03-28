package controllers.home.gym;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;
import domain.Gym;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("gymServicesList")
@RequestMapping("home/gym/services")
public class ListGymServicesController extends AbstractListController<Gym, GymService>{

	@Override
	protected String view() {
		return "gym/list";
	}
	
	@Override
	protected Page<Gym> getPage(Pageable page, String searchCriteria, List<String> context) {
		int serviceEntityId = new Integer(context.get(0));
		Page<Gym> result = service.findGymsThatOfferService(serviceEntityId, page);
		return result;
	}

}
