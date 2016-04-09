package es.us.lsi.dp.controllers.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.utilities.ContextParser;
import es.us.lsi.dp.utilities.Response;

public abstract class BaseController {

	// Logger ------------------------------------------------------------------

	protected static final Log LOG = LogFactory.getLog(BaseController.class);

	// Attributes --------------------------------------------------------------

	@Autowired
	protected MessageSource messageSource;

	private String currentViewName;

	private static final ThreadLocal<HttpServletRequest> request;
	private static final ThreadLocal<HttpServletResponse> response;

	static {
		request = new ThreadLocal<>();
		response = new ThreadLocal<>();
	}

	public String getCurrentViewName() {
		return currentViewName;
	}

	public void setRequest(final HttpServletRequest request) {
		BaseController.request.set(request);
	}

	public HttpServletRequest getRequest() {
		return request.get();
	}

	public void setResponse(final HttpServletResponse response) {
		BaseController.response.set(response);
	}

	public HttpServletResponse getResponse() {
		return response.get();
	}

	public void setRequestAndResponse(final HttpServletRequest request, final HttpServletResponse response) {
		setRequest(request);
		setResponse(response);
	}

	// Initialisation ----------------------------------------------------------

	@PostConstruct
	protected final void setView() {
		currentViewName = view();
	}

	// View generation ---------------------------------------------------------

	@SuppressWarnings("unchecked")
	protected Map<String, String> getPathVariables(final HttpServletRequest request) {
		Map<String, String> result;

		result = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		return result;
	}

	protected List<String> getContext() {
		return ContextParser.parse(getPathVariables(getRequest()));
	}

	protected ModelAndView currentView() {
		ModelAndView result;
		result = Response.create(currentViewName, Codes.VIEW_NAME, currentViewName);

		if (this instanceof AddsToModel)
			addEntitiesToModel(result, getContext());

		return result;
	}

	// Receives a context. This method is called by get method in GetController
	// class and in case of errors by the methods faiulre and errors
	protected ModelAndView currentView(final Object domainObject, final List<String> context, final Object... arguments) {
		ModelAndView result;

		result = view(currentViewName, Codes.MODEL_OBJECT_NAME, domainObject, context, arguments);

		return result;
	}

	private ModelAndView view(final String viewName, final String domainObjectName, final Object modelObject, final List<String> context,
			final Object... arguments) {
		ModelAndView result;
		boolean usesEntities;

		result = Response.create(viewName, arguments);

		result.addObject(Codes.MODEL_OBJECT_NAME, modelObject);

		usesEntities = this instanceof AddsToModel;

		if (usesEntities)
			addEntitiesToModel(result, context);

		return result;
	}

	private void addEntitiesToModel(final ModelAndView modelAndView, final List<String> context) {
		AddsToModel usesEntities;
		Map<String, Object> objects;

		objects = new HashMap<>();
		usesEntities = (AddsToModel) this;

		usesEntities.addToModel(objects, context);

		modelAndView.addAllObjects(objects);
	}

	// Template methods ------------------------------------------------------

	protected abstract String view();

	// Binders ----------------------------------------------------------------
	
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
//		Map<String, CustomInternationalization> internationalizations = new HashMap<>();
		String dateFormatStr, decimalMark, groupingSeparator, prefix, suffix;
//		String codeDateFormatStr, codeDecimalMark, codeGroupingSeparator, codePrefix, codeSuffix;
		Locale locale;
		
//		if(this instanceof AddCustomInternationalization){
//			AddCustomInternationalization addCustomInternationalization = (AddCustomInternationalization) this;
//			addCustomInternationalization.addCustomInternacionalization(internationalizations);
//		}
		
		
		
		locale = LocaleContextHolder.getLocale();

		dateFormatStr = messageSource.getMessage("date.format", null, locale);
		decimalMark = messageSource.getMessage("decimal-mark", null, locale);
		groupingSeparator = messageSource.getMessage("grouping-separator", null, locale);
		prefix = messageSource.getMessage("currency.prefix", null, locale);
		suffix = messageSource.getMessage("currency.suffix", null, locale);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//		CustomDateFormat  customDateFormat= new CustomDateFormat("", Date.class, "");
//		binder.registerCustomEditor(customDateFormat.getType(), new CustomDateEditor(customDateFormat.getFormat(), false));

//		CustomCurrencyFormatter customCurrencyFormatter = new CustomCurrencyFormatter(decimalMark, groupingSeparator, format);
//		binder.registerCustomEditor(BigDecimal.class, new CustomCurrencyEditor(customCurrencyFormatter));
		
		DecimalFormatSymbols decimalFormatSymbols;
		decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator(decimalMark.charAt(0));
		decimalFormatSymbols.setGroupingSeparator(groupingSeparator.charAt(0));
		//decimalFormatSymbols.setCurrencySymbol(currency);
		//decimalFormatSymbols.setMonetaryDecimalSeparator(sep)

		DecimalFormat numberFormat = new DecimalFormat("##.##", decimalFormatSymbols);
		numberFormat.setPositivePrefix(prefix);
		numberFormat.setPositiveSuffix(suffix);
		binder.registerCustomEditor(BigDecimal.class, "fee", new CustomNumberEditor(BigDecimal.class, numberFormat, true));
		
		//CustomCurrencyFormat  customCurrencyFormat = new CustomCurrencyFormat("", BigDecimal.class, "");
		//binder.registerCustomEditor(customCurrencyFormat.getType(), new CustomNumberEditor(customCurrencyFormat.getType(),customCurrencyFormat.getFormat(), true));
	}
}