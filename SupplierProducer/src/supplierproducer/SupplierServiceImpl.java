package supplierproducer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import databaseservice.Database;
import databaseservice.DatabaseImpl;

public class SupplierServiceImpl implements SupplierService {

	private static Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet = null;
	private Database database;
	private String sql;
	private int isSuccess;

	public SupplierServiceImpl() {
		database = new DatabaseImpl();
		connection = database.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addSupplier(String name, String address, String email, String uname, String password) {

		isSuccess = 0;
		sql = "insert into supplier values(0, ?, ?, ?, ?, ?)";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, uname);
			preparedStatement.setString(5, password);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println(name + " successfully added.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void removeSupplier(int supplierID) {

		isSuccess = 0;
		sql = "delete from supplier where idSupplier = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, supplierID);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Supplier ID: " + supplierID + ", successfully removed.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void updateSupplier(int supplierID, String name, String address, String email, String uname) {

		isSuccess = 0;
		sql = "update medicine set name = ?, address = ?, email = ?, uname = ? where idSupplier = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, uname);
			preparedStatement.setInt(5, supplierID);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Supplier ID: " + supplierID + ", successfully updated.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void updateSupplierPass(int supplierID, String pass) {

		isSuccess = 0;
		sql = "update supplier set password = ? where idSupplier = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, pass);
			preparedStatement.setInt(2, supplierID);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Password successfully updated.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public ArrayList<SupplierModel> viewSuppliers() {

		ArrayList<SupplierModel> supplierList = new ArrayList<>();
		sql = "select * from supplier";

		try {
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String address = resultSet.getString(3);
				String email = resultSet.getString(4);
				String uname = resultSet.getString(5);
				String password = resultSet.getString(6);

				SupplierModel supplierModel = new SupplierModel(id, name, address, email, uname);
				supplierList.add(supplierModel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return supplierList;

	}

	@Override
	public SupplierModel getSupplierDetails(int supplierID) {

		SupplierModel supplierModel = null;
		sql = "select * from supplier where idSupplier = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, supplierID);

			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String address = resultSet.getString(3);
			String email = resultSet.getString(4);
			String uname = resultSet.getString(5);
			String password = resultSet.getString(6);

			supplierModel = new SupplierModel(id, name, address, email, uname);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return supplierModel;

	}

}
