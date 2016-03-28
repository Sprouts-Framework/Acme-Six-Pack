package controllers.administrator.serviceOfGym;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServiceOfGymPictureService;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import forms.PicturesOfServiceOfGym;

@Controller("pictureOfServiceOfGymCreateController")
@RequestMapping("serviceOfGym/administrator/picture")
public class CreatePictureController extends AbstractCreateController<PicturesOfServiceOfGym, ServiceOfGymPictureService> {

	@Override
	public boolean authorize(PicturesOfServiceOfGym domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "serviceOfGym/addPicture";
	}

	@Override
	protected String onSuccess() {
		return "../../../../home/serviceOfGym/"+getContext().get(0)+"/show.do";
	}
	
}
