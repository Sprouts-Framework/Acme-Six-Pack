<%--
 * show.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />

<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">

		<jstl:if test="${crudAction == 'creating'}">
			<form:hidden path="serviceOfGymId"/>
			
			<sprouts:protected path="serviceOfGymId"/>
		</jstl:if>
		<jstl:if test="${crudAction == 'deleting' }">
			<form:hidden path="id"/>
			<form:hidden path="version"/>
		</jstl:if>
		
		<sprouts:moment-input code="booking.requestedMoment" path="requestedMoment"/>
		
		<div class="form-group">
			<div class="row">
				<form:label class="col-sm-2 control-label" path="duration"><spring:message code="booking.duration"/></form:label>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<form:select class="form-control" path="duration">
						<form:option label="0.5" value="0.5"/>
						<form:option label="1" value="1.0"/>
						<form:option label="1.5" value="1.5"/>
						<form:option label="2" value="2.0"/>
						<form:option label="2.5" value="2.5"/>
						<form:option label="3" value="3.0"/>
						<form:option label="3.5" value="3.5"/>
						<form:option label="4" value="4.0"/>
					</form:select>
				</div>
			</div>
			<form:errors class="alert alert-danger col-sm-3" path="duration"/>
			<br/>
		</div>

	<jstl:if test="${crudAction != 'showing'}">
		<sprouts:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<sprouts:cancel-button code="return.button" url="booking/customer/list.do"/>

</sprouts:form>

