package controllers.administrator.gym;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;
import domain.Gym;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("gymAdministratorCreate")
@RequestMapping("gym/administrator")
public class CreateController extends AbstractCreateController<Gym, GymService>{

	@Override
	public boolean authorize(Gym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "gym/create";
	}
	
	@Override
	protected String onSuccess() {
		return "../../home/gym/list.do";
	}

}
