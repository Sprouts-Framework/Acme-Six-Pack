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

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />
	<sprouts:hidden-field path="customer" />
	<sprouts:hidden-field path="fee"/>
	<sprouts:hidden-field path="inactivationDay"/>
	<sprouts:hidden-field path="paymentMoment"/>
	<sprouts:hidden-field path="gym"/>
	
	<sprouts:protected path="id" />
	<sprouts:protected path="version" />
	<sprouts:protected path="customer" />
	<sprouts:protected path="fee" />
	<sprouts:protected path="inactivationDay" />
	<sprouts:protected path="paymentMoment" />
	<sprouts:protected path="gym" />
	
	<div class="fieldset-btm-margin">
		
		<fieldset>
			<sprouts:textbox-input code="feePayment.gymName" path="gym.name" readonly="true"/>
			<sprouts:textbox-input code="feePayment.fee" path="fee" readonly="true"/>
			<sprouts:moment-input code="feePayment.activationDay" path="activationDay"/>
		</fieldset>
	
		<fieldset>
			<legend>
				<spring:message code="feePayment.legend.creditCard" />
			</legend>
			<sprouts:textbox-input code="creditcard.holdername" path="creditCard.holderName" />
			<sprouts:textbox-input code="creditcard.brandname" path="creditCard.brandName" />
			<sprouts:textbox-input code="creditcard.number" path="creditCard.number" />
			<sprouts:textbox-input code="creditcard.expirationmonth" path="creditCard.expirationMonth" />
			<sprouts:textbox-input code="creditcard.expirationyear" path="creditCard.expirationYear" />
			<sprouts:textbox-input code="creditcard.cvvcode" path="creditCard.cvvCode" />
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="pfeePayment/customer/list.do" />

</sprouts:form>

