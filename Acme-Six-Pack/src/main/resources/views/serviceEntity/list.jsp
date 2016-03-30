<%@ include file="../template/libraries.jsp" %>

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