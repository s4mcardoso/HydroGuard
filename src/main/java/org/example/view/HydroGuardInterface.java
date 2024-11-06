package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.util.ConexaoMySQL;

public class HydroGuardInterface extends JFrame {
    private static final Logger logger = Logger.getLogger(HydroGuardInterface.class.getName());
    private JTextArea displayArea;

    public HydroGuardInterface() {
        setTitle("Monitoramento de Qualidade da Água");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JButton refreshButton = new JButton("Atualizar Dados");
        refreshButton.addActionListener(e -> fetchData());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void fetchData() {
        StringBuilder data = new StringBuilder();
        try {
            Connection connection = ConexaoMySQL.getConnection();
            String query = "SELECT * FROM WaterQuality";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                data.append("Sensor ID: ").append(resultSet.getString("sensor_id")).append("\n");
                data.append("Temperatura: ").append(resultSet.getFloat("temperature")).append("°C\n");
                data.append("pH: ").append(resultSet.getFloat("ph")).append("\n");
                data.append("Turbidez: ").append(resultSet.getFloat("turbidity")).append(" NTU\n\n");
            }

            displayArea.setText(data.toString());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar dados do banco de dados", e);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            HydroGuardInterface ex = new HydroGuardInterface();
            ex.setVisible(true);
        });
    }
}
