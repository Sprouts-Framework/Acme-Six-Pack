package controllers.administrator.gym;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;

import domain.Gym;


import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller
@RequestMapping("gym/administrator")
public class UpdateController extends AbstractUpdateController<Gym, GymService>{

	@Override
	public boolean authorize(Gym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "gym/update";
	}

	@Override
	protected String onSuccess() {
		return "../../../home/gym/list.do";
	}
	

}
