package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import domain.Actor;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.ListService;

@Service
@Transactional
public class ActorService extends AbstractService<Actor, ActorRepository> implements ListService<Actor> {

	// Managed repository --------------------

	@Autowired
	private ActorRepository actorRepository;

	// Supporting services --------------------

	// Constructors --------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ------------------

	// Encuentra un actor dado un id
	public Actor findOne(int actorId) {
		Actor result;
		result = actorRepository.findOne(actorId);
		Assert.notNull(result);
		return result;
	}

	// Encuentra todos los actores registrados en el sistema
	public Collection<Actor> findAll() {
		Collection<Actor> result;
		result = actorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	// Other business methods ---------------------
	// Encuentra en actor asociado al nombre de usuario que recibe como
	// parámetro
	public Actor findActorByUserAccount(String userAccount) {
		Actor result;
		result = actorRepository.findActorByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}

	// Devuelve un Actor que se encuentre logueado actualmente en el sistema.
	public Actor findActorByPrincipal() {
		Actor result;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);
		result = actorRepository.findActorByPrincipal(userAccount.getId());
		Assert.notNull(result);
		return result;
	}

	public Page<Actor> findActorsWhoSendMoreSpamMessages(Pageable page) {
		Page<Actor> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = actorRepository.findActorsWhoSendMoreSpamMessages(auxPage);
		Assert.notNull(result);
		return result;
	}

	public Double avarageNumberOfMessagesInActorBoxes(int actorId) {
		Double result;
		result = actorRepository.avarageNumberOfMessagesInActorBoxes(actorId);
		// Assert.notNull(result);

		return result;
	}

	public Integer quantityActorsWhoSendMoreSpamMessages() {
		Integer result;
		result = actorRepository.quantityActorsWhoSendMoreSpamMessages().intValue();
		if (result == null)
			result = 0;
		return result;
	}

	@Override
	public Page<Actor> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	public Page<Actor> findActorsWhoHaveRemovedMoreComments(Pageable page) {
		Page<Actor> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findActorsWhoHaveRemovedMoreComments(auxPage);
		Assert.notNull(result);

		return result;
	}

	public Integer quantityActorsWhoHaveRemovedMoreComments() {
		Integer result;
		result = repository.quantityActorsWhoHaveRemovedMoreComments().intValue();
		if (result == null)
			result = 0;
		return result;
	}
}
