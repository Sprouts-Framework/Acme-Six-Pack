package validation.rules;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import domain.CreditCard;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotExpiredCreditCard implements BusinessRule<CreditCard> {

	@Override
	public boolean rule(CreditCard domainObject) {
		Assert.notNull(domainObject);
		
		Calendar actualDate = new GregorianCalendar();
		Calendar creditCardDate = new GregorianCalendar();
		creditCardDate.set(Calendar.MONTH, domainObject.getExpirationMonth()-1);
		creditCardDate.set(Calendar.YEAR, domainObject.getExpirationYear()+2000);

		return creditCardDate.compareTo(actualDate)>=0;
	}

	@Override
	public String warning() {
		return "creditCard.expired";
	}

}
