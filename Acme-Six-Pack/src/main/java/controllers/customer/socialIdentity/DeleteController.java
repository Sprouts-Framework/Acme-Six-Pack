package controllers.customer.socialIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Customer;
import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.CustomerService;
import es.us.lsi.dp.services.SocialIdentityService;

@Controller("socialIdentityCustomerDelete")
@RequestMapping("socialIdentity/customer")
public class DeleteController extends AbstractDeleteController<SocialIdentity, SocialIdentityService> {

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
		return "socialIdentity/delete";
	}
	
	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
}
