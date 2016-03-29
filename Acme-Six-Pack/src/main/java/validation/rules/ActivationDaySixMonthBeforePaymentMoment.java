package validation.rules;

import java.util.Date;

import org.springframework.stereotype.Component;

import domain.FeePayment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class ActivationDaySixMonthBeforePaymentMoment implements BusinessRule<FeePayment> {

	@Override
	public boolean rule(FeePayment domainObject) {
		
		Date inSixMonths;
		inSixMonths = new Date(domainObject.getPaymentMoment().getTime()+15778800000L);
		
		return (inSixMonths.after(domainObject.getActivationDay()) && 
				domainObject.getInactivationDay().after(domainObject.getActivationDay()) &&
				domainObject.getActivationDay().after(domainObject.getPaymentMoment()));
	}

	@Override
	public String warning() {
		return "feePayment.business-rule.activationDay-after-six-months";
	}

}
