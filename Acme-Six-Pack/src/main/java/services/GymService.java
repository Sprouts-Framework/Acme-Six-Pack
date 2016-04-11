package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.GymRepository;
import domain.Customer;
import domain.Gym;
import domain.ServiceEntity;
import domain.ServiceOfGym;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.CrudService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class GymService extends AbstractService<Gym, GymRepository> implements CrudService<Gym>, ListService<Gym> {

	@Autowired
	private ServiceEntityService serviceEntityService;

	@Autowired
	private ServiceOfGymService serviceOfGymService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private FeePaymentService feePaymentService;

	@Autowired
	private CommentService commentService;

	// Create methods ------------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return Gym.class;
	}

	@Override
	public void beforeCreating(Gym validable, List<String> context) {
		validable.setCustomersTotalNumber(0);
		Locale l = LocaleContextHolder.getLocale();
	}

	@Override
	public void beforeCommitingCreate(Gym gym) {
		Assert.notNull(gym);
		gym.setCustomersTotalNumber(0);
	}

	@Override
	public void createBusinessRules(List<BusinessRule<Gym>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingCreate(int id) {

		Gym aux;
		aux = repository.findOne(id);

		ServiceEntity serviceEntity;
		serviceEntity = serviceEntityService.findFitnessServiceEntity();

		ServiceOfGym serviceOfGym;
		serviceOfGym = serviceOfGymService.create();
		serviceOfGym.setCustomersTotalNumber(0);
		serviceOfGym
				.setDescription("Actividad física de movimientos repetidos que se planifica y se sigue regularmente con el propósito de mejorar o mantener el cuerpo en buenas condiciones.");
		serviceOfGym.setPictures(new ArrayList<String>());
		serviceOfGym.setGym(aux);
		serviceOfGym.setServiceEntity(serviceEntity);

		serviceOfGymService.save(serviceOfGym);
		
		

	}

	// Update methods ---------------------------------------

	@Override
	public void beforeUpdating(Gym validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(Gym validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Gym>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods ---------------------------------------

	@Override
	public void beforeDeleting(Gym gym, List<String> context) {

	}

	@Override
	public void beforeCommitingDelete(Gym validable) {
		feePaymentService.deleteAllReferedToFeePayments(validable);
		commentService.deleteAllReferedToGym(validable);
		serviceOfGymService.deleteServicesOfferedByGym(validable);
	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Gym>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Find methods ----------------------------
	@Override
	public Page<Gym> findPage(final Pageable page, final String searchCriteria) {
		Page<Gym> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findGymsByKeywordInAOfferedService(searchCriteria, aux);
		Assert.notNull(result);
		
		return result;
	}

	@Override
	public Gym findById(int id) {
		Gym result;
		result = repository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Gym findOneAux(int gymId) {
		Assert.isTrue(gymId > 0);
		Gym result;
		result = repository.findOne(gymId);
		return result;
	}

	/*
	 * Este método se usa para actualizar el atributo derivado
	 * customersTotalNumber del Gym cuyo id recibe como parámetro.
	 */
	public void increaseCustomers(int gymId) {
		Gym gym;
		gym = repository.findOne(gymId);
		Assert.notNull(gym);
		gym.setCustomersTotalNumber(gym.getCustomersTotalNumber() + 1);
		repository.save(gym);
	}

	/* Encuentra los gimnasios más populares */
	public Page<Gym> findMostPopularGyms(final Pageable page) {
		Page<Gym> result;
		result = repository.findMostPopularGyms(page);
		Assert.notNull(result);
		return result;
	}

	// *Encuentra los gimnasios menos populares*/
	public Page<Gym> findLeastPopularGyms(final Pageable page) {
		Page<Gym> result;
		result = repository.findLeastPopularGyms(page);
		Assert.notNull(result);
		return result;
	}

	/* Encuentra los gimnasios con más comentarios */
	public Page<Gym> gymsWihtMoreComments(final Pageable page) {
		Page<Gym> result;
		result = repository.gymsWihtMoreComments(page);
		Assert.notNull(result);
		return result;
	}

	// Encuentra los gimnasios donde el customer que está logueado tiene fees
	// activas
	public Page<Gym> findGymsWithActiveFeePaymentByCustomerId(final Pageable page) {

		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);

		Customer principal;
		Page<Gym> result;

		principal = customerService.findByPrincipal();
		Assert.notNull(principal);

		Date currentDate;
		currentDate = new Date(System.currentTimeMillis() - 1);

		result = repository.findGymsWithActiveFeePaymentByCustomerId(principal.getId(), currentDate, auxPage);

		Assert.notNull(result);
		return result;
	}

	// Encuentra los gimnasios donde el customer que está logueado tiene fees
	// activas
	public Collection<Gym> findGymsWithActiveFeePaymentByCustomerId() {

		Customer principal;
		Collection<Gym> result;

		principal = customerService.findByPrincipal();
		Assert.notNull(principal);

		Date currentDate;
		currentDate = new Date(System.currentTimeMillis() - 1);

		result = repository.findGymsWithActiveFeePaymentByCustomerId(principal.getId(), currentDate);

		Assert.notNull(result);
		return result;
	}

	public Page<Gym> findGymsByKeywordInAOfferedService(String searchCriteria, final Pageable page) {
		Assert.notNull(searchCriteria);
		Page<Gym> result;
		result = repository.findGymsByKeywordInAOfferedService(searchCriteria, page);
		Assert.notNull(result);
		return result;
	}

	public Page<Gym> findGymsThatHaveMoreComments(final Pageable page) {
		Page<Gym> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findGymsThatHaveMoreComments(aux);
		Assert.notNull(result);
		return result;
	}

	public Page<Gym> findGymsThatOfferService(int serviceEntityId, final Pageable page) {
		Assert.isTrue(serviceEntityId > 0);
		Page<Gym> result;

		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);

		result = repository.findGymsThatOfferService(serviceEntityId, aux);
		Assert.notNull(result);
		return result;
	}

	public Integer quantityMostPopularGyms() {
		System.out.println();
		Integer result;
		result = repository.quantityMostPopularGyms();
		if (result == null)
			result = 0;
		return result;
	}

	public Integer quantityLeastPopularGyms() {
		Integer result;
		result = repository.quantityLeastPopularGyms();
		if (result == null)
			result = 0;
		return result;
	}

	public Integer quantityGymsWithMoreComments() {
		Integer result;
		result = repository.quantityGymsWithMoreComments().intValue();
		if (result == null)
			result = 0;
		return result;
	}

}
