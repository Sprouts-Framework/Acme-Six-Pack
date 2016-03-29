<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags/template" %>

<tiles:importAttribute name="readOnly" toName="readOnly" />
<tiles:importAttribute name="action" toName="action" />


<acme:form modelAttribute="modelObject" readOnly="${readOnly}">
	
	<acme:hidden-field path="id"/>
	<acme:hidden-field path="version"/>
	
	<acme:textarea-input code="spamTerms.spamTerms" path="terms"/>
	
	<jstl:if test="${crudAction == 'updating' }">
		<acme:submit-button code="update.button"/>
		<acme:cancel-button code="return.button" url="spamTerms/administrator/show.do" />
	</jstl:if>
	<jstl:if test="${crudAction == 'showing'}">
		<a href="spamTerms/administrator/update.do" class="btn btn-primary"><spring:message code="update.button"/></a>
	</jstl:if>
</acme:form>

