package controllers.customer.feePayment;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("showFeePaymentController")
@RequestMapping("feePayment/customer")
public class ShowController extends AbstractShowController<FeePayment, FeePaymentService> {

	@Override
	public boolean authorize(FeePayment domainObject, UserAccount principal) {
		return domainObject.getCustomer().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "feePayment/show";
	}

	@Override
	public FeePayment getObject(Map<String, String> pathVariables, FeePayment entity, List<String> context) {
		FeePayment result;
		result = service.findActiveFeePaymentByGym(new Integer(context.get(0)));
		Assert.notNull(result);
		return result;
	}
}
