package controllers.administrator.spamTerms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SpamTermsService;
import domain.SpamTerms;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("spamTermsAdministratorShow")
@RequestMapping("spamTerms/administrator")
public class ShowController extends AbstractShowController<SpamTerms, SpamTermsService>{

	@Override
	protected String view() {
		return "spamTerms/show";
	}

	@Override
	public boolean authorize(SpamTerms domainObject, UserAccount principal) {
		return true;
	}

	@Override
	public SpamTerms getObject(Map<String, String> pathVariables, SpamTerms entity, List<String> context) {
		return service.findSpamTerms();
	}
	

}
