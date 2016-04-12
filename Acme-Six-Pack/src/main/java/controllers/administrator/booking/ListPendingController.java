package controllers.administrator.booking;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BookingService;
import domain.Booking;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listPedingBookingController")
@RequestMapping("booking/administrator/pending")
public class ListPendingController extends AbstractListController<Booking, BookingService> implements AddsToModel {

	@Override
	protected String view() {
		return "booking/list";
	}

	@Override
	protected Page<Booking> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Booking> result;

		result = service.findAllPendingBookings(page);
		Assert.notNull(result);

		return result;
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("requestURI", "booking/administrator/pending/list.do");
	}

}
