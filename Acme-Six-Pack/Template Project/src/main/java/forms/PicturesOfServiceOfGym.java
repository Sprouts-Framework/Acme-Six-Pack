package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import es.us.lsi.dp.domain.DomainForm;

@Entity
@Access(AccessType.PROPERTY)
public class PicturesOfServiceOfGym implements DomainForm{

	private String newPicture;
	private int oldPictureId;
	private int serviceOfGymId;
	
	@NotBlank
	@URL
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getNewPicture() {
		return newPicture;
	}
	public void setNewPicture(String newPicture) {
		this.newPicture = newPicture;
	}
	
	@Min(-1)
	public int getOldPictureId() {
		return oldPictureId;
	}
	public void setOldPictureId(int oldPictureId) {
		this.oldPictureId = oldPictureId;
	}
	
	@Min(1)
	public int getServiceOfGymId() {
		return serviceOfGymId;
	}
	public void setServiceOfGymId(int serviceOfGymId) {
		this.serviceOfGymId = serviceOfGymId;
	}
	
	
	
	
	
	
}
