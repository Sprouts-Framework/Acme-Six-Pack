package es.us.lsi.dp.controllers.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.core.contracts.Authorizable;
import es.us.lsi.dp.security.SecurityHandler;
import es.us.lsi.dp.utilities.ContextParser;
import es.us.lsi.dp.validation.contracts.Validable;

public abstract class GetController<D extends Validable, E extends Validable> extends BaseController implements Authorizable<E> {

	// Authorization -----------------------------------------------------------

	@Autowired
	protected SecurityHandler<E> security;

	// Initialisation ----------------------------------------------------------

	@PostConstruct
	protected final void setAuthorizable() {
		security.setAuthorizable(this);
	}

	// Responses ---------------------------------------------------------------

	@SuppressWarnings("unchecked")
	protected ModelAndView get(final Map<String, String> pathVariables, final E entity) {
		ModelAndView result;
		D domainObject;
		E entityDomainObject;

		domainObject = getObject(pathVariables, entity, ContextParser.parse(pathVariables));

		Assert.notNull(domainObject);

		entityDomainObject = entity;

		if (entity == null) {
			entityDomainObject = (E) domainObject;
			beforeAuthorization(entityDomainObject, ContextParser.parse(pathVariables));
		}

		security.authorize(entityDomainObject);

		result = currentView(domainObject, ContextParser.parse(pathVariables));

		return result;
	}

	// Alternative invocations -------------------------------------------------

	protected ModelAndView get(final Map<String, String> pathVariables) {
		return get(pathVariables, null);
	}

	// Helpers -----------------------------------------------------------------

	protected Integer entityId(final Map<String, String> pathVariables) {
		String pathVariable;
		Integer result;

		pathVariable = Collections.max(pathVariables.keySet());

		result = Integer.valueOf(pathVariables.get(pathVariable));

		return result;
	}

	protected D getCurrentVersion(final HttpServletRequest request) {
		return getObject(getPathVariables(request), null, ContextParser.parse(getPathVariables(request)));
	}

	// Template methods -------------------------------------------------------

	public abstract D getObject(Map<String, String> pathVariables, E entity, List<String> context);

}