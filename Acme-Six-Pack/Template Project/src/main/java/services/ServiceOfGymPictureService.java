package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import repositories.ServiceOfGymRepository;
import domain.ServiceOfGym;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.services.contracts.forms.UpdateFormService;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.PicturesOfServiceOfGym;

@Service
@Transactional
public class ServiceOfGymPictureService extends AbstractFormService<ServiceOfGym, PicturesOfServiceOfGym, ServiceOfGymRepository> implements
		CreateFormService<PicturesOfServiceOfGym, ServiceOfGym>, UpdateFormService<PicturesOfServiceOfGym, ServiceOfGym> {

	@Autowired
	private ServiceOfGymService serviceOfGymService;

	// Create methods ----------------------------------------------
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return PicturesOfServiceOfGym.class;
	}

	@Override
	public void beforeCreating(PicturesOfServiceOfGym validable, List<String> context) {
		Integer serviceOfGymId;

		serviceOfGymId = Integer.valueOf(context.get(0));

		validable.setOldPictureId(-1);
		validable.setServiceOfGymId(serviceOfGymId);
	}

	@Override
	public void beforeCommitingCreate(PicturesOfServiceOfGym validable) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<PicturesOfServiceOfGym>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	// Update methods ---------------------------------------------

	@Override
	public void beforeUpdating(PicturesOfServiceOfGym validable, List<String> context) {
		String picture;
		int pictureId;
		int serviceOfGymId;

		if (validable.getNewPicture() == null) {
			serviceOfGymId = Integer.valueOf(context.get(0));
			pictureId = Integer.valueOf(context.get(1));
			picture = serviceOfGymService.findPictureById(pictureId, serviceOfGymId);

			validable.setNewPicture(picture);
			validable.setOldPictureId(pictureId);
			validable.setServiceOfGymId(serviceOfGymId);
		}
	}

	@Override
	public void beforeCommitingUpdate(PicturesOfServiceOfGym validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<PicturesOfServiceOfGym>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Convert methods -----------------------------------
	@Override
	public PicturesOfServiceOfGym convertToForm(ServiceOfGym entity) {
		PicturesOfServiceOfGym result;

		result = new PicturesOfServiceOfGym();

		return result;
	}

	@Override
	public ServiceOfGym convertToEntity(PicturesOfServiceOfGym form) {
		ServiceOfGym result;

		if (form.getOldPictureId() >= 0) {
			result = serviceOfGymService.modifyPictureFromServiceOfGym(form.getNewPicture(), form.getOldPictureId(), form.getServiceOfGymId());
		} else {
			result = serviceOfGymService.addPictureToServiceOfGym(form.getNewPicture(), form.getServiceOfGymId());
		}

		return result;
	}

}
