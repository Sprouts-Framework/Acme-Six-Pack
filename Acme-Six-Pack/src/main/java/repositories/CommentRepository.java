package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>{

	/*Encuentra los comentarios que no est�n borrados y que est�n asociados al servicio que se le pasa por par�metro*/
	@Query("select c from Comment c where c.serviceOfGym.id = ?1 and isDeleted=false")
	public Page<Comment> findCommentsByServiceOfGymId(int serviceOfGymId, Pageable page);

	/*Encuentra los comentarios que no est�n borrados y que est�n asociados al gimnasio que se le pasa por par�metro*/
	@Query("select c from Comment c where c.gym.id = ?1 and isDeleted=false")
	public Page<Comment> findCommentsByGymId(int gymId, Pageable page);
	
	/*Encuentra los comentarios que no est�n borrados y que est�n asociados al servicio que se le pasa por par�metro*/
	@Query("select c from Comment c where c.serviceOfGym.id = ?1")
	public Collection<Comment> findCollectionOfCommentsByServiceOfGymId(int serviceOfGymId);

	/*Encuentra los comentarios que no est�n borrados y que est�n asociados al gimnasio que se le pasa por par�metro*/
	@Query("select c from Comment c where c.gym.id = ?1")
	public Collection<Comment> findCollectionOfCommentsByGymId(int gymId);
	
	/*Media de comentarios por los gimnasio*/
	@Query("select 1.0*count(c)/(select count(g) from Gym g) from Comment c where c.gym is not null")
	public Double averageNumberOfCommentsPerGym();
	
	/*Media de comentarios por servicio*/
	@Query("select 1.0*count(c)/(select count(s) from ServiceOfGym s) from Comment c where c.serviceOfGym is not null")
	public Double averageNumberOfCommentsPerService();

	/* La media del n�mero de comentarios escritos por los actores, incluyendo la desviaci�n est�ndar*/
	@Query("select (select 1.0*count(c) from Comment c) / (1.0*count(a)), sqrt(sum(a.comments.size * a.comments.size)/count(a) - ((select count(c) from Comment c) / (count(a)) ))  from Actor a")
	public Object[] averageAndStantadrdDeviationOfComments();

	@Query("select c from Comment c where c.serviceOfGym.gym.id = ?1")
	Collection<Comment> findCommentsOfServicesByGymId(int gymId);
	
}
