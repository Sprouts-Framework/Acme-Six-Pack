<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sender"/>
	<form:hidden path="moment"/>
	<form:hidden path="recipient"/>
	<form:hidden path="subject"/>
	<form:hidden path="body"/>
	
	<sprouts:protected path="id"/>
	<sprouts:protected path="version"/>
	<sprouts:protected path="sender"/>
	<sprouts:protected path="moment"/>
	<sprouts:protected path="recipient"/>
	<sprouts:protected path="subject"/>
	<sprouts:protected path="body"/>
	
	<sproutsSpecific:select items="${boxes}" itemLabel="name" code="message.box" path="box"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	
	
	<sprouts:cancel-button code="return.button" url="box/${actor}/list.do" />
	

</sprouts:form>