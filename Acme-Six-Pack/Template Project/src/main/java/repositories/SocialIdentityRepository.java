package repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends PagingAndSortingRepository<SocialIdentity, Integer>{

	//Devuelve la identidad social de un Customer, cuyo id se pasa por parámetro.
	@Query("select c.socialIdentity from Customer c where c.id = ?1")
	SocialIdentity findSocialIdentityByCustomerId(int customerId);
	
}
