# Liferay Export Form Entries

To collect the information from User’s, whether you’re asking them to submit the survey, contact us form or any application form. In this case you need to design form, Liferay provides almost limitless form building capability with Form Application. Different users can fill their form and all the data related to forms can be visible to administrator or whom so ever has access. But what if admin wants to search forms/applications with specific value, what if admin wants to export the	records and share it to someone else. With the help of “Export Form entries component” user can perform search and export form entries in different formats.

Export Form entries component has below capabilities.
*	List all the Liferay forms.
*	Date range criteria to search application/forms submitted between specific dates.
*	Search feature to find application/forms with specific value, with the help of Jquery data table.
*	Export form entries in different formats (Excel, PDF and CSV)

## Environment

* Liferay 7 - GA4 +
*	MySQL 5.6 +

## How to use

1. Download, build and install form entries jar on your server.
2. Check module status in liferay tomcat server using console log OR using gogo shell.
3. Create Liferay [form](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/basic-forms)(s). Submit form multiple times to create entries.
4. Now add liferay-forms-data-list-portlet portlet on specific page.


![ScreenShot](https://user-images.githubusercontent.com/24852574/37393831-46ac1256-2798-11e8-81c3-19885e34855f.png)


Default view should look like below screenshot.


![ScreenShot](https://user-images.githubusercontent.com/24852574/37394026-d26a3282-2798-11e8-81f1-f42a6b097b79.png)


   All the forms created in Step 3 are shown in "Select Form" Dropdown.
   
5. Select any form and click on Search button, you can see all the entries of selected form (Along with Author and submitted date). You can also search form entries between particular dates.


![ScreenShot](https://user-images.githubusercontent.com/24852574/37394103-010fbba2-2799-11e8-88ce-faa708f784e9.png)


6. You can also configured form fields which you don’t want to display in table. In previous screenshot you show all three fields First Name, Last Name and City in table. Follow below steps to 


![ScreenShot](https://user-images.githubusercontent.com/24852574/37394179-3540ce0c-2799-11e8-80db-18803f14f12d.png)


![ScreenShot](https://user-images.githubusercontent.com/24852574/37394227-4bde6ab6-2799-11e8-90ee-3535b6348bee.png)


   After providing field to hide, click on Save button. To hide multiple fields from table, you can provide comma separated field names.


![ScreenShot](https://user-images.githubusercontent.com/24852574/37394312-702f67d0-2799-11e8-806c-7ce37402c2b8.png)


7. You can also export the selected form entries in different kind of file formats like Excel, PDF, CSV. Click on Export button. You can see three file formats. Click on any of this button and data will be downloaded in desire format.


![ScreenShot](https://user-images.githubusercontent.com/24852574/37394357-90a56762-2799-11e8-924c-dc0292f87150.png)


## Support
   Please feel free to contact us on hello@byteparity.com for any issue/suggestions.
