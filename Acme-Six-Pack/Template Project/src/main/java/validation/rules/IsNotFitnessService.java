package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.ServiceEntityService;
import services.ServiceOfGymService;

import domain.ServiceEntity;
import domain.ServiceOfGym;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotFitnessService implements BusinessRule<ServiceOfGym> {

	@Autowired
	private ServiceEntityService serviceEntityService;
	
	@Autowired
	private ServiceOfGymService serviceOfGymService;
	
	@Override
	public boolean rule(final ServiceOfGym serviceOfGym) {

		ServiceOfGym old;
		ServiceEntity serviceEntity;
				
		//Se comprueba que el servicio que se elimina no es el de Fitness, puesto que debe estar en todos los gimnasios.
		
		old = serviceOfGymService.findById(serviceOfGym.getId());
		Assert.notNull(old);
		
		serviceEntity = serviceEntityService.findFitnessServiceEntity();
		Assert.notNull(serviceEntity);
		
		return !old.getServiceEntity().equals(serviceEntity);
	}

	@Override
	public String warning() {
		return "serviceOfGym.delete.fitness";
	}

}
