<%--
 * show.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="acmeSpecific" tagdir="/WEB-INF/tags/custom" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="customersTotalNumber"/>
		
		<acme:protected path="customersTotalNumber"/>
		
		<jstl:if test="${modelObject.id != 0}">
			<form:hidden path="serviceEntity"/>
			<form:hidden path="gym"/>
			<acme:protected path="serviceEntity"/>
			<acme:protected path="gym"/>
		</jstl:if>
	
		<jstl:if test="${modelObject.id == 0}">
			<acmeSpecific:select items="${serviceEntities}" itemLabel="name" code="serviceOfGym.serviceEntity" path="serviceEntity"/>
			
			<acmeSpecific:select items="${gyms}" itemLabel="name" code="serviceOfGym.gym" path="gym"/>
		</jstl:if>
		
		<acme:textarea-input code="serviceOfGym.description" path="description"/>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="./"/>

</acme:form>

