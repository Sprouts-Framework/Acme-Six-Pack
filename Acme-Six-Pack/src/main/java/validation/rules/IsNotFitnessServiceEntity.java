package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.ServiceEntityService;

import domain.ServiceEntity;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotFitnessServiceEntity implements BusinessRule<ServiceEntity> {

	@Autowired
	private ServiceEntityService serviceEntityService;
	
	@Override
	public boolean rule(final ServiceEntity serviceEntity) {
		ServiceEntity serviceEntity2;
		
		serviceEntity2 = serviceEntityService.findFitnessServiceEntity();
		Assert.notNull(serviceEntity2);
		return !serviceEntity2.equals(serviceEntity);
	}

	@Override
	public String warning() {
		return "serviceEntity.delete.fitness";
	}

}
