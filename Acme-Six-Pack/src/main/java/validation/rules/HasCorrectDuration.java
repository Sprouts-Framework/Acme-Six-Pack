package validation.rules;

import org.springframework.stereotype.Component;

import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Component
public class HasCorrectDuration implements BusinessRule<BookingForm> {
	
	@Override
	public boolean rule(BookingForm booking) {
		return (booking.getDuration() == 0.5 || booking.getDuration() == 1.0 || booking.getDuration() == 1.5 || booking.getDuration() == 2.0 ||
				booking.getDuration() == 2.5 || booking.getDuration() == 3.0 || booking.getDuration() == 3.5 || booking.getDuration() == 4.0);
	}

	@Override
	public String warning() {
		return "booking.duration.error";
	}

}
