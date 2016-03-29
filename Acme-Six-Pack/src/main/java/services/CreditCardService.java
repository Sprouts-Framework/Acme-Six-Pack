package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.CreditCardRepository;
import validation.rules.IsNotExpiredCreditCard;
import domain.CreditCard;
import domain.Customer;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.DeleteService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class CreditCardService extends AbstractService<CreditCard, CreditCardRepository> implements CreateService<CreditCard>, UpdateService<CreditCard>,
		DeleteService<CreditCard> {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private IsNotExpiredCreditCard isNotExpiredCreditCard;

	// Create methods ------------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return CreditCard.class;
	}

	@Override
	public void beforeCreating(CreditCard validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(CreditCard validable) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<CreditCard>> rules, List<Validator> validators) {
		rules.add(isNotExpiredCreditCard);
	}

	@Override
	public void afterCommitingCreate(int id) {
		CreditCard result;
		result = findById(id);
		Assert.notNull(result);

		Customer customer;
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);

		customer.setCreditCard(result);
		customerService.update(customer);
	}

	// Update methods -------------------------

	@Override
	public void beforeUpdating(CreditCard validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(CreditCard validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<CreditCard>> rules, List<Validator> validators) {
		rules.add(isNotExpiredCreditCard);
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods ---------------------------------------

	@Override
	public void beforeDeleting(CreditCard validable, List<String> context) {
	}

	@Override
	public void beforeCommitingDelete(CreditCard validable) {

	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<CreditCard>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Delete redefinition
	@Override
	public void delete(CreditCard domainObject) {
		Assert.notNull(domainObject);
		Customer customer;
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);
		customer.setCreditCard(null);
		customerService.save(customer);
		super.delete(domainObject);
	}

	// Find methods -------------------------
	public Collection<CreditCard> findCreditCardByGym(int gymId) {
		Collection<CreditCard> result;
		result = repository.findCreditCardByGym(gymId);
		Assert.notNull(result);
		return result;
	}

	public void delete(Collection<CreditCard> creditCards) {
		Assert.notNull(creditCards);
		repository.delete(creditCards);
	}

	// Save for fee payment
	public int saveFeePaymentCreditCard(CreditCard creditCard) {
		int result = super.save(creditCard);
		return result;
	}
}
