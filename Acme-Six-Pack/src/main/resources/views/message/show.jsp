<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />
	<jstl:if test="${crudAction != 'showing' }">
		<sprouts:hidden-field path="moment" />
		<sprouts:protected path="moment"/>
	</jstl:if>
	
	<jstl:if test="${crudAction == 'showing' }">
		<sprouts:textbox-input code="message.recipient" path="recipient.userAccount.username"/>
		<sprouts:textbox-input code="message.sender" path="sender.userAccount.username"/>
		<sprouts:textbox-input code="message.moment" path="moment"/>
	</jstl:if>
	<sprouts:textbox-input code="message.subject" path="subject"/>
	<sprouts:textarea-input code="message.body" path="body"/>
	<jstl:if test="${crudAction == 'creating'}">
		<sproutsSpecific:select items="${actors}" itemLabel="userAccount.username" code="message.recipient" path="recipient"/>
	</jstl:if>
	<jstl:if test="${crudAction != 'showing' }">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	
	<sprouts:cancel-button code="return.button" url="box/${actor}/list.do" />
	

</sprouts:form>