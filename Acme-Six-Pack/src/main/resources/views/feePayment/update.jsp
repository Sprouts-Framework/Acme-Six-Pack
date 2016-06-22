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
	<sprouts:hidden-field path="activationDay"/>
	<sprouts:hidden-field path="paymentMoment"/>
	<sprouts:hidden-field path="creditCard.brandName"/>
	<sprouts:hidden-field path="creditCard.holderName"/>
	<sprouts:hidden-field path="creditCard.number"/>
	<sprouts:hidden-field path="creditCard.expirationMonth"/>
	<sprouts:hidden-field path="creditCard.expirationYear"/>
	<sprouts:hidden-field path="creditCard.cvvCode"/>
	<sprouts:hidden-field path="gym"/>
	
	<sprouts:protected path="id" />
	<sprouts:protected path="version" />
	<sprouts:protected path="customer" />
	<sprouts:protected path="fee" />
	<sprouts:protected path="activationDay" />
	<sprouts:protected path="creditCard.brandName"/>
	<sprouts:protected path="creditCard.holderName"/>
	<sprouts:protected path="creditCard.number"/>
	<sprouts:protected path="creditCard.expirationMonth"/>
	<sprouts:protected path="creditCard.expirationYear"/>
	<sprouts:protected path="creditCard.cvvCode"/>
	<sprouts:protected path="paymentMoment" />
	<sprouts:protected path="gym" />
	
	<div class="fieldset-btm-margin">
		
		<fieldset>
			<sprouts:moment-input code="feePayment.activationDay" path="activationDay" readonly="true"/>
			<sprouts:moment-input code="feePayment.inactivationDay" path="inactivationDay"/>
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="feePayment/administrator/list.do" />

</sprouts:form>

