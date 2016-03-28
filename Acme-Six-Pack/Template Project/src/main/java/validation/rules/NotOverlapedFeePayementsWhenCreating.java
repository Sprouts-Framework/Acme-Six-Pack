package validation.rules;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.FeePaymentService;
import domain.Customer;
import domain.FeePayment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class NotOverlapedFeePayementsWhenCreating implements BusinessRule<FeePayment> {
	@Autowired
	private FeePaymentService feePaymentService;
	
	@Override
	public boolean rule(FeePayment domainObject) {
		Customer customer;
		
		customer=domainObject.getCustomer();
		Assert.notNull(customer);
		
		Collection<FeePayment> overlappedFeePayments;
		overlappedFeePayments = feePaymentService.findOverlappedFeePayments(domainObject.getActivationDay(),domainObject.getInactivationDay(),customer.getId(),domainObject.getGym().getId());
		
		return overlappedFeePayments.isEmpty();
	}

	@Override
	public String warning() {
		return "feePayment.business-rule.overlaped";
	}

}
