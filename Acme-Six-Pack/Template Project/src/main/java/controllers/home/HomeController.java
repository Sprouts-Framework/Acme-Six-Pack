/*
 * HomePositionController.java Copyright (C) 2013 Universidad de Sevilla The use
 * of this project is hereby constrained to the conditions of the TDG Licence, a
 * copy of which you may download from http://www.tdg-seville.info/License.html
 */

package controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.forms.Credentials;

@Controller
@RequestMapping("home")
public class HomeController {

	// @Autowired
	// private CustomerService customerService;

	// Sign in ----------------------------------------------------------------

	@RequestMapping(value = "/sign-in", method = RequestMethod.GET)
	public ModelAndView signInGet(@ModelAttribute final Credentials credentials, final BindingResult bindingResult,
			@RequestParam(required = false) final boolean showError) {
		ModelAndView result;

		result = new ModelAndView("home/sign-in");
		result.addObject("credentials", credentials);
		if (showError)
			result.addObject("failureCode", "sign-in.failure");

		return result;
	}

	// @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	// public ModelAndView welcome() {
	// ModelAndView result;
	//
	// if(SignInService.checkAuthority("Customer")){
	// Customer principal;
	// principal = customerService.findByPrincipal();
	// if(principal.getVersion() == 0)
	// result = new ModelAndView("../creditCard/customer/create.do");
	// else
	// result = new ModelAndView("home/welcome");
	// }else
	// result = new ModelAndView("home/welcome");
	//
	// return result;
	// }
}