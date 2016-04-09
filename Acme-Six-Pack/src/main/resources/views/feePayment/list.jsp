<%@ include file="../template/libraries.jsp" %>

<acme:data-table i18n="datatables.language">
	
	<security:authorize access="hasRole('Administrator')">
		<acme:data-column code="feePayment.customer.name" path="customer.name" sortable="true"/>
		<acme:data-column code="feePayment.customer.surname" path="customer.surname" sortable="true"/>
	</security:authorize>
	
	<acme:data-column code="feePayment.gymName" path="gym.name" sortable="true"/>
	
	<acme:data-column code="feePayment.paymentMoment" path="paymentMoment" sortable="true" format="date" outFormat="feePayment.date"/>
	
	<acme:data-column code="feePayment.activationDay" path="activationDay" sortable="true" format="date"/>
	
	<acme:data-column code="feePayment.inactivationDay" path="inactivationDay" sortable="true" format="date"/>
	

	<security:authorize access="hasRole('Administrator')">
		<acme:action-button url="feePayment/administrator/{0}/update.do" code="serviceOfGym.update-inactivation-day"/>
	</security:authorize>
	
</acme:data-table>