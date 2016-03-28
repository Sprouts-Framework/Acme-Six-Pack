package validation.rules;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class InactivationMomentAfterOldInactivationMoment implements BusinessRule<FeePayment> {
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	@Override
	public boolean rule(FeePayment domainObject) {
		FeePayment oldFeePayment;
		oldFeePayment = feePaymentService.findById(domainObject.getId());
		Date today = Moment.now();
		
		return domainObject.getInactivationDay().after(oldFeePayment.getInactivationDay())
				&& !today.after(domainObject.getInactivationDay());
	}

	@Override
	public String warning() {
		return "feePayment.business-rule.inactivation-moment-after-old-inactivation-moment";
	}

}
