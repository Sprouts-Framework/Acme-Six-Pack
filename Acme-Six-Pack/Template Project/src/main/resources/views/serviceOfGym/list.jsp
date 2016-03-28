<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security"	uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<acme:data-table i18n="datatables.language">

	<acme:action-button url="home/serviceOfGym/{0}/show.do" code="gym.display"/>
	<security:authorize access="hasRole('Administrator')">
		<acme:action-button url="serviceOfGym/administrator/{0}/update.do" code="update.button"/>
		<acme:action-button url="serviceOfGym/administrator/{0}/delete.do" code="delete.button"/>
	</security:authorize>
	<security:authorize access="hasRole('Customer')">
		<jstl:if test="${hasFeePayment == true}">
			<acme:action-button url="booking/customer/{0}/create.do" code="gym.service.book"/>
		</jstl:if>
	</security:authorize>
	
	<acme:data-column code="gym.name" path="serviceEntity.name" width="20%"/>
	<acme:data-column code="gym.description" path="description" width="60%"/>
	<acme:data-column code="gym.service.customersTotalNumber" path="customersTotalNumber" width="20"/>		
</acme:data-table>

<br />
<br />
<%-- Para dashboard --%>
<jstl:if test="${option=='9' }">
	<strong>
		<spring:message code="administrator.quantityServicesThatHaveMoreComments"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>