package main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class ConnectionWithDatabase {

	private String driver = "org.sqlite.JDBC";
	private String url = "jdbc:sqlite:C:\\cdbase.db"; // for demonstration purposes

	private java.sql.Connection sqliteConnection = null;

	public ConnectionWithDatabase(String url) throws ClassNotFoundException, SQLException {
		Class.forName(driver); // load sqlite driver
		this.url = url;
		sqliteConnection = DriverManager.getConnection(this.url, "", "");
	}
	
	public ArrayList<String> getSoldiersSurnames() throws SQLException {
		ArrayList<String> soldiersSurnames = this.getThisColumn("Surname", "SOLDIER");
		System.out.println(soldiersSurnames);
		return soldiersSurnames;
	}
	
	public Integer getGunIdHavingSurname(String soldierSurname) throws SQLException {

		Statement stmt = sqliteConnection.createStatement();
		// System.out.println(soldierSurname);
		
		String query = "SELECT Gun "
						+ "FROM SOLDIER "
						+ "WHERE SOLDIER.Id=("
						+ "    SELECT Id "
						+ "    FROM SOLDIER "
						+ "    WHERE Surname=\""+soldierSurname+"\""
						+ " )";
		ResultSet rs = stmt.executeQuery(query);
		Integer value = rs.getInt("Gun");
		stmt.close();
		rs.close();
		return value;
	}
	
	public Integer getSoldierIdHavingSurname(String quartermaster) throws SQLException {
		Statement stmt = sqliteConnection.createStatement();
		
		String query = "SELECT Id "
						+ "FROM SOLDIER "
						+ "WHERE Surname=\""+quartermaster+"\"";
		ResultSet rs = stmt.executeQuery(query);
		System.out.println(quartermaster);
		Integer value = rs.getInt("Id");
		stmt.close();
		rs.close();
		return value;
	}

	public void insertAMovement(String type, String rationale, String date, Integer gun, Integer quartermaster) throws SQLException {
		String[] fieldsArr = {"Type", "Gun", "Quartermaster", "Rationale", "Date"};
		ArrayList<String> fields = new ArrayList<>(Arrays.asList(fieldsArr));
		Object[] valuesArr = {type, gun, quartermaster, rationale, date};
		ArrayList<Object> values = new ArrayList<Object>(Arrays.asList(valuesArr));
		
		insertARow("MOVEMENT", fields, values);
	}
	
	// useful 
	public ArrayList<String> getThisColumn(String columnName, String table) throws SQLException {
		ArrayList<String> column = new ArrayList<>();

		Statement statement = sqliteConnection.createStatement();

		String query = " SELECT " + columnName + " FROM " + table;
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			column.add(rs.getString(columnName));
		}

		statement.close();
		rs.close();

		return column;
	}

	public void insertARow(String table, ArrayList<String> columns, ArrayList<Object> values) throws SQLException {
		/*
		 * execution of a query like ...
		 */

		Statement statement;
		statement = sqliteConnection.createStatement();
		String query = "INSERT " + "INTO " + table + "(";
		for (int i = 0; i < columns.size(); i++) {
			query = query.concat(columns.get(i)+",");
		}
		query = StringUtils.chop(query);
		query = query.concat(") " + "VALUES " + "(");
		for (int i = 0; i < values.size(); i++) {
			if(values.get(i) instanceof String) {
				query = query.concat("\""+values.get(i)+"\",");
			} else {
				query = query.concat(values.get(i)+",");
			}
			
		}
		query = StringUtils.chop(query);
		query = query.concat(")");
		System.out.println(query);
		statement.execute(query);
		statement.close();
	}
	
	public void CloseConnection() throws SQLException {
		sqliteConnection.close();
	}
	
	// old functions, they may be deleted later

	public void deleteARow(String table, String columnName, String valueOfField) throws SQLException {
		/*
		 * DELETE FROM product WHERE product.name='adsf'
		 */

		Statement statement;
		statement = sqliteConnection.createStatement();

		String query = "DELETE " + " FROM " + table + " WHERE " + table + "." + columnName + "=" + valueOfField;

		statement.execute(query);
	}

	public String getInfoOfARow(ArrayList<String> columnsName, String columnNameForRowName, String rowName,
			String table) throws SQLException {
		ArrayList<String> rowInArray = new ArrayList<>();
		String row = "";

		Statement statement;

		statement = sqliteConnection.createStatement();
		// System.out.println(columnNameForRowName);
		// System.out.println(rowName);

		String query = " SELECT * FROM " + table + " WHERE " + columnNameForRowName + " = " + rowName;
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			for (String column : columnsName)
				rowInArray.add(rs.getString(column));
		}

		rs.close();

		row = String.join(" ", rowInArray);

		return row;
	}

	public double getTheDoubleValueOfAFieldOfARow(String field, String columnNameForRowName, String rowName,
			String table) throws SQLException {

		Statement statement;

		statement = sqliteConnection.createStatement();

		String query = " SELECT " + field + " FROM " + table + " WHERE " + columnNameForRowName + "=" + rowName;
		ResultSet rs = statement.executeQuery(query);

		double value = Double.parseDouble(rs.getString(field));

		rs.close();

		return value;
	}

	public String getTheStringValueOfAFieldOfARow(String field, String columnNameForRowName, String rowName,
			String table) throws SQLException {

		Statement statement;

		statement = sqliteConnection.createStatement();

		String query = " SELECT " + field + " FROM " + table + " WHERE " + columnNameForRowName + "=" + rowName;
		ResultSet rs = statement.executeQuery(query);

		String value = rs.getString(field);

		rs.close();

		return value;
	}
}
