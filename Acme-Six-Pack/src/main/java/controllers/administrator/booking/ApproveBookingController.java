package controllers.administrator.booking;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BookingService;
import domain.Booking;
import es.us.lsi.dp.controllers.common.AbstractPostController;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.domain.DomainObject;

@Controller("bookingApproveController")
@RequestMapping("booking/administrator/{context}/approve")
public class ApproveBookingController extends AbstractPostController<BookingService> implements AddsToModel{

	@Override
	public void beforeCommiting(DomainObject object) {

	}

	@Override
	protected void action(List<String> context) {
		Booking booking;
		booking = service.findOneToManage(new Integer(context.get(0)));
		service.edit(booking, true);
	}

	@Override
	protected String onSuccess() {
		return "../managed/list.do";
	}

	@Override
	protected String view() {
		return "booking/approve";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Booking booking;
		booking = service.findOneToManage(new Integer(context.get(0)));
		Assert.notNull(booking);
		
		objects.put("modelObject", booking);
		
	}

}
