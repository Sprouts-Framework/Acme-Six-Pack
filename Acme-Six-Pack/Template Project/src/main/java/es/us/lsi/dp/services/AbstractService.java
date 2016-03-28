package es.us.lsi.dp.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.Assert;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.EntityService;
import es.us.lsi.dp.utilities.Moment;

public abstract class AbstractService<E extends DomainEntity, R extends PagingAndSortingRepository<E, Integer>> implements EntityService<E> {

	protected R repository;
	
	@Autowired
	public void setRepository(final R repository) {
		this.repository = repository;
	}
	
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public E create(){
		E result = null;
		if (this instanceof CreateService){
			CreateService createService = (CreateService) this;
			try {
				result = (E) createService.getEntityClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	@Override
	public int save(final E domainObject) {
		Assert.notNull(domainObject);

		E result;

		domainObject.setCreatedAt(Moment.now());

		result = repository.save(domainObject);

		return result.getId();
	}

	@Override
	public int update(final E domainObject) {
		Assert.notNull(domainObject);

		E result;

		domainObject.setUpdatedAt(Moment.now());

		result = repository.save(domainObject);

		return result.getId();
	}

	@Override
	public void delete(final E domainObject) {
		Assert.notNull(domainObject);

		repository.delete(domainObject);
	}

	@Override
	public E findById(final int id) {
		E result;

		result = repository.findOne(id);

		return result;
	}

	@Override
	public Collection<E> findAll() {
		Collection<E> result;

		result = (Collection<E>) repository.findAll();

		return result;
	}

	@Override
	public long count() {
		return repository.count();
	}

}
