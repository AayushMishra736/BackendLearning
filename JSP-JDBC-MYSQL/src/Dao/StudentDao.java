package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Student;

public class StudentDao {

	public int StudentRegistartion(Student student) throws ClassNotFoundException {
		String INSERT_QUERY = "Insert into Student"
				+ "  (id, first_name, last_name, username, password, address, contact) VALUES "
				+ " (?, ?, ?, ?, ?,?,?);";

		int result = 0;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "root");
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getLastName());
			preparedStatement.setString(4, student.getUsername());
			preparedStatement.setString(5, student.getPassword());
			preparedStatement.setString(6, student.getAddress());
			preparedStatement.setString(7, student.getContact());

			System.out.println(preparedStatement);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;

	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();

				}
			}
		}
	}
}
