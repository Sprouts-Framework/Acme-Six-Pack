package validation.rules;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import domain.FeePayment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotExpiredCreditCardFeePayment implements BusinessRule<FeePayment> {

	@Override
	public boolean rule(FeePayment domainObject) {
		Assert.notNull(domainObject);
		
		Calendar actualDate = new GregorianCalendar();
		Calendar creditCardDate = new GregorianCalendar();
		creditCardDate.set(Calendar.MONTH, domainObject.getCreditCard().getExpirationMonth()-1);
		creditCardDate.set(Calendar.YEAR, domainObject.getCreditCard().getExpirationYear()+2000);

		return creditCardDate.compareTo(actualDate)>=0;
	}

	@Override
	public String warning() {
		return "creditCard.expired";
	}

}
