import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

	import java.text.ParseException;
	import java.text.SimpleDateFormat;

public class GestionHoteliere {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "manager1";
    private static final String PASS = "123456";

    private JFrame frame;
	
    private JTextArea txtAreaDepts;
	
    private JPanel panelDepartements;
    private JTextField txtDeptNom;
    private JTextField txtMachineDepartementID;
	
    private JPanel panelEmployes;
    private JTextField txtEmployePrenom;
    private JTextField txtEmployeNom;
    //private JTextField txtType;
    private JComboBox<String> comboBoxEmployeType;
    private static final String[] typesemp = {"Full-Time", "Part-Time", "Contract"};
    private JTextField txtVille;
    private JTextField txtEmployePostID;
    private JTextField txtEmployeSalaireID;
	
    private JPanel panelPosts;
    private JTextField txtPostNom;
    private JTextField txtPostDepartementID;
	
    private JPanel panelMachines;
    private JTextField txtMachineNom;
	
	
    private JPanel panelHotels;
    private JTextField txtHotelAddresse;
    private JTextField txtHotelNombre_pieces;
    private JTextField txtHotelCategorie;  
	
    private JPanel panelDirecteurs;
    private JTextField txtDirecteurPrenom;
    private JTextField txtDirecteurNom;
	
    private JPanel panelGestions;
    private JTextField txtGestionHotelID;
    private JTextField txtGestionDirecteurID;
	
    private JPanel panelServices;
    private JTextField txtServiceNom;
    private JTextField txtServiceDirecteurID;
	
	
    private JPanel panelChambres;
    private JTextField txtChambreNumero;
    private JTextField txtChambreNombre_lits;
    private JTextField txtChambrePrix;
    private JTextField txtHotelID;
	
    private JPanel panelOccupations;
    private JTextField txtOccupationDate_min;
    private JTextField txtOccupationDate_max;
	
    private JPanel panelPersonnes;
    private JTextField txtPersonnePrenom;
    private JTextField txtPersonneNom;
    private JTextField txtPersonneTypeom;
	
	
    private JComboBox<String> comboBoxPersonneType;
	
    private final String[] types = { "Invité", "Personnel", "VIP" }; // Example types, modify as needed
	
	
    private JButton btnAdd;
    private JButton btnView;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                GestionHoteliere window = new GestionHoteliere();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionHoteliere() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Systeme Gestion Hoteliere");
        frame.setBounds(100, 100, 1100, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
		
		showIndexPanel();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnEntities = new JMenu("Menu");
        menuBar.add(mnEntities);
        JMenu mnEntities2 = new JMenu("Systeme de gestion Hoteliere");
        menuBar.add(mnEntities2);

        JMenuItem mntmDepartements = new JMenuItem("Departements");
        mntmDepartements.addActionListener(e -> { showDepartementsPanel(); viewDepartements(e); });
        mnEntities.add(mntmDepartements);

        JMenuItem mntmPosts = new JMenuItem("Posts");
        mntmPosts.addActionListener(e -> { showPostsPanel(); viewPosts(e); });
        mnEntities.add(mntmPosts);

        JMenuItem mntmEmployes = new JMenuItem("Employes");
		mntmEmployes.addActionListener(e -> { showEmployesPanel(); viewEmployes(e); });
        mnEntities.add(mntmEmployes);

        JMenuItem mntmMachines = new JMenuItem("Machines");
        mntmMachines.addActionListener(e -> { showMachinesPanel(); viewMachines(e); });
        mnEntities.add(mntmMachines);
		
		
		
		
		
		JMenuItem mntmHotels = new JMenuItem("Hotels");
        mntmHotels.addActionListener(e -> { showHotelsPanel(); viewHotels(e); });
        mnEntities.add(mntmHotels);

        JMenuItem mntmDirecteurs = new JMenuItem("Directeurs");
        mntmDirecteurs.addActionListener(e -> { showDirecteursPanel(); viewDirecteurs(e); });
        mnEntities.add(mntmDirecteurs);

        JMenuItem mntmGestions = new JMenuItem("Gestions");
        mntmGestions.addActionListener(e -> { showGestionsPanel(); viewGestions(e); });
        mnEntities.add(mntmGestions);

        JMenuItem mntmServices = new JMenuItem("Services");
        mntmServices.addActionListener(e -> { showServicesPanel(); viewServices(e); });
        mnEntities.add(mntmServices);




        JMenuItem mntmChambres = new JMenuItem("Chambres");
        mntmChambres.addActionListener(e -> { showChambresPanel(); viewChambres(e); });
        mnEntities.add(mntmChambres);

        JMenuItem mntmOccupations = new JMenuItem("Occupation");
        mntmOccupations.addActionListener(e -> { showOccupationsPanel(); viewOccupations(e); });
        mnEntities.add(mntmOccupations);

        JMenuItem mntmPersonnes = new JMenuItem("Personnes");
        mntmPersonnes.addActionListener(e -> { showPersonnesPanel(); viewPersonnes(e); });
        mnEntities.add(mntmPersonnes);
    }

    private void showIndexPanel() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Load the image
        ImageIcon imageIcon = new ImageIcon("./Image1.jpg"); // Replace with the path to your image
        JLabel imageLabel = new JLabel(imageIcon);
        panel.add(imageLabel, BorderLayout.NORTH);

