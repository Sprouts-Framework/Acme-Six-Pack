<%--
 * show.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	<acme:hidden-field path="customer" />
	<acme:hidden-field path="fee"/>
	<acme:hidden-field path="inactivationDay"/>
	<acme:hidden-field path="paymentMoment"/>
	<acme:hidden-field path="gym"/>
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	<acme:protected path="customer" />
	<acme:protected path="fee" />
	<acme:protected path="inactivationDay" />
	<acme:protected path="paymentMoment" />
	<acme:protected path="gym" />
	
	<div class="fieldset-btm-margin">
		
		<fieldset>
			<acme:textbox-input code="feePayment.gymName" path="gym.name" readonly="true"/>
			<acme:textbox-input code="feePayment.fee" path="fee" readonly="true"/>
			<acme:moment-input code="feePayment.activationDay" path="activationDay"/>
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
			<acme:textbox-input code="creditcard.cvvcode" path="creditCard.cvvCode" />
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="pfeePayment/customer/list.do" />

</acme:form>

