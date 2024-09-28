package cse.r.hef;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class LoginPage extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JButton loginButton;
//    private SessionFactory sessionFactory;
//
//    public LoginPage() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//
//        setTitle("Employee Management System - Login");
//        setLayout(new GridLayout(3, 2));
//
//        add(new JLabel("Username:"));
//        usernameField = new JTextField();
//        add(usernameField);
//
//        add(new JLabel("Password:"));
//        passwordField = new JPasswordField();
//        add(passwordField);
//
//        loginButton = new JButton("Login");
//        loginButton.addActionListener(new LoginButtonListener());
//        add(loginButton);
//
//        setSize(300, 150);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    private class LoginButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String username = usernameField.getText();
//            String password = new String(passwordField.getPassword());
//
//            // For simplicity, we assume successful login if username is "admin"
//            if ("admin".equals(username) && "admin".equals(password)) {
//                new EmployeePage(); // Open Employee Information Page
//                dispose(); // Close login page
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid credentials");
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new LoginPage();
//    }
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Employee Management System - Login");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Title Label
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Username Label
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Username:"), gbc);

        // Username Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Password:"), gbc);

        // Password Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new LoginButtonListener());
        add(loginButton, gbc);

        // Set frame properties
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Simple authentication check
            if ("admin".equals(username) && "admin".equals(password)) {
                new EmployeePage(); // Open Employee Management Page
                dispose(); // Close login page
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Set a more modern look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginPage();
    }
}
