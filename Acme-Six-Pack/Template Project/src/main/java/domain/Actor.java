package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.BaseActor;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy =InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends BaseActor {
 
	// Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -7589761258248167510L;

	public Actor() {
		super();
	}
	
	// Attributes -------------------------------------------------------------
	
	private String contactPhone;
		
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}



	// Relationships ----------------------------------------------------------
	private Collection<Comment> comments;
	
	@OneToMany(mappedBy="actor")
	@NotNull	
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	
}
