<%--
  * body.jsp
  *
  * Copyright (C) 2013 Universidad de Sevilla
  * 
  * The use of this project is hereby constrained to the conditions of the 
  * TDG Licence, a copy of which you may download from 
  * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<jstl:set var="successCode" value="${paramValues['successCode'][0]}" />
<jstl:set var="successArgument" value="${paramValues['successArgument'][0]}" />

<jstl:if test="${successCode != null}">
	<acme:alert code="${successCode}" arguments="${successArgument}" type="alert-success" />		
</jstl:if>

<div class="panel">
	<div class="panel-heading">
		<h1>
			<tiles:importAttribute name="title" toName="title" />
			<spring:message code="${title}" />
		</h1>
	</div>
	<div class="panel-body">
		<div>
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</div>

