package services;

import java.util.ArrayList;
import java.util.Collection;
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

import repositories.BoxRepository;
import validation.rules.IsAuthorisedToManageBox;
import validation.rules.IsNotSystemFolder;
import domain.Actor;
import domain.Box;
import domain.Message;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.CrudService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class BoxService extends AbstractService<Box, BoxRepository> implements CrudService<Box>, ListService<Box> {

	// Supporting services --------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageService messageService;

	// Rules

	@Autowired
	private IsAuthorisedToManageBox authorisedToManageBox;

	@Autowired
	private IsNotSystemFolder isNotSystemFolder;

	// Create methods ------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return Box.class;
	}

	@Override
	public int save(Box box) {
		checkIsAuthorised(box.getActor().getId());
		return super.save(box);
	}

	@Override
	public void beforeCreating(Box result, List<String> context) {
		result.setIsSystem(false);
		Actor actor;
		actor = actorService.findActorByPrincipal();
		result.setActor(actor);
	}

	@Override
	public void beforeCommitingCreate(Box validable) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<Box>> rules, List<Validator> validators) {
		rules.add(authorisedToManageBox);
	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	// Update methods --------------------------------------

	@Override
	public void beforeUpdating(Box validable, List<String> context) {

	}

	@Override
	public void beforeCommitingUpdate(Box validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Box>> rules, List<Validator> validators) {
		rules.add(isNotSystemFolder);

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods -------------------------------------

	@Override
	public void beforeDeleting(Box box, List<String> context) {
	}

	@Override
	public void beforeCommitingDelete(Box validable) {
		// checkIsAuthorised(box.getActor().getId());
		Collection<Message> messages;
		messages = messageService.findMessagesInBox(validable.getId());
		if (!messages.isEmpty()) {
			Box trashbox;
			trashbox = repository.findSystemBox(validable.getActor().getId(), "trash box");
			while (messages.toArray().length != 0) {
				Message m = (Message) messages.toArray()[0];
				m.setBox(trashbox);
				// Guardo la carpeta y el mensaje
				messageService.save(m);
				messages = messageService.findMessagesInBox(validable.getId());
			}
		}
	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Box>> rules, List<Validator> validators) {
		rules.add(isNotSystemFolder);

	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Find methods -------------------------------------------
	@Override
	public Box findById(int id) {
		Box result;
		result = super.findById(id);
		checkIsAuthorised(result.getActor().getId());
		return result;
	}

	@Override
	public Page<Box> findPage(Pageable page, String searchCriteria) {
		Page<Box> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findAll(auxPage);
		Assert.notNull(result);
		return result;
	}

	// Otros métodos

	public Page<Box> findBoxes(Integer actorId, final Pageable page) {
		Assert.notNull(SignInService.getPrincipal());
		checkIsAuthorised(actorId);
		Page<Box> result;
		result = repository.findBoxesByActor(actorId, page);
		Assert.notNull(result);

		return result;
	}

	public Collection<Box> findBoxes(Integer actorId) {
		Assert.notNull(SignInService.getPrincipal());
		checkIsAuthorised(actorId);
		Collection<Box> result;
		result = repository.findBoxesByActor(actorId);
		Assert.notNull(result);

		return result;
	}

	public Box findSystemBox(Integer actorId, String name) {
		Box result;

		result = repository.findSystemBox(actorId, name);
		Assert.notNull(result);

		return result;
	}

	public Collection<Box> createAndSaveSystemBoxes(Actor actor) {
		Assert.notNull(actor);
		Collection<Box> boxes = new ArrayList<Box>();

		Box inbox = new Box();
		inbox.setIsSystem(true);
		inbox.setName("in box");
		inbox.setActor(actor);

		Box outbox = new Box();
		outbox.setIsSystem(true);
		outbox.setName("out box");
		outbox.setActor(actor);

		Box trashbox = new Box();
		trashbox.setIsSystem(true);
		trashbox.setName("trash box");
		trashbox.setActor(actor);

		Box spambox = new Box();
		spambox.setIsSystem(true);
		spambox.setName("spam box");
		spambox.setActor(actor);

		boxes.add(inbox);
		boxes.add(outbox);
		boxes.add(trashbox);
		boxes.add(spambox);

		Collection<Box> result;
		result = (Collection<Box>) repository.save(boxes);

		return result;
	}

	public void edit(Box box) {
		Assert.notNull(box);

		Assert.isTrue(box.getIsSystem() == false);

		repository.save(box);
	}

	public void checkIsAuthorised(int actorId) {
		Actor actor;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		actor = actorService.findActorByUserAccount(userAccount.getUsername());
		Assert.isTrue(actor.getId() == actorId);
	}

}
