package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)

public class Booking extends DomainEntity {

	// Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 5227288452309823894L;

	public Booking() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private Date creationMoment;
	private Date requestedMoment;
	private double duration;
	private Date endMoment;
	private Boolean hasBeenApproved;
	
	//Getters y setters
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP) //Obligatorio
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP) //Obligatorio
	public Date getRequestedMoment() {
		return requestedMoment;
	}
	public void setRequestedMoment(Date requestedMoment) {
		this.requestedMoment = requestedMoment;
	}
	
	@Digits(fraction=1,integer=1)
	@Range(min=0,max=4)
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP) //Obligatorio
	public Date getEndMoment() {
		return endMoment;
	}
	public void setEndMoment(Date endMoment) {
		this.endMoment = endMoment;
	}
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public Boolean getHasBeenApproved() {
		return hasBeenApproved;
	}
	public void setHasBeenApproved(Boolean hasBeenApproved) {
		this.hasBeenApproved = hasBeenApproved;
	}
	
	
	
	// Relationships ----------------------------------------------------------
	private Administrator administrator;
	private Customer customer;
	private ServiceOfGym serviceOfGym;
	
	//Getters y setters
	
	@ManyToOne(optional=true)
	@Valid
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public ServiceOfGym getServiceOfGym() {
		return serviceOfGym;
	}
	public void setServiceOfGym(ServiceOfGym serviceOfGym) {
		this.serviceOfGym = serviceOfGym;
	}
	
}
