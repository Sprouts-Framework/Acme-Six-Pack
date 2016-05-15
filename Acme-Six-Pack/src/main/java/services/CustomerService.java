package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.CustomerRepository;
import domain.Customer;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class CustomerService extends AbstractService<Customer, CustomerRepository> implements ShowService<Customer>, UpdateService<Customer>,
		ListService<Customer> {
	
	@Autowired
	private KieContainer kieContainer;

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);
		result = repository.findByPrincipal(userAccount.getId());
		Assert.notNull(result);
		
		 KieSession kieSession = kieContainer.newKieSession("KSession");
		 Collection<Customer> customers = (Collection<Customer>) repository.findAll();
		 Date date = new Date(System.currentTimeMillis()-15778500000L*2);
		 kieSession.insert(date);
		
		 for(Customer e: customers){
			 kieSession.insert(e);
		 }
		 
	     kieSession.fireAllRules();	     
		
		return result;
	}

	// Update methods ------------------------------

	@Override
	public void beforeUpdating(Customer validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(Customer validable, List<String> context) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Customer>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Find methods ---------------------------------
	public Customer findByCreditCard(int creditCardId) {
		Customer result;
		result = repository.findByCreditCard(creditCardId);
		Assert.notNull(result);
		return result;
	}

	public Customer findBySocialIdentity(int socialIdentityId) {
		Customer result;
		result = repository.findBySocialIdentity(socialIdentityId);
		Assert.notNull(result);
		return result;
	}

	// Dashboard methods -------------------------

	@Override
	public Page<Customer> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	public Page<Customer> findCustomersWhoHavePaidMoreFees(Pageable page) {
		Page<Customer> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findCustomersWhoHavePaidMoreFees(auxPage);
		Assert.notNull(result);

		return result;
	}

	public Page<Customer> findCustomersWhoHavePaidLessFees(Pageable page) {
		Page<Customer> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findCustomersWhoHavePaidLessFees(auxPage);
		Assert.notNull(result);

		return result;
	}

	public Integer quantityCustomersWhoHavePaidMoreFees() {
		Integer result;
		result = repository.quantityCustomersWhoHavePaidMoreFees().intValue();
		if (result == null)
			result = 0;
		return result;
	}

	public Integer quantityCustomersWhoHavePaidLessFees() {
		Integer result;
		result = repository.quantityCustomersWhoHavePaidLessFees().intValue();
		if (result == null)
			result = 0;
		return result;
	}
	
	public Customer findBySocialAccount(String providerId, String userId){
		Assert.notNull(providerId);
		Assert.notNull(userId);
		
		Customer result;
		
		result = repository.findBySocialAccount(providerId, userId);
		
		return result;
	}
	
	public Long findNumberOfSpamMessages(int id){
		return repository.findNumberOfSpamMessages(id);
	}
	
	public Long findNumberOfDeletedComments(int id){
		return repository.findNumberOfDeletedComments(id);
	}
	
	public Long findNumberOfFeeInAGym(int customerId, int gymId){
		return repository.findNumberOfFeeInAGym(customerId, gymId);
	}
	
	



}
