package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import cz.jirutka.validator.collection.constraints.EachNotBlank;
import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class SpamTerms extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742380956535892271L;

	// Constructors -----------------------------------------------------------

	public SpamTerms() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	private List<String> terms;

	@NotNull
	@ElementCollection
	@EachNotBlank
	public List<String> getTerms() {
		return terms;
	}
	public void setTerms(List<String> terms) {
		this.terms = terms;
	}
	
	
}
