////package cse.r.hef;
////
////import org.hibernate.Session;
////import org.hibernate.SessionFactory;
////import org.hibernate.cfg.Configuration;
////import org.hibernate.Transaction;
////
////public class App 
////{
////    public static void main( String[] args )
////    {
////////    	emp_details emp = new emp_details();
////////    	emp.setDepartment("Sales");
////////    	emp.setEmail("xyz@gmail.com");
////////    	
////    	emloyees em = new emloyees();
////    	em.setId(6876);
////    	em.setFname("tarun");
////    	em.setLname("bankar");
////    	em.setEid(666);
////    	em.setEd(emp);
//////    	student st = new student();
//////    	st.setSname("Shushama");
//////    	st.setYear(3384);
//////    	st.setRollno(104);
//////    	
//////    	pc p = new pc();
//////    	p.setPname("Mac");
//////    	p.setPid(47);
//////    	p.setStd(st);
//////    	
//////    	st.getP().add(p);
//////    	
//////    	
//////    	
//////    	
////    	Configuration con = new Configuration().configure().addAnnotatedClass(student.class).addAnnotatedClass(pc.class);
////    	SessionFactory sf = con.buildSessionFactory();
//////    	
////    	Session s = sf.openSession();
////    	Transaction tx = s.beginTransaction();
//////    	
//////    	
//////    	s.save(p);
//////    	s.save(st);
//////    	
//////    	
//////    	tx.commit();
////    	
////    	Configuration con = new Configuration().configure().addAnnotatedClass(employees.class);
////    	SessionFactory sf = con.buildSessionFactory();
//////    	
////    	Session s = sf.openSession();
////    	Transaction tx = s.beginTransaction();
////    	
////    	
////    }
////}
//package cse.r.hef;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//
//public class App 
//{
//	 public static void main( String[] args )
//	    {
//	employees emp = new employees();
//	emp.setEid(874);
//	emp.setEname("jfjdbvj");
//	emp.setEaddress("nagpur");
//	emp.setWorkrole("manager");
//	emp.setEmail("xyz.gmail.com");
//
//	
//	
//	
//	Configuration con = new Configuration().configure().addAnnotatedClass(employees.class);
//	SessionFactory sf = con.buildSessionFactory();
//	
//	Session s = sf.openSession();
//	Transaction tx = s.beginTransaction();
//	
//	s.save(emp);
//	
//	tx.commit();
//	
//	    }
//}

package cse.r.hef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private SessionFactory sessionFactory;

    public App() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        setTitle("Employee Management System - Login");
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        add(loginButton);

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // For simplicity, we assume successful login if username is "admin"
            if ("admin".equals(username) && "admin".equals(password)) {
                new EmployeePage(); // Open Employee Information Page
                dispose(); // Close login page
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials");
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
