<%@page import="java.util.Locale"%>
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
	
	<security:authorize access="hasRole('Administrator')">
		<acme:data-column code="feePayment.customer.name" path="customer.name" sortable="true"/>
		<acme:data-column code="feePayment.customer.surname" path="customer.surname" sortable="true"/>
	</security:authorize>
	
	<acme:data-column code="feePayment.gymName" path="gym.name" sortable="true"/>
	
	<acme:data-column code="feePayment.paymentMoment" path="paymentMoment" sortable="true" format="date"/>
	
	<acme:data-column code="feePayment.activationDay" path="activationDay" sortable="true" format="date"/>
	
	<acme:data-column code="feePayment.inactivationDay" path="inactivationDay" sortable="true" format="date"/>
	

	<security:authorize access="hasRole('Administrator')">
		<acme:action-button url="feePayment/administrator/{0}/update.do" code="serviceOfGym.update-inactivation-day"/>
	</security:authorize>
	
</acme:data-table>