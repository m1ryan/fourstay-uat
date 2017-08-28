package com.fourstay.runners;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCode {
	
	private static ResultSet resultSet;

	public static void main(String[] args) throws SQLException {

		String phoneNumber = "5151234567";
		phoneNumber = phoneNumber.substring(0, 3) + "." + phoneNumber.substring(3, 6)
		+ "." + phoneNumber.substring(6);
		System.out.println(phoneNumber + " this is it");
	}

}
