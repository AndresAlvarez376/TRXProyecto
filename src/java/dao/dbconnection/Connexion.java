package dao.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connexion {
	private static Connection con = null;
	public static Connection getConnection(){
		if(con != null){
			return con;
		}
		else {
			try {
			   Class.forName("com.mysql.jdbc.Driver");  
		    	    //String dbURL = "jdbc:sqlite::resource:res/communUrbaine1.sqlite";
		            String url = "jdbc:mysql://localhost:3306/trx_fitit";
		            String user = "root";
		            String passwd = "961104";

                    con = DriverManager.getConnection(url,user,passwd);
		            System.out.println("Connexion effective !");
				
			} catch (ClassNotFoundException cne) {
				System.out.println("***Driver***");
				cne.printStackTrace();
			} catch (SQLException e) {
				System.out.println("***SQLException***");
				System.out.println(e);
			}
			return con;
		}
	}
}
