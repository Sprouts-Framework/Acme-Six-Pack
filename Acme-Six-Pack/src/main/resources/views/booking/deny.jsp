<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<form:form action="booking/administrator/${modelObject.id}/deny.do">	
	<spring:message code='booking.confirm.deny' var="deny"/>
	<jstl:out value="${deny}"/>
	<br/>
	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="booking/administrator/pending/list.do"/>
</form:form>

