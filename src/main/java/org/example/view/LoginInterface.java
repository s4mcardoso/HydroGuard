package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel messageLabel;

    public LoginInterface() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        messageLabel = new JLabel("", JLabel.CENTER);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            char[] password = passField.getPassword();

            // Credenciais de teste
            if ("admin".equals(username) && "admin".equals(new String(password))) {
                dispose();
                HydroGuardInterface mainInterface = new HydroGuardInterface();
                mainInterface.setVisible(true);
            } else {
                messageLabel.setText("Invalid credentials! Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginInterface login = new LoginInterface();
            login.setVisible(true);
        });
    }
}
