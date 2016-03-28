package services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.SpamTermsRepository;
import domain.SpamTerms;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class SpamTermsService extends AbstractService<SpamTerms, SpamTermsRepository> implements ShowService<SpamTerms>, UpdateService<SpamTerms> {

	// Update methods ----------------------------------

	@Override
	public void beforeUpdating(SpamTerms validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(SpamTerms validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<SpamTerms>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Find methods ----------------------------------
	public SpamTerms findSpamTerms() {
		SpamTerms result;
		result = repository.findAll().get(0);
		Assert.notNull(result);
		return result;
	}

}
