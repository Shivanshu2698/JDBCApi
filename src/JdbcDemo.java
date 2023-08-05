import java.util.*;
import java.sql.*;

import java.sql.DriverManager;

public class JdbcDemo {

	public static void main(String[] args) {
		// JDBC database URL, userName, and password
		String url="jdbc:mysql://localhost:3306/JdbcDemo";
		String userName="root";
		String password="shukla2698";
		
		// performing the process of database Interaction
		try{
			// Loading the drivers && Registering the drivers
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			//Establishing connection with database
			Connection cann=DriverManager.getConnection(url, userName, password);
			
			// : Disable auto-commit to handle transactions
            cann.setAutoCommit(false);
            
			//creating the query
		    String insertQuery="Insert into Test (id, name, age) values (?, ?, ?)";
			
			//Creating PreparedStatement object to insert data into database
			PreparedStatement insertPst =cann.prepareStatement(insertQuery);
			
			//Binding the queryParamete with pst object
			insertPst.setInt(1, 121);
			insertPst.setString(2, "prateek");
			insertPst.setInt(3, 28);
			
			//: Create another PreparedStatement to update data in a table
            /*String updateQuery = "UPDATE Test SET age = ? WHERE id = ?";
            PreparedStatement updateStatement = cann.prepareStatement(updateQuery);
            updateStatement.setInt(1, 31);
            updateStatement.setInt(2, 101);
            updateStatement.executeUpdate();*/
			
			
			//submitting the query object
			insertPst.executeUpdate();
			//updateStatement.executeUpdate();
			
			// : Commit the changes (transaction) if everything is successful
            cann.commit();
            
            //closing the resources
            insertPst.close();
            //updateStatement.close();
            cann.close();
            
		}catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error or transaction failure!");
            e.printStackTrace();
        }
	}

}
