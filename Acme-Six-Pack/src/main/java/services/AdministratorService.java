package services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import domain.Administrator;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class AdministratorService extends AbstractService<Administrator, AdministratorRepository> implements UpdateService<Administrator> {

	// Update methods --------------------------------------

	@Override
	public void beforeUpdating(Administrator validable, List<String> context) {

	}

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);

		result = repository.findByPrincipal(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	@Override
	public void beforeCommitingUpdate(Administrator validable, List<String> context) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Administrator>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

}
