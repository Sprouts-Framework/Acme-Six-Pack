<%--
 * list.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>

<h4><spring:message code="administrator.select"/></h4>

<h4>
	<a href="dashboard/administrator/gym/0/list.do">
		<spring:message code="administrator.mostPopularGyms"/>
	</a>
</h4>


<h4>
	<a href="dashboard/administrator/gym/1/list.do">
		<spring:message code="administrator.leastPopularGyms"/>
	</a>
</h4>


<h4>
	<a href="dashboard/administrator/serviceEntity/2/list.do">
		<spring:message code="administrator.mostPopularServices"/>
	</a>
</h4>


<h4>
	<a href="dashboard/administrator/serviceEntity/3/list.do">
		<spring:message code="administrator.leastPopularServices"/>
	</a>
</h4>


<h4>
	<a href="dashboard/administrator/customer/4/list.do">
		<spring:message code="administrator.customersWhoHavePaidMoreFees"/>
	</a>
</h4>


<h4>
	<a href="dashboard/administrator/customer/5/list.do">
		<spring:message code="administrator.customersWhoHavePaidLessFees"/>
	</a>
</h4>
	

<h4>
	<a href="dashboard/administrator/actor/6/list.do">
		<spring:message code="administrator.actorsWhoSendMoreSpamMessages"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/actor/7/list.do">
		<spring:message code="administrator.averageNumberOfMessagesOfAnActor"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/gym/8/list.do">
		<spring:message code="administrator.gymsThatHaveMoreComments"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/serviceOfGym/9/list.do">
		<spring:message code="administrator.servicesThatHaveMoreComments"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/10/list.do">
		<spring:message code="administrator.averageAndStandardDeviationOfComment"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/11/list.do">
		<spring:message code="administrator.averageNumberOfCommentsPerGym"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/12/list.do">
		<spring:message code="administrator.averageNumberOfCommentsPerService"/>
	</a>
</h4>

<h4>
	<a href="dashboard/administrator/actor/13/list.do">
		<spring:message code="administrator.actorsWhoHaveRemovedMoreComments"/>
	</a>
</h4>

<jstl:if test="${option=='10'  }">
	<strong><spring:message code="administrator.averageOfComments"/>:</strong> <jstl:out value="${averageOfComments}"/>
	<br />
	<strong><spring:message code="administrator.standardDeviationOfComments"/>:</strong> <jstl:out value="${standardDeviationOfComments}"/>
</jstl:if>

<jstl:if test="${option=='11' }">
	<strong><spring:message code="administrator.averageNumberOfCommentsPerGym"/>:</strong> <jstl:out value="${averageNumberOfCommentsPerGym}"/>
</jstl:if>

<jstl:if test="${option=='12' }">
	<strong><spring:message code="administrator.averageNumberOfCommentsPerService"/>:</strong> <jstl:out value="${averageNumberOfCommentsPerService}"/>
</jstl:if>