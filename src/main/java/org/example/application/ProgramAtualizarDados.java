package org.example.application;

import org.example.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramAtualizarDados {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(DepartmentId = ?)");

            preparedStatement.setDouble(1, 200);
            preparedStatement.setInt(2, 2);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DB.closeConnection();
            DB.closeStatement(preparedStatement);
        }

    }
}
