package controllers.customer.feePayment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("feePaymentCustomerControllerCreate")
@RequestMapping("feePayment/customer")
public class CreateController extends AbstractCreateController<FeePayment, FeePaymentService> {

	@Override
	public boolean authorize(FeePayment domainObject, UserAccount principal) {
		return domainObject.getCustomer().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "feePayment/create";
	}
}
