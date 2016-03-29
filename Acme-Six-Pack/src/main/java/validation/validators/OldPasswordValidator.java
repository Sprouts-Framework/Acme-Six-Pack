package validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import services.CustomerService;
import es.us.lsi.dp.utilities.PasswordEncoder;
import forms.UserAccountForm;

@Component
public class OldPasswordValidator implements Validator {
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccountForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAccountForm uaf = (UserAccountForm) target;
		
		String oldPasswordHashed = PasswordEncoder.encode(uaf.getOldPassword());
		
		if(!oldPasswordHashed.equals(customerService.findByPrincipal().getUserAccount().getPassword()))
			errors.rejectValue("oldPassword", "acme.validators.old-password");

	}

}
