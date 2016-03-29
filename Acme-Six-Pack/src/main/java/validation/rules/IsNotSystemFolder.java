package validation.rules;

import org.springframework.stereotype.Component;

import domain.Box;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotSystemFolder implements BusinessRule<Box> {
	
	
	@Override
	public boolean rule(final Box box) {
		return !box.getIsSystem();
	}

	@Override
	public String warning() {
		return "box.isSystemFolder.error";
	}

}
