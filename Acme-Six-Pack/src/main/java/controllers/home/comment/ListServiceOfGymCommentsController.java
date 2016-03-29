package controllers.home.comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;
import domain.Comment;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listServiceOfGymCommentsController")
@RequestMapping("home/comment/serviceOfGym")
public class ListServiceOfGymCommentsController extends AbstractListController<Comment, CommentService>{

	@Override
	protected String view() {
		return "serviceOfGym/display";
	}
	
	@Override
	protected Page<Comment> getPage(Pageable page, String searchCriteria, List<String> context) {
		return service.findCommentsByServiceOfGymId(new Integer(context.get(0)), page);
	}
}
