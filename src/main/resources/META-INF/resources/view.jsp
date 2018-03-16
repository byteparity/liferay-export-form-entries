<%@ include file="init.jsp" %>
<liferay-portlet:resourceURL var="viewFormEntries" id="/view-form-entries"/>
<liferay-portlet:resourceURL var="viewForms" id="/view-forms"/>

<aui:form name="frm-filter">
   	<div class="row">
		<div class="col-lg-2">
			 <aui:input type="text" name="fromDateSearch" label="from-data"></aui:input>
		</div>	
		<div class="col-lg-2">
			 <aui:input type="text" name="toDateSearch" label="to-date" ></aui:input>
		</div>
		<div class="col-lg-2">
			<aui:select name="liferay-forms" label="liferay-forms" showEmptyOption="true">
               </aui:select>
		</div>
		<div class="col-lg-2">
			<aui:button onClick="getLiferayFormsData();" value="liferay-form-search-button" />
			<aui:button type="clear" name="clear" value="clear"/>
		</div>
	</div>
</aui:form>
<div class="row">
	<div class="col-md-12">
		<div id="tableDiv"></div>
	</div>
</div>

<%@ include file="view_js.jsp"%>