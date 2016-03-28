package controllers.administrator.serviceOfGym;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymPictureService;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import forms.PicturesOfServiceOfGym;

@Controller("updatePictureOfServiceOfGymController")
@RequestMapping("serviceOfGym/administrator/picture")
public class UpdatePictureController extends AbstractUpdateController<PicturesOfServiceOfGym, ServiceOfGymPictureService> {

	@Override
	public boolean authorize(PicturesOfServiceOfGym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceOfGym/editPicture";
	}
	
	@Override
	public PicturesOfServiceOfGym getObject(Map<String, String> pathVariables, PicturesOfServiceOfGym entity, List<String> context) {
		return service.convertToForm(null);
	}
	
	@Override
	protected String onSuccess() {
		return "../../../../home/serviceOfGym/"+getContext().get(0)+"/show.do";
	}

}
