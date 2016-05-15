package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.ServiceOfGymRepository;
import validation.rules.IsNotFitnessService;
import domain.Booking;
import domain.Comment;
import domain.Customer;
import domain.Gym;
import domain.ServiceOfGym;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.CrudService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class ServiceOfGymService extends AbstractService<ServiceOfGym, ServiceOfGymRepository> implements CrudService<ServiceOfGym> {

	@Autowired
	private IsNotFitnessService serviceOfGymIsNotFitnessService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private GymService gymService;

	// Create ---------------------------------------------
	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return ServiceOfGym.class;
	}

	@Override
	public void beforeCreating(ServiceOfGym validable, List<String> context) {
		validable.setCustomersTotalNumber(0);
		validable.setPictures(new ArrayList<String>());
	}

	@Override
	public void beforeCommitingCreate(ServiceOfGym validable, List<String> context) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<ServiceOfGym>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	// Update ---------------------------------------------

	@Override
	public void beforeUpdating(ServiceOfGym validable, List<String> context) {

	}

	@Override
	public void beforeCommitingUpdate(ServiceOfGym validable, List<String> context) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<ServiceOfGym>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete ---------------------------------------------
	@Override
	public void beforeDeleting(ServiceOfGym validable, List<String> context) {

	}

	@Override
	public void beforeCommitingDelete(ServiceOfGym validable, List<String> context) {
		Collection<Booking> bookings;

		// Se eliminan las reservas que estuvieran asociadas al servicio que se
		// va a borrar.
		// También se eliminan los comentarios asociados al servicio a borrar.

		bookings = bookingService.findBookingsByServiceOfGymId(validable.getId());
		Assert.notNull(bookings);

		bookingService.deleteAllReferedToServiceOfGym(bookings);
		commentService.deleteAllReferedToServiceOfGym(validable);
	}

	@Override
	public void deleteBusinessRules(final List<BusinessRule<ServiceOfGym>> rules, final List<Validator> validators) {
		rules.add(serviceOfGymIsNotFitnessService);
	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Find methods ----------------------------------------
	@Override
	public Page<ServiceOfGym> findPage(Pageable page, String searchCriteria) {
		Page<ServiceOfGym> result;

		result = repository.findAll(page);
		Assert.notNull(result);

		return result;
	}

	// Other business methods ------------------

	/*
	 * Este método se usa para actualizar el atributo derivado
	 * customersTotalNumber del ServiceOfGym cuyo id recibe como parámetro.
	 */
	public void increaseCustomers(int serviceOfGymId) {
		ServiceOfGym serviceOfGym;
		serviceOfGym = repository.findOne(serviceOfGymId);
		Assert.notNull(serviceOfGym);
		serviceOfGym.setCustomersTotalNumber(serviceOfGym.getCustomersTotalNumber() + 1);
		repository.save(serviceOfGym);
	}

	// Método que sirve para borrar todos los servicios dado un gimnasio.
	// Es un método auxiliar que sirve para cuando se borre un gimnasio, lo
	// hagan antes todos los servicios asociados al mismo.
	public void deleteServicesOfferedByGym(Gym gym) {
		Assert.notNull(gym);
		Collection<ServiceOfGym> result;
		Collection<Booking> bookings;
		Collection<Comment> comments;

		bookings = bookingService.findBookingByGymId(gym.getId());
		Assert.notNull(bookings);
		bookingService.deleteAllReferedToServiceOfGym(bookings);

		comments = commentService.findCommentsOfServicesByGymId(gym.getId());
		Assert.notNull(comments);
		commentService.delete(comments);

		result = repository.findServiceOfGymByGymId(gym.getId());
		Assert.notNull(result);

		repository.delete(result);
	}

	// Método que devuelve los servicios asociados a un gimnasio, cuya id se
	// pasa por parámetro
	public Page<ServiceOfGym> findServiceOfGymByGymId(int gymId, Pageable page) {
		Assert.isTrue(gymId > 0);
		Page<ServiceOfGym> result;

		result = repository.findServiceOfGymByGymId(gymId, page);
		Assert.notNull(result);

		return result;
	}

	// Método que devuelve la URL de una foto dado el índice de la lista y el
	// servicio al que pertenece.
	public String findPictureById(int pictureId, int serviceOfGymId) {
		Assert.isTrue(pictureId >= 0 && serviceOfGymId > 0);
		String result;
		ServiceOfGym service;

		// Se devuelve el servicio cuya id se pasa por parámetro y a partir de
		// la lista de fotos de este y el índice que se tiene, la URL de la foto
		// pedida.
		service = repository.findOne(serviceOfGymId);
		result = service.getPictures().get(pictureId);
		Assert.notNull(result);

		return result;
	}

	// Método que añade una foto a un servicio del gimnasio, cuyo id se pasa por
	// parámetro.
	public ServiceOfGym addPictureToServiceOfGym(String picture, int serviceOfGymId) {
		Assert.notNull(picture);
		Assert.isTrue(serviceOfGymId > 0);

		ServiceOfGym serviceOfGym;
		List<String> pictures;

		// Se devuelve el servicio cuyo id se pasa por parámetro y a la lista de
		// fotos, se añade la nueva que se pasa por parámetro
		// para posteriormente cambiar la lista que tenía asignada el servicio,
		// por la nueva que tiene la foto añadida.
		serviceOfGym = repository.findOne(serviceOfGymId);
		pictures = serviceOfGym.getPictures();

		pictures.add(picture);
		serviceOfGym.setPictures(pictures);

		return serviceOfGym;
	}

	// Método que elimina una foto
	public void removePictureFromServiceOfGym(int pictureId, int serviceOfGymId) {
		Assert.isTrue(pictureId >= 0);
		Assert.isTrue(serviceOfGymId > 0);

		ServiceOfGym serviceOfGym;
		List<String> pictures;

		// Se devuelve el servicio cuyo id se pasa por parámetro y la lista de
		// fotos, a la cual se le borra la de la posición pasada por parámetro
		// para posteriormente cambiar la lista que tenía asignada el servicio,
		// por la nueva que tiene la foto eliminada.
		serviceOfGym = repository.findOne(serviceOfGymId);
		pictures = serviceOfGym.getPictures();
		Assert.isTrue(pictureId <= pictures.size());

		pictures.remove(pictureId);
		serviceOfGym.setPictures(pictures);

		repository.save(serviceOfGym);
	}

	// Método que modifica una foto
	public ServiceOfGym modifyPictureFromServiceOfGym(String picture, int oldPictureId, int serviceOfGymId) {
		Assert.notNull(picture);
		Assert.isTrue(oldPictureId >= 0);
		Assert.isTrue(serviceOfGymId > 0);

		ServiceOfGym serviceOfGym;
		List<String> pictures;

		// Se devuelve el servicio cuyo id se pasa por parámetro y en la lista
		// de fotos, se modifica la existente, cuyo índice se pasa por parámetro
		// por la nueva, también pasada por parámeetro, para posteriormente
		// cambiar la lista que tenía asignada el servicio, por la nueva que
		// tiene la foto modificada.
		serviceOfGym = repository.findOne(serviceOfGymId);
		pictures = serviceOfGym.getPictures();
		Assert.isTrue(oldPictureId <= pictures.size());

		pictures.set(oldPictureId, picture);
		serviceOfGym.setPictures(pictures);

		return serviceOfGym;
	}

	// Devuelve un servicio aleatorio o nulo si no tiene ninguna fee activa o ya
	// ha reservado todos
	// los servicios disponibles.
	public ServiceOfGym findRandomServiceOfGym() {
		ServiceOfGym result;
		Customer customer;
		Collection<Gym> gyms;
		List<ServiceOfGym> serviceOfGyms = new ArrayList<ServiceOfGym>();

		// Devuelve el Customer Principal.
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);

		// Devuelve una lista con todos los gimnasios en los que el Customer
		// tenga una Fee activa.
		gyms = gymService.findGymsWithActiveFeePaymentByCustomerId();

		// Si los gimnasios son nulos, se devuelve null.
		if (gyms == null)
			result = null;
		else {
			// En otro caso, se recorre la lista de gimnasios disponibles y se
			// añade a la lista creada previamente todos los servicios que tenga
			// asociado dicho gimnasio.
			for (Gym g : gyms) {
				Collection<ServiceOfGym> aux;
				aux = repository.findServiceOfGymByGymId(g.getId());
				if (aux != null)
					serviceOfGyms.addAll(aux);
			}
			// Se crea una colección con todos los servicios que ya tiene
			// reservados un cliente.
			Collection<ServiceOfGym> booked;
			booked = repository.findBookedServiceOfGyms(customer.getId());

			// Si la lista de servicios reservados no es nula, se eliminan de la
			// lista de servicios anterior, aquellos que ha reservado el
			// cliente.
			// De forma que en la lista solo quedarán aquellos servicios
			// disponibles en los gimnasios que tiene Fees activas y aún no ha
			// reservado.
			if (booked != null)
				serviceOfGyms.removeAll(booked);

			// Si la lista de servicios de gimnasio no ha quedado vacía,
			// entonces se coge un elemento aleatorio de la misma, que se
			// devolverá.
			if (serviceOfGyms.isEmpty()) {
				result = null;
			} else {
				int index;
				index = RandomUtils.nextInt(serviceOfGyms.size());
				result = serviceOfGyms.get(index);
			}

		}

		return result;
	}

	// Método que devuelve el/los servicio/s que tiene/n más comentarios,
	// mostrados en el dashboard.
	public Page<ServiceOfGym> findServicesThatHaveMoreComments(Pageable page) {
		Page<ServiceOfGym> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findServicesThatHaveMoreComments(aux);
		Assert.notNull(result);
		return result;
	}

	public Integer quantityServicesThatHaveMoreComments() {
		Integer result;
		result = repository.quantityServicesThatHaveMoreComments().intValue();
		if (result == null)
			result = 0;
		return result;
	}

	public Long countServiceOfGymByServiceId(int serviceEntityId) {
		Assert.isTrue(serviceEntityId > 0);

		Long result;

		result = repository.countServiceOfGymByServiceId(serviceEntityId);
		Assert.notNull(result);

		return result;
	}

}
