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

	<acme:data-column code="serviceOfGym.name" path="name" sortable="true"/>
	
	<acme:data-column code="serviceOfGym.totalNumber" path="customersTotalNumber" sortable="true"/>
	
	<acme:action-button url="home/gym/services/{0}/list.do" code="serviceOfGym.displayGym"/>
	
	<security:authorize access="hasRole('Administrator')">
		<acme:action-button url="serviceEntity/administrator/{0}/delete.do" code="serviceOfGym.delete"/>
	</security:authorize>

</acme:data-table>

	<security:authorize access="hasRole('Administrator')">
		<br/>
		<br/>
		<a class="btn btn-default" href="serviceEntity/administrator/create.do"><spring:message code="serviceEntity.create"/></a>
	</security:authorize>

<br />
<br />
<%-- Para el dashboard --%>
<jstl:if test="${option=='2' }">
	<strong>
		<spring:message code="administrator.quantityMostPopularServices"/>: <jstl:out value="${quantity}"/>
	</strong>
</jstl:if>
<jstl:if test="${option=='3' }">
	<strong>
		<spring:message code="administrator.quantityLeastPopularServices"/>: <jstl:out value="${quantity}"/>
	</strong>
</jstl:if>