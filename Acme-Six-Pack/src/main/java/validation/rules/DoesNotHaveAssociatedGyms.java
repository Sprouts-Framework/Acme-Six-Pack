package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.ServiceOfGymService;
import domain.ServiceEntity;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class DoesNotHaveAssociatedGyms implements BusinessRule<ServiceEntity> {

	@Autowired
	private ServiceOfGymService serviceOfGymService;
	
	@Override
	public boolean rule(final ServiceEntity serviceEntity) {
		
		Long numberOfServiceOfGyms;
		numberOfServiceOfGyms = serviceOfGymService.countServiceOfGymByServiceId(serviceEntity.getId());
		Assert.notNull(numberOfServiceOfGyms);		
		
		return numberOfServiceOfGyms.equals(new Long(0));
	}

	@Override
	public String warning() {
		return "serviceEntity.delete.gymsError";
	}

}
