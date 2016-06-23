package controllers.customer.socialIdentity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SocialIdentityService;

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
