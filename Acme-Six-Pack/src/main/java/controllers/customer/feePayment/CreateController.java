package controllers.customer.feePayment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FeePaymentService;
import domain.FeePayment;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("feePaymentCustomerControllerCreate")
@RequestMapping("feePayment/customer")
public class CreateController extends AbstractCreateController<FeePayment, FeePaymentService> {
	

	
	@Override
	public boolean authorize(FeePayment domainObject, UserAccount principal) {
		return domainObject.getCustomer().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "feePayment/create";
	}
	
	
	
//	@InitBinder
//	protected void feePaymentInitBinder(WebDataBinder binder) {
//		String dateFormatStr;
//		String decimalMark;
//		String groupingSeparator;
//		String prefix;
//		String suffix;
//		Locale locale;
//		locale = LocaleContextHolder.getLocale();
//
//		dateFormatStr = messageSource.getMessage("date.format", null, locale);
//		decimalMark = messageSource.getMessage("decimal-mark", null, locale);
//		groupingSeparator = messageSource.getMessage("grouping-separator", null, locale);
//		prefix = messageSource.getMessage("fp.currency.prefix", null, locale);
//		suffix = messageSource.getMessage("fp.currency.suffix", null, locale);
//		
////		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
////		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//
////		CustomCurrencyFormatter customCurrencyFormatter = new CustomCurrencyFormatter(decimalMark, groupingSeparator, format);
////		binder.registerCustomEditor(BigDecimal.class, new CustomCurrencyEditor(customCurrencyFormatter));
//		
//		DecimalFormatSymbols decimalFormatSymbols;
//		decimalFormatSymbols = DecimalFormatSymbols.getInstance();
//		decimalFormatSymbols.setDecimalSeparator(decimalMark.charAt(0));
//		decimalFormatSymbols.setGroupingSeparator(groupingSeparator.charAt(0));
//		//decimalFormatSymbols.setCurrencySymbol(currency);
//		//decimalFormatSymbols.setMonetaryDecimalSeparator(sep)
//
//		DecimalFormat numberFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
//		numberFormat.setPositivePrefix(prefix);
//		numberFormat.setPositiveSuffix(suffix);
//		binder.registerCustomEditor(BigDecimal.class, "fee", new CustomNumberEditor(BigDecimal.class, numberFormat, true));
//	}

}
