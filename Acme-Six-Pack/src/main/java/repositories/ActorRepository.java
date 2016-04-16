package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.username=?1")
	Actor findActorByUserAccount(String userAccount);

	/*
	 * Entendemos como "usuario que ha enviado más mensajes de spam" aquellos
	 * usuarios emisores cuyos receptores tengan más mensajes del emisor en la
	 * carpeta spam box. Para ello, seleccionamos los emisores de los mensajes
	 * contenidos en las carpetas con nombre 'spam box' y sean carpetas del
	 * sistema. Agrupamos el resultado por emisor, quedándonos con aquel grupo
	 * cuyo número de mensajes que cumplen la condición antes citada sea mayor
	 * igual que todos los grupos que devuelve la consulta.
	 */
	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findActorByPrincipal(int id);

	@Query("select m.sender from Message m where m.box.name='spam box' and m.box.isSystem=true group by m.sender having count(m)>=ALL(select count(m2) from Message m2 where m2.box.name='spam box' and m2.box.isSystem=true group by m2.sender)")
	Page<Actor> findActorsWhoSendMoreSpamMessages(Pageable page);

	/*
	 * Cantidad de mensajes de spam enviados por el actor que más spam ha
	 * enviado.
	 */
	@Query("select count(m.sender) from Message m where m.box.name='spam box' and m.box.isSystem=true group by m.sender having count(m)>=ALL(select count(m2) from Message m2 where m2.box.name='spam box' and m2.box.isSystem=true group by m2.sender)")
	Long quantityActorsWhoSendMoreSpamMessages();

	@Query("select count(m)/(1.0*(select count(b) from Box b where b.actor.id = ?1)) from Message m where (m.sender.id = ?1 or m.recipient.id = ?1) and m.box.actor.id =?1")
	Double avarageNumberOfMessagesInActorBoxes(int actorId);

	@Query("select c.actor from Comment c where c.isDeleted = true group by c.actor having count(c) >= ALL(select count(c2) from Comment c2 where c2.isDeleted = true group by c2.actor)")
	Page<Actor> findActorsWhoHaveRemovedMoreComments(Pageable page);

	@Query("select count(c.actor) from Comment c where c.isDeleted = true group by c.actor having count(c) >= ALL(select count(c2) from Comment c2 where c2.isDeleted = true group by c2.actor)")
	Long quantityActorsWhoHaveRemovedMoreComments();
}