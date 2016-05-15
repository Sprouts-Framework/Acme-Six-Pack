package controllers.customer.creditcard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CustomerService;
import validation.rules.IsNotExpiredCreditCard;
import datatypes.CreditCard;
import domain.Customer;
import es.us.lsi.dp.controllers.datatypes.AbstractPostController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Controller("creditCardCustomerUpdate")
@RequestMapping("creditCard/customer/{0}/update")
public class UpdateController extends AbstractPostController<CreditCard, Customer, CustomerService> {

	@Autowired
	private IsNotExpiredCreditCard isNotExpiredCreditCard;

	@Override
	public void businessRules(List<BusinessRule<Customer>> rules, List<Validator> validators) {
		rules.add(isNotExpiredCreditCard);
	}

	@Override
	public boolean authorize(Customer domainObject, UserAccount principal) {
		boolean authorized = false;

		authorized = domainObject.getUserAccount().equals(principal);

		return authorized;
	}

	@Override
	protected String successCode() {
		return "creditCard.create.successful";
	}

	@Override
	protected void action(CreditCard object, Customer entity, Map<String, String> pathVariables) {
		entity.setCreditCard(object);
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
		return "creditCard/update";
	}

	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}

	@Override
	public void beforeCommiting(CreditCard entityOrDatatype, Customer entity, List<String> context) {
		entity.setCreditCard(entityOrDatatype);
	}
}