        txtAreaDepts = new JTextArea();
        txtAreaDepts.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaDepts);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        txtAreaDepts.setText("Bienvenu dans Systeme de Gestion Hoteliere");

        frame.revalidate();
        frame.repaint();
    }






    private void showDepartementsPanel() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblDeptNom = new JLabel("Nom:");
        txtDeptNom = new JTextField(15);

        btnAdd = new JButton("Ajouter Departement");
        btnAdd.addActionListener(this::addDepartement);

        inputPanel.add(lblDeptNom);
        inputPanel.add(txtDeptNom);
        inputPanel.add(btnAdd);

        panelDepartements = new JPanel();
        panelDepartements.setLayout(new BoxLayout(panelDepartements, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelDepartements);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Actualiser Departements");
        btnView.addActionListener(this::viewDepartements);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addDepartement(ActionEvent e) {
        String nom = txtDeptNom.getText();
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "nom ne peux pas être vide.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager1.Departement (Id, Nom) VALUES (employe_seq.NEXTVAL, ?)")) {

            stmt.setString(1, nom);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Departement ajouté avec succès!");
                viewDepartements(e);
                txtDeptNom.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout departement: " + ex.getMessage());
        }
    }

    private void viewDepartements(ActionEvent e) {
        panelDepartements.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM manager1.Departement")) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String nom = rs.getString("Nom");

                JPanel departementPanel = new JPanel(new FlowLayout());
                JTextField txtDeptNom = new JTextField(nom, 15);
                JButton btnSave = new JButton("Modifier");
                JButton btnDelete = new JButton("Supprimer");

                btnDelete.addActionListener(evt -> deleteDepartement(id));
                btnSave.addActionListener(evt -> saveDepartement(id, txtDeptNom.getText()));

                departementPanel.add(new JLabel("Id : "+id+", "));
				departementPanel.add(new JLabel("Nom:"));
                departementPanel.add(txtDeptNom);
                departementPanel.add(btnSave);
                departementPanel.add(btnDelete);

                panelDepartements.add(departementPanel);
            }
            frame.revalidate();
            frame.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving departements: " + ex.getMessage());
        }
    }

    private void saveDepartement(int id, String nom) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE manager1.Departement SET Nom = ? WHERE Id = ?")) {

            stmt.setString(1, nom);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(frame, "Departement Mis à jour avec succés!");
            } else {
                JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de mise à jour departement: " + ex.getMessage());
        }
    }

    private void deleteDepartement(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager1.Departement WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Departement Supprimé avec succès!");
                viewDepartements(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression departement: " + ex.getMessage());
        }
    }













    private void showPostsPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblPostNom = new JLabel("Nom:");
		txtPostNom = new JTextField(15);

		JLabel lblPostDepartementID = new JLabel("DepartementID:");
		txtPostDepartementID  = new JTextField(5);

		btnAdd = new JButton("Ajouter Post");
		btnAdd.addActionListener(this::addPost);

		inputPanel.add(lblPostNom);
		inputPanel.add(txtPostNom);
		inputPanel.add(lblPostDepartementID);
		inputPanel.add(txtPostDepartementID);
		inputPanel.add(btnAdd);

		panelPosts = new JPanel();
		panelPosts.setLayout(new BoxLayout(panelPosts, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelPosts);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Posts");
		btnView.addActionListener(this::viewPosts);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}

	private void addPost(ActionEvent e) {
		String nom = txtPostNom.getText();
		String departmentID = txtPostDepartementID.getText();
		if (nom.isEmpty() || departmentID.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Nom et DepartementID ne peux pas être vide.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager1.Post (Id, Nom, DepartementID) VALUES (employe_seq.NEXTVAL, ?, ?)")) {

			stmt.setString(1, nom);
			stmt.setString(2, departmentID);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Post ajouté avec succès!");
				viewPosts(e);
				txtPostNom.setText("");
				txtPostDepartementID.setText("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout post: " + ex.getMessage());
		}
	}

	private void viewPosts(ActionEvent e) {
		panelPosts.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager1.Post")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String nom = rs.getString("Nom");
				String departmentID = rs.getString("DepartementID");

				JPanel postPanel = new JPanel(new FlowLayout());
				JTextField txtPostNom = new JTextField(nom, 15);
				JTextField txtPostDepartementID  = new JTextField(departmentID, 5);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deletePost(id));
				btnSave.addActionListener(evt -> savePost(id, txtPostNom.getText(), txtPostDepartementID.getText()));

				postPanel.add(new JLabel("Id : "+id+", "));
				postPanel.add(new JLabel("Nom:"));
				postPanel.add(txtPostNom);
				postPanel.add(new JLabel("DepartementID:"));
				postPanel.add(txtPostDepartementID);
				postPanel.add(btnSave);
				postPanel.add(btnDelete);

				panelPosts.add(postPanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error retrieving posts: " + ex.getMessage());
		}
	}

	private void savePost(int id, String nom, String departmentID) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager1.Post SET Nom = ?, DepartementID = ? WHERE Id = ?")) {

			stmt.setString(1, nom);
			stmt.setString(2, departmentID);
			stmt.setInt(3, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Post Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour post: " + ex.getMessage());
		}
	}


    private void deletePost(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager1.Post WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Post Supprimé avec succès!");
                viewPosts(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression post: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	

    private void showEmployesPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblPrenom = new JLabel("Prenom:");
		txtEmployePrenom = new JTextField(7);
		JLabel lblNom = new JLabel("Nom:");
		txtEmployeNom = new JTextField(7);
		JLabel lblType = new JLabel("Type:");
		String[] typesemp = {"Full-Time", "Part-Time", "Contract"};
		comboBoxEmployeType = new JComboBox<>(typesemp);
		JLabel lblVille = new JLabel("Ville:");
		txtVille = new JTextField(7);
		JLabel lblPostID = new JLabel("PostID:");
		txtEmployePostID = new JTextField(7);
		JLabel lblSalaireID = new JLabel("SalaireID:");
		txtEmployeSalaireID = new JTextField(7);

		btnAdd = new JButton("Ajouter Employe");
		btnAdd.addActionListener(this::addEmploye);

		inputPanel.add(lblPrenom);
		inputPanel.add(txtEmployePrenom);
		inputPanel.add(lblNom);
		inputPanel.add(txtEmployeNom);
		inputPanel.add(lblType);
		inputPanel.add(comboBoxEmployeType);
		inputPanel.add(lblVille);
		inputPanel.add(txtVille);
		inputPanel.add(lblPostID);
		inputPanel.add(txtEmployePostID);
		inputPanel.add(lblSalaireID);
		inputPanel.add(txtEmployeSalaireID);
		inputPanel.add(btnAdd);

		panelEmployes = new JPanel();
		panelEmployes.setLayout(new BoxLayout(panelEmployes, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelEmployes);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Employes");
		btnView.addActionListener(this::viewEmployes);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}

	private void addEmploye(ActionEvent e) {
		String prenom = txtEmployePrenom.getText();
		String nom = txtEmployeNom.getText();
		String type = (String) comboBoxEmployeType.getSelectedItem();
		String ville = txtVille.getText();
		String postID = txtEmployePostID.getText();
		String salaireID = txtEmployeSalaireID.getText();
		if (prenom.isEmpty() || nom.isEmpty() || type.isEmpty() || ville.isEmpty() || postID.isEmpty() || salaireID.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Prenom, Nom, Type, Ville, PostID et SalaireID ne peux pas être vide.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager1.Employe (Id, Prenom, Nom, Type, Ville, PostID, SalaireID, Date_embauche) VALUES (employe_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE)")) {

			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			stmt.setString(3, type);
			stmt.setString(4, ville);
			stmt.setString(5, postID);
			stmt.setString(6, salaireID);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Employe ajouté avec succès!");
				viewEmployes(e);
				txtEmployePrenom.setText("");
				txtEmployeNom.setText("");
				comboBoxEmployeType.setSelectedIndex(0);
				txtVille.setText("");
				txtEmployePostID.setText("");
				txtEmployeSalaireID.setText("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout employe: " + ex.getMessage());
		}
	}

	private void viewEmployes(ActionEvent e) {
		panelEmployes.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager1.Employe")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String prenom = rs.getString("Prenom");
				String nom = rs.getString("Nom");
				String type = rs.getString("Type");
				String ville = rs.getString("Ville");
				String postID = rs.getString("PostID");
				String salaireID = rs.getString("SalaireID");

				JPanel employePanel = new JPanel(new FlowLayout());
				JTextField txtEmployePrenomField = new JTextField(prenom, 7);
				JTextField txtEmployeNomField = new JTextField(nom, 7);
				JComboBox<String> comboBoxTypeField = new JComboBox<>(typesemp);
				comboBoxTypeField.setSelectedItem(type);
				JTextField txtVilleField = new JTextField(ville, 7);
				JTextField txtEmployePostIDField = new JTextField(postID, 7);
				JTextField txtEmployeSalaireIDField = new JTextField(salaireID, 7);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deleteEmploye(id));
				btnSave.addActionListener(evt -> saveEmploye(id, txtEmployePrenomField.getText(), txtEmployeNomField.getText(), (String) comboBoxTypeField.getSelectedItem(), txtVilleField.getText(), txtEmployePostIDField.getText(), txtEmployeSalaireIDField.getText()));

				employePanel.add(new JLabel("Id : "+id+", "));
				employePanel.add(new JLabel("Prenom:"));
				employePanel.add(txtEmployePrenomField);
				employePanel.add(new JLabel("Nom:"));
				employePanel.add(txtEmployeNomField);
				employePanel.add(new JLabel("Type:"));
				employePanel.add(comboBoxTypeField);
				employePanel.add(new JLabel("Ville:"));
				employePanel.add(txtVilleField);
				employePanel.add(new JLabel("PostID:"));
				employePanel.add(txtEmployePostIDField);
				employePanel.add(new JLabel("SalaireID:"));
				employePanel.add(txtEmployeSalaireIDField);
				employePanel.add(btnSave);
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

	private void saveEmploye(int id, String prenom, String nom, String type, String ville, String postID, String salaireID) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager1.Employe SET Prenom = ?, Nom = ?, Type = ?, Ville = ?, PostID = ?, SalaireID = ? WHERE Id = ?")) {

			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			stmt.setString(3, type);
			stmt.setString(4, ville);
			stmt.setString(5, postID);
			stmt.setString(6, salaireID);
			stmt.setInt(7, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Employe Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour employe: " + ex.getMessage());
		}
	}


    private void deleteEmploye(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager1.Employe WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Employe Supprimé avec succès!");
                viewEmployes(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression employe: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	

    private void showMachinesPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblMachineNom = new JLabel("Nom:");
		txtMachineNom = new JTextField(15);

		JLabel lblMachineDepartementID = new JLabel("DepartementID:");
		JTextField txtMachineDepartementID = new JTextField(10);

		btnAdd = new JButton("Ajouter Machine");
		btnAdd.addActionListener(e -> addMachine(txtMachineNom.getText(), txtMachineDepartementID.getText()));

		inputPanel.add(lblMachineNom);
		inputPanel.add(txtMachineNom);
		inputPanel.add(lblMachineDepartementID);
		inputPanel.add(txtMachineDepartementID);
		inputPanel.add(btnAdd);

		panelMachines = new JPanel();
		panelMachines.setLayout(new BoxLayout(panelMachines, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelMachines);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Machines");
		btnView.addActionListener(this::viewMachines);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}


	private void addMachine(String nom, String departmentID) {
		if (nom.isEmpty() || departmentID.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Nom et DepartementID ne peux pas être vide.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager1.Machine (Id, Nom, DepartementID) VALUES (employe_seq.NEXTVAL, ?, ?)")) {

			stmt.setString(1, nom);
			stmt.setString(2, departmentID);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Machine ajouté avec succès!");
				viewMachines(null);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout machine: " + ex.getMessage());
		}
	}


	private void viewMachines(ActionEvent e) {
		panelMachines.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager1.Machine")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String nom = rs.getString("Nom");
				String departmentID = rs.getString("DepartementID");

				JPanel machinePanel = new JPanel(new FlowLayout());
				JTextField txtMachineNom = new JTextField(nom, 15);
				JTextField txtMachineDepartementID = new JTextField(departmentID, 10);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deleteMachine(id));
				btnSave.addActionListener(evt -> saveMachine(id, txtMachineNom.getText(), txtMachineDepartementID.getText()));

				machinePanel.add(new JLabel("Id : "+id+", "));
				machinePanel.add(new JLabel("Nom:"));
				machinePanel.add(txtMachineNom);
				machinePanel.add(new JLabel("DepartementID:"));
				machinePanel.add(txtMachineDepartementID);
				machinePanel.add(btnSave);
				machinePanel.add(btnDelete);

				panelMachines.add(machinePanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error retrieving machines: " + ex.getMessage());
		}
	}


	private void saveMachine(int id, String nom, String departmentID) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager1.Machine SET Nom = ?, DepartementID = ? WHERE Id = ?")) {

			stmt.setString(1, nom);
			stmt.setString(2, departmentID);
			stmt.setInt(3, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Machine Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour machine: " + ex.getMessage());
		}
	}


    private void deleteMachine(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager1.Machine WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Machine Supprimé avec succès!");
                viewMachines(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression machine: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	    private void showHotelsPanel() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblHotelAddresse = new JLabel("Addresse:");
        txtHotelAddresse = new JTextField(15);
        JLabel lblHotelNombre_pieces = new JLabel("Nombre_pieces:");
        txtHotelNombre_pieces = new JTextField(15);
        JLabel lblHotelCategorie = new JLabel("Categorie:");
        txtHotelCategorie = new JTextField(15);

        btnAdd = new JButton("Ajouter Hotel");
        btnAdd.addActionListener(this::addHotel);

        inputPanel.add(lblHotelAddresse);
        inputPanel.add(txtHotelAddresse);
        inputPanel.add(lblHotelNombre_pieces);
        inputPanel.add(txtHotelNombre_pieces);
        inputPanel.add(lblHotelCategorie);
        inputPanel.add(txtHotelCategorie);
        inputPanel.add(btnAdd);

        panelHotels = new JPanel();
        panelHotels.setLayout(new BoxLayout(panelHotels, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelHotels);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Actualiser Hotels");
        btnView.addActionListener(this::viewHotels);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addHotel(ActionEvent e) {
        String addresse = txtHotelAddresse.getText();
        String nombre_pieces = txtHotelNombre_pieces.getText();
        String categorie = txtHotelCategorie.getText();
        if (addresse.isEmpty() || nombre_pieces.isEmpty() || categorie.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "addresse, nombre_pieces et Categorie ne peux pas être vide.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager2.Hotel (Id, Addresse, Nombre_pieces, Categorie) VALUES (employe_seq.NEXTVAL, ?, ?, ?)")) {

            stmt.setString(1, addresse);
            stmt.setString(2, nombre_pieces);
            stmt.setString(3, categorie);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Hotel ajouté avec succès!");
                viewHotels(e);
                txtHotelAddresse.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout Hotel: " + ex.getMessage());
        }
    }

    private void viewHotels(ActionEvent e) {
        panelHotels.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM manager2.Hotel")) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String addresse = rs.getString("Addresse");
                String nombre_pieces = rs.getString("Nombre_pieces");
                String categorie = rs.getString("Categorie");

                JPanel HotelPanel = new JPanel(new FlowLayout());
                JTextField txtHotelAddresse = new JTextField(addresse, 15);
                JTextField txtHotelNombre_pieces = new JTextField(nombre_pieces, 15);
                JTextField txtHotelCategorie = new JTextField(categorie, 15);
                JButton btnSave = new JButton("Modifier");
                JButton btnDelete = new JButton("Supprimer");

                btnDelete.addActionListener(evt -> deleteHotel(id));
                btnSave.addActionListener(evt -> saveHotel(id, txtHotelAddresse.getText(), txtHotelNombre_pieces.getText(), txtHotelCategorie.getText()));

                HotelPanel.add(new JLabel("Id : "+id+", "));
				HotelPanel.add(new JLabel("Addresse:"));
                HotelPanel.add(txtHotelAddresse);
                HotelPanel.add(new JLabel("Nombre_pieces:"));
                HotelPanel.add(txtHotelNombre_pieces);
                HotelPanel.add(new JLabel("Categorie:"));
                HotelPanel.add(txtHotelCategorie);
                HotelPanel.add(btnSave);
                HotelPanel.add(btnDelete);

                panelHotels.add(HotelPanel);
            }
            frame.revalidate();
            frame.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving Hotels: " + ex.getMessage());
        }
    }

    private void saveHotel(int id, String addresse, String nombre_pieces, String categorie) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE manager2.Hotel SET Addresse = ?, Nombre_pieces = ?, Categorie = ? WHERE Id = ?")) {

            stmt.setString(1, addresse);
            stmt.setString(2, nombre_pieces);
            stmt.setString(3, categorie);
            stmt.setInt(4, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(frame, "Hotel Mis à jour avec succés!");
            } else {
                JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de mise à jour Hotel: " + ex.getMessage());
        }
    }

    private void deleteHotel(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager2.Hotel WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Hotel Supprimé avec succès!");
                viewHotels(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Hotel: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	private void showDirecteursPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblDirecteurPrenom = new JLabel("Prenom:");
		txtDirecteurPrenom = new JTextField(15);
		JLabel lblDirecteurNom = new JLabel("Nom:");
		txtDirecteurNom = new JTextField(15);

		btnAdd = new JButton("Ajouter Directeur");
		btnAdd.addActionListener(this::addDirecteur);

		inputPanel.add(lblDirecteurPrenom);
		inputPanel.add(txtDirecteurPrenom);
		inputPanel.add(lblDirecteurNom);
		inputPanel.add(txtDirecteurNom);
		inputPanel.add(btnAdd);

		panelDirecteurs = new JPanel();
		panelDirecteurs.setLayout(new BoxLayout(panelDirecteurs, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelDirecteurs);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Directeurs");
		btnView.addActionListener(this::viewDirecteurs);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}


	private void addDirecteur(ActionEvent e) {
		String prenom = txtDirecteurPrenom.getText();
		String nom = txtDirecteurNom.getText();
		if (prenom.isEmpty() || nom.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Prenom et Nom ne peux pas être vide.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager2.Directeur (Id, Prenom, Nom) VALUES (employe_seq.NEXTVAL, ?, ?)")) {

			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Directeur ajouté avec succès!");
				viewDirecteurs(e);
				txtDirecteurPrenom.setText("");
				txtDirecteurNom.setText("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout Directeur: " + ex.getMessage());
		}
	}


	private void viewDirecteurs(ActionEvent e) {
		panelDirecteurs.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager2.Directeur")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String prenom = rs.getString("Prenom");
				String nom = rs.getString("Nom");

				JPanel DirecteurPanel = new JPanel(new FlowLayout());
				JTextField txtDirecteurPrenom = new JTextField(prenom, 15);
				JTextField txtDirecteurNom = new JTextField(nom, 15);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deleteDirecteur(id));
				btnSave.addActionListener(evt -> saveDirecteur(id, txtDirecteurPrenom.getText(), txtDirecteurNom.getText()));

				DirecteurPanel.add(new JLabel("Id : "+id+", "));
				DirecteurPanel.add(new JLabel("Prenom:"));
				DirecteurPanel.add(txtDirecteurPrenom);
				DirecteurPanel.add(new JLabel("Nom:"));
				DirecteurPanel.add(txtDirecteurNom);
				DirecteurPanel.add(btnSave);
				DirecteurPanel.add(btnDelete);

				panelDirecteurs.add(DirecteurPanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error retrieving Directeurs: " + ex.getMessage());
		}
	}


	private void saveDirecteur(int id, String prenom, String nom) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager2.Directeur SET Prenom = ?, Nom = ? WHERE Id = ?")) {

			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			stmt.setInt(3, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Directeur Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour Directeur: " + ex.getMessage());
		}
	}


    private void deleteDirecteur(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager2.Directeur WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Directeur Supprimé avec succès!");
                viewDirecteurs(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Directeur: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	private void showGestionsPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblGestionHotelID = new JLabel("HotelID:");
		txtGestionHotelID = new JTextField(15);
		JLabel lblGestionDirecteurID = new JLabel("DirecteurID:");
		JTextField txtGestionDirecteurID = new JTextField(15);

		btnAdd = new JButton("Ajouter Gestion");
		btnAdd.addActionListener(e -> addGestion(txtGestionHotelID.getText(), txtGestionDirecteurID.getText()));

		inputPanel.add(lblGestionHotelID);
		inputPanel.add(txtGestionHotelID);
		inputPanel.add(lblGestionDirecteurID);
		inputPanel.add(txtGestionDirecteurID);
		inputPanel.add(btnAdd);

		panelGestions = new JPanel();
		panelGestions.setLayout(new BoxLayout(panelGestions, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelGestions);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Gestions");
		btnView.addActionListener(this::viewGestions);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}


	private void addGestion(String hotelId, String directeurId) {
		if (hotelId.isEmpty() || directeurId.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "hotelId et directeurId ne peux pas être vide.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager2.Gestion (Id, HotelID, DirecteurID, Date_dr) VALUES (employe_seq.NEXTVAL, ?, ?, SYSDATE)")) {

			stmt.setString(1, hotelId);
			stmt.setString(2, directeurId);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Gestion ajouté avec succès!");
				viewGestions(null);
				txtGestionHotelID.setText("");
				txtGestionDirecteurID.setText("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout Gestion: " + ex.getMessage());
		}
	}


	private void viewGestions(ActionEvent e) {
		panelGestions.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager2.Gestion")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String hotelId = rs.getString("HotelID");
				String directeurId = rs.getString("DirecteurID");

				JPanel GestionPanel = new JPanel(new FlowLayout());
				JTextField txtGestionHotelID = new JTextField(hotelId, 15);
				JTextField txtGestionDirecteurID = new JTextField(directeurId, 15);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deleteGestion(id));
				btnSave.addActionListener(evt -> saveGestion(id, txtGestionHotelID.getText(), txtGestionDirecteurID.getText()));

				GestionPanel.add(new JLabel("Id : "+id+", "));
				GestionPanel.add(new JLabel("HotelID:"));
				GestionPanel.add(txtGestionHotelID);
				GestionPanel.add(new JLabel("DirecteurID:"));
				GestionPanel.add(txtGestionDirecteurID);
				GestionPanel.add(btnSave);
				GestionPanel.add(btnDelete);

				panelGestions.add(GestionPanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error retrieving Gestions: " + ex.getMessage());
		}
	}


	private void saveGestion(int id, String hotelId, String directeurId) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager2.Gestion SET HotelID = ?, DirecteurID = ? WHERE Id = ?")) {

			stmt.setString(1, hotelId);
			stmt.setString(2, directeurId);
			stmt.setInt(3, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Gestion Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour Gestion: " + ex.getMessage());
		}
	}


    private void deleteGestion(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager2.Gestion WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Gestion Supprimé avec succès!");
                viewGestions(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Gestion: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	private void showServicesPanel() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JLabel lblServiceNom = new JLabel("Nom:");
        txtServiceNom = new JTextField(15);
        
        JLabel lblDirecteurID = new JLabel("DirecteurID:");
        txtServiceDirecteurID = new JTextField(15);

        btnAdd = new JButton("Ajouter Service");
        btnAdd.addActionListener(this::addService);

        inputPanel.add(lblServiceNom);
        inputPanel.add(txtServiceNom);
        inputPanel.add(lblDirecteurID);
        inputPanel.add(txtServiceDirecteurID);
        inputPanel.add(btnAdd);

        panelServices = new JPanel();
        panelServices.setLayout(new BoxLayout(panelServices, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelServices);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnView = new JButton("Actualiser Services");
        btnView.addActionListener(this::viewServices);
        panel.add(btnView, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addService(ActionEvent e) {
        String nom = txtServiceNom.getText();
        String directeurID = txtServiceDirecteurID.getText();
        if (nom.isEmpty() || directeurID.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nom et DirecteurID ne peux pas être vide.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager2.Service (Id, Nom, DirecteurID) VALUES (employe_seq.NEXTVAL, ?, ?)")) {

            stmt.setString(1, nom);
            stmt.setString(2, directeurID);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "Service ajouté avec succès!");
                viewServices(e);
                txtServiceNom.setText("");
                txtServiceDirecteurID.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout Service: " + ex.getMessage());
        }
    }

    private void viewServices(ActionEvent e) {
        panelServices.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM manager2.Service")) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String nom = rs.getString("Nom");
                String directeurID = rs.getString("DirecteurID");

                JPanel servicePanel = new JPanel(new FlowLayout());
                JTextField txtServiceNom = new JTextField(nom, 15);
                JTextField txtServiceDirecteurID = new JTextField(directeurID, 15);
                JButton btnSave = new JButton("Modifier");
                JButton btnDelete = new JButton("Supprimer");

                btnDelete.addActionListener(evt -> deleteService(id));
                btnSave.addActionListener(evt -> saveService(id, txtServiceNom.getText(), txtServiceDirecteurID.getText()));

                servicePanel.add(new JLabel("Id : "+id+", "));
				servicePanel.add(new JLabel("Nom:"));
                servicePanel.add(txtServiceNom);
                servicePanel.add(new JLabel("DirecteurID:"));
                servicePanel.add(txtServiceDirecteurID);
                servicePanel.add(btnSave);
                servicePanel.add(btnDelete);

                panelServices.add(servicePanel);
            }
            frame.revalidate();
            frame.repaint();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving Services: " + ex.getMessage());
        }
    }

    private void saveService(int id, String nom, String directeurID) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE manager2.Service SET Nom = ?, DirecteurID = ? WHERE Id = ?")) {

            stmt.setString(1, nom);
            stmt.setString(2, directeurID);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(frame, "Service Mis à jour avec succés!");
            } else {
                JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de mise à jour Service: " + ex.getMessage());
        }
    }

    private void deleteService(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager2.Service WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Service Supprimé avec succès!");
                viewServices(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Service: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void showChambresPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblChambreNumero = new JLabel("Numero:");
		txtChambreNumero = new JTextField(15);

		JLabel lblNombreLits = new JLabel("Nombre de lits:");
		JTextField txtNombreLits = new JTextField(5);

		JLabel lblPrix = new JLabel("Prix:");
		JTextField txtChambrePrix = new JTextField(10);

		JLabel lblHotelID = new JLabel("Hotel ID:");
		JTextField txtHotelID = new JTextField(10);

		btnAdd = new JButton("Ajouter Chambre");
		btnAdd.addActionListener(e -> addChambre(e, txtNombreLits, txtChambrePrix, txtHotelID));

		inputPanel.add(lblChambreNumero);
		inputPanel.add(txtChambreNumero);
		inputPanel.add(lblNombreLits);
		inputPanel.add(txtNombreLits);
		inputPanel.add(lblPrix);
		inputPanel.add(txtChambrePrix);
		inputPanel.add(lblHotelID);
		inputPanel.add(txtHotelID);
		inputPanel.add(btnAdd);

		panelChambres = new JPanel();
		panelChambres.setLayout(new BoxLayout(panelChambres, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelChambres);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Chambres");
		btnView.addActionListener(this::viewChambres);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}

	private void addChambre(ActionEvent e, JTextField txtNombreLits, JTextField txtChambrePrix, JTextField txtHotelID) {
		String numero = txtChambreNumero.getText();
		String nombreLits = txtNombreLits.getText();
		String prix = txtChambrePrix.getText();
		String hotelID = txtHotelID.getText();

		if (numero.isEmpty() || nombreLits.isEmpty() || prix.isEmpty() || hotelID.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Tous les champs doivent être remplis.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement(
				 "INSERT INTO manager3.Chambre (Id, Numero, Nombre_lits, Prix, HotelID) VALUES (employe_seq.NEXTVAL, ?, ?, ?, ?)")) {

			stmt.setString(1, numero);
			stmt.setInt(2, Integer.parseInt(nombreLits));
			stmt.setDouble(3, Double.parseDouble(prix));
			stmt.setInt(4, Integer.parseInt(hotelID));

			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Chambre ajoutée avec succès!");
				viewChambres(e);
				txtChambreNumero.setText("");
				txtNombreLits.setText("");
				txtChambrePrix.setText("");
				txtHotelID.setText("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de la chambre: " + ex.getMessage());
		}
	}

	private void viewChambres(ActionEvent e) {
		panelChambres.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager3.Chambre")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String numero = rs.getString("Numero");
				int nombreLits = rs.getInt("Nombre_lits");
				double prix = rs.getDouble("Prix");
				int hotelID = rs.getInt("HotelID");

				JPanel chambrePanel = new JPanel(new FlowLayout());
				JTextField txtChambreNumero = new JTextField(numero, 15);
				JTextField txtNombreLits = new JTextField(String.valueOf(nombreLits), 5);
				JTextField txtChambrePrix = new JTextField(String.valueOf(prix), 10);
				JTextField txtHotelID = new JTextField(String.valueOf(hotelID), 10);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deleteChambre(id));
				btnSave.addActionListener(evt -> saveChambre(id, txtChambreNumero.getText(), txtNombreLits.getText(), txtChambrePrix.getText(), txtHotelID.getText()));

				chambrePanel.add(new JLabel("Id : "+id+", "));
				chambrePanel.add(new JLabel("Numero:"));
				chambrePanel.add(txtChambreNumero);
				chambrePanel.add(new JLabel("Nombre de lits:"));
				chambrePanel.add(txtNombreLits);
				chambrePanel.add(new JLabel("Prix:"));
				chambrePanel.add(txtChambrePrix);
				chambrePanel.add(new JLabel("Hotel ID:"));
				chambrePanel.add(txtHotelID);
				chambrePanel.add(btnSave);
				chambrePanel.add(btnDelete);

				panelChambres.add(chambrePanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de la récupération des chambres: " + ex.getMessage());
		}
	}

	private void saveChambre(int id, String numero, String nombreLits, String prix, String hotelID) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement(
				 "UPDATE manager3.Chambre SET Numero = ?, Nombre_lits = ?, Prix = ?, HotelID = ? WHERE Id = ?")) {

			stmt.setString(1, numero);
			stmt.setInt(2, Integer.parseInt(nombreLits));
			stmt.setDouble(3, Double.parseDouble(prix));
			stmt.setInt(4, Integer.parseInt(hotelID));
			stmt.setInt(5, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Chambre mise à jour avec succès!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucun changement effectué.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de la mise à jour de la chambre: " + ex.getMessage());
		}
	}



    private void deleteChambre(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager3.Chambre WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Chambre Supprimé avec succès!");
                viewChambres(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Chambre: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void showOccupationsPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblOccupationDate_min = new JLabel("Date_min:");
		txtOccupationDate_min = new JTextField(15);
		JLabel lblOccupationDate_max = new JLabel("Date_max:");
		txtOccupationDate_max = new JTextField(15);

		btnAdd = new JButton("Ajouter Occupation");
		btnAdd.addActionListener(this::addOccupation);

		inputPanel.add(lblOccupationDate_min);
		inputPanel.add(txtOccupationDate_min);
		inputPanel.add(lblOccupationDate_max);
		inputPanel.add(txtOccupationDate_max);
		inputPanel.add(btnAdd);

		panelOccupations = new JPanel();
		panelOccupations.setLayout(new BoxLayout(panelOccupations, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelOccupations);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Occupations");
		btnView.addActionListener(this::viewOccupations);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}


	private void addOccupation(ActionEvent e) {
		String date_min = txtOccupationDate_min.getText();
		String date_max = txtOccupationDate_max.getText();
		if (date_min.isEmpty() || date_max.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "date_min et date_max ne peux pas être vide.");
			return;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
		java.sql.Date sqlDate_min;
		java.sql.Date sqlDate_max;
		try {
			java.util.Date parsedDate_min = dateFormat.parse(date_min);
			java.util.Date parsedDate_max = dateFormat.parse(date_max);
			sqlDate_min = new java.sql.Date(parsedDate_min.getTime());
			sqlDate_max = new java.sql.Date(parsedDate_max.getTime());
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(frame, "Invalid date format. Please use yyyy-MM-dd.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager3.Occupation (Id, Date_min, Date_max, Date_eng) VALUES (employe_seq.NEXTVAL, ?, ?, SYSDATE)")) {

			stmt.setDate(1, sqlDate_min);
			stmt.setDate(2, sqlDate_max);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Occupation ajouté avec succès!");
				viewOccupations(e);
				txtOccupationDate_min.setText("");
				txtOccupationDate_max.setText("");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout Occupation: " + ex.getMessage());
		}
	}



	private void viewOccupations(ActionEvent e) {
		panelOccupations.removeAll();
	
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM manager3.Occupation")) {
	
			while (rs.next()) {
				int id = rs.getInt("Id");
				String date_min = rs.getString("Date_min");
				String date_max = rs.getString("Date_max");
	
				JPanel OccupationPanel = new JPanel(new FlowLayout());
				JTextField txtOccupationDate_min = new JTextField(date_min, 15);
				JTextField txtOccupationDate_max = new JTextField(date_max, 15);
				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");
	
				btnDelete.addActionListener(evt -> deleteOccupation(id));
				btnSave.addActionListener(evt -> saveOccupation(id, txtOccupationDate_min.getText(), txtOccupationDate_max.getText()));
	
				OccupationPanel.add(new JLabel("Id : "+id+", "));
				OccupationPanel.add(new JLabel("Date_min:"));
				OccupationPanel.add(txtOccupationDate_min);
				OccupationPanel.add(new JLabel("Date_max:"));
				OccupationPanel.add(txtOccupationDate_max);
				OccupationPanel.add(btnSave);
				OccupationPanel.add(btnDelete);
	
				panelOccupations.add(OccupationPanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error retrieving Occupations: " + ex.getMessage());
		}
	}


		private void saveOccupation(int id, String date_min, String date_max) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
		java.sql.Date sqlDate_min;
		java.sql.Date sqlDate_max;
		try {
			java.util.Date parsedDate_min = dateFormat.parse(date_min);
			java.util.Date parsedDate_max = dateFormat.parse(date_max);
			sqlDate_min = new java.sql.Date(parsedDate_min.getTime());
			sqlDate_max = new java.sql.Date(parsedDate_max.getTime());
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(frame, "Invalid date format. Please use yyyy-MM-dd.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager3.Occupation SET Date_min = ?, Date_max = ? WHERE Id = ?")) {

			stmt.setDate(1, sqlDate_min);
			stmt.setDate(2, sqlDate_max);
			stmt.setInt(3, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Occupation Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour Occupation: " + ex.getMessage());
		}
	}



    private void deleteOccupation(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager3.Occupation WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Occupation Supprimé avec succès!");
                viewOccupations(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Occupation: " + ex.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	private void showPersonnesPanel() {
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		panel.add(inputPanel, BorderLayout.NORTH);

		JLabel lblPersonnePrenom = new JLabel("Prenom:");
		txtPersonnePrenom = new JTextField(15);
		
		JLabel lblPersonneNom = new JLabel("Nom:");
		txtPersonneNom = new JTextField(15);
		
		JLabel lblPersonneType = new JLabel("Type:");
		String[] types = { "Invité", "Personnel", "VIP" }; // Example types, modify as needed
		comboBoxPersonneType = new JComboBox<>(types);

		btnAdd = new JButton("Ajouter Personne");
		btnAdd.addActionListener(this::addPersonne);

		inputPanel.add(lblPersonnePrenom);
		inputPanel.add(txtPersonnePrenom);
		inputPanel.add(lblPersonneNom);
		inputPanel.add(txtPersonneNom);
		inputPanel.add(lblPersonneType);
		inputPanel.add(comboBoxPersonneType);
		inputPanel.add(btnAdd);

		panelPersonnes = new JPanel();
		panelPersonnes.setLayout(new BoxLayout(panelPersonnes, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(panelPersonnes);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnView = new JButton("Actualiser Personnes");
		btnView.addActionListener(this::viewPersonnes);
		panel.add(btnView, BorderLayout.SOUTH);

		frame.revalidate();
		frame.repaint();
	}

	private void addPersonne(ActionEvent e) {
		String prenom = txtPersonnePrenom.getText();
		String nom = txtPersonneNom.getText();
		String type = (String) comboBoxPersonneType.getSelectedItem();
		
		if (prenom.isEmpty() || nom.isEmpty() || type.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Prenom, Nom et Type ne peux pas être vide.");
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO manager3.Personne (Id, Prenom, Nom, Type) VALUES (employe_seq.NEXTVAL, ?, ?, ?)")) {

			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			stmt.setString(3, type);
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(frame, "Personne ajouté avec succès!");
				viewPersonnes(e);
				txtPersonnePrenom.setText("");
				txtPersonneNom.setText("");
				comboBoxPersonneType.setSelectedIndex(0);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout Personne: " + ex.getMessage());
		}
	}

	private void viewPersonnes(ActionEvent e) {
		panelPersonnes.removeAll();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM manager3.Personne")) {

			while (rs.next()) {
				int id = rs.getInt("Id");
				String prenom = rs.getString("Prenom");
				String nom = rs.getString("Nom");
				String type = rs.getString("Type");

				JPanel PersonnePanel = new JPanel(new FlowLayout());
				JTextField txtPersonnePrenom = new JTextField(prenom, 15);
				JTextField txtPersonneNom = new JTextField(nom, 15);
				JComboBox<String> comboBoxPersonneType = new JComboBox<>(types);
				comboBoxPersonneType.setSelectedItem(type);

				JButton btnSave = new JButton("Modifier");
				JButton btnDelete = new JButton("Supprimer");

				btnDelete.addActionListener(evt -> deletePersonne(id));
				btnSave.addActionListener(evt -> savePersonne(id, txtPersonnePrenom.getText(), txtPersonneNom.getText(), (String) comboBoxPersonneType.getSelectedItem()));

				PersonnePanel.add(new JLabel("Id : "+id+", "));
				PersonnePanel.add(new JLabel("Prenom:"));
				PersonnePanel.add(txtPersonnePrenom);
				PersonnePanel.add(new JLabel("Nom:"));
				PersonnePanel.add(txtPersonneNom);
				PersonnePanel.add(new JLabel("Type:"));
				PersonnePanel.add(comboBoxPersonneType);
				PersonnePanel.add(btnSave);
				PersonnePanel.add(btnDelete);

				panelPersonnes.add(PersonnePanel);
			}
			frame.revalidate();
			frame.repaint();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error retrieving Personnes: " + ex.getMessage());
		}
	}

	private void savePersonne(int id, String prenom, String nom, String type) {
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 PreparedStatement stmt = conn.prepareStatement("UPDATE manager3.Personne SET Prenom = ?, Nom = ?, Type = ? WHERE Id = ?")) {

			stmt.setString(1, prenom);
			stmt.setString(2, nom);
			stmt.setString(3, type);
			stmt.setInt(4, id);

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(frame, "Personne Mis à jour avec succés!");
			} else {
				JOptionPane.showMessageDialog(frame, "Aucune modification apportée.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Erreur de mise à jour Personne: " + ex.getMessage());
		}
	}


    private void deletePersonne(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM manager3.Personne WHERE Id = ?")) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Personne Supprimé avec succès!");
                viewPersonnes(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur de suppression Personne: " + ex.getMessage());
        }
    }
	
	
}