package controllers.home.gym;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.FeePayment;
import domain.Gym;

import services.FeePaymentService;
import services.GymService;

import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;

@Controller("gymDisplayController")
@RequestMapping("home/gym")
public class DisplayController extends AbstractShowController<Gym, GymService> implements AddsToModel {
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Gym gym;
		int gymId;
		Boolean hasFeePayment = false;
		
		gymId = Integer.valueOf(context.get(0));
		gym = service.findById(gymId);
		
		if(SignInService.checkAuthority("Customer")){
			FeePayment feePayment = feePaymentService.findActiveFeePaymentByGym(gymId);
			hasFeePayment = feePayment != null;
		}
		
		objects.put("gym", gym);
		objects.put("_viewName", "gym/display");
		objects.put("hasFeePayment", hasFeePayment);
	}

	@Override
	public boolean authorize(Gym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "gym/display";
	}

}
