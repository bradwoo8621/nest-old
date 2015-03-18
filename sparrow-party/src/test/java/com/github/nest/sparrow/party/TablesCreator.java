/**
 * 
 */
package com.github.nest.sparrow.party;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author brad.wu
 *
 */
public class TablesCreator {
	public static void create() throws Exception {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:memdb", "username", "password");
		Statement stat = conn.createStatement();
		stat.execute("create table T_PARTY(PARTY_ID BIGINT, "//
				+ "PARTY_TYPE VARCHAR(1), "//
				+ "PARTY_NAME VARCHAR(100), "//
				+ "PARTY_CODE VARCHAR(10), "//
				+ "PARTY_ENABLED INT, "//
				+ "CREATE_TIME TIMESTAMP, "//
				+ "CREATE_USER_ID BIGINT, "//
				+ "LAST_MODIFY_USER_ID BIGINT, "//
				+ "LAST_MODIFY_TIME TIMESTAMP, "//
				+ "OPTIMISTIC_LOCK BIGINT, "//
				+ "ID_NUMBER VARCHAR(30), "//
				+ "FIRST_NAME VARCHAR(20), "//
				+ "MIDDLE_NAME VARCHAR(20), "//
				+ "LAST_NAME VARCHAR(20), "//
				+ "GENDER_CODE VARCHAR(1), "//
				+ "INDUSTRY_CODE VARCHAR(3), "//
				+ "DATE_OF_BIRTH DATE, "//
				+ "DATE_OF_DEATH DATE, "//
				+ "BORN_IN_COUNTRY_CODE VARCHAR(3), "//
				+ "NATIONALITY_CODE VARCHAR(3))");
		stat.execute("create sequence S_PARTY AS BIGINT start with 1");

		stat.execute("create table T_PARTY_ADDRESS(ADDRESS_ID BIGINT, "//
				+ "PARTY_ID BIGINT, "//
				+ "POSTCODE VARCHAR(10), "//
				+ "DISTRICT_CODE VARCHAR(5), "//
				+ "CITY_CODE VARCHAR(5), "//
				+ "PROVINCE_CODE VARCHAR(5), "//
				+ "COUNTRY_CODE VARCHAR(3), "//
				+ "ADDRESS_LINE1 VARCHAR(30), "//
				+ "ADDRESS_LINE2 VARCHAR(30), "//
				+ "ADDRESS_LINE3 VARCHAR(30), "//
				+ "ADDRESS_LINE4 VARCHAR(30), "//
				+ "ADDRESS_LINE5 VARCHAR(30), "//
				+ "TELEPHONE VARCHAR(20), " //
				+ "FAX VARCHAR(20), " //
				+ "IS_ENABLED INT)");
		stat.execute("create sequence S_PARTY_ADDRESS AS BIGINT start with 1");

		stat.execute("create table T_PARTY_ROLE(PARTY_ROLE_ID BIGINT, "//
				+ "PARTY_ROLE_CODE VARCHAR(10), "//
				+ "PARTY_ROLE_ENABLED INT, " //
				+ "PARTY_ROLE_TYPE VARCHAR(3), "//
				+ "PARTY_ID BIGINT, "//
				+ "CREATE_TIME TIMESTAMP, "//
				+ "CREATE_USER_ID BIGINT, "//
				+ "LAST_MODIFY_USER_ID BIGINT, "//
				+ "LAST_MODIFY_TIME TIMESTAMP, "//
				+ "OPTIMISTIC_LOCK BIGINT)");
		stat.execute("create sequence S_PARTY_ROLE AS BIGINT start with 1");

		stat.execute("create table T_MY_EMPLOYEE(MY_EMPLOYEE_ID BIGINT, "//
				+ "BRANCH_ID BIGINT, "//
				+ "DEPARTMENT_ID BIGINT)");

		stat.execute("create table T_MY_BRANCH(MY_BRANCH_ID BIGINT, "//
				+ "PARENT_BRANCH_ID BIGINT, "//
				+ "IS_HEAD_OFFICE INT)");

		stat.execute("create table T_MY_DEPARTMENT(MY_DEPARTMENT_ID BIGINT, "//
				+ "BRANCH_ID BIGINT, "//
				+ "PARENT_DEPARTMENT_ID BIGINT)");

		conn.commit();
		conn.close();
	}
}
