package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import cz.jirutka.validator.collection.constraints.EachURL;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)

public class ServiceOfGym extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -895437397699637351L;

	// Constructors -----------------------------------------------------------

	public ServiceOfGym() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	private String description;
	private List<String> pictures;
	private int customersTotalNumber;
	
	//Getters y setters
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ElementCollection
	@NotNull
	@EachURL
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	@Min(0)
	public int getCustomersTotalNumber() {
		return customersTotalNumber;
	}
	public void setCustomersTotalNumber(int customersTotalNumber) {
		this.customersTotalNumber = customersTotalNumber;
	}
	
	// Relationships ----------------------------------------------------------

	private Gym gym;
	private ServiceEntity serviceEntity;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public ServiceEntity getServiceEntity() {
		return serviceEntity;
	}
	public void setServiceEntity(ServiceEntity serviceEntity) {
		this.serviceEntity = serviceEntity;
	}
	
	
	
}
