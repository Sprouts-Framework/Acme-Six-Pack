package controllers.administrator.serviceOfGym;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymService;
import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.common.AbstractPostController;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.domain.DomainObject;
import forms.PicturesOfServiceOfGym;

@Controller("deletePictureOfServiceOfGymController")
@RequestMapping("serviceOfGym/administrator/picture/"+Codes.DELETE_MAPPING_VALUE_PARAMS)
public class DeletePictureController extends AbstractPostController<ServiceOfGymService> implements AddsToModel{

	@Override
	public void beforeCommiting(DomainObject object) {
		
	}

	@Override
	protected void action(List<String> context) {
		service.removePictureFromServiceOfGym(new Integer(context.get(1)), new Integer(context.get(0)));
		
	}

	@Override
	protected String onSuccess() {
		return "../../../../home/serviceOfGym/"+getContext().get(0)+"/show.do";
	}

	@Override
	protected String view() {
		return "serviceOfGym/deletePicture";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		PicturesOfServiceOfGym modelObject = new PicturesOfServiceOfGym();
		
		modelObject.setNewPicture(service.findPictureById(new Integer(context.get(1)), new Integer(context.get(0))));
		modelObject.setOldPictureId(new Integer(context.get(1)));
		modelObject.setServiceOfGymId(new Integer(context.get(0)));
		
		objects.put("modelObject", modelObject);
		
	}
	

}
