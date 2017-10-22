package com.yu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) throws Exception {
		 String driver = "com.mysql.jdbc.Driver";
		 String url = "jdbc:mysql://192.168.10.30/bak_log";
		 String user = "log";
		 String password = "1234";
//		 String driver = "com.mysql.jdbc.Driver";
//		 String url = "jdbc:mysql://192.168.10.183/edge";
//		 String user = "edge";
//		 String password = "1234";

		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		//String user = "scott";
		//String password = "tiger";

		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);

		String sql = "select * from log";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			if (resultSet.next()) {
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
					System.out.print(columnLabel + "\t");
				}
				System.out.println("");

				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					Object columnValue = resultSet.getObject(i + 1);
					System.out.print(columnValue + "\t");
				}
				System.out.println("");

				while (resultSet.next()) {
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						Object columnValue = resultSet.getObject(i + 1);
						System.out.print(columnValue + "\t");
					}
					System.out.println("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}
	}
}
