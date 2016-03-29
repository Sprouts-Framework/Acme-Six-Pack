package controllers.administrator.serviceEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceEntityService;
import domain.ServiceEntity;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("serviceEntityCreate")
@RequestMapping("serviceEntity/administrator")
public class CreateServiceEntityController extends AbstractCreateController<ServiceEntity, ServiceEntityService> {

	@Override
	public boolean authorize(ServiceEntity domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceEntity/create";
	}
	
	@Override
	protected String onSuccess(){
		return "../../home/serviceEntity/list.do";
	}
	


}
