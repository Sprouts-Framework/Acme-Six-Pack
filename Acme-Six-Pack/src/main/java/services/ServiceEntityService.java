package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.ServiceEntityRepository;
import validation.rules.DoesNotHaveAssociatedGyms;
import validation.rules.IsNotFitnessServiceEntity;
import domain.ServiceEntity;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.DeleteService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class ServiceEntityService extends AbstractService<ServiceEntity, ServiceEntityRepository> implements ListService<ServiceEntity>,
		CreateService<ServiceEntity>, DeleteService<ServiceEntity> {

	@Autowired
	private DoesNotHaveAssociatedGyms serviceEntityDoesNotHaveAsociatedGyms;

	@Autowired
	private IsNotFitnessServiceEntity serviceEntityIsNotFitnessServiceEntity;

	// Create methods ---------------------------------------
	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return ServiceEntity.class;
	}

	@Override
	public void beforeCreating(ServiceEntity validable, List<String> context) {
		validable.setCustomersTotalNumber(0);
	}

	@Override
	public void beforeCommitingCreate(ServiceEntity validable, List<String> context) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<ServiceEntity>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	// Delete methods --------------------------------------
	@Override
	public void beforeDeleting(ServiceEntity validable, List<String> context) {

	}

	@Override
	public void beforeCommitingDelete(ServiceEntity validable, List<String> context) {

	}

	@Override
	public void deleteBusinessRules(final List<BusinessRule<ServiceEntity>> rules, final List<Validator> validators) {
		rules.add(serviceEntityIsNotFitnessServiceEntity);
		rules.add(serviceEntityDoesNotHaveAsociatedGyms);
	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Find methods -------------------------------------
	@Override
	public Page<ServiceEntity> findPage(Pageable page, String searchCriteria) {
		Page<ServiceEntity> result;

		result = repository.findAll(page);
		Assert.notNull(result);

		return result;
	}

	// Other business methods ------------------

	/*
	 * Devuelve el ServiceEntity cuyo nombre es Fitness (solo debe existir uno
	 * con ese nombre)
	 */
	public ServiceEntity findFitnessServiceEntity() {
		ServiceEntity serviceEntity;
		serviceEntity = repository.findFitnessServiceEntity();
		Assert.notNull(serviceEntity);
		return serviceEntity;
	}

	/*
	 * Este método se usa para actualizar el atributo derivado
	 * customersTotalNumber del ServiceEntity cuyo id recibe como parámetro.
	 */
	public void increaseCustomers(int serviceEntityId) {
		ServiceEntity serviceEntity;
		serviceEntity = repository.findOne(serviceEntityId);
		Assert.notNull(serviceEntity);
		serviceEntity.setCustomersTotalNumber(serviceEntity.getCustomersTotalNumber() + 1);
		repository.save(serviceEntity);
	}

	// Método que devuelve el/los servicio/s más popular/es, mostrados en el
	// dashboard.
	public Page<ServiceEntity> findMostPopularServices(Pageable page) {
		Page<ServiceEntity> result;

		result = repository.findMostPopularServices(page);
		Assert.notNull(result);
		return result;
	}

	// Método que devuelve el/los servicio/s menos popular/es, mostrados en el
	// dashboard.
	public Page<ServiceEntity> findLeastPopularServices(Pageable page) {
		Page<ServiceEntity> result;

		result = repository.findLeastPopularServices(page);
		Assert.notNull(result);
		return result;
	}

	public Integer quantityMostPopularServices() {
		Integer result;
		result = repository.quantityMostPopularServices();
		if (result == null)
			result = 0;
		return result;
	}

	public Integer quantityLeastPopularServices() {
		Integer result;
		result = repository.quantityLeastPopularServices();
		if (result == null)
			result = 0;
		return result;
	}

}
