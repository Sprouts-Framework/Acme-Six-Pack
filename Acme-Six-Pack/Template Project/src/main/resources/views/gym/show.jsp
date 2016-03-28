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

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	
	<acme:textbox-input code="gym.name" path="name"/>
	<acme:textarea-input code="gym.description" path="description"/>
	<acme:textbox-input code="gym.postalAddress" path="postalAddress"/>
	<acme:textbox-input code="gym.phoneNumber" path="phoneNumber"/>
	<acme:textbox-input code="gym.fee" path="fee"/>
	<acme:textbox-input code="gym.picture" path="picture"/>

	<acme:submit-button code="${action}" name="${action}" />
	<acme:cancel-button code="return.button" url="home/gym/list.do" />

</acme:form>