package controllers.administrator.serviceEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceEntityService;
import domain.ServiceEntity;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("serviceEntityDelete")
@RequestMapping("serviceEntity/administrator")
public class DeleteController extends AbstractDeleteController<ServiceEntity, ServiceEntityService> {

	@Override
	public boolean authorize(ServiceEntity domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceEntity/delete";
	}
	
	@Override
	protected String onSuccess() {
		return "home/serviceEntity/list.do";
	}
	
}
