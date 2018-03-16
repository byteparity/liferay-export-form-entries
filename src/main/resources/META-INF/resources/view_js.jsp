<script type="text/javascript">
	$( document ).ready(function() {
		getLiferayForms();
		
		$('#<portlet:namespace />fromDateSearch').datepicker({
			onSelect: function(selected) {
				$("#<portlet:namespace />toDateSearch").datepicker("option","minDate", selected)
			}
		});
		
		$('#<portlet:namespace />toDateSearch').datepicker({
			onSelect: function(selected) {
		    	$("#<portlet:namespace />fromDateSearch").datepicker("option","maxDate", selected)
		 	}
		});
	});
	
	function getLiferayForms(){
		$.ajax({
			url :"${viewForms}",
			success: function(data){
		    	var content= JSON.parse(data);
		    	jQuery(content).each(function(i, form){
		    		$("#<portlet:namespace />liferay-forms").append("<option value="+form.recordSetId+","+form.ddmStructureId+">"+form.formName+"</option>");
		    	});
		        return false;	
			}
		});
	}
	function getLiferayFormsData(){
		
		if($("#<portlet:namespace />liferay-forms").val() == ""){
			alert("Please select any form.");
		}else{
			var formData = new FormData($("#<portlet:namespace />frm-filter")[0]);
			 $.ajax({
				 "url": "${viewFormEntries}",
				 "data":formData,
				 contentType: false,
			     processData: false,
				 cache: false,
	             "success": function(json) {
	                var tableHeaders = "";
	                $.each(json.columns, function(i, val){
	                     tableHeaders += "<th>" + val + "</th>";
	                });
                 	$("#tableDiv").empty();
               		$("#tableDiv").append('<table id="displayTable" class="display responsive nowrap" cellspacing="0" width="100%"><thead><tr>' + tableHeaders + '</tr></thead></table>');	                	 
	                $('#displayTable').dataTable({
	                	dom: 'Bfrtip',
	                	data: json.data,
	                	"scrollX": true,
	                	"autoWidth" : true,
	                	"processing": true,
		     		    "dom": 'lBfrtip',
		     		    select: true,
		     	        "buttons": [
		     	            {
		     	                extend: 'collection',
		     	                text: 'Export',
		                        autoClose: true,
		     	                buttons: [
		     	                          
									$.extend( true, {},{
										extend : 'excelHtml5',
										title : function() {
									          return $("#<portlet:namespace />liferay-forms option:selected").text();
									     }
									} ),
									$.extend( true, {},{
										extend : 'pdfHtml5',
									      title : function() {
									          return $("#<portlet:namespace />liferay-forms option:selected").text();
									      },
									      orientation : 'landscape',
									      pageSize : 'A0',
									      text : 'PDF',
									      titleAttr : 'PDF',
									      filename: 'download'
									} ),
									$.extend( true, {}, {
										extend : 'csvHtml5',
										title : function() {
									          return $("#<portlet:namespace />liferay-forms option:selected").text();
									     }
									} )
		     	                ]
		     	            }
		     	        ]    
               		});
	             }, 
	             "dataType": "json"
        	});
		}
	}
	
</script>