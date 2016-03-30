<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="oldPictureId"/>
	<acme:hidden-field path="serviceOfGymId"/>
	
	<acme:protected path="oldPictureId"/>
	<acme:protected path="serviceOfGymId"/>
	
	<acme:textbox-input code="serviceOfGym.gym.picture" path="newPicture"/>	

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="home/serviceOfGym/${modelObject.serviceOfGymId}/show.do"/>

</acme:form>

