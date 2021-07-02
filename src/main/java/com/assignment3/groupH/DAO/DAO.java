package com.assignment3.groupH.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Dalveer Singh
 * 
 * @Date 28/06/2021
 * 
 * @Description this is singleton class for creating connection only once for all DAOs
 * 
 */

public class DAO {
	
	public final static int SUCCESS = 0;
	public final static int ALREADY_EXISTS = 1;
	public final static int NO_DATA = 2;
	public final static int FAIL = 3;
	public final static int NOT_EXISTS = 1;
	
	private final static String url = "jdbc:mysql://127.0.0.1:3307/assignment3?serverTimezone=UTC";
	private final static String user = "root";
	private final static String password = "password";

	private static Connection con = null;
	
	// this class cannot be instantiated
	private DAO() {

		if (con == null) {

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");

				con = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}
	}
	
	// get connection
	public static Connection getConnection() {

		new DAO();

		return con;
	}
	
	// close connection
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

}
