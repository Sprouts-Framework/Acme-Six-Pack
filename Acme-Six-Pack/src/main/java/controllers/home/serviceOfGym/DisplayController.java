package controllers.home.serviceOfGym;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymService;
import domain.ServiceOfGym;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("serviceOfGymShow")
@RequestMapping("home/serviceOfGym")
public class DisplayController extends AbstractShowController<ServiceOfGym, ServiceOfGymService> implements AddsToModel {

	@Override
	public boolean authorize(ServiceOfGym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceOfGym/display";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		int serviceOfGymId;
		ServiceOfGym serviceOfGym;

		serviceOfGymId = Integer.valueOf(context.get(0));
		serviceOfGym = service.findById(serviceOfGymId);

		objects.put("serviceOfGym", serviceOfGym);
		objects.put("_viewName", "serviceOfGym/display");

	}

}
