package controllers.administrator.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;
import domain.Comment;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;

@Controller("deleteCommentController")
@RequestMapping("comment/administrator")
public class DeleteController extends AbstractDeleteController<Comment, CommentService> {

	@Override
	public boolean authorize(Comment domainObject, UserAccount principal) {
		return SignInService.checkAuthority("Administrator");
	}

	@Override
	protected String view() {
		return "comment/delete";
	}

	@Override
	protected String onSuccess() {
		return "../../../home/gym/list.do";
	}
}
