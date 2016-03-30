<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	<acme:hidden-field path="actor" />
	<acme:hidden-field path="isDeleted" />
	<acme:hidden-field path="moment" />
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	<acme:protected path="actor" />
	<acme:protected path="isDeleted" />
	<acme:protected path="moment" />
	
	<acme:textbox-input code="comment.author.info" path="actor.userAccount.username" readonly="true"/>
	<acme:textarea-input code="comment.text" path="text"/>
	<div class="form-group">
		<div class="row">
			<form:label class="col-md-12" path="starRating">
				<spring:message code="comment.starRating"/>
			</form:label>
		</div>
		<div class="row">
			<div class="col-md-3">
				<form:select class="form-control" id="ratings" path="starRating">
					<form:option value="3" label="3/3" />
					<form:option value="2" label="2/3" />
					<form:option value="1" label="1/3" />
					<form:option value="0" label="0/3" />
				</form:select>
			</div>
		</div>
		<form:errors path="starRating" class="text-danger" />
	</div>
	<br />
	<br />
	<br />
	<acme:submit-button code="${action}" name="${action}" />
	
	<jstl:choose>
		<jstl:when test="${gymId!=0 }">
				<acme:cancel-button code="return.button" url="home/gym/${gymId}/show.do" />
		</jstl:when>
		<jstl:otherwise>
			<acme:cancel-button code="return.button" url="home/serviceOfGym/${serviceOfGymId}/show.do" />
		</jstl:otherwise>
	</jstl:choose>
	


</acme:form>

