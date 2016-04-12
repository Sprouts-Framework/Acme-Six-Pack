package controllers.administrator.booking;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Booking;

import services.BookingService;
import es.us.lsi.dp.controllers.common.AbstractPostController;
import es.us.lsi.dp.domain.DomainObject;

@Controller("bookingDenyController")
@RequestMapping("booking/administrator/{context}/deny")
public class DenyBookingController extends AbstractPostController<BookingService> {

	@Override
	public void beforeCommiting(DomainObject object) {

	}

	@Override
	protected void action(Map<String, String> pathVariables) {
		Booking booking;
		booking = service.findOneToManage(new Integer(getContext().get(0)));
		service.edit(booking, false);
	}

	@Override
	protected String onSuccess() {
		return "../managed/list.do";
	}

	@Override
	protected String view() {
		return "booking/deny";
	}

	@Override
	public DomainObject getObject(Map<String, String> pathVariables, DomainObject entity, List<String> context) {
		Booking booking;
		booking = service.findOneToManage(new Integer(getContext().get(0)));
		Assert.notNull(booking);
		return booking;
	}

}
