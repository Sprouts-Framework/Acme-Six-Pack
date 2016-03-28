package controllers.customer.userAccount;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserAccountChangePasswordService;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;
import forms.UserAccountForm;

@Controller("userAccountChangePasswordController")
@RequestMapping("profile/userAccount")
public class UpdateController extends AbstractUpdateController<UserAccountForm, UserAccountChangePasswordService>{
	
	@Override
	public boolean authorize(UserAccountForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "profile/editUserAccount";
	}

	@Override
	public UserAccountForm getObject(Map<String, String> pathVariables, UserAccountForm entity, List<String> context) {
		UserAccount result;
		result = SignInService.getPrincipal();
		return service.convertToForm(result);
	}

	@Override
	protected String onSuccess() {
		return "/profile/customer/show.do";
	}
}
