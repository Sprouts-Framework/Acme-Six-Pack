package domain;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.apache.lucene.analysis.charfilter.MappingCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.TruncateTokenFilterFactory;
import org.apache.lucene.analysis.phonetic.PhoneticFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.CharFilterDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  }),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
	    @Parameter(name = "language", value = "Spanish")
  }),
  @TokenFilterDef(factory = TruncateTokenFilterFactory.class, params = {
	  	@Parameter(name = "prefixLength", value = "3")
  }),
  @TokenFilterDef(factory = PhoneticFilterFactory.class, params = {
	  	@Parameter(name = "encoder", value = "DoubleMetaphone"),
	  	@Parameter(name = "inject", value = "false"),
	  	@Parameter(name = "maxCodeLength", value = "4")
  })
}
)
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
	
	@Boost(value=2.0f)
	@Analyzer(definition = "customanalyzer")
	@Field
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Boost(value=1.9f)
	@Analyzer(definition = "customanalyzer")
	@Field
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Boost(value=1.5f)
	@Analyzer(definition = "customanalyzer")
	@Field
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	@Boost(value=1.0f)
	@Analyzer(definition = "customanalyzer")
	@Field
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Min(0)
	@Digits(integer=12, fraction=2)
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
