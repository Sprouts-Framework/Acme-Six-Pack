package controllers.customer.booking;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BookingService;
import domain.Booking;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("bookingCustomerList")
@RequestMapping("booking/customer")
public class ListController extends AbstractListController<Booking, BookingService> {

	@Override
	protected String view() {
		return "booking/list";
	}
	
	@Override
	protected Page<Booking> getPage(Pageable page, String searchCriteria, List<String> context) {
		return service.findAllByPrincipal(page);
	}

}
