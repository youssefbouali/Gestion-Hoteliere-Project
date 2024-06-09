import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GestionDB {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "manager1";
    private static final String PASS = "123456";
	
	
    private JTextField txtType;
    private JPanel panelEmployes;

    private JFrame frame;
    private JTextField txtDeptName;
    private JTextField txtPrenom; // New text field for Prenom
    private JTextField txtNom;    // New text field for Nom
    private JTextArea txtAreaDepts;
    private JButton btnAdd;
    private JButton btnView;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Set a modern look and feel
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                GestionDB window = new GestionDB();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionDB() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Hotel GestionDB");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
		
		showIndexPanel();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnEntities = new JMenu("Menu");
        menuBar.add(mnEntities);

        JMenuItem mntmDepartments = new JMenuItem("Departments");
        mntmDepartments.addActionListener(e -> { showDepartmentPanel(); viewDepartments(e); });
        mnEntities.add(mntmDepartments);

        JMenuItem mntmPosts = new JMenuItem("Posts");
        mntmPosts.addActionListener(e -> { showPostsPanel(); viewPosts(e); });
        mnEntities.add(mntmPosts);

        JMenuItem mntmEmployes = new JMenuItem("Employes");
		mntmEmployes.addActionListener(e -> { showEmployesPanel(); viewEmployes(e); });
        mnEntities.add(mntmEmployes);

        JMenuItem mntmMachines = new JMenuItem("Machines");
        mntmMachines.addActionListener(e -> { showMachinesPanel(); viewMachines(e); });
        mnEntities.add(mntmMachines);
    }

    private void showIndexPanel() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        txtAreaDepts = new JTextArea();
        txtAreaDepts.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaDepts);
        panel.add(scrollPane, BorderLayout.CENTER);
        
		txtAreaDepts.setText("Bienvenu dans Systeme de Gestion Hoteliere");

        frame.revalidate();
        frame.repaint();
    }

    private void showDepartmentPanel() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblDeptName = new JLabel("Department Name:");
        txtDeptName = new JTextField(20);

        btnAdd = new JButton("Ajouter Department");
        btnAdd.addActionListener(this::addDepartment);

        inputPanel.add(lblDeptName);
        inputPanel.add(txtDeptName);
        inputPanel.add(btnAdd);

        txtAreaDepts = new JTextArea();
        txtAreaDepts.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaDepts);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Afficher Departments");
        btnView.addActionListener(this::viewDepartments);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void showPostsPanel() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblPostName = new JLabel("Post Name:");
        txtDeptName = new JTextField(20);

        btnAdd = new JButton("Ajouter Post");
        btnAdd.addActionListener(this::addPost);

        inputPanel.add(lblPostName);
        inputPanel.add(txtDeptName);
        inputPanel.add(btnAdd);

        txtAreaDepts = new JTextArea();
        txtAreaDepts.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaDepts);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Afficher Posts");
        btnView.addActionListener(this::viewPosts);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void showEmployesPanel() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblPrenom = new JLabel("Prenom:");
        txtPrenom = new JTextField(15);
        JLabel lblNom = new JLabel("Nom:");
        txtNom = new JTextField(15);
        JLabel lblType = new JLabel("Type:");
        txtType = new JTextField(15);

        btnAdd = new JButton("Ajouter Employe");
        btnAdd.addActionListener(this::addEmploye);

        inputPanel.add(lblPrenom);
        inputPanel.add(txtPrenom);
        inputPanel.add(lblNom);
        inputPanel.add(txtNom);
        inputPanel.add(lblType);
        inputPanel.add(txtType);
        inputPanel.add(btnAdd);

        panelEmployes = new JPanel();
        panelEmployes.setLayout(new BoxLayout(panelEmployes, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelEmployes);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Afficher Employes");
        btnView.addActionListener(this::viewEmployes);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void showMachinesPanel() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblMachineName = new JLabel("Machine Name:");
        txtDeptName = new JTextField(20);

        btnAdd = new JButton("Ajouter Machine");
        btnAdd.addActionListener(this::addMachine);

        inputPanel.add(lblMachineName);
        inputPanel.add(txtDeptName);
        inputPanel.add(btnAdd);

        txtAreaDepts = new JTextArea();
        txtAreaDepts.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaDepts);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Afficher Machines");
        btnView.addActionListener(this::viewMachines);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addDepartment(ActionEvent e) {
        String deptName = txtDeptName.getText();
        if (deptName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Department name cannot be empty.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Departement (Id, Nom) VALUES (departement_seq.NEXTVAL, ?)")) {

            stmt.setString(1, deptName);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Department added successfully!");
				viewDepartments(e);
                txtDeptName.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding department: " + ex.getMessage());
        }
    }

    private void addPost(ActionEvent e) {
        String postName = txtDeptName.getText();
        if (postName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Post name cannot be empty.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Post (Id, Nom) VALUES (post_seq.NEXTVAL, ?)")) {

            stmt.setString(1, postName);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Post added successfully!");
				viewPosts(e);
                txtDeptName.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding post: " + ex.getMessage());
        }
    }

    

    private void addEmploye(ActionEvent e) {
        String prenom = txtPrenom.getText();
        String nom = txtNom.getText();
        String type = txtType.getText();
        if (prenom.isEmpty() || nom.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Prenom and Nom cannot be empty.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Employe (Id, Prenom, Nom, Type, Date_embauche) VALUES (emp_seq.NEXTVAL, ?, ?, ?, SYSDATE)")) {

            stmt.setString(1, prenom);
            stmt.setString(2, nom);
            stmt.setString(3, type);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Employe added successfully!");
                viewEmployes(e);
                txtPrenom.setText("");
                txtNom.setText("");
                txtType.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding employe: " + ex.getMessage());
        }
    }

    private void addMachine(ActionEvent e) {
        String machineName = txtDeptName.getText();
        if (machineName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Machine name cannot be empty.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Machine (Id, Nom) VALUES (machine_seq.NEXTVAL, ?)")) {

            stmt.setString(1, machineName);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Machine added successfully!");
				viewMachines(e);
                txtDeptName.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding machine: " + ex.getMessage());
        }
    }

    private void viewDepartments(ActionEvent e) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Departement")) {

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("Id")).append(", Name: ").append(rs.getString("Nom")).append("\n");
            }
            txtAreaDepts.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving departments: " + ex.getMessage());
        }
    }

    private void viewPosts(ActionEvent e) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Post")) {

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("Id")).append(", Name: ").append(rs.getString("Nom")).append("\n");
            }
            txtAreaDepts.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving posts: " + ex.getMessage());
        }
    }

    private void viewEmployes(ActionEvent e) {
        panelEmployes.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employe")) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String prenom = rs.getString("Prenom");
                String nom = rs.getString("Nom");
                String type = rs.getString("Type");

                JPanel employePanel = new JPanel(new FlowLayout());
                JTextField txtPrenomField = new JTextField(prenom, 15);
                JTextField txtNomField = new JTextField(nom, 15);
                JTextField txtTypeField = new JTextField(type, 15);
                JButton btnDelete = new JButton("Delete");

                btnDelete.addActionListener(evt -> deleteEmploye(id));

                employePanel.add(new JLabel("Prenom:"));
                employePanel.add(txtPrenomField);
                employePanel.add(new JLabel("Nom:"));
                employePanel.add(txtNomField);
                employePanel.add(new JLabel("Type:"));
                employePanel.add(txtTypeField);
                employePanel.add(btnDelete);

                panelEmployes.add(employePanel);
            }
            frame.revalidate();
            frame.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving employes: " + ex.getMessage());
        }
    }

    private void viewMachines(ActionEvent e) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Machine")) {

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("Id")).append(", Name: ").append(rs.getString("Nom")).append("\n");
            }
            txtAreaDepts.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving machines: " + ex.getMessage());
        }
    }

    private void deleteEmploye(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Employe WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Employe deleted successfully!");
                viewEmployes(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error deleting employe: " + ex.getMessage());
        }
    }
}
