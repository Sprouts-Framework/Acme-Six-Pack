package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.CreditCard;

@Repository
public interface CreditCardRepository extends PagingAndSortingRepository<CreditCard, Integer>{
	
	//Devuelve la tarjeta de crédito de un determinado customer, cuyo id se pasa por parámetro.
	@Query("select c.creditCard from Customer c where c.id = ?1")
	CreditCard findCreditCardByCustomerId(int customerId);

	/*
	 * Encuentra todas las CreditCard asociadas a los FeePayment relacionados con el
	 * gimnasio cuyo id recibe como parámetro.
	 * */
	@Query("select fp.creditCard from FeePayment fp where fp.gym.id = ?1")
	Page<CreditCard> findCreditCardByGym(int gymId, Pageable page);
	
	@Query("select fp.creditCard from FeePayment fp where fp.gym.id = ?1")
	Collection<CreditCard> findCreditCardByGym(int gymId);
}
