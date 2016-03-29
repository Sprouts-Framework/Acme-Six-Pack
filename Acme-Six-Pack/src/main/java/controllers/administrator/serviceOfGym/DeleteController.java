package controllers.administrator.serviceOfGym;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymService;
import domain.ServiceOfGym;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("deleteServiceOfGymController")
@RequestMapping("serviceOfGym/administrator")
public class DeleteController extends AbstractDeleteController<ServiceOfGym, ServiceOfGymService> {

	@Override
	public boolean authorize(ServiceOfGym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceOfGym/delete";
	}
	
	@Override
	protected String onSuccess() {
		return "../../../home/gym/list.do";
	}

}
