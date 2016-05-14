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

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="customersTotalNumber"/>
		
		<sprouts:protected path="customersTotalNumber"/>
		
		<jstl:if test="${modelObject.id != 0}">
			<form:hidden path="serviceEntity"/>
			<form:hidden path="gym"/>
			<sprouts:protected path="serviceEntity"/>
			<sprouts:protected path="gym"/>
		</jstl:if>
	
		<jstl:if test="${modelObject.id == 0}">
			<sproutsSpecific:select items="${serviceEntities}" itemLabel="name" code="serviceOfGym.serviceEntity" path="serviceEntity"/>
			
			<sproutsSpecific:select items="${gyms}" itemLabel="name" code="serviceOfGym.gym" path="gym"/>
		</jstl:if>
		
		<sprouts:textarea-input code="serviceOfGym.description" path="description"/>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="./"/>

</sprouts:form>

