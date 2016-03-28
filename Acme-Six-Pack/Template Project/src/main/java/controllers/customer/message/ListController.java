package controllers.customer.message;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MessageService;
import domain.Message;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("messageListCustomer")
@RequestMapping("message/customer")
public class ListController extends AbstractListController<Message, MessageService> implements AddsToModel{

	@Override
	protected Page<Message> getPage(Pageable page, String searchCriteria,List<String> context) {
		int boxId = new Integer(context.get(0));
		Page<Message> result = service.findMessagesInBox(boxId, page);
		return result;
	}
	
	@Override
	protected String view() {
		return "message/list";
	}
	
	@Override
	public void addToModel(final Map<String, Object> objects, List<String> context) {
		String rol = new String("customer");
		objects.put("actor", rol);
	}

}
