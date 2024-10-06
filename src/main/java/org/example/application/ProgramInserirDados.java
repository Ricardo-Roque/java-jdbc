package org.example.application;

import org.example.db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInserirDados {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO seller " +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "Ricardo Roque");
            preparedStatement.setString(2, "ricardodebonaroque@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(sdf.parse("02/05/2002").getTime()));
            preparedStatement.setDouble(4, Double.parseDouble("4000"));
            preparedStatement.setInt(5, 4);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }
        } catch (SQLException exception) {
            exception.getMessage();
        } catch (ParseException exception) {
            exception.getMessage();
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }

    }
}
