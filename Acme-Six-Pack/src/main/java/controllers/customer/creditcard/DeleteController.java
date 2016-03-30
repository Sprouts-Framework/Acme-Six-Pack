package controllers.customer.creditcard;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CustomerService;
import datatypes.CreditCard;
import domain.Customer;
import es.us.lsi.dp.controllers.datatypes.AbstractPostController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Controller("creditCardCustomerDelete")
@RequestMapping("creditCard/customer/{0}/delete")
public class DeleteController extends AbstractPostController<CreditCard, Customer, CustomerService> {

	@Override
	public void businessRules(List<BusinessRule<Customer>> rules, List<Validator> validators) {
	}

	@Override
	public boolean authorize(Customer domainObject, UserAccount principal) {
		boolean authorized = false;

		authorized = domainObject.getUserAccount().equals(principal);

		return authorized;
	}

	@Override
	protected String successCode() {
		return "creditCard.delete.successful";
	}

	@Override
	protected void action(CreditCard object, Customer entity, Map<String, String> pathVariables) {
		entity.setCreditCard(null);
		service.update(entity);
	}

	@Override
	public CreditCard getObject(Map<String, String> pathVariables, Customer entity, List<String> context) {
		CreditCard result;

		result = service.findByPrincipal().getCreditCard();

		return result;
	}

	@Override
	protected String view() {
		return "creditCard/delete";
	}

	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}

	@Override
	public void beforeCommiting(CreditCard entityOrDatatype, Customer entity) {
	}
}
