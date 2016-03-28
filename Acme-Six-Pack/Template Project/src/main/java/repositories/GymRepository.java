package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Gym;

@Repository
public interface GymRepository extends PagingAndSortingRepository<Gym, Integer> {

	@Query("select s.gym from ServiceOfGym s where s.serviceEntity.id = ?1")
	Page<Gym> findGymsThatOfferService(int serviceEntityId, Pageable page);
	
	/*
	 * Entendemos como gimnasios más populares aquellos en los que más Customers distintos han reservado alguno
	 * de sus servicios.
	 * Para ello, seleccionamos aquellos gimnasios cuyo valor del atributo customersTotalNumber sea igual
	 * al máximo valor de dicho atributo. Para ello, hacemos una consulta anidada seleccionando el máximo
	 * valor de dicho atributo mediante la función max.
	 */
	@Query("select g from Gym g where g.customersTotalNumber = (select max(g.customersTotalNumber) from Gym g)")
	Page<Gym> findMostPopularGyms(Pageable page);

	/* 
	 * Devuelve el valor máximo del atributo customersTotalNumber
	 *  */
	@Query("select max(g.customersTotalNumber) from Gym g")
	Integer quantityMostPopularGyms();

	/*
	 * Entendemos como gimnasios menos populares aquellos en los que menos Customers distintos han reservado alguno
	 * de sus servicios.
	 * Para ello, seleccionamos aquellos gimnasios cuyo valor del atributo customersTotalNumber sea igual
	 * al mínimo valor de dicho atributo. Para ello, hacemos una consulta anidada seleccionando el mínimo
	 * valor de dicho atributo mediante la función min.
	 */
	@Query("select g from Gym g where g.customersTotalNumber = (select min(g.customersTotalNumber) from Gym g)")
	Page<Gym> findLeastPopularGyms(Pageable page);

	/* 
	 * Devuelve el valor mínimo del atributo customersTotalNumber
	 *  */
	@Query("select min(g.customersTotalNumber) from Gym g")
	Integer quantityLeastPopularGyms();
	
	/*
	 * Para realizar esta consulta, seleccionamos los gyms que están
	 * relacionados con los comentarios y lo agrupamos por gyms, poniendo como
	 * condición que el número de gyms de cada grupo sea mayor o igual que el de
	 * todos los grupos de gyms relacionados con los comentarios.
	 */
	@Query("select c.gym from Comment c where c.isDeleted = false group by c.gym having count(c.gym) >= ALL(select count(c.gym) from Comment c where c.isDeleted = false group by c.gym)")
	Page<Gym> gymsWihtMoreComments(Pageable page);

	/*
	 * Cantidad de comentarios del gimnasio con más comentarios
	 */
	@Query("select count(c.gym) from Comment c where c.isDeleted = false group by c.gym having count(c.gym) >= ALL(select count(c.gym) from Comment c where c.isDeleted = false group by c.gym)")
	Long quantityGymsWithMoreComments();

	/*
	 * Encuentra los gimnasios que tengan una feePayment activa, dado un
	 * customer
	 */
	@Query("select f.gym from FeePayment f where f.customer.id = ?1 and f.activationDay <= ?2 and f.inactivationDay >= ?2")
	Page<Gym> findGymsWithActiveFeePaymentByCustomerId(int customerId, Date currentDate, Pageable page);

	/*
	 * Encuentra los gimnasios que tengan una feePayment activa, dado un
	 * customer
	 */
	@Query("select f.gym from FeePayment f where f.customer.id = ?1 and f.activationDay <= ?2 and f.inactivationDay >= ?2")
	Collection<Gym> findGymsWithActiveFeePaymentByCustomerId(int customerId, Date currentDate);
	
	/*
	 * Encuentra los gimnasios cuyos servicios conengan la palabra que se pasa
	 * por parámetro en el nombre o en la descripción.
	 */
	@Query("select s.gym from ServiceOfGym s where (s.serviceEntity.name like %?1% or s.description like %?1%) group by s.gym")
	Page<Gym> findGymsByKeywordInAOfferedService(String searchCriteria, Pageable page);

	/* Encuentra los gimnasios con más comentarios */
	@Query("select c.gym from Comment c where c.isDeleted = false group by c.gym having count(c.gym) >= ALL(select count(c.gym) from Comment c where c.isDeleted = false group by c.gym)")
	Page<Gym> findGymsThatHaveMoreComments(Pageable page);

}
