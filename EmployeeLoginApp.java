package cse.r.hef;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.*;

	public class EmployeeLoginApp extends JFrame {
	    private JTextField empIdField;
	    private JPasswordField passwordField;

	    public EmployeeLoginApp() {
	        // Frame setup
	        setTitle("Employee Login");
	        setSize(400, 250);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Center the window
	        setLayout(new BorderLayout());

	        // Create a panel for the form fields
	        JPanel formPanel = new JPanel();
	        formPanel.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        JLabel empIdLabel = new JLabel("Employee ID:");
	        empIdField = new JTextField(20);

	        JLabel passwordLabel = new JLabel("Password:");
	        passwordField = new JPasswordField(20);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        formPanel.add(empIdLabel, gbc);
	        gbc.gridx = 1;
	        formPanel.add(empIdField, gbc);

	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        formPanel.add(passwordLabel, gbc);
	        gbc.gridx = 1;
	        formPanel.add(passwordField, gbc);

	        add(formPanel, BorderLayout.CENTER);

	        // Create a panel for the login button
	        JPanel buttonPanel = new JPanel();
	        JButton loginButton = new JButton("Login");
	        loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                loginEmployee();
	            }
	        });
	        buttonPanel.add(loginButton);
	        add(buttonPanel, BorderLayout.SOUTH);

	        setVisible(true);
	    }

	    // Method to handle login logic
	    private void loginEmployee() {
	        String empId = empIdField.getText();
	        String password = new String(passwordField.getPassword());

	        if (empId.isEmpty() || password.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please enter both Employee ID and Password.");
	            return;
	        }

	        // Database connection
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ashish", "root", "Patleashish0023@@");
	             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Edata WHERE EmpID=? AND password=?")) {

	            pstmt.setString(1, empId);
	            pstmt.setString(2, password);

	            ResultSet rs = pstmt.executeQuery();
 
	            if (rs.next()) {
	                JOptionPane.showMessageDialog(this, "Login successful!");
	                dispose(); // Close the login window
	                new SimpleCrudApp(); // Proceed to the main application or dashboard
	            } else {
	                JOptionPane.showMessageDialog(this, "Invalid Employee ID or Password.");
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(EmployeeLoginApp::new);
	    }
	}


