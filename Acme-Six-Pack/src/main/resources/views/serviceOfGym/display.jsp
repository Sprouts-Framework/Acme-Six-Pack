<%@ include file="../template/libraries.jsp" %>

	<spring:message var="name" code="serviceOfGym.name"/>
	<spring:message var="description" code="serviceOfGym.description"/>
	<spring:message var="pictures" code="serviceOfGym.pictures"/>
	<spring:message var="customersTotalNumber" code="serviceOfGym.totalNumber"/>
	<spring:message var="gym" code="serviceOfGym.gym"/>
	<spring:message var="gymData" code="serviceOfGym.gymData"/>
	<spring:message var="gymName" code="serviceOfGym.gym.name"/>
	<spring:message var="gymDescription" code="serviceOfGym.gym.description"/>
	<spring:message var="gymPostalAddress" code="serviceOfGym.gym.postalAddress"/>
	<spring:message var="gymPhoneNumber" code="serviceOfGym.gym.phoneNumber"/>
	<spring:message var="gymFee" code="serviceOfGym.gym.fee"/>
	<spring:message var="gymPicture" code="serviceOfGym.gym.picture"/>
	<spring:message var="gymCustomersTotalNumber" code="serviceOfGym.gym.customersTotalNumber"/>

	<div class="text-center">
		<sproutsSpecific:display-column title="${name}" data="${serviceOfGym.serviceEntity.name}"/>
		<sproutsSpecific:display-column title="${description}" data="${serviceOfGym.description}"/>
		<sproutsSpecific:display-column title="${customersTotalNumber}" data="${serviceOfGym.customersTotalNumber}"/>
	</div>
	
	<br />
	<security:authorize access="hasRole('Customer')">
		<a class="btn btn-default" href="booking/customer/create.do?serviceOfGymId=${serviceOfGym.id }"><spring:message code="serviceOfGym.book" /></a>
	</security:authorize>
	<br />
	
	
	
	<div class="text-center">
		<h2><jstl:out value="${gymData}"/></h2>
		<sproutsSpecific:display-image-column src="${serviceOfGym.gym.picture}"/>
		<sproutsSpecific:display-column title="${gymName}"/>
		<sproutsSpecific:display-column data="${serviceOfGym.gym.description}"/>
		<sproutsSpecific:display-column title="${gymFee}" data="${serviceOfGym.gym.fee}" message="EUR"/>
		<sproutsSpecific:display-column title="${gymPostalAddress}" message="${serviceOfGym.gym.postalAddress}" url="https://www.google.es/maps/search/${serviceOfGym.gym.postalAddress}"/>
		<sproutsSpecific:display-column title="${gymPhoneNumber}" data="${serviceOfGym.gym.phoneNumber}"/>
		<sproutsSpecific:display-column title="${gymCustomersTotalNumberLabel}" data="${serviceOfGym.gym.customersTotalNumber}"/>
	</div>
	
	<h3 class="text-center">
		<spring:message code="serviceOfGym.pictures"/>
	</h3>
			<jstl:set var="count" value="0"/>
			<jstl:forEach items="${serviceOfGym.pictures}" var="picture">
				<jstl:if test="${count % 3 == 0}">
					<div class="row">
				</jstl:if>
		  		<div class="col-sm-6 col-md-4">
				    <div class="thumbnail">
				      <img src="${picture}" class="img-rounded" width="242px" height="200px">
				      <div class="caption text-center">
				        <p><a href="${picture}" target="_blank" class="btn btn-primary"><spring:message code="serviceOfGym.picture.see"/></a> 
				        <security:authorize access="hasRole('Administrator')">
				        <a href="serviceOfGym/administrator/picture/${serviceOfGym.id},${count}/update.do" class="btn btn-default"><spring:message
							code="serviceOfGym.picture.edit" /></a>
						<a href="serviceOfGym/administrator/picture/${serviceOfGym.id},${count}/delete.do" class="btn btn-danger"><spring:message
							code="serviceOfGym.picture.delete" /></a>
				        </security:authorize>
				        </p>
				      </div>
				    </div>
			  	</div>
			  	<jstl:set var="count" value="${count + 1}"/>
			  	<jstl:if test="${count % 3 == 0}">
			  		</div>
			  	</jstl:if>
			</jstl:forEach>
			<jstl:if test="${count % 3 != 0}">
			  		</div>
			</jstl:if>
		
		<security:authorize access="hasRole('Administrator')">
			<a href="serviceOfGym/administrator/picture/${serviceOfGym.id}/create.do" class="btn btn-default"><spring:message
					code="serviceOfGym.picture.add"/></a>
			<br />
		</security:authorize>
		
		<br/>
		
		<spring:message code="serviceOfGym.comment.moment" var="momentLabel"/>
		<spring:message code="serviceOfGym.comment.text" var="textLabel"/>
		<spring:message code="serviceOfGym.comment.starRating" var="starRatingLabel"/>
		<spring:message code="serviceOfGym.comment.author" var="authorLabel"/>
		
		
		<h3>
			<spring:message code="serviceOfGym.comments" />
		</h3>
		
		<security:authorize access="hasRole('Customer')">
			<a class="btn btn-default" href="comment/customer/0,${serviceOfGym.id}/create.do" class="btn btn-default"><spring:message code="serviceOfGym.comment.create"/></a>
		<br/>
		</security:authorize>
		
		
		<security:authorize access="hasRole('Administrator')">
			<a class="btn btn-default" href="comment/administrator/0,${serviceOfGym.id}/create.do"><spring:message code="serviceOfGym.comment.create"/></a>
			<br/>
		</security:authorize>
		
		<sprouts:data-table i18n="datatables.language" source="home/comment/serviceOfGym/${serviceOfGym.id}/list/data.do"  searcheable="false">
			<security:authorize access="hasRole('Administrator')">
				<sprouts:action-button url="comment/administrator/{0}/delete.do" code="serviceOfGym.comment.delete"/>
			</security:authorize>
			<sprouts:data-column code="serviceOfGym.comment.starRating" format="image" path="starRating" outFormat="/Acme-Six-Pack/images/starRatings/+PATHde3.png" imgSize="85x30" width="90px"/>
			<sprouts:data-column code="serviceOfGym.comment.author" path="actor.userAccount.username"/>
			<sprouts:data-column code="serviceOfGym.comment.moment" path="moment" format="date"/>
			<sprouts:data-column code="serviceOfGym.comment.text" path="text"/>
			
		</sprouts:data-table>
		