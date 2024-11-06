package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.model.QualidadedeAgua;
import org.example.util.ConexaoMySQL;
import org.example.view.LoginInterface;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginInterface login = new LoginInterface();
            login.setVisible(true);
        });

        QualidadedeAgua leitura = new QualidadedeAgua("sensor_1", 25.3f, 7.2f, 0.8f, "2024-11-06 12:00:00");
        int rowsInserted = insertData(leitura);
        if (rowsInserted > 0) {
            System.out.println("Dados inseridos com sucesso!");
        }
    }

    private static int insertData(QualidadedeAgua leitura) {
        String insertSQL = "INSERT INTO WaterQuality (sensor_id, temperature, ph, turbidity, timestamp) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, leitura.getSensorId());
            preparedStatement.setFloat(2, leitura.getTemperature());
            preparedStatement.setFloat(3, leitura.getPh());
            preparedStatement.setFloat(4, leitura.getTurbidity());
            preparedStatement.setString(5, leitura.getTimestamp());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir dados no banco de dados", e);
            return 0;
        }
    }
}
