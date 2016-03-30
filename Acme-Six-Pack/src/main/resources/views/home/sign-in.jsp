
<%--
 * sign-in.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<acme:form modelAttribute="credentials" action="j_spring_security_check" >

	<acme:textbox-input code="sign-in.username" path="username" />
	<acme:password-input code="sign-in.password" path="password" />

	<acme:submit-button code="sign-in.sign-in" />
	<acme:cancel-button code="sign-in.cancel" url="home/welcome.do" />

</acme:form>
