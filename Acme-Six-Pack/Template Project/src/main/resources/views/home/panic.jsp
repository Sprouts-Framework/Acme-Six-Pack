<%--
 * panic.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" errorPage="true"
	import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<%
	Log log;

	log = LogFactory.getLog(getClass());
	log.error(String.format("HTTP error %d while fetching %s", pageContext.getErrorData().getStatusCode(), pageContext.getErrorData().getRequestURI()));
	if (pageContext.getErrorData() != null && pageContext.getErrorData().getThrowable() != null) {
		log.fatal(pageContext.getErrorData().getThrowable());
	}
%>

<p>
	<spring:message code="panic.sorry" />
</p>

<dl class="dl-horizontal">
	<dt>
		<spring:message code="panic.resource" />
	</dt>
	<dd>
		<jstl:out value="${pageContext.errorData.requestURI}" />
	</dd>

	<dt>
		<spring:message code="panic.status" />
	</dt>
	<dd>
		<jstl:out value="${pageContext.errorData.statusCode}" />
	</dd>

	<jstl:if test="${pageContext.errorData.throwable != null}">
		<dt>
			<spring:message code="panic.exceptions" />
		</dt>
		<dd>
			<jstl:set var="current" value="${pageContext.errorData.throwable}" />
			<jstl:forEach var="i" begin="0" end="25">
				<jstl:if test="${current != null}">
					<div class="exception-block">
						<jstl:out value="${current.stackTrace[0]}" />						
						<div class="inner-exception-block"><jstl:out value="${current}" /></div>						
					</div>
					<jstl:set var="current" value="${current.cause}" />
				</jstl:if>
			</jstl:forEach>
		</dd>
	</jstl:if>
</dl>

<p>
	<a href="<spring:url value='/' />" class="btn btn-primary">
		<spring:message	code="panic.return" />
	</a>
<p>