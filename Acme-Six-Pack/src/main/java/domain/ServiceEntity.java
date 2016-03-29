package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)

public class ServiceEntity extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6205071418214128076L;

	// Constructors -----------------------------------------------------------

	public ServiceEntity() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private String name;
	private int customersTotalNumber;
	
	//Getters y setters
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Min(0)
	public int getCustomersTotalNumber() {
		return customersTotalNumber;
	}
	public void setCustomersTotalNumber(int customersTotalNumber) {
		this.customersTotalNumber = customersTotalNumber;
	}
	
	// Relationships ----------------------------------------------------------	
	
}
