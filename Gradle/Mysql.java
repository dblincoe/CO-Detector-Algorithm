package org.Garage;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.*;

public class Mysql{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/Garage";

	//  Database credentials
	private static final String USER = "root";
	private static final String PASS = "teeth109";
   
	private Connection conn = null;
  private	Statement stmt = null;
  private ResultSet rs;

  private ArrayList vals = new ArrayList();
  private ArrayList<String> timestamps = new ArrayList<String>();

	public Mysql() {
   		try{
      		System.out.println("Loading driver...");

			try {
    			Class.forName("com.mysql.jdbc.Driver");
    			System.out.println("Driver loaded!");
			} catch (ClassNotFoundException e) {
    			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
			}

			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
    			System.out.println("Database connected!");
			} catch (SQLException e) {
    			throw new IllegalStateException("Cannot connect the database!", e);
			}

      		System.out.println("Connecting to database...");
      		this.conn = DriverManager.getConnection(DB_URL, USER, PASS);

   		}catch(SQLException se){
      		//Handle errors for JDBC
      		se.printStackTrace();
   		}catch(Exception e){
      		//Handle errors for Class.forName
      		e.printStackTrace();
   		}
	}

	public void reading(double value) throws SQLException{
		System.out.println("Creating statement...");
      	this.stmt = this.conn.createStatement();
      	String sql;
      	sql = "INSERT INTO `readings1` (`reading_Value`) VALUES ('"+ value +"');";
      	this.stmt.executeUpdate(sql);

	}

	public void garageOpen() throws SQLException{
		System.out.println("Creating statement...");
      	this.stmt = this.conn.createStatement();
      	String sql;
      	sql = "INSERT INTO `opened`() VALUES ();";
      	this.stmt.executeUpdate(sql);
	}
/*
  public void setReadings(int n)throws SQLException{
    this.stmt = this.conn.createStatement();
    ResultSet rs = this.stmt.executeQuery("SELECT  `reading_Id` ,  `reading_Value` , `timestamp` FROM `readings` ORDER BY `timestamp` DESC LIMIT 0,"+n);
    
    this.timestamps.clear();
    this.vals.clear();
    

    ArrayList dates = new ArrayList();

    while(!rs.isLast() && rs.next()){
      this.timestamps.add(rs.getString("timestamp"));
      this.vals.add(rs.getDouble("reading_Value"));
      rs.next();
    }

    for(int i=0; i<timestamps.size(); i++){
      java.util.Date date = new java.util.Date(timestamps.toString().get(i));
      dates.add(date.getTime());

    }
  }*/

  public ArrayList getTimestamps(){
    return this.timestamps;
  }

	public void closeConn() throws SQLException{
		rs.close();
      	stmt.close();
      	conn.close();
	}
}//end FirstExample