<%--
 * list.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>


 
<acme:data-table i18n="datatables.language" >
	<jstl:if test="${option=='7' }">
		<acme:action-button url="dashboard/administrator/actor/${option},{0}/list.do" code="actor.displayAverage"/>
	</jstl:if>
	<acme:data-column code="actor.name" path="name" sortable="true"/>
	<acme:data-column code="actor.surname" path="surname" sortable="true"/>
	<acme:data-column code="actor.username" path="userAccount.username" sortable="true"/>
	<acme:data-column code="actor.contactPhone" path="contactPhone" sortable="true"/>
</acme:data-table>

<br />
<br />
<%-- Para el dashboard --%>
<jstl:if test="${option=='4' }">
	<strong>
		<spring:message code="administrator.customersWhoHavePaidMoreFees"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
<jstl:if test="${option=='5' }">
	<strong>
		<spring:message code="administrator.customersWhoHavePaidLessFees"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
<jstl:if test="${option=='6' }">
	<strong>
		<spring:message code="administrator.quantityActorsWhoSendMoreSpamMessages"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
<jstl:if test="${option=='7' }">
	<jstl:if test="${actorUsername != null }">
		<strong>
			<spring:message code="actor.averageInfo"/> <jstl:out value=" ${actorUsername } "/> <spring:message code="actor.is"/> <jstl:out value="${averageNumberOfMessagesOfAnActor }"/>
		</strong>
	<br />
	</jstl:if>
</jstl:if>
<jstl:if test="${option=='13' }">
	<strong>
		<spring:message code="administrator.quantityCustomersWhoHaveRemovedMoreComments"/>: <jstl:out value="${quantity}"/>
	</strong>	
</jstl:if>
