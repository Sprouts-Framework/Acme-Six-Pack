package validation.rules;

import org.springframework.stereotype.Component;

import domain.FeePayment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class ActivationDayAfterPaymentMoment implements BusinessRule<FeePayment> {

	@Override
	public boolean rule(FeePayment domainObject) {
		
		return (domainObject.getActivationDay().after(domainObject.getPaymentMoment()));
	}

	@Override
	public String warning() {
		return "feePayment.business-rule.activationDay-after-paymentMoment";
	}

}
