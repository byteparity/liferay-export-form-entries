package com.byteparity.forms.portlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.byteparity.forms.portlet.constants.FormsMVCPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

@Component(property = { 
		"javax.portlet.name=" + FormsMVCPortletKeys.PORTLET_ID,
		"mvc.command.name=/view-forms" }, 
		service = MVCResourceCommand.class)
public class ViewFormMVCResourceCommand implements MVCResourceCommand {

	private static Log LOGGER = LogFactoryUtil.getLog(ViewFormMVCResourceCommand.class.getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// GET FORMS BY GROUP ID
		DynamicQuery dynamicQuery = DDLRecordSetLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("companyId").eq(themeDisplay.getCompanyId()));
		List<DDLRecordSet> recordSet = DDLRecordSetLocalServiceUtil.dynamicQuery(dynamicQuery);
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = null;
		for (DDLRecordSet ddlRecords : recordSet) {
			jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("formName", ddlRecords.getName(Locale.getDefault()));
			jsonObject.put("recordSetId", ddlRecords.getRecordSetId());
			jsonObject.put("ddmStructureId", ddlRecords.getDDMStructureId());
			jsonArray.put(jsonObject);
		}
		try {
			resourceResponse.getWriter().println(jsonArray);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
}
