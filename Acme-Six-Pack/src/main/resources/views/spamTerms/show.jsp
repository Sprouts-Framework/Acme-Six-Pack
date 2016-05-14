<%@ include file="../template/libraries.jsp" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />


<sprouts:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<sprouts:hidden-field path="id"/>
	<sprouts:hidden-field path="version"/>
	
	<sprouts:textarea-input code="spamTerms.spamTerms" path="terms"/>
	
	<jstl:if test="${crudAction == 'updating' }">
		<sprouts:submit-button code="update.button"/>
		<sprouts:cancel-button code="return.button" url="spamTerms/administrator/show.do" />
	</jstl:if>
	<jstl:if test="${crudAction == 'showing'}">
		<a href="spamTerms/administrator/update.do" class="btn btn-primary"><spring:message code="update.button"/></a>
	</jstl:if>
</sprouts:form>

