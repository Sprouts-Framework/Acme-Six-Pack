package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.FeePaymentRepository;
import validation.rules.ActivationDayAfterPaymentMoment;
import validation.rules.ActivationDaySixMonthBeforePaymentMoment;
import validation.rules.InactivationMomentAfterOldInactivationMoment;
import validation.rules.IsNotExpiredCreditCardFeePayment;
import validation.rules.IsNotExpiredFeePayment;
import validation.rules.NotOverlapedFeePayementsWhenCreating;
import validation.rules.NotOverlapedFeePayementsWhenUpdating;
import datatypes.CreditCard;
import domain.Customer;
import domain.FeePayment;
import domain.Gym;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.CustomerService;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class FeePaymentService extends AbstractService<FeePayment, FeePaymentRepository> implements CreateService<FeePayment>, UpdateService<FeePayment>,
		ListService<FeePayment>, ShowService<FeePayment> {

	@Autowired
	private CustomerService customerService;
//	@Autowired
//	private CreditCardService creditCardService;
	@Autowired
	private GymService gymService;
	@Autowired
	private NotOverlapedFeePayementsWhenCreating notOverlapedFeePayementsWhenCreating;
	@Autowired
	private ActivationDaySixMonthBeforePaymentMoment activationDaySixMonthBeforePaymentMoment;
	@Autowired
	private IsNotExpiredFeePayment isNotExpiredFeePayment;
	@Autowired
	private InactivationMomentAfterOldInactivationMoment inactivationMomentAfterOldInactivationMoment;
	@Autowired
	private NotOverlapedFeePayementsWhenUpdating notOverlapedFeePayementsWhenUpdating;
	@Autowired
	private IsNotExpiredCreditCardFeePayment isNotExpiredCreditCardFeePayment;
	@Autowired
	private ActivationDayAfterPaymentMoment activationDayAfterPaymentMoment;
/* FIXME
	@Autowired
	private KieContainer kieContainer;
	*/
	// Create methods --------------------------------
	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return FeePayment.class;
	}

	@Override
	public void beforeCreating(FeePayment validable, List<String> context) {
		Customer principal;
		principal = customerService.findByPrincipal();
		CreditCard customerCreditCard;
		customerCreditCard = principal.getCreditCard();

		// Como el método se invoca antes y después de enviar el formulario,
		// debemos advertirle que si la tarjeta de crédito ya se seteó, no
		// vuelva
		// a a signarle ninguna otra tarjeta de crédito.
		if (validable.getCreditCard() == null) {
			CreditCard fpCreditCard;
			//fpCreditCard = creditCardService.create();
			fpCreditCard = new CreditCard();
			
			if (customerCreditCard != null) {
				fpCreditCard.setBrandName(customerCreditCard.getBrandName());
				fpCreditCard.setHolderName(customerCreditCard.getHolderName());
				fpCreditCard.setNumber(customerCreditCard.getNumber());
				fpCreditCard.setExpirationMonth(customerCreditCard.getExpirationMonth());
				fpCreditCard.setExpirationYear(customerCreditCard.getExpirationYear());
				fpCreditCard.setCvvCode(customerCreditCard.getCvvCode());
			}
			validable.setCreditCard(fpCreditCard);
		}

		validable.setCustomer(principal);
		validable.setPaymentMoment(Moment.now());
		validable.setInactivationDay(Moment.now());

		Gym gym;
		gym = gymService.findById(new Integer(context.get(0)));
		validable.setGym(gym);
		validable.setFee(gym.getFee());
		
		/* FIXME
		Customer customer = customerService.findByPrincipal();
		Long numberOfFeeInAGym = customerService.findNumberOfFeeInAGym(customer.getId(), validable.getGym().getId());

		
		KieSession kieSession = kieContainer.newKieSession("KSession");
	    kieSession.insert(customer);
	    kieSession.insert(numberOfFeeInAGym);
	    kieSession.insert(validable);
	    kieSession.fireAllRules();
*/
	}

	@Override
	public void beforeCommitingCreate(FeePayment validable, List<String> context) {
		Assert.notNull(validable);
		
		validable.setPaymentMoment(Moment.now());
		
		Date inactivationDay;
		inactivationDay = new Date(validable.getActivationDay().getTime() + 2592000000L);

		validable.setInactivationDay(inactivationDay);
	}

	@Override
	public void createBusinessRules(List<BusinessRule<FeePayment>> rules, List<Validator> validators) {
		rules.add(notOverlapedFeePayementsWhenCreating);
		rules.add(activationDayAfterPaymentMoment);
		rules.add(activationDaySixMonthBeforePaymentMoment);
		rules.add(isNotExpiredCreditCardFeePayment);
	}

	@Override
	public void afterCommitingCreate(int id) {
	}

	// Update methods -----------------------------------
	@Override
	public void beforeUpdating(FeePayment validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(FeePayment validable, List<String> context) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<FeePayment>> rules, List<Validator> validators) {
		rules.add(isNotExpiredFeePayment);
		rules.add(inactivationMomentAfterOldInactivationMoment);
		rules.add(notOverlapedFeePayementsWhenUpdating);
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Find methods methods ---------------------------------
	public Page<FeePayment> findNonInactiveFeePayments(Pageable page) {
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);

		Page<FeePayment> result;
		Date today;
		today = Moment.now();
		Customer customer;
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);
		result = repository.findNonInactiveFeePayments(today, customer.getId(), auxPage);
		Assert.notNull(result);
		return result;
	}

	public Collection<FeePayment> findOverlappedFeePayments(Date activationDay, Date inactivationDay, int customerId, int gymId) {
		Collection<FeePayment> result;
		result = repository.findOverlappedFeePayments(activationDay, inactivationDay, customerId, gymId);
		Assert.notNull(result);
		return result;
	}

	@Override
	public Page<FeePayment> findPage(Pageable page, String searchCriteria) {
		Page<FeePayment> result;
		result = repository.findAll(page);
		return result;
	}

	/*
	 * Encuentra el FeePayment activo del customer que está logueado en el
	 * gimnasio que recibe como parámetro.
	 */
	public FeePayment findActiveFeePaymentByGym(int gymId) {
		FeePayment result;
		Customer customer;
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);
		Date today;
		today = new Date();
		result = repository.findActiveFeePaymentByGym(gymId, customer.getId(), today);

		return result;
	}

	public void deleteAllReferedToFeePayments(Gym gym) {
		Assert.notNull(gym);

		Collection<FeePayment> feePayments;
		feePayments = repository.findFeePaymentsByGym(gym.getId());
		Assert.notNull(feePayments);

		//Collection<CreditCard> creditCards;
		//creditCards = creditCardService.findCreditCardByGym(gym.getId());
		//Assert.notNull(creditCards);

		repository.delete(feePayments);
		//creditCardService.delete(creditCards);
	}

}
