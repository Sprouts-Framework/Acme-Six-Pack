<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="acmeCustom" tagdir="/WEB-INF/tags/custom"%>

<acmeCustom:button code="create.button" url="message/${actor}/create.do"/>
<br />

<acme:data-table i18n="datatables.language">
		<acme:action-button code="move.button" url="message/${actor}/{0}/update.do" />
		<acme:action-button code="delete.button" url="message/${actor}/{0}/delete.do" />
		<acme:action-button code="details.button" url="message/${actor}/{0}/show.do" />

	<acme:data-column code="message.sender" path="sender.userAccount.username" sortable="true" width="10%"/>
	<acme:data-column code="message.recipient" path="recipient.userAccount.username" sortable="true" width="10%"/>
	<acme:data-column code="message.moment" path="moment" sortable="true" width="20%"/>
	<acme:data-column code="message.subject" path="subject" sortable="true" width="60%"/>
	
</acme:data-table>
<br/>

		<acme:cancel-button url="box/${actor}/list.do" code="return.button"/>
	
