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

<jstl:if test="${error!=null}">
	<spring:message code="profile.socialAccount.error" var="errorMessage"/>

	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<jstl:out value="${errorMessage}"/>
	</div>
</jstl:if>

<acme:form modelAttribute="modelObject" readOnly="${readOnly}">

	<acme:hidden-field path="id" />
	<acme:hidden-field path="version" />
	
	<acme:protected path="id" />
	<acme:protected path="version" />
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="customer.account-info" />
			</legend>
			<acme:textbox-input code="customer.name" path="name" />
			<acme:textbox-input code="customer.surname" path="surname" />
			<acme:textbox-input code="customer.contactPhone" path="contactPhone" />
			<acme:textbox-input code="customer.userAccount.username" path="userAccount.username" />
			
			<jstl:if test="${modelObject.userAccount.socialAccounts == null}">
				<a class="btn btn-default" href="profile/userAccount/update.do"><spring:message code="customer.userAccount-update" /></a>
			</jstl:if>
			
			

			<a class="btn btn-default" href="profile/customer/update.do"><spring:message code="customer.profile-update" /></a>
			
		</fieldset>
	</div>
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="customer.creditCard-info" />
			</legend>
			<acme:textbox-input code="customer.creditCard.holderName" path="creditCard.holderName" />
			<acme:textbox-input code="customer.creditCard.brandName" path="creditCard.brandName" />
			<acme:textbox-input code="customer.creditCard.number" path="creditCard.number" />
			<acme:textbox-input code="customer.creditCard.expirationMonth" path="creditCard.expirationMonth" />
			<acme:textbox-input code="customer.creditCard.expirationYear" path="creditCard.expirationYear" />
			
			<jstl:choose>
				<jstl:when test="${modelObject.creditCard.number == null}">
					<a class="btn btn-default" href="creditCard/customer/${modelObject.id}/create.do"><spring:message code="customer.creditCard-create" /></a>
				</jstl:when>
				<jstl:otherwise>
					<a class="btn btn-default" href="creditCard/customer/${modelObject.id}/update.do"><spring:message code="customer.creditCard-update" /></a>
					<a class="btn btn-default" href="creditCard/customer/${modelObject.id}/delete.do"><spring:message code="customer.creditCard-delete" /></a>
				</jstl:otherwise>
			</jstl:choose>
			
			
		</fieldset>
	</div>
	
	<div class="fieldset-btm-margin">
		<fieldset>
			<legend>
				<spring:message code="customer.socialIdentity-info" />
			</legend>
			
			<jstl:if test="${crudAction == 'showing' && modelObject.socialIdentity != null}">
				<acmeSpecific:display-image-column src="${modelObject.socialIdentity.picture}"/>
			</jstl:if>
			
			<jstl:if test="${crudAction == 'showing' && modelObject.socialIdentity != null}">
				<acmeSpecific:display-column url="${modelObject.socialIdentity.homePage}" title="${homePage}" message="${modelObject.socialIdentity.homePage}"/>
			</jstl:if>
			
			<acme:textbox-input code="customer.socialIdentity.nick" path="socialIdentity.nick" />
			<acme:textbox-input code="customer.socialIdentity.socialNetwork" path="socialIdentity.socialNetwork" />
			
			<spring:message code="customer.socialIdentity.homePage" var="homePage"/>
			
			<jstl:choose>
				<jstl:when test="${modelObject.socialIdentity.id == 0 }">
					<a class="btn btn-default" href="socialIdentity/customer/create.do"><spring:message code="customer.socialIdentity-create" /></a>
				</jstl:when>
				<jstl:otherwise>
					<a class="btn btn-default" href="socialIdentity/customer/${modelObject.socialIdentity.id }/update.do"><spring:message code="customer.socialIdentity-update" /></a>
					<a class="btn btn-default" href="socialIdentity/customer/${modelObject.socialIdentity.id }/delete.do"><spring:message code="customer.socialIdentity-delete" /></a>
				</jstl:otherwise>
			</jstl:choose>
			
		</fieldset>
	</div>

	<jstl:if test="${crudAction != 'showing'}">
		<acme:submit-button code="${action}" name="${action}" />
	</jstl:if>
	<acme:cancel-button code="return.button" url="" />

</acme:form>
<br/>
<div class="row">
<div class="col-xs-12 col-md-3 col-sm-4">
<spring:message code="authorize.twitter.add" var="twitter"/>
	<form id="tw_signin" action="<jstl:url value="/signin/twitter.do"/>" method="POST">
		  <button type="submit" class="btn btn-twitter">
		    <i class="fa fa-twitter"></i> | <jstl:out value="${twitter}"/>
		  </button>
	</form>
</div>

<div class="col-xs-12 col-md-3 col-sm-4">
	<spring:message code="authorize.google.add" var="google"/>
	<form id="google_signin" action="<jstl:url value="/signin/google.do"/>" method="POST">
		  <button type="submit" class="btn btn-google-plus">
		    <i class="fa fa-google"></i> | <jstl:out value="${google}"/>
		  </button>
	</form>
</div>
</div>

