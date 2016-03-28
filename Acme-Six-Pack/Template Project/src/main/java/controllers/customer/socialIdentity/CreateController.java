package controllers.customer.socialIdentity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SocialIdentityService;
import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("socialIdentityCustomerCreate")
@RequestMapping("socialIdentity/customer")
public class CreateController extends AbstractCreateController<SocialIdentity, SocialIdentityService> {

	@Override
	public boolean authorize(SocialIdentity domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "socialIdentity/create";
	}
	
	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
}
