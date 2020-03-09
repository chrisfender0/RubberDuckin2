package alpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class Controller {
	
	private Model model;
	private View view;
	String url = "jdbc:mysql://127.0.0.1:3306/rubberduckin";
	String user = "default";
	String pass = "cova";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	ResultSetMetaData rsmd = null;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		try {
			taskManager();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initConnection() throws SQLException {
		Properties props = new Properties();
		props.put("user", user);
		props.put("password", pass);
		conn = DriverManager.getConnection(url, props);
		conn.setAutoCommit(false);
	}
	
	private void taskManager() throws IOException, SQLException {
		view.menuOptions();
		initConnection();
		processUserInput();
		closeResources();
		closeConnection();
	}
	
	private String getUserInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput = br.readLine();
		br.close();
		return userInput;
	}
	
	private void processUserInput() throws IOException, SQLException {
		int userInput = Integer.parseInt(getUserInput());
		switch (userInput) {
		case 1: {
			executeSimpleQuery(model.selectAllFromProduct());
			view.viewStringBuilder(showResults());
			closeResources();
			break;
		}
		case 2:{
			executeSimpleQuery(model.selectAllFromOrders());
			view.viewStringBuilder(showResults());
			closeResources();
			break;
		}
		case 3:{
			executeSimpleQuery(model.selectAllFromCustomers());
			view.viewStringBuilder(showResults());
			closeResources();
			break;
		}
		default: 
			break;
		}
	}
	
	private void executeSimpleQuery(String str) throws SQLException {
		pstmt = conn.prepareStatement(str);
		pstmt.execute();
		rset = pstmt.getResultSet();
		rsmd = rset.getMetaData();
	}
	
	private StringBuilder showResults() throws SQLException {
		int columnCount = rsmd.getColumnCount();
		StringBuilder sb = new StringBuilder();
		
		while(rset.next()) {
			for(int i=1; i<=columnCount; i++) {
				if(i==1) {
					sb.append(String.format("%s", rsmd.getColumnName(i)));
				} else {
					sb.append(String.format("%20s", rsmd.getColumnName(i)));
				}
			}
		}
		
		sb.append("\n\n");
		rset.first();
		
		while(rset.next()) {
			
			for(int i=1; i<=columnCount; i++) {
				if(i==1) {
					sb.append(String.format("%s", rset.getString(i)));
				} else {
					sb.append(String.format("%20s", rset.getString(i)));
				}
			}
			sb.append("\n");
		}
		return sb;
	}
	
//	private StringBuilder showResults() throws SQLException {
//		int columnCount = rsmd.getColumnCount();
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("\n");
//		
//		while(rset.next()) {
//			for(int i=1; i<columnCount; i++) {
//				sb.append(rsmd.getColumnName(i) + "\t");
//				sb.append(rset.getString(i));
//				sb.append("\n");
//			}
//			sb.append("\n");
//		}
//		
//		return sb;
//		
//	}
	
	private StringBuilder showResultsTableFormat() throws SQLException {
		int columnCount = rsmd.getColumnCount();
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<columnCount; i++) {
			sb.append(rsmd.getColumnName(i) + "\t\t");
		}
		
		sb.append("\n*****************************************************************\n");
		
		while(rset.next()) {
			for(int i=1; i<columnCount; i++) {
				sb.append(String.format("%-5s\t\t", rset.getString(i)));
			}
			sb.append("\n");
		}
		
		return sb;
		
	}
	
	private void closeConnection() throws SQLException {
		conn.close();
	}
	
	private void closeResources() throws SQLException {
		pstmt.close();
		rset.close();
	}
	
}
