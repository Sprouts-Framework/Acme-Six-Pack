<%@ include file="../template/libraries.jsp" %>

<sprouts:data-table i18n="datatables.language"  searcheable="false">

	<sprouts:action-button url="home/serviceOfGym/{0}/show.do" code="gym.display"/>
	<security:authorize access="hasRole('Administrator')">
		<sprouts:action-button url="serviceOfGym/administrator/{0}/update.do" code="update.button"/>
		<sprouts:action-button url="serviceOfGym/administrator/{0}/delete.do" code="delete.button"/>
	</security:authorize>
	<security:authorize access="hasRole('Customer')">
		<jstl:if test="${hasFeePayment == true}">
			<sprouts:action-button url="booking/customer/{0}/create.do" code="gym.service.book"/>
		</jstl:if>
	</security:authorize>
	
	<sprouts:data-column code="gym.name" path="serviceEntity.name" width="20%"/>
	<sprouts:data-column code="gym.description" path="description" width="60%"/>
	<sprouts:data-column code="gym.service.customersTotalNumber" path="customersTotalNumber" width="20"/>		
</sprouts:data-table>

<br />
<br />
<%-- Para dashboard --%>
<jstl:if test="${option=='9' }">
	<strong>
		<spring:message code="administrator.quantityServicesThatHaveMoreComments"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>