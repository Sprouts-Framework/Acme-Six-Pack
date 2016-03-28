package validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.us.lsi.dp.forms.BaseRegistrationForm;
import forms.UserAccountForm;

@Component
public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BaseRegistrationForm.class.isAssignableFrom(clazz) ||
				UserAccountForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof BaseRegistrationForm){
			BaseRegistrationForm brf = (BaseRegistrationForm) target;
			
			if(!brf.getPassword().equals(brf.getPassword2()))
				errors.rejectValue("password", "acme.validators.password");
		} else {
			UserAccountForm uaf = (UserAccountForm) target;
			
			if(!uaf.getPassword().equals(uaf.getPassword2()))
				errors.rejectValue("password", "acme.validators.password");
		}


	}

}
