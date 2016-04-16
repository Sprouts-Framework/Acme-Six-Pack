package controllers.customer.feePayment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("feePaymentCustomerControllerList")
@RequestMapping("feePayment/customer")
public class ListController extends AbstractListController<FeePayment, FeePaymentService> {

	@Override
	protected String view() {
		return "feePayment/list";
	}

	@Override
	protected Page<FeePayment> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<FeePayment> result;
		result = service.findNonInactiveFeePayments(page);
		Assert.notNull(result);
		return result;
	}
}
