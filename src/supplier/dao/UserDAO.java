package supplier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import supplier.model.Report;
import supplier.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_supplier_SQL = "INSERT INTO supplier" + "  (name, email, company) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,company from supplier where id =?";
	private static final String SELECT_ALL_SUPPLIERS = "select * from supplier";
	private static final String GET_SUPP_STAT = "SELECT SUM(o.finalPrice) as cost, s.ID, s.email, s.company, s.Name FROM orders o Left join supplier  s on s.ID = o. \n" +
			"GROUP BY o.supplierId\n" +
			"ORDER BY SUM(o.finalPrice) DESC";// TODO
	private static final String DELETE_supplier_SQL = "delete from supplier where id = ?;";
	private static final String UPDATE_supplier_SQL = "update supplier set name = ?,email= ?, company =? where id = ?;";

	public UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_supplier_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_supplier_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCompany());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public List<Report> getReport() {

		List<Report> report = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			 // Step 2:Create a statement using connection object
			 PreparedStatement preparedStatement = connection.prepareStatement(GET_SUPP_STAT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String company = rs.getString("company");
				double price = rs.getDouble("cost");
//				report = new Report(name, price, email, company,id);
				report.add(new Report(name, price, email, company,id));
			}
		} catch (Exception e) {
//			pr(e);
		}
		return report;
	}
	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String company = rs.getString("company");
				user = new User(id, name, email, company);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllSuppliers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> supplier = new ArrayList<>();
		// Step 1: Establishing a Connection
		// Step 2:Create a statement using connection object
		try (Connection connection = getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUPPLIERS)) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String company = rs.getString("company");
					supplier.add(new User(id, name, email, company));
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return supplier;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_supplier_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_supplier_SQL);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCompany());
			statement.setInt(4, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
