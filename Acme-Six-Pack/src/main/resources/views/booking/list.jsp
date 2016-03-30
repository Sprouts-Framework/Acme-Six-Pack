<%@ include file="../template/libraries.jsp" %>


<acme:data-table i18n="datatables.language" tableId="row" idProperty="row">

	<spring:message code="booking.approved" var="approved"/>
	<spring:message code="booking.denied" var="denied"/>
	<acme:data-column code="booking.creationMoment" path="creationMoment" format="date" sortable="true"/>
	<acme:data-column code="booking.requestedMoment" path="requestedMoment" format="date" sortable="true"/>
	<acme:data-column code="booking.duration" path="duration"/>
	<acme:data-column code="booking.hasBeenApproved" path="hasBeenApproved" nullCode="booking.pending" falseCode="booking.denied" trueCode="booking.approved"/>
	<acme:data-column code="booking.expiration" path="endMoment" format="date"/>
	<acme:data-column code="booking.serviceOfGym" path="serviceOfGym.serviceEntity.name"/>
	<acme:data-column code="booking.serviceOfGym.gym" path="serviceOfGym.gym.name"/>
	
	<security:authorize access="hasRole('Customer')">
		<acme:data-column path="administrator.userAccount.username" code="booking.administrator"/>
		<acme:action-button code="booking.cancel" url="booking/customer/{0}/delete.do"/>
	</security:authorize>
	<security:authorize access="hasRole('Administrator')">
		<acme:data-column path="customer.userAccount.username" code="booking.customer"/>
		<jstl:if test="${requestURI == 'booking/administrator/pending/list.do' }">
			<acme:action-button code="booking.approve" url="booking/administrator/{0}/approve.do"/>
			<acme:action-button code="booking.deny" url="booking/administrator/{0}/deny.do"/>
		</jstl:if>
	</security:authorize>

</acme:data-table>