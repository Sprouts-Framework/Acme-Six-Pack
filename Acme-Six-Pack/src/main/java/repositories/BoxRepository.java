package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Box;

@Repository
public interface BoxRepository extends PagingAndSortingRepository<Box, Integer>{
	
	//@Query() //Consulta sin punto y coma al final
	@Query("select b from Box b where b.actor.id=?1")
	Page<Box> findBoxesByActor(int actorId, Pageable page);
	
	@Query("select b from Box b where b.actor.id=?1")
	Collection<Box> findBoxesByActor(int actorId);
	
	@Query("select b from Box b where b.actor.id=?1 and b.isSystem=true and b.name=?2")
	Box findSystemBox(Integer actorId, String name);
}