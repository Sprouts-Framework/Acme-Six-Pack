package controllers.customer.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BookingFormService;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import forms.BookingForm;

@Controller("bookingFormCreateController")
@RequestMapping("booking/customer")
public class CreateController extends AbstractCreateController<BookingForm, BookingFormService> {

	@Override
	public boolean authorize(BookingForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "booking/create";
	}

}
