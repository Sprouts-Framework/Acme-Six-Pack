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


	<div class="fieldset-btm-margin">
			<acme:textbox-input code="customer.creditCard.holderName" path="holderName" />
			<acme:textbox-input code="customer.creditCard.brandName" path="brandName" />
			<acme:textbox-input code="customer.creditCard.number" path="number" />
			<acme:textbox-input code="customer.creditCard.expirationMonth" path="expirationMonth" />
			<acme:textbox-input code="customer.creditCard.expirationYear" path="expirationYear" />
			<acme:textbox-input code="customer.creditCard.cvvCode" path="cvvCode" />
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="profile/customer/show.do" />

</acme:form>

