<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="acmeSpecific" tagdir="/WEB-INF/tags/custom" %>

	<spring:message code="gym.servicesLabel" var="servicesLabel"/>
	<spring:message code="gym.name" var="nameLabel"/>
	<spring:message code="gym.description" var="descriptionLabel"/>
	<spring:message code="gym.picture" var="pictureLabel"/>
	<spring:message code="gym.customersTotalNumber" var="customersTotalNumberLabel"/>
	<spring:message code="gym.service.customersTotalNumber" var="customersTotalNumberServiceLabel"/>
	<spring:message code="gym.postalAddress" var="postalAddressLabel"/>
	<spring:message code="gym.phoneNumber" var="phoneNumberLabel"/>
	
	<spring:message code="gym.feePayment.feePayments" var="feePaymentsLabel"/>
	<spring:message code="gym.feePayment.paymentMoment" var="paymentMomentLabel"/>
	<spring:message code="gym.feePayment.fee" var="feeLabel"/>
	<spring:message code="gym.feePayment.activationDay" var="activationDayLabel"/>
	<spring:message code="gym.feePayment.innactivationDay" var="innactivationDayLabel"/>
	
	<div class="text-center">
		<acmeSpecific:display-image-column src="${gym.picture}" />
		<acmeSpecific:display-column title="${gym.name}"/>
		<acmeSpecific:display-column data="${gym.description}"/>
		<acmeSpecific:display-column title="${feeLabel}" data="${gym.fee}" message="EUR"/>
		<acmeSpecific:display-column title="${postalAddressLabel}" message="${gym.postalAddress}" url="https://www.google.es/maps/search/${gym.postalAddress}"/>
		<acmeSpecific:display-column title="${phoneNumberLabel}" data="${gym.phoneNumber}"/>
		<acmeSpecific:display-column title="${customersTotalNumberLabel}" data="${gym.customersTotalNumber}"/>
	</div>
	
<br />
<security:authorize access="hasRole('Customer')">
	<a class="btn btn-default" href="feePayment/customer/${gym.id}/create.do"><spring:message code="gym.pay.fee" /></a>
</security:authorize>
<br />

		<h1><jstl:out value="${servicesLabel}"/></h1>
		<br/>
		<acme:data-table i18n="datatables.language" source="home/gym/serviceOfGym/${gym.id}/list/data.do">

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
		
		<br/>
		<security:authorize access="hasRole('Administrator')">
			<a href="serviceOfGym/administrator/${gym.id}/create.do" class="btn btn-default"><spring:message code="serviceOfGym.create"/></a>
			<br/>
		</security:authorize>
		
		<br/>
		
		<spring:message code="gym.comment.moment" var="momentLabel"/>
		<spring:message code="gym.comment.text" var="textLabel"/>
		<spring:message code="gym.comment.starRating" var="starRatingLabel"/>
		<spring:message code="gym.comment.author" var="authorLabel"/>
		
		
		
		<h3>
			<spring:message code="gym.comments" />
		</h3>
		
		<security:authorize access="hasRole('Customer')">
			<a href="comment/customer/${gym.id},0/create.do" class="btn btn-default"><spring:message code="gym.comment.create"/></a>
			<br/>
		</security:authorize>
		
		<security:authorize access="hasRole('Administrator')">
			<a href="comment/administrator/${gym.id},0/create.do" class="btn btn-default"><spring:message code="gym.comment.create"/></a>
			<br/>
		</security:authorize>
		<br/>
		<acme:data-table i18n="datatables.language" source="home/comment/gym/${gym.id}/list/data.do">
			<security:authorize access="hasRole('Administrator')">
				<acme:action-button url="comment/administrator/{0}/delete.do" code="gym.comment.delete"/>
			</security:authorize>
			<acme:data-column code="gym.comment.starRating" format="image" path="starRating" outFormat="/Acme-Six-Pack/images/starRatings/+PATHde3.png" imgSize="85x30" width="90px"/>
			<acme:data-column code="gym.comment.author" path="actor.userAccount.username"/>
			<acme:data-column code="gym.comment.moment" path="moment" format="date"/>
			<acme:data-column code="gym.comment.text" path="text"/>
			
		</acme:data-table>