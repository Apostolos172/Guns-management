package main;

import java.nio.file.Paths;
import java.sql.SQLException;
import com.formdev.flatlaf.FlatDarkLaf;
import GUI.Main_GUI;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// FlatLightLaf.setup();
		FlatDarkLaf.setup();
		
		new Main_GUI("Gun Management");
		testing();
	}
	
	public static void testing() throws ClassNotFoundException, SQLException {
		// a method that uses methods from other classes in order to check their results 
		
		String path = "";
		path = "C:\\databases\\guns.db";
		// System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
		// path = "C:\\Users\\apost\\eclipse-workspace\\GunSystem\\guns.db";
		path = "guns.db";
		ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);
		conn.getSoldiersSurnames();
		conn.CloseConnection();
		hr();
		
	}
	
	public static void hr() {
		// print dashed horizontal ruler
		
		System.out.println("-----------------------------");
	}

}
