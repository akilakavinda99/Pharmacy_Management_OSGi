package medicineproducer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import databaseservice.Database;
import databaseservice.DatabaseImpl;

public class MedicineServiceImpl implements MedicineService {

	private static Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet = null;
	private Database database;
	private String sql;
	private int isSuccess;

	public MedicineServiceImpl() {
		database = new DatabaseImpl();
		connection = database.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertMedicine(String name, float price, int qty) {

		isSuccess = 0;
		sql = "insert into medicine values(0, ?, ?, ?)";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);
			preparedStatement.setFloat(2, price);
			preparedStatement.setInt(3, qty);

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
	public void removeMedicine(int medicineID) {

		isSuccess = 0;
		sql = "delete from medicine where idmedicine='" + medicineID + "'";

		try {
			isSuccess = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Medicine ID: " + medicineID + ", successfully removed.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public void updateMedicine(int medicineID, String name, float price, int qty) {

		isSuccess = 0;
		sql = "update medicine set name = ?, price = ?, qty = ? where idmedicine = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);
			preparedStatement.setFloat(2, price);
			preparedStatement.setInt(3, qty);
			preparedStatement.setInt(4, medicineID);

			isSuccess = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isSuccess > 0) {
			System.out.println("Medicine ID: " + medicineID + ", successfully updated.\n");
		} else {
			System.out.println("Error! Please try again.\n");
		}

	}

	@Override
	public ArrayList<MedicineModel> viewMedicines() {

		ArrayList<MedicineModel> mediList = new ArrayList<>();
		sql = "select * from medicine";

		try {
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				float price = resultSet.getFloat(3);
				int qty = resultSet.getInt(4);

				MedicineModel medicineModel = new MedicineModel(id, name, price, qty);
				mediList.add(medicineModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mediList;
	}

	@Override
	public MedicineModel getMedicineDetails(int medicineID) {

		MedicineModel medicineModel = null;
		sql = "select * from medicine where idmedicine = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, medicineID);

			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			float price = resultSet.getFloat(3);
			int qty = resultSet.getInt(4);

			medicineModel = new MedicineModel(id, name, price, qty);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medicineModel;
	}

}
