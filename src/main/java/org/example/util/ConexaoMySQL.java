package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    public static Connection getConnection() throws SQLException {
        // URL de conexão, nome de usuário e senha
        String jdbcURL = "jdbc:mysql://localhost:3306/hydro_guard_db"; // 'localhost' aqui
        String dbUser = "root"; // Nome de usuário
        String dbPassword = "C@rdoso09092003"; // Sua senha

        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    }
}
