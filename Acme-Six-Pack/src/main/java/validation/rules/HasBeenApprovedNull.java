package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.BookingService;

import domain.Booking;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class HasBeenApprovedNull implements BusinessRule<Booking> {

	@Autowired
	private BookingService bookingService;
	
	@Override
	public boolean rule(Booking domainObject) {
		//Se comprueba que la reserva no había sido supervisada previamente.
		//Se comprueba que la reserva no tenía asignada ningún administrador que la hubiera revisado previamente.
		Booking booking;
		booking = bookingService.findById(domainObject.getId());
		return (booking.getHasBeenApproved() == null) && (booking.getAdministrator() == null) && booking.getRequestedMoment().after(Moment.now());
	}

	@Override
	public String warning() {
		return "booking.hasBeenApproved.error";
	}

}
