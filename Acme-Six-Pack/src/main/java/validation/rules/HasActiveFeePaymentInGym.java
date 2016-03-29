package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.BookingFormService;
import services.FeePaymentService;
import domain.Booking;
import domain.FeePayment;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Component
public class HasActiveFeePaymentInGym implements BusinessRule<BookingForm> {

	@Autowired
	FeePaymentService feePaymentService;
	
	@Autowired
	BookingFormService bookingFormService;
	
	@Override
	public boolean rule(BookingForm bookingForm) {
		Booking booking;
		booking = bookingFormService.convertToEntity(bookingForm);
		// Se comprueba que el Customer tiene una Fee activa en el gimnasio donde ha solicitado la reserva.
		FeePayment feePayment;
		feePayment = feePaymentService.findActiveFeePaymentByGym(booking.getServiceOfGym().getGym().getId());
		return feePayment != null;
	}

	@Override
	public String warning() {
		return "booking.feePayment.error";
	}

}
