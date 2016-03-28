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

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	<acme:hidden-field path="customer" />
	<acme:hidden-field path="fee"/>
	<acme:hidden-field path="activationDay"/>
	<acme:hidden-field path="paymentMoment"/>
	<acme:hidden-field path="creditCard"/>
	<acme:hidden-field path="gym"/>
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	<acme:protected path="customer" />
	<acme:protected path="fee" />
	<acme:protected path="activationDay" />
	<acme:protected path="creditCard" />
	<acme:protected path="paymentMoment" />
	<acme:protected path="gym" />
	
	<div class="fieldset-btm-margin">
		
		<fieldset>
			<acme:moment-input code="feePayment.activationDay" path="activationDay" readonly="true"/>
			<acme:moment-input code="feePayment.inactivationDay" path="inactivationDay"/>
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="feePayment/administrator/list.do" />

</acme:form>

