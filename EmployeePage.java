package cse.r.hef;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//public class employees extends JFrame {
//    private JTextField enameField;
//    private JTextField emailField;
//    private JTextField addressField;
//    private JTextField workRoleField;
//    private JButton saveButton;
//    private JButton loadButton;
//    private SessionFactory sessionFactory;
//
//    public EmployeePage() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//
//        setTitle("Employee Information");
//        setLayout(new GridLayout(6, 2));
//
//        add(new JLabel("Name:"));
//        enameField = new JTextField();
//        add(enameField);
//
//        add(new JLabel("Email:"));
//        emailField = new JTextField();
//        add(emailField);
//
//        add(new JLabel("Address:"));
//        addressField = new JTextField();
//        add(addressField);
//
//        add(new JLabel("Work Role:"));
//        workRoleField = new JTextField();
//        add(workRoleField);
//
//        saveButton = new JButton("Save");
//        saveButton.addActionListener(new SaveButtonListener());
//        add(saveButton);
//
//        loadButton = new JButton("Load");
//        loadButton.addActionListener(new LoadButtonListener());
//        add(loadButton);
//
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true); 
//    }
//
//    private class SaveButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try (Session session = sessionFactory.openSession()) {
//                Transaction tx = session.beginTransaction();
//                employees employee = new employees();
//                employee.setEname(enameField.getText());
//                employee.setEmail(emailField.getText());
//                employee.setEaddress(addressField.getText());
//                employee.setWorkRole(workRoleField.getText());
//                session.save(employee);
//                tx.commit();
//                JOptionPane.showMessageDialog(null, "Employee saved successfully!");
//            }
//        }
//    }
//
//    private class LoadButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try (Session session = sessionFactory.openSession()) {
//                // Load employee with a specific ID (for simplicity, using ID 1)
//                employees employee = session.get(employees.class, 1L);
//                if (employee != null) {
//                    enameField.setText(employee.getEname());
//                    emailField.setText(employee.getEmail());
//                    addressField.setText(employee.getEaddress());
//                    workRoleField.setText(employee.getWorkRole());
//                } else {
//                    JOptionPane.showMessageDialog(null, "Employee not found.");
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new EmployeePage();
//    }
//import javax.swing.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class EmployeePage extends JFrame {
    private JTextField idField, nameField, addressField, workRoleField, emailField;
    private DefaultTableModel tableModel;
    private JTable table;

    public EmployeePage() {
        setTitle("Employee Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));

        // Apply a new modern color scheme with gradient background
        Color startColor = new Color(0xF5F7FA);
        Color endColor = new Color(0xC3CFE2);
        GradientPanel gradientPanel = new GradientPanel(startColor, endColor);
        gradientPanel.setLayout(new BorderLayout(10, 10));
        setContentPane(gradientPanel);

        // Title Label
        JLabel titleLabel = new JLabel("Employee Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0x3C3B54));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel with new design
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0x3C3B54)), "Employee Details"));
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);

        JLabel workRoleLabel = new JLabel("Work Role:");
        workRoleField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(workRoleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(workRoleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        add(formPanel, BorderLayout.WEST);

        // Button Panel with flat design
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(0xC3CFE2));

        JButton addButton = createButton("Add", new Color(0x6C5CE7));
        JButton showButton = createButton("Show", new Color(0x74B9FF));
        JButton updateButton = createButton("Update", new Color(0x00CEC9));
        JButton deleteButton = createButton("Delete", new Color(0xFF7675));

        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Table Panel with a clean look
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Address", "Work Role", "Email"}, 0);
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(0xF1F1F1) : Color.WHITE);
                }
                return c;
            }
        };
        table.setRowHeight(25);
        table.setFillsViewportHeight(true);

        // Custom table header with modern style
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0x3C3B54));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Verdana", Font.BOLD, 14));

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0x3C3B54)), "Employee Records"));
        add(tableScrollPane, BorderLayout.EAST);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadEmployeeTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        setVisible(true);
    }

    // Method to create a custom button with hover effects
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(100, 40));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    private void addEmployee() {
        String id = idField.getText();
        String name = nameField.getText();
        String address = addressField.getText();
        String workRole = workRoleField.getText();
        String email = emailField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "RainB@12.");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO edata (id,name, address, workRole, email) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, workRole);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding employee: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = idField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            String workRole = workRoleField.getText();
            String email = emailField.getText();

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "RainB@12.");
                 PreparedStatement pstmt = conn.prepareStatement("UPDATE edata SET id=?, name=?, address=?, workRole=?, email=? WHERE id=?")) {
                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, address);
                pstmt.setString(4, workRole);
                pstmt.setString(5, email);
                pstmt.setString(6, id);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating employee: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "RainB@12.");
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM edata WHERE id=?")) {
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting employee: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    private void loadEmployeeTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "RainB@12.");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM edata")) {

            tableModel.setRowCount(0); // Clear the table before loading new data

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("address"));
                row.add(rs.getString("workRole"));
                row.add(rs.getString("email"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage());
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        workRoleField.setText("");
        emailField.setText("");
    }

    // Custom JPanel to create a gradient background
    class GradientPanel extends JPanel {
        private Color startColor;
        private Color endColor;

        public GradientPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            GradientPaint gp = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeePage());
    }
}
