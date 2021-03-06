package domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import datatypes.CreditCard;
import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class FeePayment extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5554009802702404923L;

	// Constructors -----------------------------------------------------------

	public FeePayment() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private Date paymentMoment;
	private BigDecimal fee;
	private Date activationDay;
	private Date inactivationDay;
	private CreditCard creditCard;

	// Getters y setters

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	// Obligatorio
	public Date getPaymentMoment() {
		return paymentMoment;
	}

	public void setPaymentMoment(Date paymentMoment) {
		this.paymentMoment = paymentMoment;
	}

	@Min(0)
	@Digits(integer=12, fraction=2)
	@NotNull
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	// Obligatorio
	public Date getActivationDay() {
		return activationDay;
	}

	public void setActivationDay(Date activationDay) {
		this.activationDay = activationDay;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	// Obligatorio
	public Date getInactivationDay() {
		return inactivationDay;
	}

	public void setInactivationDay(Date inactivationDay) {
		this.inactivationDay = inactivationDay;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationships ----------------------------------------------------------
	private Customer customer;
	private Gym gym;

	// Attributes to map

	// Getters y setters

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

}
