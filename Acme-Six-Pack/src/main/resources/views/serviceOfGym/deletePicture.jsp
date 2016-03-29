<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<form:form action="serviceOfGym/administrator/picture/${modelObject.serviceOfGymId},${modelObject.oldPictureId}/delete.do" readOnly="${readOnly}">	
	<spring:message code='serviceOfGym.gym.picture' var="picture"/>
	<jstl:out value="${picture}: "/><jstl:out value="${modelObject.newPicture}"/>
	<br/>
	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="home/serviceOfGym/${modelObject.serviceOfGymId}/show.do"/>
</form:form>

