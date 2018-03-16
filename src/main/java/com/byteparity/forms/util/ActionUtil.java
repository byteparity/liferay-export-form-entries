package com.byteparity.forms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class ActionUtil {

	private List<String> hiddenFormField = null;

	/**
	 * This method get DDLrecord by ddmStructureId
	 * 
	 * @param ddmStructureId
	 * @return
	 * @throws PortalException
	 */
	public List<Map<String, String>> formFields(long ddmStructureId) throws PortalException {
		String data = DDMStructureLocalServiceUtil.getDDMStructure(ddmStructureId).getDefinition();
		JSONObject jsonObject = null;
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map = null;
		jsonObject = JSONFactoryUtil.createJSONObject(data);
		JSONArray fields = jsonObject.getJSONArray("fields");
		map = new HashMap<String, String>();
		map.put("author", "Author");
		map.put("createDate", "Create Date");
		list.add(map);
		for (int i = 0; i < fields.length(); i++) {
			map = new HashMap<String, String>();
			JSONObject fieldsName = fields.getJSONObject(i);
			JSONObject localField = fieldsName.getJSONObject("label");
			if (!getHiddenField(localField.getString("en_US").replace(":", ""))) {
				map.put(fieldsName.getString("name"), localField.getString("en_US").replace(":", ""));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * This method get DDMcontent by recordSetId
	 * 
	 * @param filedList
	 * @param recordSetId
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws PortalException
	 * @throws ParseException
	 */
	public List<List<String>> formEntries(List<Map<String, String>> filedList, long recordSetId, String fromDate,
			String toDate) throws PortalException, ParseException {

		String data = null;
		List<List<String>> recordsList = new ArrayList<List<String>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JSONObject jsonObject = null;
		for (DDLRecord record : getDDLRecords(recordSetId, fromDate, toDate)) {
			data = DDMContentLocalServiceUtil.getDDMContent(record.getDDMStorageId()).getData();
			List<String> row = new ArrayList<String>();
			row.add(record.getUserName().isEmpty()?"Guest":record.getUserName());
			row.add(dateFormat.format(record.getCreateDate()));
			jsonObject = JSONFactoryUtil.createJSONObject(data);
			JSONArray fields = jsonObject.getJSONArray("fieldValues");
			String key = "";
			boolean isValueFound = false;
			for (Map<String, String> field : filedList) {
				key = (String) field.keySet().toArray()[0];
				if (key != "author" && key != "createDate") {
					isValueFound = false;
					for (int i = 0; i < fields.length(); i++) {
						if (Validator.isNotNull(fields.getJSONObject(i))) {
							JSONObject fieldsValue = fields.getJSONObject(i);
							JSONObject localFieldValue = fieldsValue.getJSONObject("value");
							if (key.equals(fieldsValue.getString("name"))) {
								row.add(localFieldValue.getString("en_US").replace("[", "").replace("]", "")
										.replace("\"", ""));
								isValueFound = true;
								break;
							}
						}
					}
					if (!isValueFound) {
						row.add("");
					}
				}

			}

			recordsList.add(row);
		}
		return recordsList;
	}

	/**
	 * This method get DDLRecords by recordSetId And filtering record fromDate
	 * to toDate
	 * 
	 * @param recordSetId
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	private List<DDLRecord> getDDLRecords(long recordSetId, String fromDate, String toDate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		DynamicQuery dynamicQuery = DDLRecordLocalServiceUtil.dynamicQuery();
		if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
			cal.setTime(StringToDate(toDate));
			cal.add(Calendar.DATE, +1);
			Date todate = cal.getTime();
			dynamicQuery.add(PropertyFactoryUtil.forName("createDate").between(StringToDate(fromDate), todate));
		}
		dynamicQuery.add(PropertyFactoryUtil.forName("recordSetId").eq(new Long(recordSetId)));
		return DDLRecordLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * This method formating string date to date
	 * 
	 * @param searchDate
	 * @return
	 * @throws ParseException
	 */
	private static Date StringToDate(String inputDate) throws ParseException {
		Date formatDate = null;
		formatDate = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate);
		return formatDate;
	}

	/**
	 * This method check hidden form field
	 * 
	 * @param fieldName
	 * @return
	 */
	private boolean getHiddenField(String fieldName) {
		if (hiddenFormField.contains(fieldName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method set hidden form field
	 * 
	 * @param fields
	 */
	public void setHiddenField(String fields) {
		String fieldList[] = fields.split(",");
		this.hiddenFormField = Arrays.asList(fieldList);
	}
}
