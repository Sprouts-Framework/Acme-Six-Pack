package validation.rules;

import org.springframework.stereotype.Component;

import domain.Booking;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotExpiredBooking implements BusinessRule<Booking> {

	@Override
	public boolean rule(Booking booking) {
		//Se comprueba que la reserva no esté ya expirada, es decir, su fecha solicitada sea posterior a la actual.
		return (!booking.getRequestedMoment().before(Moment.now()));
	}

	@Override
	public String warning() {
		return "booking.expired.error";
	}

}
