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
	<sprouts:hidden-field path="userAccount" />
	<sprouts:hidden-field path="comments" />
	
	<sprouts:protected path="id" />
	<sprouts:protected path="version" />
	<sprouts:protected path="userAccount" />
	<sprouts:protected path="comments" />
	
	<div class="fieldset-btm-margin">
		<sprouts:textbox-input code="customer.name" path="name" />
		<sprouts:textbox-input code="customer.surname" path="surname" />
		<sprouts:textbox-input code="customer.contactPhone" path="contactPhone" />
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="profile/customer/show.do" />

</sprouts:form>

