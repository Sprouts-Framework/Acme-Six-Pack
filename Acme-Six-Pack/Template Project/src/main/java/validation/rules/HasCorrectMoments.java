package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.BookingFormService;

import domain.Booking;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Component
public class HasCorrectMoments implements BusinessRule<BookingForm> {

	@Autowired
	private BookingFormService bookingFormService;
	
	@Override
	public boolean rule(BookingForm bookingForm) {
		Booking booking;
		
		booking = bookingFormService.convertToEntity(bookingForm);
		return (booking.getCreationMoment().before(booking.getRequestedMoment()));
	}

	@Override
	public String warning() {
		return "booking.moments.error";
	}

}
