package controllers.administrator.box;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorService;
import services.BoxService;
import domain.Actor;
import domain.Box;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;
import es.us.lsi.dp.services.SignInService;

@Controller("boxListAdministrator")
@RequestMapping("box/administrator")
public class ListController extends AbstractListController<Box, BoxService> implements AddsToModel {

	@Autowired
	private ActorService actorService;

	@Override
	protected Page<Box> getPage(Pageable page, String searchCriteria, List<String> context) {
		Actor thisActor;
		thisActor = actorService.findActorByUserAccount(SignInService.getPrincipal().getUsername());
		return service.findBoxes(thisActor.getId(), page);
	}

	@Override
	protected String view() {
		return "box/list";
	}

	@Override
	public void addToModel(final Map<String, Object> objects, List<String> context) {
		String rol = new String("administrator");
		objects.put("actor", rol);
	}
}
