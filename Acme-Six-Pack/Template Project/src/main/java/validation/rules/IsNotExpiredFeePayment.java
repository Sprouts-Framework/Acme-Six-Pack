package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotExpiredFeePayment implements BusinessRule<FeePayment> {
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	@Override
	public boolean rule(FeePayment domainObject) {
		FeePayment feePayment;
		feePayment = feePaymentService.findById(domainObject.getId());
		return !Moment.now().after(feePayment.getInactivationDay());
	}

	@Override
	public String warning() {
		return "feePayment.business-rule.is-not-inexpired";
	}

}
