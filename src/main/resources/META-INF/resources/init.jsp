<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.byteparity.forms.configuration.ViewFormEntriesConfiguration"%>

<c:set var = "now" value = "<%= new java.util.Date()%>" />
<fmt:formatDate var="temp" pattern="s" type="time" value="${now}" />

<script src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/dataTables.buttons.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/buttons.flash.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/buttons.html5.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/buttons.print.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/jszip.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/pdfmake.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/vfs_fonts.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/jquery.plugin.min.js?${temp}"></script>
<script src="<%=request.getContextPath()%>/js/jquery.datepick.js?${temp}"></script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css?${temp}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css?${temp}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buttons.dataTables.min.css?${temp}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.datepick.css?${temp}">

<liferay-theme:defineObjects />
<portlet:defineObjects />