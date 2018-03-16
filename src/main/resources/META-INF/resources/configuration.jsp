<%@ include file="init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<%
	ViewFormEntriesConfiguration  viewFormEntriesConfiguration =(ViewFormEntriesConfiguration)renderRequest.getAttribute(ViewFormEntriesConfiguration.class.getName());
    String hiddenFormField = StringPool.BLANK;
    if (Validator.isNotNull(viewFormEntriesConfiguration)) {
    	hiddenFormField = portletPreferences.getValue("hiddenFormField", viewFormEntriesConfiguration.getHiddenFormField());
    }
%>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input name="redirect" type="hidden"  value="<%= configurationRenderURL %>" />
	<div class="container-fluid-1280">
		<div class="card-horizontal main-content-card">
			<div class="row">
				<div class="col-lg-4">
					<aui:fieldset>
						<aui:input type="text" name="hiddenFormField" value="<%=hiddenFormField%>" label="hidden-field"/>
				    </aui:fieldset>
				</div>
			</div>
		</div>
	</div>
	<aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>