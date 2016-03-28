package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.ServiceOfGym;

@Repository
public interface ServiceOfGymRepository extends PagingAndSortingRepository<ServiceOfGym, Integer>{
	
	//Devuelve la cantidad de servicios de un gimnasio que están asociados a una entidad servicio determinada, cuyo id se pasa por parámetro.
	@Query("select count(s) from ServiceOfGym s where s.serviceEntity.id = ?1")
	Long countServiceOfGymByServiceId(int serviceEntityId);
	
	// Devuelve aquellos servicios que pertenezcan al gimnasio cuyo id se pasa por parámetro
	@Query("select s from ServiceOfGym s where s.gym.id = ?1")
	Page<ServiceOfGym> findServiceOfGymByGymId(int gymId, Pageable page);
	
	// Devuelve aquellos servicios que pertenezcan al gimnasio cuyo id se pasa por parámetro
	@Query("select s from ServiceOfGym s where s.gym.id = ?1")
	Collection<ServiceOfGym> findServiceOfGymByGymId(int gymId);
	
	//Devuelve los servicios de un gimnasio que han sido reservados por un determinado customer, cuyo id se pasa por parámetro.
	@Query("select b.serviceOfGym from Booking b where b.customer.id=?1")
	Collection<ServiceOfGym> findBookedServiceOfGyms(int customerId);
	
	/*
	 * Para realizar esta consulta, seleccionamos los services  que están relacionados con los 
	 * comentarios y lo agrupamos por services, poniendo como condición que el número de services 
	 * de cada grupo sea mayor o igual que el de todos los grupos de services relacionados con los 
	 * comentarios.
	 * */
	@Query("select c.serviceOfGym from Comment c where c.isDeleted = false group by c.serviceOfGym having count(c.serviceOfGym) >= ALL(select count(c.serviceOfGym) from Comment c where c.isDeleted = false group by c.serviceOfGym)")
	Page<ServiceOfGym> findServicesThatHaveMoreComments(Pageable page);
	
	/*
	 * Cantidad de comentarios del servicio con más comentarios
	 * */
	@Query("select count(c.serviceOfGym) from Comment c where c.isDeleted = false group by c.serviceOfGym having count(c.serviceOfGym) >= ALL(select count(c.serviceOfGym) from Comment c where c.isDeleted = false group by c.serviceOfGym)")
	Long quantityServicesThatHaveMoreComments();
}
