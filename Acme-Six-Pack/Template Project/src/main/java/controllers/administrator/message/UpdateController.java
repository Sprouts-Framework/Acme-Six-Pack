package controllers.administrator.message;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorService;
import services.BoxService;
import services.MessageService;
import domain.Actor;
import domain.Box;
import domain.Message;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;

@Controller("messageUpdateAdministrator")
@RequestMapping("message/administrator")
public class UpdateController extends AbstractUpdateController<Message, MessageService> implements AddsToModel{
	
	@Autowired
	private BoxService boxService;
	
	@Autowired
	private ActorService actorService;
	
	@Override
	protected String view() {
		return "message/update";
	}

	
	@Override
	public boolean authorize(Message domainObject, UserAccount principal) {
		return domainObject.getBox().getActor().equals(principal.getId());
	}


	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Collection<Box> boxes;
		Actor thisActor;
		String rol = new String("administrator");
		thisActor = actorService.findActorByUserAccount(SignInService.getPrincipal().getUsername());
		
		boxes = boxService.findBoxes(thisActor.getId());
		objects.put("boxes", boxes);
		objects.put("actor", rol);
	}

	@Override
	protected String onSuccess() {
		return "../../../box/administrator/list.do";
	}
}
