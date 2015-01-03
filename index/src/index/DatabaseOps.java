package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOps {
	
	Connection con = null;
	
	public DatabaseOps( String dbclass, String cs, String uname, String password) {
		try {
			Class.forName(dbclass).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(cs, uname, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int dbinsert(String query) {
		int i = 0;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(query);
			i = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet dbselect(String query){
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}		    
		return rs;
	}
	
	public void dbclose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
