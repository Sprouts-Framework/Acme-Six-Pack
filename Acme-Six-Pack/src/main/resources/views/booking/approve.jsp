<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<form:form action="booking/administrator/${modelObject.id}/approve.do">	
	<spring:message code='booking.confirm.approve' var="approve"/>
	<jstl:out value="${approve}"/>
	<br/>
	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="booking/administrator/pending/list.do"/>
</form:form>

