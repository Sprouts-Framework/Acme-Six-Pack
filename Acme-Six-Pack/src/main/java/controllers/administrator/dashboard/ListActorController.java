package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorService;
import domain.Actor;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listActorDashboardController")
@RequestMapping("dashboard/administrator/actor")
public class ListActorController extends AbstractListController<Actor, ActorService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/list/actor";
	}
	
	@Override
	protected Page<Actor> getPage(Pageable page, String searchCriteria, List<String> context) {
		Integer option = new Integer(context.get(0));
	
		switch(option){
		case 6:
			return service.findActorsWhoSendMoreSpamMessages(page);
		case 7:
			return service.findPage(page,searchCriteria);
		case 13:
			return service.findActorsWhoHaveRemovedMoreComments(page);
		default:
			return null;
		}
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		Integer quantity = null;
		
		switch(option){
		case 6:
			quantity = service.quantityActorsWhoSendMoreSpamMessages();
			break;
		case 7:
			if(context.size()>1){
				Integer actorId = new Integer(context.get(1));
				Actor actor;
				actor = service.findById(actorId);
				Double averageNumberOfMessagesOfAnActor = service.avarageNumberOfMessagesInActorBoxes(actorId);
				objects.put("averageNumberOfMessagesOfAnActor", averageNumberOfMessagesOfAnActor);
				objects.put("actorUsername", actor.getUserAccount().getUsername());
			}
			break;
		case 13:
			quantity = service.quantityActorsWhoHaveRemovedMoreComments();
		default:
			break;
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}
	
	
	
}
