package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.BookingFormService;
import services.FeePaymentService;

import domain.Booking;
import domain.FeePayment;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Component
public class IsValidRequestedMoment implements BusinessRule<BookingForm> {

	@Autowired
	private FeePaymentService feePaymentService;
	
	@Autowired
	private BookingFormService bookingFormService;
	
	@Override
	public boolean rule(BookingForm bookingForm) {
		FeePayment feePayment;
		Booking booking;
		
		booking = bookingFormService.convertToEntity(bookingForm);
		
		// Se comprueba que el Customer tiene una Fee activa en el gimnasio donde ha solicitado la reserva.
		feePayment = feePaymentService.findActiveFeePaymentByGym(booking.getServiceOfGym().getGym().getId());
		Assert.notNull(feePayment);
		
		
		return (booking.getRequestedMoment().after(feePayment.getActivationDay())) &&
				(!booking.getRequestedMoment().after(feePayment.getInactivationDay())) &&
				(!booking.getEndMoment().after(feePayment.getInactivationDay()));
	}

	@Override
	public String warning() {
		return "booking.requestedMoment.error";
	}

}
