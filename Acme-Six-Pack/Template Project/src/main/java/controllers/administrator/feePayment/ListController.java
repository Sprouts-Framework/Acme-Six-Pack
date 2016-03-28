package controllers.administrator.feePayment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("feePaymentAdministratorListController")
@RequestMapping("feePayment/administrator")
public class ListController extends AbstractListController<FeePayment, FeePaymentService>{

	@Override
	protected String view() {
		return "feePayment/list";
	}
	
}
