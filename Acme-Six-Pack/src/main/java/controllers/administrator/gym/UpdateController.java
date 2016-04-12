package controllers.administrator.gym;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.GymService;

import domain.Gym;


import es.us.lsi.dp.controllers.core.contracts.AddCustomFormat;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.formats.CustomCurrencyFormat;
import es.us.lsi.dp.formats.CustomFormat;

@Controller
@RequestMapping("gym/administrator")
public class UpdateController extends AbstractUpdateController<Gym, GymService> implements AddCustomFormat{

	@Override
	public boolean authorize(Gym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "gym/update";
	}

	@Override
	protected String onSuccess() {
		return "../../../home/gym/list.do";
	}
	
	@Override
	public void addCustomFormats(List<CustomFormat> formats) {
		formats.add(new CustomCurrencyFormat("", BigDecimal.class, "fee"));
	}
}
