package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.SpamTerms;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.CrudService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class MessageService extends AbstractService<Message, MessageRepository> implements CrudService<Message>, ListService<Message> {

	@Autowired
	private ActorService actorService;

	@Autowired
	private BoxService boxService;

	@Autowired
	private SpamTermsService spamTermsService;

	// Create methods --------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return Message.class;
	}

	@Override
	public void beforeCreating(Message result, List<String> context) {

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(currentMoment);

		Actor actor;
		actor = actorService.findActorByPrincipal();

		Box box;
		box = boxService.findSystemBox(actor.getId(), "out box");
		Assert.notNull(box);

		result.setBox(box);
		result.setSender(actor);
	}

	@Override
	public void beforeCommitingCreate(Message validable) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<Message>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	@Override
	public int save(Message messageSender) {
		Assert.notNull(messageSender);
		checkIsAuthorised(messageSender.getBox().getActor().getId());
		Actor sender;
		sender = messageSender.getSender();

		Actor recipient;
		recipient = messageSender.getRecipient();

		String subject;
		subject = messageSender.getSubject();

		String body;
		body = messageSender.getBody();

		Message messageRecipient;
		messageRecipient = new Message();

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis() - 1);

		messageSender.setMoment(currentMoment);
		messageRecipient.setMoment(currentMoment);

		messageRecipient.setSender(sender);
		messageRecipient.setRecipient(recipient);

		messageRecipient.setSubject(subject);
		messageRecipient.setBody(body);

		Box recipientBox;

		if (checkIsSpam(messageSender))
			recipientBox = boxService.findSystemBox(recipient.getId(), "spam box");
		else
			recipientBox = boxService.findSystemBox(recipient.getId(), "in box");

		messageRecipient.setBox(recipientBox);

		Message saved;
		saved = repository.save(messageRecipient);
		repository.save(messageSender);
		return saved.getId();
	}
	
	// Update methods ------------------------------

	@Override
	public void beforeUpdating(Message validable, List<String> context) {

	}

	@Override
	public void beforeCommitingUpdate(Message validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Message>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods ------------------------

	@Override
	public void beforeDeleting(Message validable, List<String> context) {

	}

	@Override
	public void beforeCommitingDelete(Message validable) {

	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Message>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	@Override
	public void delete(Message message) {
		Assert.notNull(message);
		checkIsAuthorised(message.getBox().getActor().getId());

		if (message.getBox().getIsSystem() == true && message.getBox().getName().equals("trash box"))
			repository.delete(message);
		else {
			Actor actor;
			actor = actorService.findActorByPrincipal();
			Box trashbox;
			trashbox = boxService.findSystemBox(actor.getId(), "trash box");
			message.setBox(trashbox);
			repository.save(message);
		}
	}

	// Find methods -----------------------------
	@Override
	public Message findById(int id) {
		Message result;
		result = super.findById(id);
		checkIsAuthorised(result.getBox().getActor().getId());
		return result;
	}

	public Page<Message> findMessagesInBox(Integer boxId, Pageable page) {
		Assert.notNull(boxId);

		Box box;
		// El motivo de llamar a este método es que el findOne verifique
		// que el usuario logueado es el dueño de la carpeta.
		box = boxService.findById(boxId);

		Page<Message> result;
		result = repository.findMessagesInBox(box.getId(), page);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findMessagesInBox(Integer boxId) {
		Assert.notNull(boxId);

		Box box;
		// El motivo de llamar a este método es que el findOne verifique
		// que el usuario logueado es el dueño de la carpeta.
		box = boxService.findById(boxId);

		Collection<Message> result;
		result = repository.findMessagesInBox(box.getId());
		Assert.notNull(result);
		return result;
	}

	@Override
	public Page<Message> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	public boolean checkIsSpam(Message message) {
		Assert.notNull(message);
		boolean result = false;

		String subject = message.getSubject();
		String body = message.getBody();

		SpamTerms spamTerms;
		spamTerms = spamTermsService.findSpamTerms();

		for (String term : spamTerms.getTerms())
			if (subject.contains(term) || body.contains(term)) {
				result = true;
				break;
			}
		return result;
	}

	public void checkIsAuthorised(int actorId) {
		Actor actor;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		actor = actorService.findActorByUserAccount(userAccount.getUsername());
		Assert.isTrue(actor.getId() == actorId);
	}

}
