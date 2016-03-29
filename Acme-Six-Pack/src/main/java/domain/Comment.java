package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)

public class Comment extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8137515921749389427L;

	// Constructors -----------------------------------------------------------

	public Comment() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private Date moment;
	private String text;
	private int starRating;
	private boolean isDeleted;

	//Getters y setters
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP) //Obligatorio
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") //Formato de la fecha
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Range(min=0,max=3)
	public int getStarRating() {
		return starRating;
	}
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	// Relationships ----------------------------------------------------------
	private Actor actor;
	private ServiceOfGym serviceOfGym;
	private Gym gym;
	

	
	//Getters y setters
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	@ManyToOne(optional=true)
	@Valid
	public ServiceOfGym getServiceOfGym() {
		return serviceOfGym;
	}
	public void setServiceOfGym(ServiceOfGym serviceOfGym) {
		this.serviceOfGym = serviceOfGym;
	}
	
	@ManyToOne(optional=true)
	@Valid
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}
	
}
