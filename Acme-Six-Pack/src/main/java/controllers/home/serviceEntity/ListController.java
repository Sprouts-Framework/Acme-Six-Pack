package controllers.home.serviceEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceEntityService;
import domain.ServiceEntity;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("serviceEntityList")
@RequestMapping("home/serviceEntity")
public class ListController extends AbstractListController<ServiceEntity, ServiceEntityService> {

	protected String view() {
		return "serviceEntity/list";
	}

}
