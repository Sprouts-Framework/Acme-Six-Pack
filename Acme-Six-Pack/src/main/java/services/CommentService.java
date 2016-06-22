package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;
import domain.Customer;
import domain.Gym;
import domain.ServiceOfGym;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.DeleteService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class CommentService extends AbstractService<Comment, CommentRepository> implements CreateService<Comment>, DeleteService<Comment>, ListService<Comment> {

	@Autowired
	private GymService gymService;
	@Autowired
	private ServiceOfGymService serviceOfGymService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private CustomerService customerService;

	/*FIXME
	@Autowired
	private KieContainer kieContainer;
*/
	// Create methods-----------------------------------------
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return Comment.class;
	}

	@Override
	public void beforeCreating(Comment validable, List<String> context) {
		Assert.isTrue(context.size() == 2);
		// Customer customer;
		// customer = customerService.findByPrincipal();
		Actor actor = actorService.findActorByPrincipal();
		validable.setActor(actor);

		Integer gymId = new Integer(context.get(0));
		Integer serviceOfGymId = new Integer(context.get(1));

		if (gymId != 0) {
			Gym gym;
			gym = gymService.findById(gymId);
			Assert.notNull(gym);
			validable.setGym(gym);
		} else {
			ServiceOfGym serviceOfGym;
			serviceOfGym = serviceOfGymService.findById(serviceOfGymId);
			Assert.notNull(serviceOfGym);
			validable.setServiceOfGym(serviceOfGym);
		}
		validable.setMoment(Moment.now());
	}

	@Override
	public void beforeCommitingCreate(Comment validable, List<String> context) {
	}

	@Override
	public void createBusinessRules(List<BusinessRule<Comment>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingCreate(int id) {
	}

	// Delete methods ------------------------------------------

	@Override
	public void beforeDeleting(Comment validable, List<String> context) {
	}

	@Override
	public void beforeCommitingDelete(Comment validable, List<String> context) {
	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Comment>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingDelete(int id) {
		Comment result = findById(id);
		Long numberOfDeletedComment = customerService.findNumberOfDeletedComments(result.getActor().getId());
		Customer customer = (Customer) result.getActor();
		/* FIXME
		KieSession kieSession = kieContainer.newKieSession("KSession");
	    kieSession.insert(numberOfDeletedComment);
	    kieSession.insert(customer);
	    kieSession.fireAllRules();
	    System.out.println(customer.getCustomerType());
	    */
	}

	// Comments are not deleted, but they are no shown again
	@Override
	public void delete(Comment domainObject) {
		domainObject.setIsDeleted(true);
		super.update(domainObject);
	}

	/* Borra la colección de comentaros que recibe como parámetro */
	public void delete(Collection<Comment> comments) {
		Assert.notNull(comments);
		repository.delete(comments);
	}

	// Other business methods -------------------------------------------------

	// Delete all refered ------------------------------------------------------
	/*
	 * Borra todos los comentarios referidos al gimnasio que se le pasa por
	 * parámetro. El método es usado, cuando se borra un gimnasio
	 */
	public void deleteAllReferedToGym(Gym gym) {
		Assert.notNull(gym);
		Collection<Comment> result;
		result = repository.findCollectionOfCommentsByGymId(gym.getId());
		repository.delete(result);
	}

	/*
	 * Borra todos los comentarios referidos al servicio que se le pasa por
	 * parámetro. El método es usado, cuando se borra un gimnasio
	 */
	public void deleteAllReferedToServiceOfGym(ServiceOfGym serviceOfGym) {
		Assert.notNull(serviceOfGym);
		Collection<Comment> result;
		result = repository.findCollectionOfCommentsByServiceOfGymId(serviceOfGym.getId());
		Assert.notNull(result);
		repository.delete(result);
	}

	// Find methods -----------------------------------------------
	public Page<Comment> findCommentsByServiceOfGymId(final int serviceOfGymId, final Pageable page) {
		Assert.isTrue(serviceOfGymId > 0);
		Page<Comment> result;
		result = repository.findCommentsByServiceOfGymId(serviceOfGymId, page);
		Assert.notNull(result);
		return result;
	}

	public Page<Comment> findCommentsByGymId(final int gymId, final Pageable page) {
		Assert.isTrue(gymId > 0);
		Page<Comment> result;
		result = repository.findCommentsByGymId(gymId, page);
		Assert.notNull(result);
		return result;
	}

	@Override
	public Page<Comment> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	/* Método que devuelve la media de comenatrios por gimnasio */
	public Double averageNumberOfCommentsPerGym() {
		Double result;
		result = repository.averageNumberOfCommentsPerGym();
		if (result == null)
			result = 0.0;
		return result;
	}

	/* Método que devuelve la media de comenatrios por servicio */
	public Double averageNumberOfCommentsPerService() {
		Double result;
		result = repository.averageNumberOfCommentsPerService();
		if (result == null)
			result = 0.0;
		return result;
	}

	/*
	 * Método que devuelve la media del número de comentarios escritos por los
	 * actores, incluyendo la desviación estándar
	 */
	public Object[] averageAndStantadrdDeviationOfComments() {
		Object[] result;
		result = repository.averageAndStantadrdDeviationOfComments();
		Assert.notNull(result);
		return result;
	}

	public Collection<Comment> findCommentsOfServicesByGymId(int gymId) {
		Assert.isTrue(gymId > 0);
		Collection<Comment> result;
		result = repository.findCommentsOfServicesByGymId(gymId);
		Assert.notNull(result);
		return result;
	}

}
