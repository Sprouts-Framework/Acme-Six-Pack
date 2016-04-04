<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp"%>


<acme:data-table i18n="datatables.language">

	<security:authorize access="hasRole('Administrator')">
		<acme:action-button code="update.button"
			url="gym/administrator/{0}/update.do" />
		<acme:action-button code="delete.button"
			url="gym/administrator/{0}/delete.do" />
	</security:authorize>

	<security:authorize access="hasRole('Customer')">
		<jstl:if test="${fromCustomer != null}">
			<acme:action-button code="gym.viewFeePayment"
				url="feePayment/customer/{0}/show.do" />
		</jstl:if>
		<acme:action-button code="feePayment.create"
			url="feePayment/customer/{0}/create.do" />
	</security:authorize>

	<acme:action-button code="show.button" url="home/gym/{0}/show.do" />

	<acme:data-column code="gym.picture" path="picture" format="image"
		imgSize="200x100" width="10%" />

	<acme:data-column code="gym.name" path="name" sortable="true"
		width="10%" />

	<acme:data-column code="gym.description" path="description" width="25%" />

	<acme:data-column code="gym.postalAddress" path="postalAddress"
		toShow="https://www.google.es/maps/search/+PATH" format="url"
		outFormat="+PATH" width="20%" />

	<acme:data-column code="gym.phoneNumber" path="phoneNumber" width="15%" />

	<acme:data-column code="gym.fee" path="fee" sortable="true" width="5%"
		format="price" />

	<acme:data-column code="gym.customersTotalNumber"
		path="customersTotalNumber" sortable="true" width="15%" />


</acme:data-table>

<br />
<br />
<security:authorize access="hasRole('Administrator')">
	<jstl:if test="${option == null}">
		<acmeSpecific:button url="gym/administrator/create.do"
			code="gym.create" />
	</jstl:if>
</security:authorize>

<br />
<br />
<%-- Para el dashboard --%>
<jstl:if test="${option=='0' }">
	<strong> <spring:message
			code="administrator.quantityMostPopularGyms" />: <jstl:out
			value="${quantity}" />
	</strong>
</jstl:if>
<jstl:if test="${option=='1' }">
	<strong> <spring:message
			code="administrator.quantityLeastPopularGyms" />: <jstl:out
			value="${quantity}" />
	</strong>
</jstl:if>
<jstl:if test="${option=='8' }">
	<strong> <spring:message
			code="administrator.quantityGymsThatHaveMoreComments" />: <jstl:out
			value="${quantity}" />
	</strong>
</jstl:if>
