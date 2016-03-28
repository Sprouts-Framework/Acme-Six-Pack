package validation.rules;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.BookingFormService;
import services.BookingService;
import services.CustomerService;

import domain.Booking;
import domain.Customer;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Component
public class HasNotOverlappedBookings implements BusinessRule<BookingForm> {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingFormService bookingFormService;
	
	@Override
	public boolean rule(BookingForm bookingForm) {
		Collection<Booking> bookings;
		Customer customer;
		Booking booking;
		
		booking = bookingFormService.convertToEntity(bookingForm);
		
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);
		
		bookings = bookingService.findOverlappedBookings(booking.getRequestedMoment(), booking.getEndMoment(), customer.getId());
		return bookings.isEmpty();
	}

	@Override
	public String warning() {
		return "booking.overlapping.error";
	}

}
