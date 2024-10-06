package org.example.application;

import org.example.db.DB;
import org.example.db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramDeletarDados {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");

            preparedStatement.setInt(1, 2);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException exception) {
            throw new DbIntegrityException(exception.getMessage());
        } finally {
            DB.closeConnection();
            DB.closeStatement(preparedStatement);
        }

    }
}
