package com.byteparity.forms.portlet;

import com.byteparity.forms.portlet.constants.FormsMVCPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author baps
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category="+FormsMVCPortletKeys.PORTLET_CATEGORY,
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name="+FormsMVCPortletKeys.PORTLET_DISPLAY_NAME,
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FormsMVCPortletKeys.PORTLET_ID,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FormsMVCPortlet extends MVCPortlet {
}