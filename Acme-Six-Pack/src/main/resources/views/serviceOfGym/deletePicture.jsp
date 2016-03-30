<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<form:form action="serviceOfGym/administrator/picture/${modelObject.serviceOfGymId},${modelObject.oldPictureId}/delete.do" readOnly="${readOnly}">	
	<spring:message code='serviceOfGym.gym.picture' var="picture"/>
	<jstl:out value="${picture}: "/><jstl:out value="${modelObject.newPicture}"/>
	<br/>
	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="home/serviceOfGym/${modelObject.serviceOfGymId}/show.do"/>
</form:form>

