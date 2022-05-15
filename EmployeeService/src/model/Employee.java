package model;

import java.sql.*;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

public class Employee {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/lab", "root", "");
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	}
	
	public String insertEmployee(String eName, String eRole, String ePass)
	{
	   String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
		{
			
			return "Error while connecting to the database for inserting."; 
		}
	
		  // create a prepared statement
			String query = " insert into emp(`eID`,`eName`,`eRole`,`ePass`)"+ " values (?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, eName);
			preparedStmt.setString(3, eRole);
			preparedStmt.setString(4, ePass);
	
	
		  // execute the statement
			preparedStmt.execute();
			con.close();
			
			 //create JSON Object
			  String newEmployees = readEmployees();
			  output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readEmployees()
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {	
			  return "Error while connecting to the database for ePass."; 
	      }
			  
			// Prepare the html table to be displayed
		        output = "<table class='table'><tr align='center'>"
		        		+ "<th> Employee-Name </th>"
		        		+ "<th> Employee-Role</th><th> Employee-password  </th>"
		        		+"<th> Update </th><th> Delete </th>";
					  
					 ;
			  String query = "select * from emp";
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		  
		  while (rs.next())
		  {
				String eID = Integer.toString(rs.getInt("eID"));
				String eName = rs.getString("eName");
				String eRole = rs.getString("eRole");
				String ePass = rs.getString("ePass");
			
				// Add into the html table
		        output += "<tr align='center'><td><input id='hidEmployeeIDUpdate' name='hidEmployeeIDUpdate' type='hidden' value='" + eID + "'>" + eName + "</td>";
		        output += "<td>" + eRole + "</td>";
		        output += "<td>" + ePass + "</td>";
		        
		     // buttons     
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-primary'></td>"
				        + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-employeeid='"+ eID +"'>"+"</td></tr>";
		
			
		  }
				con.close();
				// Complete the html table
				output += "</table>";
		  }
		  catch (Exception e)
		  {
				output = "Error while ePass the emp.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String updateEmployee(String eID,String eName,String eRole,String ePass)
			{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {
			  return "Error while connecting to the database for updating."; 
		  }
			  
			  // create a prepared statement
				  String query = "UPDATE emp SET eName=?,eRole=?,ePass=? WHERE eID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				preparedStmt.setString(1, eName);
				preparedStmt.setString(2, eRole);
				preparedStmt.setString(3, ePass);
				preparedStmt.setInt(4, Integer.parseInt(eID));
			 
			  // execute the statement
				preparedStmt.execute();
				con.close();
				//create JSON Object
				  String newEmployee = readEmployees();
				  output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
		  }
		  catch (Exception e)
		  {
			  output = "{\"status\":\"error\", \"data\": \"Error while updating the Employee.\"}";
				System.err.println(e.getMessage());
		  }
		return output;
	}
			
	public String deleteEmployee(String eID)
	{
		String output = "";
		  try
		  {
			  	Connection con = connect();
			  	if (con == null)
		  {
				return "Error while connecting to the database for deleting."; 
		  }
			  	
			// create a prepared statement
			  	String query = "delete from emp where eID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(eID));
			
			// execute the statement
			  	preparedStmt.execute();
			  	con.close();
			  //create JSON Object
				  String newEmployee = readEmployees();
				  output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
		  }
		  catch (Exception e)
	      {
	          output = "{\"status\":\"error\", \"data\": \"Error while deleting the Employee.\"}";
	          System.err.println(e.getMessage());
		  }
		  return output;
			}
	}
