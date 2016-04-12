package controllers.customer.feePayment;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.core.contracts.AddCustomFormat;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.formats.CustomCurrencyFormat;
import es.us.lsi.dp.formats.CustomFormat;

@Controller("feePaymentCustomerControllerCreate")
@RequestMapping("feePayment/customer")
public class CreateController extends AbstractCreateController<FeePayment, FeePaymentService> implements AddCustomFormat {

	@Override
	public boolean authorize(FeePayment domainObject, UserAccount principal) {
		return domainObject.getCustomer().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "feePayment/create";
	}

	@Override
	public void addCustomFormats(List<CustomFormat> formats) {
		formats.add(new CustomCurrencyFormat("", BigDecimal.class, "fee"));
	}

}
