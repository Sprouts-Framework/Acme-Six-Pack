package controllers.customer.gym;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;
import domain.Gym;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("gymListCustomer")
@RequestMapping("gym/customer")
public class ListController extends AbstractListController<Gym, GymService> implements AddsToModel {

	@Override
	protected Page<Gym> getPage(final Pageable page, final String searchCriteria, List<String> context) {
		return service.findGymsWithActiveFeePaymentByCustomerId(page);
	}

	@Override
	protected String view() {
		return "gym/list";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("fromCustomer", 0);
	}

}
