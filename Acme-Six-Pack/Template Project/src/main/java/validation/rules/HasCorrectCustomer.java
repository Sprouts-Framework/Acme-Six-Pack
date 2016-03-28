package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.BookingFormService;
import services.CustomerService;

import domain.Booking;
import domain.Customer;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Component
public class HasCorrectCustomer implements BusinessRule<BookingForm> {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingFormService bookingFormService;

	@Override
	public boolean rule(BookingForm bookingForm) {
		Customer customer;
		Booking booking;
		
		booking = bookingFormService.convertToEntity(bookingForm);
		
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);
		
		return booking.getCustomer().equals(customer);
	}

	@Override
	public String warning() {
		return "booking.customer.error";
	}

}
