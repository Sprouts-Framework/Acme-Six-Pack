package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Booking;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer>{
	
	//Devuelve aquel/llos Booking que se encuentre solapados con la fecha que ha solicitado un customer para hacer una nueva reserva.
	//En caso de no existir ninguno que se solape, se devuelve una lista vacía.
	@Query("select b from Booking b where ((b.requestedMoment<=?1 and b.endMoment>=?1) or (b.requestedMoment<=?2 and b.endMoment>=?2) or (b.requestedMoment >= ?1 and b.endMoment <=?2) or (b.requestedMoment <= ?1 and b.endMoment >= ?2)) and b.customer.id =?3 and (b.hasBeenApproved is null or b.hasBeenApproved = true)")
	Collection<Booking> findOverlappedBookings(Date requestedMoment, Date endMoment, int customerId);
	
	//Devuelve los Booking, ordenados por la fecha solicitada, de un customer dado, cuyo id se pasa por parámetro.
	@Query("select b from Booking b where b.customer.id = ?1 order by b.requestedMoment")
	Page<Booking> findAllByCustomerId(int customerId, Pageable page);
	
	//Devuelve los Booking que aún no han sido supervisados y no han expirado.
	@Query("select b from Booking b where b.hasBeenApproved = null and b.requestedMoment >= ?1")
	Page<Booking> findAllPendingBookings(Date today, Pageable page);
	
	//Devuelve los Booking que han sido supervisados por el administrador cuyo id se pasa por parámetro, ordenados por la fecha solicitada.
	@Query("select b from Booking b where b.administrator.id = ?1 order by b.requestedMoment")
	Page<Booking> findAllSupervisedByAdministratorId(int administratorId, Pageable page);
	
	//Devuelve los Booking que están aprobados, que pertenecen al servicio del gimnasio cuyo id se pasa por parámetro
	//y que han sido realizados por el customer cuyo id se pasa por parámetro.
	@Query("select b from Booking b where b.customer.id = ?2 and b.serviceOfGym.id = ?1 and b.hasBeenApproved = true")
	Collection<Booking> findApprovedBookingsByServiceAndCustomerId(int serviceOfGymId, int customerId);
	
	/*
	 * Encuentra aquellos Bookings aprobados relacionados con el serviceEntity y customer cuyos id's recibe como
	 * parámetros.
	 * */
	@Query("select b from Booking b where b.customer.id = ?2 and b.serviceOfGym.serviceEntity.id = ?1 and b.hasBeenApproved = true")
	Collection<Booking> findApprovedBookingsByServiceEntityAndCustomerId(int serviceEntityId, int customerId);
	
	//Devuelve los Booking que están aprobados, que son en un gimnasio cuyo id se pasa por parámetro y pertenecen al customer cuyo id se pasa también por parámetro.
	@Query("select b from Booking b where b.customer.id = ?2 and b.serviceOfGym.gym.id =?1 and b.hasBeenApproved = true")
	Collection<Booking> findApprovedBookingsByGymAndCustomerId(int gymId, int customerId);
	
	//Devuelve los Booking del servicio del gimnasio cuyo id se pasa por parámetro.
	@Query("select b from Booking b where b.serviceOfGym.id = ?1")
	Collection<Booking> findBookingsByServiceOfGymId(int serviceOfGymId);
	
	//Devuelve los Booking de un gimnasio concreto, cuyo id se pasa por parámetro.
	@Query("select b from Booking b where b.serviceOfGym.gym.id =?1")
	Collection<Booking> findBookingsByGymId(int gymId);
	
	@Query("select count(b) from Booking b where b.customer.id = ?1 group by b.customer")
	Long countTotalBookingsByCustomer(int customerId);
	
	@Query("select 1.0*count(b)/(select count(b) from Booking b where b.customer.id = ?1) from Booking b where b.customer.id = ?1 and b.hasBeenApproved = false")
	Double ratioOfCancelledBookings(int customerId);
	
}
