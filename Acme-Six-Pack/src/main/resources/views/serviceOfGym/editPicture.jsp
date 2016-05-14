<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="oldPictureId"/>
	<sprouts:hidden-field path="serviceOfGymId"/>
	
	<sprouts:protected path="oldPictureId"/>
	<sprouts:protected path="serviceOfGymId"/>
	
	<sprouts:textbox-input code="serviceOfGym.gym.picture" path="newPicture"/>	

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="home/serviceOfGym/${modelObject.serviceOfGymId}/show.do"/>

</sprouts:form>

