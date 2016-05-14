<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />
	
	<sprouts:protected path="id" />
	<sprouts:protected path="version" />
	
	<sprouts:textbox-input code="gym.name" path="name"/>
	<sprouts:textarea-input code="gym.description" path="description"/>
	<sprouts:textbox-input code="gym.postalAddress" path="postalAddress"/>
	<sprouts:textbox-input code="gym.phoneNumber" path="phoneNumber"/>
	<sprouts:textbox-input code="gym.fee" path="fee"/>
	<sprouts:textbox-input code="gym.picture" path="picture"/>

	<sprouts:submit-button code="${action}" name="${action}" />
	<sprouts:cancel-button code="return.button" url="home/gym/list.do" />

</sprouts:form>