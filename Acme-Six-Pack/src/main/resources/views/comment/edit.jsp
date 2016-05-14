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

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

	<sprouts:hidden-field path="id" />
	<sprouts:hidden-field path="version" />
	<sprouts:hidden-field path="actor" />
	<sprouts:hidden-field path="isDeleted" />
	<sprouts:hidden-field path="moment" />
	
	<sprouts:protected path="id" />
	<sprouts:protected path="version" />
	<sprouts:protected path="actor" />
	<sprouts:protected path="isDeleted" />
	<sprouts:protected path="moment" />
	
	<sprouts:textbox-input code="comment.author.info" path="actor.userAccount.username" readonly="true"/>
	<sprouts:textarea-input code="comment.text" path="text"/>
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
	<sprouts:submit-button code="${action}" name="${action}" />
	
	<jstl:choose>
		<jstl:when test="${gymId!=0 }">
				<sprouts:cancel-button code="return.button" url="home/gym/${gymId}/show.do" />
		</jstl:when>
		<jstl:otherwise>
			<sprouts:cancel-button code="return.button" url="home/serviceOfGym/${serviceOfGymId}/show.do" />
		</jstl:otherwise>
	</jstl:choose>
	


</sprouts:form>

