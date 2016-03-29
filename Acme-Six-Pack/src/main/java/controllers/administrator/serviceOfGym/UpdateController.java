package controllers.administrator.serviceOfGym;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymService;
import domain.ServiceOfGym;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("serviceOfGymUpdate")
@RequestMapping("serviceOfGym/administrator")
public class UpdateController extends AbstractUpdateController<ServiceOfGym, ServiceOfGymService> {

	@Override
	public boolean authorize(ServiceOfGym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceOfGym/edit";
	}
	
	@Override
	protected String onSuccess() {
		Integer entityId = entityId(getPathVariables(getRequest()));
		
		ServiceOfGym serviceOfGym;
		serviceOfGym = service.findById(entityId);
		Assert.notNull(serviceOfGym);
		
		return "gym/display.do?gymId="+serviceOfGym.getGym().getId(); 
	}
	

}
