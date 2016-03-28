package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.ActorService;
import domain.Actor;
import domain.Box;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsAuthorisedToManageBox implements BusinessRule<Box> {
	
	@Autowired
	private ActorService actorService;
	
	@Override
	public boolean rule(final Box box) {
		
		Actor actor;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		actor = actorService.findActorByUserAccount(userAccount.getUsername());
		return actor.getId()==box.getActor().getId();
	}

	@Override
	public String warning() {
		return "box.isAuthorised.error";
	}

}
