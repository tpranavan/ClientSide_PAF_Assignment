<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="image/d.jpg" rel ="icon" type= "image/icon">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Component/jquery-3.2.1.min.js"></script>
<script src="Component/Emp.js"></script>
</head>
<body>

   
	<div class="container">
		
		<div class="row">

			<div class="bgimg">
			
				<br>
				<h3 class="m-3">Employee Service</h3><br>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

                <form id="formEmployee" name="formEmployee" method="post" class="form" action="Employee.jsp">
						
					
					Employee Name:   <br>
					<input id="eName" name="eName" type="text" >   <br>
					
					<br> 
					Employee Role:  <br> 
					<input id="eRole" name="eRole" type="text" >   <br>
					
					<br>
					 Employee Password: <br>
					<input id="ePass" name="ePass" type="text"><br>
					 
				    	
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">

				</form>
				</div>
				  <br>
 			    <div >
				<div id="divEmployeeGrid">
					<%
					Employee appObj = new Employee();
					out.print(appObj.readEmployees());
					%>

				</div>
			</div>
		</div>
	</div>

</body>
</html>