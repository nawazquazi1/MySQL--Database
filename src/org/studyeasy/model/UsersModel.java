package org.studyeasy.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.studyeasy.entity.User;

public class UsersModel {

	public List<User> listUsers(DataSource dataSource) {
		// Step 1: Initialize connection objects
		List<User> listUsers = new ArrayList<>(); 
        Connection connect = null;
        Statement stmt = null;
        ResultSet rs = null;       
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "select * from new_table;";
			stmt = connect.createStatement();
			// Step 3: Execute SQL query
         rs = stmt.executeQuery(query);
			// Step 4: Process the result set
			while(rs.next()){
				listUsers.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email")));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return listUsers;
	}

	public boolean addUser(DataSource dataSource, User newUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String username = newUser.getUsername();
			String email = newUser.getEmail();
			String query = "insert into new_table (username,email) values (?,?)";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			return statement.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public void updateUser(DataSource dataSource, User updatedUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			int usersId = updatedUser.getUsers_id();
			String username = updatedUser.getUsername();
			String email = updatedUser.getEmail();
			String query = "update new_table set username = ? , email = ? where user_Id = ? ";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setInt(3, usersId);
			statement.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void deleteUser(DataSource dataSource,int usersID) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String query = "delete from new_table where user_Id = ? ";
			statement = connect.prepareStatement(query);
			statement.setInt(1, usersID);
			statement.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}


}




