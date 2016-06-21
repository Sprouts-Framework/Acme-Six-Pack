<%@ include file="../template/libraries.jsp" %>

<sprouts:data-table i18n="datatables.language"  searcheable="false">
	
	<security:authorize access="hasRole('Administrator')">
		<sprouts:data-column code="feePayment.customer.name" path="customer.name" sortable="true"/>
		<sprouts:data-column code="feePayment.customer.surname" path="customer.surname" sortable="true"/>
	</security:authorize>
	
	<sprouts:data-column code="feePayment.gymName" path="gym.name" sortable="true"/>
	
	<sprouts:data-column code="feePayment.paymentMoment" path="paymentMoment" sortable="true" format="date"/>
	
	<sprouts:data-column code="feePayment.activationDay" path="activationDay" sortable="true" format="date"/>
	
	<sprouts:data-column code="feePayment.inactivationDay" path="inactivationDay" sortable="true" format="date"/>
	

	<security:authorize access="hasRole('Administrator')">
		<sprouts:action-button url="feePayment/administrator/{0}/update.do" code="serviceOfGym.update-inactivation-day"/>
	</security:authorize>
	
</sprouts:data-table>