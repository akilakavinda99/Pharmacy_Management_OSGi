package pharmacistproducer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import databaseservice.Database;
import databaseservice.DatabaseImpl;

public class PharmacistServiceImpl implements PharmacistService {

	private static Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet = null;
	private Database database;
	private String sql;
	private int isSuccess;

	public PharmacistServiceImpl() {
		database = new DatabaseImpl();
		connection = database.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPharmacist(String name, int phoneNumber, String email, String nic, String address, String password) {

		isSuccess = 0;
		sql = "insert into pharmacist values(0, ?, ?, ?, ?, ?, ?)";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, phoneNumber);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, nic);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, password);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println(name + " successfully inserted.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void removePharmacist(int pharmacistID) {

		isSuccess = 0;
		sql = "delete from pharmacist where idpharmacist = '" + pharmacistID + "'";

		try {
			isSuccess = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Pharmacist ID: " + pharmacistID + ", successfully removed.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void updatePharmacist(int pharmacistID, String name, int phoneNumber, String email, String nic,
			String address) {

		isSuccess = 0;
		sql = "update pharmacist set name = ?, phoneNumber = ?, email = ?, nic = ?, address = ? where idpharmacist = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, phoneNumber);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, nic);
			preparedStatement.setString(5, address);
			preparedStatement.setInt(6, pharmacistID);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Pharmacist ID: " + pharmacistID + ", successfully updated.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void updatePharmacistPass(int pharmacistID, String password) {

		isSuccess = 0;
		sql = "update pharmacist set password = ? where idpharmacist = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, pharmacistID);

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
	public ArrayList<PharmacistModel> viewPharmacists() {

		ArrayList<PharmacistModel> pharmacistList = new ArrayList<>();
		sql = "select * from pharmacist";

		try {
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				int phoneNumber = resultSet.getInt(3);
				String email = resultSet.getString(4);
				String nic = resultSet.getString(5);
				String address = resultSet.getString(6);

				PharmacistModel pharmacistModel = new PharmacistModel(id, name, phoneNumber, email, nic, address);
				pharmacistList.add(pharmacistModel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pharmacistList;
	}

	@Override
	public PharmacistModel getPharmacistDetails(int pharmacistID) {

		PharmacistModel pharmacistModel = null;
		sql = "select * from pharmacist where idpharmacist = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, pharmacistID);

			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			int phoneNumber = resultSet.getInt(3);
			String email = resultSet.getString(4);
			String nic = resultSet.getString(5);
			String address = resultSet.getString(6);

			pharmacistModel = new PharmacistModel(id, name, phoneNumber, email, nic, address);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pharmacistModel;

	}

}
