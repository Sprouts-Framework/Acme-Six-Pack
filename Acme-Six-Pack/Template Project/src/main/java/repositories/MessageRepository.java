package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer>{
	
	//@Query() //Consulta sin punto y coma al final
	@Query("select m from Message m where m.box.id=?1 order by m.moment desc")
	Page<Message> findMessagesInBox(int boxId, final Pageable page);
	
	@Query("select m from Message m where m.box.id=?1 order by m.moment desc")
	Collection<Message> findMessagesInBox(int boxId);
}