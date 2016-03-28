<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="acmeSpecfic" tagdir="/WEB-INF/tags/custom"%>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	<jstl:if test="${crudAction != 'showing' }">
		<acme:hidden-field path="moment" />
		<acme:protected path="moment"/>
	</jstl:if>
	
	<jstl:if test="${crudAction == 'showing' }">
		<acme:textbox-input code="message.recipient" path="recipient.userAccount.username"/>
		<acme:textbox-input code="message.sender" path="sender.userAccount.username"/>
		<acme:textbox-input code="message.moment" path="moment"/>
	</jstl:if>
	<acme:textbox-input code="message.subject" path="subject"/>
	<acme:textarea-input code="message.body" path="body"/>
	<jstl:if test="${crudAction == 'creating'}">
		<acmeSpecfic:select items="${actors}" itemLabel="userAccount.username" code="message.recipient" path="recipient"/>
	</jstl:if>
	<jstl:if test="${crudAction != 'showing' }">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	
	<acme:cancel-button code="return.button" url="box/${actor}/list.do" />
	

</acme:form>