package controllers.administrator.spamTerms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SpamTermsService;

import domain.SpamTerms;

import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("spamTermsUpdate")
@RequestMapping("spamTerms/administrator")
public class UpdateController extends AbstractUpdateController<SpamTerms, SpamTermsService>{

	@Override
	public boolean authorize(SpamTerms domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "spamTerms/update";
	}
	
	@Override
	protected String onSuccess() {
		return "../../spamTerms/administrator/show.do";
	}
	
	@Override
	public SpamTerms getObject(Map<String, String> pathVariables, SpamTerms entity, List<String> context) {
		return service.findSpamTerms();
	}
	
	

}
