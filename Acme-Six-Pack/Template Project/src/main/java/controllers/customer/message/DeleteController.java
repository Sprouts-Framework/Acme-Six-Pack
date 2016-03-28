package controllers.customer.message;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MessageService;
import domain.Message;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("messageDeleteCustomer")
@RequestMapping("message/customer")
public class DeleteController extends AbstractDeleteController<Message, MessageService> implements AddsToModel{

	
	@Override
	protected String view() {
		return "message/delete";
	}

	@Override
	public boolean authorize(Message domainObject, UserAccount principal) {
		return domainObject.getBox().getActor().equals(principal.getId());
	}
	

	@Override
	public void addToModel(final Map<String, Object> objects, List<String> context) {
		String rol = new String("customer");
		objects.put("actor", rol);
	}
	
	@Override
	protected String onSuccess() {
		return "../../../box/customer/list.do";
	}


}
