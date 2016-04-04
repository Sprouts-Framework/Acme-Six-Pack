package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import datatypes.CreditCard;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	private CreditCard creditCard;

	// Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 7976602760210895425L;

	public Customer() {
		super();
	}

	// Attributes -------------------------------------------------------------

	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationships ----------------------------------------------------------

	private SocialIdentity socialIdentity;

	@OneToOne(optional = true)
	@Valid
	public SocialIdentity getSocialIdentity() {
		return socialIdentity;
	}

	public void setSocialIdentity(SocialIdentity socialIdentity) {
		this.socialIdentity = socialIdentity;
	}

}
