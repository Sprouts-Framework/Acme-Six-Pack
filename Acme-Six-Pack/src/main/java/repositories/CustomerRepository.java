package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

	@Query("select c from Customer c, SocialAccount s where s member of c.userAccount.socialAccounts and s.providerId = ?1 and s.userId = ?2")
	Customer findBySocialAccount(String providerId, String userId);
	
	@Query("select c from Customer c where c.creditCard.id = ?1")
	Customer findByCreditCard(int creditCardId);
	
	@Query("select c from Customer c where c.socialIdentity.id=?1")
	Customer findBySocialIdentity(int socialIdentityId);
	
//	//Devuelve el Customer cuya cuenta de usuario tiene el id que se pasa por parámetro.
	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByPrincipal(int userAccountId);
	
	/*
	 * El customer que más fees ha pagado, es simplemente el cliente que más fees tiene relacionadas.
	 * Para ello, seleccionamos los customers  que están relacionados con los fees y lo agrupamos por 
	 * customers, poniendo como condición que el número de customers de cada grupo sea mayor o igual 
	 * que el de todos los grupos de customers relacionados con los fees.
	 * */
	@Query("select fp.customer from FeePayment fp group by fp.customer having count(fp.customer) >= ALL(select count(fp.customer) from FeePayment fp group by fp.customer)")
	Page<Customer> findCustomersWhoHavePaidMoreFees(Pageable page);
	
	/*
	 * Cantidad de feePayments del consumer que ha pagado más fees.
	 * */
	@Query("select count(fp.customer) from FeePayment fp group by fp.customer having count(fp.customer) >= ALL(select count(fp.customer) from FeePayment fp group by fp.customer)")
	Long quantityCustomersWhoHavePaidMoreFees();
	
	/*
	 * El customer que menos fees ha pagado, entendemos que es, de entre los que ha pagado al menos un fee,
	 * el que menos fees tiene relacionadas.
	 * De forma análoga a la anterior consulta, seleccionamos los customers  que están relacionados con los 
	 * fees y lo agrupamos por customers, poniendo como condición que el número de customers de cada grupo 
	 * sea menor o igual que el de todos los grupos de customers relacionados con los fees.
	 * */
	@Query("select fp.customer from FeePayment fp group by fp.customer having count(fp.customer) <= ALL(select count(fp.customer) from FeePayment fp group by fp.customer)")
	Page<Customer> findCustomersWhoHavePaidLessFees(Pageable page);
	
	/*
	 * Cantidad de feePayments del consumer que ha pagado más fees.
	 * */
	@Query("select count(fp.customer) from FeePayment fp group by fp.customer having count(fp.customer) <= ALL(select count(fp.customer) from FeePayment fp group by fp.customer)")
	Long quantityCustomersWhoHavePaidLessFees();
	
	@Query("select count(m) from Message m where m.sender.id = ?1 and m.box.name ='spam box'")
	Long findNumberOfSpamMessages(int id);
	
	@Query("select count(c) from Comment c where c.isDeleted=true and c.actor.id=?1")
	Long findNumberOfDeletedComments(int id);
	
	@Query("select count(f) from FeePayment f where f.gym.id = ?2 and f.customer.id = ?1")
	Long findNumberOfFeeInAGym(int customerId, int gymId);
	
}
