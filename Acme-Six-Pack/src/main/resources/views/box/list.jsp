<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ include file="../template/libraries.jsp" %>


<sproutsSpecific:button code="createMessage.button" url="message/${actor}/create.do"/>
<sproutsSpecific:button code="box.create" url="box/${actor}/create.do"/>
<br />
<br />

<sprouts:data-table i18n="datatables.language"  searcheable="false">
		<sprouts:action-button code="update.button" url="box/${actor}/{0}/update.do" />
		<sprouts:action-button code="delete.button" url="box/${actor}/{0}/delete.do" />
		<sprouts:action-button code="details.button" url="message/${actor}/{0}/list.do" />
	
	<sprouts:data-column code="box.name" path="name" sortable="true"/>

</sprouts:data-table>

