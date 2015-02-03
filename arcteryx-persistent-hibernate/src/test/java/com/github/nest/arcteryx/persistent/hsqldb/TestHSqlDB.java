/**
 * 
 */
package com.github.nest.arcteryx.persistent.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author brad.wu
 *
 */
public class TestHSqlDB {
	@BeforeClass
	public static void init() throws SQLException {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (Exception e) {
			System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
			e.printStackTrace();
			return;
		}
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
		Statement stat = conn.createStatement();
		stat.execute("create table person(NAME VARCHAR(20), AGE INTEGER)");
		System.out.println("create TABLE:person OK");

		stat.executeUpdate("INSERT INTO person VALUES('张三丰',22)");
		stat.executeUpdate("INSERT INTO person VALUES('amos','25')");
		System.out.println("insert data into TABLE:person OK!");

		conn.close();
	}
	
	@Test
	public void test() throws SQLException {
		Connection conn2 = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");

		// 查询数据
		PreparedStatement pstmt = conn2.prepareStatement("SELECT * FROM person");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String s = null;
			s = rs.getString(1) + "," + rs.getString(2);
			System.out.println(s);
		}
		System.out.println("select data OK");
	}
}
