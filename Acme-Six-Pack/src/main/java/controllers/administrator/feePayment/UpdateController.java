package controllers.administrator.feePayment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;

@Controller("feePaymentAdministratorUpdateController")
@RequestMapping("feePayment/administrator")
public class UpdateController extends AbstractUpdateController<FeePayment, FeePaymentService> {

	@Override
	public boolean authorize(FeePayment domainObject, UserAccount principal) {
		return SignInService.checkAuthority("Administrator");
	}

	@Override
	protected String view() {
		return "feePayment/update";
	}

}
