<%--
  * master.jsp
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

<spring:url var="baseUrl" value="/" />
<jstl:set var="locale" value="${pageContext.response.locale}" />
<jstl:set var="genericLocale" value="${pageContext.response.locale.language}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="${locale}">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
	
	<base href="${baseUrl}" />
	
	<link rel="shortcut icon" href="${baseUrl}favicon.ico" />
	
	<link rel="stylesheet" href="styles/bootstrap.min.css" />
	<link rel="stylesheet" href="styles/glyphicons.min.css" />
	<link rel="stylesheet" href="styles/datatables.min.css" />
	<link rel="stylesheet" href="styles/tabletools.min.css" />
	<link rel="stylesheet" href="styles/datetimepicker.min.css" />
	<link rel="stylesheet" href="styles/acme.css" />
	
	<script src="scripts/jquery.min.js"></script>
	<script src="scripts/bootstrap.min.js"></script>
	<script src="scripts/datatables.min.js"></script> 
	<script src="scripts/tabletools.min.js"></script>
	<script src="scripts/datetimepicker.min.js"></script>
	<script src="scripts/datetimepicker.${genericLocale}.min.js"></script>		
	<script src="scripts/acme.js"></script>
	
	<!--[if lt IE 9]>
		<script src="scripts/html5shiv.min.js"></script>
		<script src="scripts/respond.min.js"></script>
	<![endif]-->
	
	<script>
		$(document).ready(function() {
			$('.nav li.dropdown').hover(function() {
				$(this).addClass('open');
			}, function() {
				$(this).removeClass('open');
			});
		});
	</script>
	
	<title>
		<tiles:importAttribute name="title" toName="title" />
		<spring:message code="${title}" />
	</title>

</head>

<body class="container">

	<%@ include file="header.jsp" %>
	<%@ include file="body.jsp" %>
	<%@ include file="footer.jsp" %>

</body>
</html>