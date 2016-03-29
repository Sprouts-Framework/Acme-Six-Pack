package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.lsi.dp.domain.DomainForm;


public class BookingForm implements DomainForm{

	private Date requestedMoment;
	private double duration;
	private int serviceOfGymId;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP) //Obligatorio
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") //Formato de la fecha
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
	
	@Min(1)
	public int getServiceOfGymId() {
		return serviceOfGymId;
	}
	public void setServiceOfGymId(int serviceOfGymId) {
		this.serviceOfGymId = serviceOfGymId;
	}

	
	
}
