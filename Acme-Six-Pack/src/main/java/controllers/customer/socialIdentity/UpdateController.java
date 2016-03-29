package controllers.customer.socialIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CustomerService;
import services.SocialIdentityService;
import domain.Customer;
import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("socialIdentityCustomerUpdate")
@RequestMapping("socialIdentity/customer")
public class UpdateController extends AbstractUpdateController<SocialIdentity, SocialIdentityService> {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public boolean authorize(SocialIdentity domainObject, UserAccount principal) {
		Customer customer;
		customer = customerService.findBySocialIdentity(domainObject.getId());
		Assert.notNull(customer);
		
		return customer.getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "socialIdentity/update";
	}
	
	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
}
