package controllers.home.gym;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;
import domain.Gym;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("gymList")
@RequestMapping("home/gym")
public class ListController extends AbstractListController<Gym, GymService> implements AddsToModel{

	@Override
	protected String view() {
		return "gym/list";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("requestURI", "home/gym/list.do");
		
	}

}
