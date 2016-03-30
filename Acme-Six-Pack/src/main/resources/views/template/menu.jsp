<%@ include file="../template/libraries.jsp" %>


<!-- TODO: Menu must be changed, here's an example -->

<!-- Links that are shown to everybody -->

<ul class="nav navbar-nav">
	<security:authorize access="permitAll()">
		<li class="dropdown"><a href="home/gym/list.do"><spring:message code="gym.list" /></a></li>
		<li class="dropdown"><a href="home/serviceEntity/list.do"><spring:message code="serviceEntity.list" /></a></li>
	</security:authorize>
</ul>

<!-- Links that are shown to Admins -->

<ul class="nav navbar-nav">
	<security:authorize access="hasRole('Administrator')">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.administrator" />
				<span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a href="serviceEntity/administrator/create.do"><spring:message	code="master.administrator.serviceEntity.create" /></a></li>
				<li><a href="serviceOfGym/administrator/create.do"><spring:message code="serviceOfGym.create" /></a></li>
				<li><a href="gym/administrator/create.do"><spring:message code="master.administrator.gym.create" /></a></li>
				<li><a href="dashboard/administrator/list.do"><spring:message	code="dashboard.list" /></a></li>
				<li><a href="feePayment/administrator/list.do"><spring:message code="master.administrator.list-fee-payments" /></a></li>
				<li><a href="booking/administrator/managed/list.do"><spring:message	code="master.administrator.booking.managed" /></a></li>
				<li><a href="booking/administrator/pending/list.do"><spring:message	code="master.administrator.booking.pending" /></a></li>
				<li><a href="spamTerms/administrator/show.do"><spring:message	code="master.administrator.spamTerms.list" /></a></li>
			</ul>
		</li>
	</security:authorize>
</ul>

<!-- Links that are shown to Customers -->

<ul class="nav navbar-nav">
	<security:authorize access="hasRole('Customer')">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.customer" />
				<span class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="booking/customer/list.do"><spring:message code="booking.list" /></a></li>
				<li><a href="feePayment/customer/list.do"><spring:message code="master.customer.feePayment-list-not-expired" /></a></li>
				<li><a href="gym/customer/list.do"><spring:message code="master.customer.mygyms" /></a></li>
			</ul></li>
	</security:authorize>
</ul>

<!-- Links that are shown to Anonymous users -->

<security:authorize access="isAnonymous()">
	
	<ul class="nav navbar-nav navbar-right">
		<li><a href="home/sign-in.do"><spring:message
					code="master.sign-in" /></a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> <spring:message code="master.sign-up" />
				<span class="caret"></span>
		</a>
			<ul class="dropdown-menu">
				<li><a href="home/customer/create.do"><spring:message
							code="master.sign-up.as-customer" /></a></li>
			</ul></li>
	</ul>
</security:authorize>

<!-- Links that are shown to Authenticated users (PROFILE SECTION) -->

<security:authorize access="isAuthenticated()">
	<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
			<a href="#" class="dropdown-toggle"	data-toggle="dropdown"> <spring:message	code="master.profile" /> <span class="caret"></span></a>
				<security:authorize access="hasRole('Administrator')">
				<ul class="dropdown-menu">
					<li><a href="box/administrator/list.do"><spring:message code="master.box" /></a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('Customer')">
				<ul class="dropdown-menu">
					<li><a href="profile/customer/show.do"><spring:message code="master.profile.data" /></a></li>
					<li><a href="box/customer/list.do"><spring:message code="master.box" /></a></li>
				</ul>
				</security:authorize>
			</li>
		<li><a href="home/sign-out.do"> <spring:message
					code="master.sign-out" /> (<security:authentication
					property="principal.username" />)
		</a></li>
	</ul>
</security:authorize>