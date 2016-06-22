package services;

import java.util.ArrayList;
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

import repositories.BookingRepository;
import validation.rules.HasBeenApprovedNull;
import validation.rules.IsNotExpiredBooking;
import domain.Administrator;
import domain.Booking;
import domain.Customer;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.DeleteService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class BookingService extends AbstractService<Booking, BookingRepository> implements ListService<Booking>, UpdateService<Booking>, DeleteService<Booking> {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ServiceOfGymService serviceOfGymService;

	@Autowired
	private ServiceEntityService serviceEntityService;

	@Autowired
	private GymService gymService;
	
	/*FIXME
	@Autowired
	private KieContainer kieContainer;
*/
	@Autowired
	private HasBeenApprovedNull bookingHasBeenApprovedNull;

	@Autowired
	private IsNotExpiredBooking bookingIsNotExpiredBooking;

	// Update methods ----------------------------------------

	@Override
	public void beforeUpdating(Booking result, List<String> context) {

	}

	@Override
	public void beforeCommitingUpdate(Booking validable, List<String> context) {
		Administrator administrator;
		administrator = administratorService.findByPrincipal();
		Assert.notNull(administrator);
		validable.setAdministrator(administrator);
	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Booking>> rules, List<Validator> validators) {
		rules.add(bookingHasBeenApprovedNull);
		rules.add(bookingIsNotExpiredBooking);
	}

	@Override
	public void afterCommitingUpdate(int id) {
		Booking booking;
		Collection<Booking> bookings;

		booking = repository.findOne(id);

		if (booking.getHasBeenApproved() == true) {
			// Se encuentran aquellas reservas aprobadas que haya realizado este
			// mismo Customer en este mismo servicio del gimnasio.
			// Si el tamaño la colección devuelta es 1, es decir, esta es la
			// primera reserva que tiene aprobada en este servicio del
			// gimanasio,
			// se incrementa el contador asociado a este servicio del gimnasio.
			bookings = repository.findApprovedBookingsByServiceAndCustomerId(booking.getServiceOfGym().getId(), booking.getCustomer().getId());
			if (bookings.size() == 1)
				serviceOfGymService.increaseCustomers(booking.getServiceOfGym().getId());

			// Se encuentran aquellas reservas aprobadas que haya realizado este
			// mismo Customer en este misma entidad servicio.
			// Si el tamaño la colección devuelta es 1, es decir, esta es la
			// primera reserva que tiene aprobada en este servicio, se
			// incrementa el contador asociado a esta entidad servicio.
			bookings = repository.findApprovedBookingsByServiceEntityAndCustomerId(booking.getServiceOfGym().getServiceEntity().getId(), booking.getCustomer()
					.getId());
			if (bookings.size() == 1)
				serviceEntityService.increaseCustomers(booking.getServiceOfGym().getServiceEntity().getId());

			// Se encuentran aquellas reservas aprobadas que haya realizado este
			// mismo Customer en este gimnasio.
			// Si el tamaño la colección devuelta es 1, es decir, esta es la
			// primera reserva que tiene aprobada en este gimnasio, se
			// incrementa el contador asociado a este gimnasio.
			bookings = repository.findApprovedBookingsByGymAndCustomerId(booking.getServiceOfGym().getGym().getId(), booking.getCustomer().getId());
			if (bookings.size() == 1)
				gymService.increaseCustomers(booking.getServiceOfGym().getGym().getId());
		}

	}

	// Delete methods -----------------------------------

	@Override
	public void beforeDeleting(Booking validable, List<String> context) {

	}

	@Override
	public void beforeCommitingDelete(Booking validable, List<String> context) {

	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Booking>> rules, List<Validator> validators) {
		rules.add(bookingHasBeenApprovedNull);

	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Borra todos los Booking que se reciben como parámetro.
	// Método que sirve cuando se realiza el borrado de un servicio de un
	// gimnasio, por lo cual es necesario borrar las reservas que hubiera
	// asociadas previamente.
	public void deleteAllReferedToServiceOfGym(Collection<Booking> bookings) {
		Assert.notNull(bookings);

		repository.delete(bookings);
	}

	// Other business methods ------------------

	public void edit(Booking booking, Boolean hasBeenApproved) {
		Booking result;
		result = booking;
		result.setHasBeenApproved(hasBeenApproved);
		if(hasBeenApproved == false){
			Customer c = result.getCustomer();
			Long numberOfBookings = repository.countTotalBookingsByCustomer(c.getId());
			Double ratioOfCancelledBookings = repository.ratioOfCancelledBookings(c.getId());
			/*FIXME
			KieSession kieSession = kieContainer.newKieSession("KSession");
		    kieSession.insert(c);
		    kieSession.insert(numberOfBookings);
		    kieSession.insert(ratioOfCancelledBookings);
		    kieSession.fireAllRules();
		    */
		}
		beforeCommitingUpdate(result, new ArrayList<String>());
		update(result);
		afterCommitingUpdate(result.getId());
	}

	@Override
	public Page<Booking> findPage(Pageable page, String searchCriteria) {
		return null;
	}

	// Encontrar un Booking dado un id que se pasa por parámetro.
	// Se comprueba que el Customer que lo ha buscado es el que lo ha realizado.
	@Override
	public Booking findById(int id) {
		Booking result;
		Customer customer;

		customer = customerService.findByPrincipal();
		Assert.notNull(customer);

		result = repository.findOne(id);
		Assert.notNull(result);
		Assert.isTrue(result.getCustomer().equals(customer));

		return result;
	}

	// Encontrar un Booking dado un id que se pasa por parámetro.
	// Este método se distingue del anterior en que no se realiza la
	// comprobación del Customer, dado que este está destinado a un admin.
	// Se comprueba que el Booking a gestionar no haya sido supervisado aún.
	public Booking findOneToManage(int bookingId) {
		Booking result;

		result = repository.findOne(bookingId);
		Assert.notNull(result);
		Assert.isTrue(result.getHasBeenApproved() == null);
		Assert.isTrue(result.getAdministrator() == null);

		return result;
	}

	public Page<Booking> findAllByPrincipal(Pageable page) {
		Page<Booking> result;
		Customer customer;

		customer = customerService.findByPrincipal();
		Assert.notNull(customer);

		result = repository.findAllByCustomerId(customer.getId(), page);
		Assert.notNull(result);

		return result;
	}

	public Page<Booking> findAllPendingBookings(Pageable page) {
		Page<Booking> result;
		Date date;

		date = new Date(System.currentTimeMillis());
		Assert.notNull(date);

		result = repository.findAllPendingBookings(date, page);
		Assert.notNull(result);

		return result;
	}

	public Page<Booking> findAllSupervisedByAdministratorId(Pageable page) {
		Page<Booking> result;
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		Assert.notNull(administrator);

		result = repository.findAllSupervisedByAdministratorId(administrator.getId(), page);
		Assert.notNull(result);

		return result;
	}

	public Collection<Booking> findBookingsByServiceOfGymId(int serviceOfGymId) {
		Assert.isTrue(serviceOfGymId > 0);
		Collection<Booking> result;

		result = repository.findBookingsByServiceOfGymId(serviceOfGymId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Booking> findBookingByGymId(int gymId) {
		Assert.isTrue(gymId > 0);
		Collection<Booking> result;

		result = repository.findBookingsByGymId(gymId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Booking> findOverlappedBookings(Date requestedMoment, Date endMoment, int id) {
		Assert.notNull(requestedMoment);
		Assert.notNull(endMoment);
		Assert.isTrue(id > 0);

		Collection<Booking> result;

		result = repository.findOverlappedBookings(requestedMoment, endMoment, id);
		Assert.notNull(result);

		return result;
	}

}
