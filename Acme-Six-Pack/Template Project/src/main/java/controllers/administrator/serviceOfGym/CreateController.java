package controllers.administrator.serviceOfGym;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Gym;
import domain.ServiceEntity;
import domain.ServiceOfGym;
import services.GymService;
import services.ServiceEntityService;
import services.ServiceOfGymService;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("serviceOfGymCreate")
@RequestMapping("serviceOfGym/administrator")
public class CreateController extends AbstractCreateController<ServiceOfGym,ServiceOfGymService> implements AddsToModel{

	@Autowired
	private GymService gymService;
	@Autowired
	private ServiceEntityService serviceEntityService;
	
	
	@Override
	public boolean authorize(ServiceOfGym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceOfGym/create";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Collection<Gym> gyms;
		Collection<ServiceEntity> serviceEntities;
		
		gyms = gymService.findAll();
		serviceEntities = serviceEntityService.findAll();
		
		objects.put("gyms", gyms);
		objects.put("serviceEntities", serviceEntities);
	}
	
	@Override
	protected String onSuccess() {
		Integer entityId = entityId(getPathVariables(getRequest()));
		
		ServiceOfGym serviceOfGym;
		serviceOfGym = service.findById(entityId);
		Assert.notNull(serviceOfGym);
		
		return "../../../home/gym/display.do?gymId="+serviceOfGym.getGym().getId(); 
	}
	
	@Override
	public ServiceOfGym getObject(Map<String, String> pathVariables, ServiceOfGym entity, List<String> context) {
		ServiceOfGym result;
		result = super.getObject(pathVariables, entity, context);
		if(context != null && !context.isEmpty()){
			Gym gym;
			gym = gymService.findById(new Integer(context.get(0)));
			result.setGym(gym);
		}
		
		return result;
	}

}
