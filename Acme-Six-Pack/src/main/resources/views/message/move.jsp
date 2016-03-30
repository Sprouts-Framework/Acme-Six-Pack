<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sender"/>
	<form:hidden path="moment"/>
	<form:hidden path="recipient"/>
	<form:hidden path="subject"/>
	<form:hidden path="body"/>
	
	<acme:protected path="id"/>
	<acme:protected path="version"/>
	<acme:protected path="sender"/>
	<acme:protected path="moment"/>
	<acme:protected path="recipient"/>
	<acme:protected path="subject"/>
	<acme:protected path="body"/>
	
	<acmeSpecfic:select items="${boxes}" itemLabel="name" code="message.box" path="box"/>

	<acme:submit-button code="${action}" name="${action}" />
	
	
	<acme:cancel-button code="return.button" url="box/${actor}/list.do" />
	

</acme:form>