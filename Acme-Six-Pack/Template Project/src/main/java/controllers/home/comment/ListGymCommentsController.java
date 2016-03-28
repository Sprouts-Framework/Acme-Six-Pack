package controllers.home.comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;
import domain.Comment;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listGymCommentsController")
@RequestMapping("home/comment/gym")
public class ListGymCommentsController extends AbstractListController<Comment, CommentService>{

	@Override
	protected String view() {
		return "gym/display";
	}
	
	@Override
	protected Page<Comment> getPage(Pageable page, String searchCriteria, List<String> context) {
		return service.findCommentsByGymId(new Integer(context.get(0)), page);
	}
}
