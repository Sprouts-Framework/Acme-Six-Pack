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
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	<acme:protected path="customer" />
	
	<div class="fieldset-btm-margin">
		
		<fieldset>
			<acme:textbox-input code="feePayment.gymName" path="gym.name"/>
			<acme:textbox-input code="feePayment.fee" path="fee"/>
			<acme:moment-input code="feePayment.paymentMoment" path="paymentMoment"/>
			<acme:moment-input code="feePayment.activationDay" path="activationDay"/>
			<acme:moment-input code="feePayment.inactivationDay" path="inactivationDay"/>
			
		</fieldset>
	
		<fieldset>
			<legend>
				<spring:message code="feePayment.legend.creditCard" />
			</legend>
			<acme:textbox-input code="creditcard.holdername" path="creditCard.holderName" />
			<acme:textbox-input code="creditcard.brandname" path="creditCard.brandName" />
			<acme:textbox-input code="creditcard.number" path="creditCard.number" />
			<acme:textbox-input code="creditcard.expirationmonth" path="creditCard.expirationMonth" />
			<acme:textbox-input code="creditcard.expirationyear" path="creditCard.expirationYear" />
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="feePayment/customer/list.do" />

</acme:form>

