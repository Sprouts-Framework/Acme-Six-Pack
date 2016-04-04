package domain;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import es.us.lsi.dp.domain.DomainEntity;
import formatters.CurrencyFormatter;

@Entity
@Access(AccessType.PROPERTY)

public class Gym extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4008562730380231032L;

	// Constructors -----------------------------------------------------------

	public Gym() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	private String name;
	private String description;
	private String postalAddress;
	private String phoneNumber;
	private BigDecimal fee;
	private String picture;
	private int customersTotalNumber;
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Min(0)
	@CurrencyFormatter(currency="EUR")
	//@NumberFormat(style = Style.CURRENCY)
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
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
