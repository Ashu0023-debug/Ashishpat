package cse.r.hef;
	
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	public class SimpleCrudApp extends JFrame {

	    private JTextField insertField1, insertField2, insertField3;
	    private JTextField deleteField;
	    private JTextField updateField1, updateField2;
	    private JTextArea outputArea;

	    private Connection connect() {
	        // MySQL connection string
	        String url = "jdbc:mysql://localhost:3306/ashish";
	        String user = "root";
	        String password = "Patleashish0023@@";
	        try {
	            return DriverManager.getConnection(url, user, password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public SimpleCrudApp() {
	        setTitle("Simple CRUD Application");
	        setSize(600, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // Creating components
	        JLabel labelInsert1 = new JLabel("Insert Field 1:");
	        insertField1 = new JTextField(15);
	        JLabel labelInsert2 = new JLabel("Insert Field 2:");
	        insertField2 = new JTextField(15);
	        JLabel labelInsert3 = new JLabel("Insert Field 3:");
	        insertField3 = new JTextField(15);
	        JButton insertButton = new JButton("Insert");

	        JLabel labelDelete = new JLabel("Delete Field:");
	        deleteField = new JTextField(15);
	        JButton deleteButton = new JButton("Delete");

	        JLabel labelUpdate1 = new JLabel("Update Field 1:");
	        updateField1 = new JTextField(15);
	        JLabel labelUpdate2 = new JLabel("Update Field 2:");
	        updateField2 = new JTextField(15);
	        JButton updateButton = new JButton("Update");

	        JButton viewButton = new JButton("View");
	        outputArea = new JTextArea(15, 40);
	        outputArea.setEditable(false);
	        outputArea.setLineWrap(true);
	        outputArea.setWrapStyleWord(true);

	        // Layout configuration
	        setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        // Insert Panel
	        JPanel insertPanel = new JPanel();
	        insertPanel.setBorder(BorderFactory.createTitledBorder("Insert Data"));
	        insertPanel.setLayout(new GridLayout(4, 2, 5, 5));
	        insertPanel.add(labelInsert1);
	        insertPanel.add(insertField1);
	        insertPanel.add(labelInsert2);
	        insertPanel.add(insertField2);
	        insertPanel.add(labelInsert3);
	        insertPanel.add(insertField3);
	        insertPanel.add(new JLabel()); // Empty label for alignment
	        insertPanel.add(insertButton);

	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 2;
	        add(insertPanel, gbc);

	        // Delete Panel
	        JPanel deletePanel = new JPanel();
	        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Data"));
	        deletePanel.setLayout(new GridLayout(2, 2, 5, 5));
	        deletePanel.add(labelDelete);
	        deletePanel.add(deleteField);
	        deletePanel.add(new JLabel()); // Empty label for alignment
	        deletePanel.add(deleteButton);

	        gbc.gridy = 1;
	        add(deletePanel, gbc);

	        // Update Panel
	        JPanel updatePanel = new JPanel();
	        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Data"));
	        updatePanel.setLayout(new GridLayout(3, 2, 5, 5));
	        updatePanel.add(labelUpdate1);
	        updatePanel.add(updateField1);
	        updatePanel.add(labelUpdate2);
	        updatePanel.add(updateField2);
	        updatePanel.add(new JLabel()); // Empty label for alignment
	        updatePanel.add(updateButton);

	        gbc.gridy = 2;
	        add(updatePanel, gbc);

	        // View Panel
	        JPanel viewPanel = new JPanel();
	        viewPanel.setBorder(BorderFactory.createTitledBorder("View Data"));
	        viewPanel.add(viewButton);

	        gbc.gridy = 3;
	        add(viewPanel, gbc);

	        // Output Area
	        JScrollPane scrollPane = new JScrollPane(outputArea);
	        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
	        gbc.gridy = 4;
	        gbc.gridwidth = 2;
	        add(scrollPane, gbc);

	        // Adding action listeners for buttons
	        insertButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String field1 = insertField1.getText();
	                String field2 = insertField2.getText();
	                String field3 = insertField3.getText();
	                try (Connection conn = connect();
	                     PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee VALUES (?,?,?)")) {
	                    pstmt.setString(1, field1);
	                    pstmt.setString(2, field2);
	                    pstmt.setString(3, field3);
	                    
	                    pstmt.executeUpdate();
	                    outputArea.append("Inserted: " + field1 + ", " + field2 + ", " + field3 + "\n");
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        deleteButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String field = deleteField.getText();
	                try (Connection conn = connect();
	                     PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employee WHERE Ename= ?")) {
	                    pstmt.setString(1, field);
	                    pstmt.executeUpdate();
	                    outputArea.append("Deleted: " + field + "\n");
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        updateButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String field1 = updateField1.getText();
	                String field2 = updateField2.getText();
	               
	                try (Connection conn = connect();
	                     PreparedStatement pstmt = conn.prepareStatement("UPDATE employee SET Ename = ? WHERE EID= ?")) {
	                    pstmt.setString(1, field2);
	                    pstmt.setString(2, field1);
	                    pstmt.executeUpdate();
	                    outputArea.append("Updated: " + field1 + " to " + field2 + "\n");
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        viewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try (Connection conn = connect();
	                     PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee");
	                     ResultSet rs = pstmt.executeQuery()) {
	                    outputArea.append("Viewing data...\n");
	                    while (rs.next()) {
	                        outputArea.append(rs.getString("EId") + ", " + rs.getString("Ename")+ ", " + rs.getString("EAdd") + "\n");
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new SimpleCrudApp().setVisible(true));
	    }
	}

