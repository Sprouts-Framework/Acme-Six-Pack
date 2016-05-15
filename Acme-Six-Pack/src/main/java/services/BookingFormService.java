package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.BookingRepository;
import validation.rules.HasActiveFeePaymentInGym;
import validation.rules.HasCorrectCustomer;
import validation.rules.HasCorrectDuration;
import validation.rules.HasCorrectMoments;
import validation.rules.HasNotOverlappedBookings;
import validation.rules.IsValidRequestedMoment;
import domain.Booking;
import domain.Customer;
import domain.ServiceOfGym;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BookingForm;

@Service
@Transactional
public class BookingFormService extends AbstractFormService<Booking, BookingForm, BookingRepository> implements CreateFormService<BookingForm, Booking> {

	@Autowired
	private ServiceOfGymService serviceOfGymService;
	
	
	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private HasActiveFeePaymentInGym bookingHasActiveFeePaymentInGym;
	
	@Autowired
	private HasCorrectCustomer bookingHasCorrectCustomer;
	
	@Autowired
	private HasCorrectDuration bookingHasCorrectDuration;
	
	@Autowired
	private IsValidRequestedMoment bookingIsValidRequestedMoment;
	
	@Autowired
	private HasCorrectMoments bookingHasCorrectMoments;
	
	@Autowired
	private HasNotOverlappedBookings bookingHasNotOverlappedBookings;
	
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return BookingForm.class;
	}

	@Override
	public void createBusinessRules(List<BusinessRule<BookingForm>> rules, List<Validator> validators) {
		rules.add(bookingHasCorrectCustomer);
		rules.add(bookingHasActiveFeePaymentInGym);
		rules.add(bookingHasCorrectDuration);
		rules.add(bookingIsValidRequestedMoment);
		rules.add(bookingHasCorrectMoments);
		rules.add(bookingHasNotOverlappedBookings);		
	}

	@Override
	public void beforeCreating(BookingForm validable, List<String> context) {
		int serviceOfGymId;
		ServiceOfGym serviceOfGym;
		
		serviceOfGymId = new Integer(context.get(0));
		serviceOfGym = serviceOfGymService.findById(serviceOfGymId);
		Assert.notNull(serviceOfGym);
		
		validable.setServiceOfGymId(serviceOfGymId);		
	}

	@Override
	public void beforeCommitingCreate(BookingForm validable, List<String> context) {
		
	}

	@Override
	public void afterCommitingCreate(int id) {
		
	}

	@Override
	public Booking convertToEntity(BookingForm bookingForm) {
		Assert.notNull(bookingForm);
		
		Booking result;
		ServiceOfGym serviceOfGym;
		Date endMoment;
		Date date;
		Customer customer;
		
		date = Moment.now();
		Assert.notNull(date);
		
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);	
		
		// Se recupera el servicio del gimnasio que se ha reservado a partir de su id.
		serviceOfGym = serviceOfGymService.findById(bookingForm.getServiceOfGymId());
		Assert.notNull(serviceOfGym);
		
		// Se calcula el atributo derivado fecha de finalización sumando a la fecha solicitada la duración de la reserva.
		double durationDouble = bookingForm.getDuration() * 3600000.0;
		
		endMoment = new Date((bookingForm.getRequestedMoment().getTime()+ (long) durationDouble));
		
		// Se crea un Booking y se le asignan los valores introducidos por el usuario en el formulario y los que se acaban de calcular.
		result = new Booking();
		result.setCreationMoment(date);
		result.setCustomer(customer);	
		result.setRequestedMoment(bookingForm.getRequestedMoment());
		result.setDuration(bookingForm.getDuration());
		result.setServiceOfGym(serviceOfGym);
		result.setEndMoment(endMoment);
		
		return result;
	}

}
