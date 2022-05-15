$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function() 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateEmployeeForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "EmployeeAPI",
		type : t,
		data : $("#formEmployee").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onEmployeeSaveComplete(response.responseText, status);
		}
	});
}); 

function onEmployeeSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divEmployeeGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidEmployeeIDSave").val("");
	$("#formUnit")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function() 
		{     
	$("#hidEmployeeIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());
	$("#eName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#eRole").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#ePass").val($(this).closest("tr").find('td:eq(2)').text());     

});

//Remove Operation
$(document).on("click", ".btnRemove", function(){
	$.ajax(
	{
		url : "EmployeeAPI",
		type : "DELETE",
		data : "eID=" + $(this).data("employeeid"),
		dataType : "text",
		complete : function(response, status)
		{
			onEmployeeDeletedComplete(response.responseText, status);
		}
	});
});

function onEmployeeDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divEmployeeGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateEmployeeForm() {  
	// District 
	if ($("#eName").val().trim() == "")  {   
		return "Insert Employee Name";  
		
	} 
	
	 // DistrictID  
	if ($("#eRole").val().trim() == "")  {   
		return "Insert Employee Role ID";  
		
	} 

	 // Unit Price
	if ($("#ePass").val().trim() == "")  {   
		return "Insert Employee Password";  
		
	} 
		 
	 return true; 
	 
}
