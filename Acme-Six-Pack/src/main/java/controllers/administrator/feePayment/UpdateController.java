package controllers.administrator.feePayment;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.core.contracts.AddCustomFormat;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.formats.CustomCurrencyFormat;
import es.us.lsi.dp.formats.CustomFormat;
import es.us.lsi.dp.services.SignInService;

@Controller("feePaymentAdministratorUpdateController")
@RequestMapping("feePayment/administrator")
public class UpdateController extends AbstractUpdateController<FeePayment, FeePaymentService> implements AddCustomFormat {

	@Override
	public boolean authorize(FeePayment domainObject, UserAccount principal) {
		return SignInService.checkAuthority("Administrator");
	}

	@Override
	protected String view() {
		return "feePayment/update";
	}

	@Override
	public void addCustomFormats(List<CustomFormat> formats) {
		formats.add(new CustomCurrencyFormat("", BigDecimal.class, "fee"));
	}
}
