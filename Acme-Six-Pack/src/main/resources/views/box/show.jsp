<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />
	<sprouts:hidden-field path="actor"/>
	<sprouts:hidden-field path="createdAt"/>
	<sprouts:hidden-field path="updatedAt"/>
	
	<sprouts:protected path="id"/>
	<sprouts:protected path="version"/>
	<sprouts:protected path="actor"/>
	<sprouts:protected path="createdAt"/>
	<sprouts:protected path="updatedAt"/>
	
	<sprouts:textbox-input code="box.name" path="name"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	
	
		<sprouts:cancel-button code="return.button" url="box/${actor}/list.do" />


</sprouts:form>