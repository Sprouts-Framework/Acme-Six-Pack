package controllers.administrator.booking;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BookingService;
import domain.Booking;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listManagedBookingController")
@RequestMapping("booking/administrator/managed")
public class ManagedListController extends AbstractListController<Booking, BookingService> {

	@Override
	protected String view() {
		return "booking/list";
	}

	@Override
	protected Page<Booking> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Booking> result;

		result = service.findAllSupervisedByAdministratorId(page);
		Assert.notNull(result);

		return result;
	}

}
