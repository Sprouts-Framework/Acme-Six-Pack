package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.ServiceEntity;

@Repository
public interface ServiceEntityRepository extends PagingAndSortingRepository<ServiceEntity, Integer>{
	
	/*
	 * Devuelve el ServiceEntity cuyo nombre es Fitness (solo debe existir uno con ese nombre)
	 * */
	@Query("select s from ServiceEntity s where s.name='Fitness'")
	ServiceEntity findFitnessServiceEntity();
	
	/*
	 * Entendemos como servicios m�s populares aquellos en los que m�s Customers distintos han reservado 
	 * el servicio.
	 * Para ello, seleccionamos aquellos servicios cuyo valor del atributo customersTotalNumber sea igual
	 * al m�ximo valor de dicho atributo. Para ello, hacemos una consulta anidada seleccionando el m�ximo
	 * valor de dicho atributo mediante la funci�n max.
	 */
	@Query("select s from ServiceEntity s where s.customersTotalNumber = (select max(s.customersTotalNumber) from ServiceEntity s)")
	Page<ServiceEntity> findMostPopularServices(Pageable page);
	
	/* 
	 * Devuelve el valor m�ximo del atributo customersTotalNumber
	 * */
	@Query("select max(s.customersTotalNumber) from ServiceEntity s")
	Integer quantityMostPopularServices();
	
	/*
	 * Entendemos como servicios menos populares aquellos en los que menos Customers distintos han reservado 
	 * el servicio.
	 * Para ello, seleccionamos aquellos servicios cuyo valor del atributo customersTotalNumber sea igual
	 * al m�nimo valor de dicho atributo. Para ello, hacemos una consulta anidada seleccionando el m�nimo
	 * valor de dicho atributo mediante la funci�n min.
	 */
	@Query("select s from ServiceEntity s where s.customersTotalNumber = (select min(s.customersTotalNumber) from ServiceEntity s)")
	Page<ServiceEntity> findLeastPopularServices(Pageable page);

	/* 
	 * Devuelve el valor m�nimo del atributo customersTotalNumber
	 * */
	@Query("select min(s.customersTotalNumber) from ServiceEntity s")
	Integer quantityLeastPopularServices();
	
}
