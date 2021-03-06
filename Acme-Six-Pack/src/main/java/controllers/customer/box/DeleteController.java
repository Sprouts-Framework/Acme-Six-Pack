package controllers.customer.box;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BoxService;
import domain.Box;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("boxDeleteCustomer")
@RequestMapping("box/customer")
public class DeleteController extends AbstractDeleteController<Box, BoxService> implements AddsToModel{

	@Override
	public boolean authorize(Box domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "box/delete";
	}

	@Override
	public void addToModel(final Map<String, Object> objects, List<String> context) {
		String rol = new String("customer");
		objects.put("actor", rol);
	}
	
}
