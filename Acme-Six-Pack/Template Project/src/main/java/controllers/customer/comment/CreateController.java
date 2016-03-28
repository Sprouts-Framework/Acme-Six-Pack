package controllers.customer.comment;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;
import domain.Comment;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("createCommentController")
@RequestMapping("comment/customer")
public class CreateController extends AbstractCreateController<Comment, CommentService> implements AddsToModel{

	@Override
	public boolean authorize(Comment domainObject, UserAccount principal) {
		return domainObject.getActor().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "comment/create";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Assert.isTrue(context.size()==2);
		objects.put("gymId", context.get(0));
		objects.put("serviceOfGymId", context.get(1));
	}
	
	@Override
	protected String onSuccess() {
		List<String> context = getContext();
		Integer gymId = new Integer(context.get(0));
		Integer serviceOfGymId = new Integer(context.get(1));
		
		if(gymId!=0)
			return "../../../home/gym/"+gymId+"/show.do";
		else
			return "../../../home/serviceOfGym/"+serviceOfGymId+"/show.do";
	}
	

}
