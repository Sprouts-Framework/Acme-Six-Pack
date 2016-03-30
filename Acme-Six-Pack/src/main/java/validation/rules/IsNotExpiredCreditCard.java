package validation.rules;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import datatypes.CreditCard;
import domain.Customer;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotExpiredCreditCard implements BusinessRule<Customer> {

	@Override
	public boolean rule(Customer domainObject) {
		Assert.notNull(domainObject);
		
		CreditCard creditCard = domainObject.getCreditCard();
		Assert.notNull(creditCard);
		
		Calendar actualDate = new GregorianCalendar();
		Calendar creditCardDate = new GregorianCalendar();
		creditCardDate.set(Calendar.MONTH, creditCard.getExpirationMonth()-1);
		creditCardDate.set(Calendar.YEAR, creditCard.getExpirationYear()+2000);

		return creditCardDate.compareTo(actualDate)>=0;
	}

	@Override
	public String warning() {
		return "creditCard.expired";
	}

}
