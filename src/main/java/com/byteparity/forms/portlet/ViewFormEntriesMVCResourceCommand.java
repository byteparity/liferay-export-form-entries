package com.byteparity.forms.portlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.byteparity.forms.portlet.constants.FormsMVCPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

@Component(property = { 
		"javax.portlet.name=" + FormsMVCPortletKeys.PORTLET_ID,
		"mvc.command.name=/view-form-entries" }, 
		service = MVCResourceCommand.class)
public class ViewFormEntriesMVCResourceCommand implements MVCResourceCommand {

	private static Log LOGGER = LogFactoryUtil.getLog(ViewFormEntriesMVCResourceCommand.class.getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		// VIEW FORM ENTERIES CONFIGURATION
		PortletPreferences portletPreferences = resourceRequest.getPreferences();
		String hiddenFormFields = portletPreferences.getValue("hiddenFormField", StringPool.TRUE);
		com.byteparity.forms.util.ActionUtil actionUtil = new com.byteparity.forms.util.ActionUtil();
		actionUtil.setHiddenField(hiddenFormFields);

		// GET SELECTED FORM
		String selectedForm = ParamUtil.getString(resourceRequest, "liferay-forms");

		if (Validator.isNotNull(selectedForm)) {
			int ind = selectedForm.indexOf(",");
			long recordSetId = Long.parseLong(selectedForm.substring(0, ind));
			long ddmStructureId = Long.parseLong(selectedForm.substring(ind + 1, selectedForm.length()));
			String fromToDate = ParamUtil.getString(resourceRequest, "fromToDateSearch");
			String fromDate = "";
			String toDate = "";
			if(Validator.isNotNull(fromToDate)){
				fromDate = fromToDate.split("-")[0];
				toDate = fromToDate.split("-")[1];
			}
			
			// SET FORM ENTERIES
			List<List<String>> recordsList = null;
			List<Map<String, String>> fieldList = null;
			try {
				fieldList = actionUtil.formFields(ddmStructureId);
				recordsList = actionUtil.formEntries(fieldList, recordSetId, fromDate, toDate);

				JSONArray formFiledList = JSONFactoryUtil.createJSONArray();
				JSONArray filed = null;
				for (Map<String, String> map : fieldList) {
					for (Map.Entry<String, String> entry : map.entrySet()) {
						filed = JSONFactoryUtil.createJSONArray();
						filed.put(entry.getValue());
						formFiledList.put(filed);
					}
				}
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put("columns", formFiledList);
				jsonObject.put("data", recordsList);

				try {
					resourceResponse.getWriter().println(jsonObject);
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			} catch (PortalException | ParseException e) {
				LOGGER.error(e.getMessage());

			}
		}
		return false;
	}
}
