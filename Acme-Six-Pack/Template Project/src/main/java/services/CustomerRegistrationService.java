package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import repositories.CustomerRepository;
import validation.validators.LegalTermsValidator;
import validation.validators.PasswordValidator;
import domain.Actor;
import domain.Comment;
import domain.Customer;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.forms.BaseRegistrationForm;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.UserAccountService;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class CustomerRegistrationService extends AbstractFormService<Customer, BaseRegistrationForm, CustomerRepository> implements
		CreateFormService<BaseRegistrationForm, Customer> {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private LegalTermsValidator legalTermsValidator;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private BoxService boxService;
	@Autowired
	private ActorService actorService;
	
	// Create methods ---------------------------------------
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return BaseRegistrationForm.class;
	}

	@Override
	public void beforeCreating(BaseRegistrationForm validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(BaseRegistrationForm validable) {
	}

	@Override
	public void createBusinessRules(List<BusinessRule<BaseRegistrationForm>> rules, List<Validator> validators) {
		validators.add(legalTermsValidator);
		validators.add(passwordValidator);
	}

	@Override
	public void afterCommitingCreate(int id) {
		Actor actor;
		actor = actorService.findById(id);
		boxService.createAndSaveSystemBoxes(actor);
	}

	// Convert metods -----------------------------------
	@Override
	public Customer convertToEntity(BaseRegistrationForm form) {
		Customer result;
		result = new Customer();
		UserAccount userAccount;
		userAccount = userAccountService.create(result);

		result.setComments(new ArrayList<Comment>());
		result.setName(form.getName());
		result.setSurname(form.getSurname());
		result.setContactPhone(form.getContactPhone());

		userAccount.setUsername(form.getUsername());
		userAccount.setPassword(form.getPassword());

		userAccount = userAccountService.save(userAccount);

		result.setUserAccount(userAccount);

		return result;
	}

}
