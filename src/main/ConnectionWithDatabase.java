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
	
	public String getGunsNumber() throws SQLException {
		Statement stmt = sqliteConnection.createStatement();
		
		String query = "SELECT count(*) AS guns\n"
				+ "FROM GUN";
		
		ResultSet rs = stmt.executeQuery(query);
		Integer value = rs.getInt("guns");
		stmt.close();
		rs.close();
		return value.toString();
	}
	
	public boolean isGunOut(Integer gunId) throws SQLException {		
		Statement stmt = this.sqliteConnection.createStatement();
		
		String query = "SELECT count(*) movements_of_the_gun\r\n"
				+ "FROM MOVEMENT\r\n"
				+ "WHERE Gun = " + gunId;
		
		ResultSet rs = stmt.executeQuery(query);
		Integer movements_of_the_gun = rs.getInt("movements_of_the_gun");
		// gun out where the movements are odd - περιττός
		boolean isGunOut = (movements_of_the_gun % 2 == 0) ? false : true;
        
		rs.close();
		stmt.close();
		
		return isGunOut;
	}
	
	public String getGunsMissingNumber() throws SQLException {
		Statement stmt = sqliteConnection.createStatement();
		
		String query = "SELECT count(*) AS inputGuns\n"
				+ "FROM MOVEMENT\n"
				+ "WHERE Type=\"input\"";
		
		ResultSet rs = stmt.executeQuery(query);
		Integer value1 = rs.getInt("inputGuns");
		
		query = "SELECT count(*) AS outputGuns\n"
				+ "FROM MOVEMENT\n"
				+ "WHERE Type=\"output\"";
		
		rs = stmt.executeQuery(query);
		Integer value2 = rs.getInt("outputGuns");
		
		Integer value = Math.abs(value1 - value2);
		
		stmt.close();
		rs.close();
		return value.toString();
	}

	public void insertAMovement(String type, String rationale, String date, Integer gun, Integer quartermaster) throws SQLException {
		String[] fieldsArr = {"Type", "Gun", "Quartermaster", "Rationale", "Date"};
		ArrayList<String> fields = new ArrayList<>(Arrays.asList(fieldsArr));
		Object[] valuesArr = {type, gun, quartermaster, rationale, date};
		ArrayList<Object> values = new ArrayList<Object>(Arrays.asList(valuesArr));
		
		insertARow("MOVEMENT", fields, values);
	}
	
	public String[][] getOutputMovements() throws SQLException {
		// columns of result set as retrieved by the following query
		String[] fieldsArr = {"Type", "Number", "Soldier_Surname", "Rationale", "Date"};
		ArrayList<String> fields = new ArrayList<>(Arrays.asList(fieldsArr));
		
		Statement stmt = this.sqliteConnection.createStatement();
		String query = "SELECT Type, Number, Soldier_Surname, Rationale, Date\n"
				+ "FROM ( \n"
				+ "    SELECT Id, Gun, Rationale, Date\n"
				+ "    FROM MOVEMENT\n"
				+ "    WHERE Type = \"output\"\n"
				+ ") as movements \n"
				+ "JOIN (\n"
				+ "    SELECT Type, Number, Gun, Surname AS Soldier_Surname\n"
				+ "    FROM SOLDIER \n"
				+ "    JOIN GUN\n"
				+ "    ON SOLDIER.Gun = GUN.Id\n"
				+ ") as soldiers\n"
				+ "ON movements.Gun = soldiers.Gun\n"
				+ "ORDER BY movements.Id DESC";
		
		// add data of each row as arraylist of the arraylist of all data
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<ArrayList<String>> dataList = new ArrayList<>(); 
		ArrayList<String> rowArrayList = null;
		// System.out.println();
		while(rs.next()) {
			rowArrayList = new ArrayList<>();
			for (String column : fields) {
				rowArrayList.add(rs.getString(column));
			}
			//System.out.println(rowArrayList);
			dataList.add(rowArrayList);
		}
				
		// System.out.println(dataList.size());
		// run second time the query and then αφού έχεις ορίσει το κατάλληλο μέγεθος τότε
		// πήγαινε και συμπλήρωσε τον πίνακα για να μην έχει null και να είσαι ακριβής στο μέγεθος
		
		rs = stmt.executeQuery(query);
		String[][] data = new String[dataList.size()][fields.size()];
		int counter = 0;
		while(rs.next()) {
			for (int i = 0; i < fields.size(); i++) {
				data[counter][i] = rs.getString(fields.get(i));
			}
			counter++;
		}
		
//		for (int i = 0; i < data.length; i++) {
//			for (int j = 0; j < data[i].length; j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		// System.out.println(dataList);

		stmt.close();
		rs.close();
		return data;

	}
	
	public String[][] getInputMovements() throws SQLException {
		// columns of result set as retrieved by the following query
		String[] fieldsArr = {"Type", "Number", "Quartermaster_Surname", "Rationale", "Date"};
		ArrayList<String> fields = new ArrayList<>(Arrays.asList(fieldsArr));
		
		Statement stmt = this.sqliteConnection.createStatement();
		String query = "SELECT Type, Number, Quartermaster_Surname, Rationale, Date\n"
				+ "FROM ( \n"
				+ "    SELECT Id, Gun, Quartermaster, Rationale, Date\n"
				+ "    FROM MOVEMENT\n"
				+ "    WHERE Type = \"input\"\n"
				+ ") as movements JOIN (\n"
				+ "    SELECT Id, Surname AS Quartermaster_Surname\n"
				+ "    FROM SOLDIER\n"
				+ ") as soldiers\n"
				+ "ON movements.Quartermaster = soldiers.Id\n"
				+ "JOIN (\n"
				+ "    SELECT Id, Type, Number\n"
				+ "    FROM GUN\n"
				+ ") as guns\n"
				+ "ON movements.Gun = guns.Id\n"
				+ "ORDER BY movements.Id DESC;";
		
		// add data of each row as arraylist of the arraylist of all data
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<ArrayList<String>> dataList = new ArrayList<>(); 
		ArrayList<String> rowArrayList = null;
		while(rs.next()) {
			rowArrayList = new ArrayList<>();
			for (String column : fields) {
				rowArrayList.add(rs.getString(column));
			}
			//System.out.println(rowArrayList);
			dataList.add(rowArrayList);
		}
				
		// System.out.println(dataList.size());
		// run second time the query and then αφού έχεις ορίσει το κατάλληλο μέγεθος τότε
		// πήγαινε και συμπλήρωσε τον πίνακα για να μην έχει null και να είσαι ακριβής στο μέγεθος
		
		rs = stmt.executeQuery(query);
		// String[][] data = new String[8][5];
		String[][] data = new String[dataList.size()][fields.size()];
		int counter = 0;
		while(rs.next()) {
			for (int i = 0; i < fields.size(); i++) {
				data[counter][i] = rs.getString(fields.get(i));
			}
			counter++;
		}
		
//		for (int i = 0; i < data.length; i++) {
//			for (int j = 0; j < data[i].length; j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		// System.out.println(dataList);

		stmt.close();
		rs.close();
		return data;
	}
	
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
