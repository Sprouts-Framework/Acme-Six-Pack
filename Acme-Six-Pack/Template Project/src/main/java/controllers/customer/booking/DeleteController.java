package controllers.customer.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BookingService;
import domain.Booking;
import es.us.lsi.dp.controllers.entities.crud.AbstractDeleteController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("deleteBookingController")
@RequestMapping("booking/customer")
public class DeleteController extends AbstractDeleteController<Booking, BookingService> {

	@Override
	public boolean authorize(Booking domainObject, UserAccount principal) {
		return domainObject.getCustomer().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "booking/delete";
	}

}
