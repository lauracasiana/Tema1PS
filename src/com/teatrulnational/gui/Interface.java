package com.teatrulnational.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.teatrulnational.models.Bilet;
import com.teatrulnational.models.Spectacol;
import com.teatrulnational.models.User;
import com.teatrulnational.services.SpectacolManager;
import com.teatrulnational.services.TicketManager;
import com.teatrulnational.services.UserManager;

public class Interface {

	private JFrame frame;
	private JTextField TFieldUsername;
	private JTextField TFieldPassword;
	private JTextField TFieldTitlu;
	private JTextField TFieldRegia;
	private JTextField TFieldDistributia;
	private JTextField TFieldData;
	private JTextField TFieldNrBilete;
	private JTextField TFieldNume;
	private JTextField TFieldUsernameAng;
	private JTextField TFieldPwdAng;
	private JTextField TFieldSpecTitlu;
	private JTextField TFieldRand;
	private JTextField TFieldNrBil;
	private JPanel Login;
	private JPanel Admin;
	private JPanel Angajat;
	private TextArea textAreaAng;
	private TextArea textAreaBilete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	private Spectacol getFieldsSpectacol() {
		boolean ok = true;

		if (TFieldTitlu.getText().equals("")) {
			ok = false;

		} else if (TFieldRegia.getText().equals("")) {
			ok = false;

		} else if (TFieldDistributia.getText().equals("")) {
			ok = false;

		} else if (TFieldData.getText().equals("")) {
			ok = false;

		} else if (TFieldNrBilete.getText().equals("")) {
			ok = false;

		}
		if (ok == true) {
			String titlu = TFieldTitlu.getText();
			String regia = TFieldRegia.getText();
			String distrib = TFieldDistributia.getText();
			Date data = Date.valueOf(TFieldData.getText());
			int nrbil = Integer.parseInt(TFieldNrBilete.getText());

			Spectacol spec = new Spectacol(titlu, regia, distrib, data, nrbil);
			return spec;
		} else
			return null;
	}

	private User getAnjatFields() {
		User usr;
		boolean ok = true;
		if (TFieldNume.getText().equals("")) {
			ok = false;
		} else if (TFieldUsernameAng.getText().equals("")) {
			ok = false;
		} else if (TFieldPwdAng.getText().equals("")) {
			ok = false;
		}
		if (ok == true) {
			usr = new User(TFieldNume.getText(), TFieldUsernameAng.getText(),
					TFieldPwdAng.getText(), "angajat");
			return usr;
		}
		return null;
	}

	private Bilet getBiletFields() {
		boolean ok = true;
		if (TFieldSpecTitlu.equals("")) {
			ok = false;
		} else if (TFieldRand.equals("")) {
			ok = false;
		} else if (TFieldNrBil.equals("")) {
			ok = false;
		}
		if (ok == true) {
			Spectacol spec = new Spectacol(TFieldSpecTitlu.getText(), "", "",
					null, -1);
			Bilet b = new Bilet(spec, Integer.parseInt(TFieldRand.getText()),
					Integer.parseInt(TFieldNrBil.getText()));
			return b;
		} else
			return null;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		Login = new JPanel();
		Login.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		frame.getContentPane().add(Login, "name_296484742492345");
		Login.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(205, 119, 77, 14);
		Login.add(lblUsername);

		TFieldUsername = new JTextField();
		TFieldUsername.setBounds(292, 116, 236, 20);
		Login.add(TFieldUsername);
		TFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(205, 163, 77, 14);
		Login.add(lblPassword);

		TFieldPassword = new JTextField();
		TFieldPassword.setColumns(10);
		TFieldPassword.setBounds(292, 160, 236, 20);
		Login.add(TFieldPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManager um = new UserManager();
				try {
					User loger = um.Login(TFieldUsername.getText(),
							TFieldPassword.getText());
					if (loger.getRole().equals("admin")) {
						Admin.setVisible(true);
						Login.setVisible(false);
					} else if (loger.getRole().equals("angajat")) {
						Angajat.setVisible(true);
						Login.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null,
								"Wrong credentials. Try again.");
					}
				} catch (Exception e1) {

					JOptionPane.showMessageDialog(null,
							"Error. Could not log in.");
				}

			}
		});
		btnLogin.setBounds(439, 229, 89, 23);
		Login.add(btnLogin);

		Admin = new JPanel();
		frame.getContentPane().add(Admin, "name_296486889857103");
		Admin.setLayout(null);

		JLabel lblSpectacol = new JLabel("Spectacol");
		lblSpectacol.setBounds(127, 11, 81, 14);
		Admin.add(lblSpectacol);

		JLabel lblAngajat = new JLabel("Angajat");
		lblAngajat.setBounds(525, 11, 46, 14);
		Admin.add(lblAngajat);

		JLabel lblTitlu = new JLabel("Titlu:");
		lblTitlu.setBounds(10, 54, 46, 14);
		Admin.add(lblTitlu);

		JLabel lblRegia = new JLabel("Regia:");
		lblRegia.setBounds(10, 79, 46, 14);
		Admin.add(lblRegia);

		JLabel lblDistributia = new JLabel("Distributia:");
		lblDistributia.setBounds(10, 104, 68, 20);
		Admin.add(lblDistributia);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 129, 46, 20);
		Admin.add(lblData);

		JLabel lblNrBilete = new JLabel("Nr. bilete:");
		lblNrBilete.setBounds(10, 154, 58, 20);
		Admin.add(lblNrBilete);

		TFieldTitlu = new JTextField();
		TFieldTitlu.setBounds(72, 51, 198, 20);
		Admin.add(TFieldTitlu);
		TFieldTitlu.setColumns(10);

		TFieldRegia = new JTextField();
		TFieldRegia.setColumns(10);
		TFieldRegia.setBounds(72, 79, 198, 20);
		Admin.add(TFieldRegia);

		TFieldDistributia = new JTextField();
		TFieldDistributia.setBounds(72, 104, 198, 20);
		Admin.add(TFieldDistributia);
		TFieldDistributia.setColumns(10);

		TFieldData = new JTextField();
		TFieldData.setColumns(10);
		TFieldData.setBounds(72, 129, 198, 20);
		Admin.add(TFieldData);

		TFieldNrBilete = new JTextField();
		TFieldNrBilete.setColumns(10);
		TFieldNrBilete.setBounds(72, 154, 198, 20);
		Admin.add(TFieldNrBilete);

		JButton btnAdaugaSpectacol = new JButton("Adauga Spectacol");
		btnAdaugaSpectacol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpectacolManager sm = new SpectacolManager();
				Spectacol spec = getFieldsSpectacol();
				if (spec != null) {
					boolean ok = sm.addSpectacol(spec);
					if (ok == true) {
						JOptionPane.showMessageDialog(null, "Success!");
					} else
						JOptionPane.showMessageDialog(null,
								"Error. Could not add Spectacol.");
				} else
					JOptionPane.showMessageDialog(null,
							"Please fill all fields for Spectacol.");
			}

		});
		btnAdaugaSpectacol.setBounds(72, 185, 168, 23);
		Admin.add(btnAdaugaSpectacol);

		JButton btnModificaSpectacol = new JButton("Modifica Spectacol");
		btnModificaSpectacol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SpectacolManager sm = new SpectacolManager();
				Spectacol spec = getFieldsSpectacol();
				if (spec != null) {
					boolean ok = sm.updateSpectacol(spec);
					if (ok == true) {
						JOptionPane.showMessageDialog(null, "Success!");
					} else
						JOptionPane.showMessageDialog(null,
								"Error. Could not update Spectacol.");
				} else
					JOptionPane.showMessageDialog(null,
							"Please fill all fields for Spectacol.");

			}
		});
		btnModificaSpectacol.setBounds(72, 219, 168, 23);
		Admin.add(btnModificaSpectacol);

		JButton btnStergeSpectacol = new JButton("Sterge Spectacol");
		btnStergeSpectacol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpectacolManager sm = new SpectacolManager();
				Spectacol spec = getFieldsSpectacol();
				if (spec != null) {
					boolean ok = sm.deleteSpectacol(spec);
					if (ok == true) {
						JOptionPane.showMessageDialog(null, "Success!");
					} else
						JOptionPane.showMessageDialog(null,
								"Error. Could not delete Spectacol.");
				} else
					JOptionPane.showMessageDialog(null,
							"Please fill all fields for Spectacol.");
			}
		});
		btnStergeSpectacol.setBounds(70, 253, 170, 23);
		Admin.add(btnStergeSpectacol);

		JLabel lblNume = new JLabel("Nume:");
		lblNume.setBounds(379, 54, 46, 14);
		Admin.add(lblNume);

		TFieldNume = new JTextField();
		TFieldNume.setColumns(10);
		TFieldNume.setBounds(459, 51, 198, 20);
		Admin.add(TFieldNume);

		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(379, 82, 63, 14);
		Admin.add(lblUsername_1);

		TFieldUsernameAng = new JTextField();
		TFieldUsernameAng.setColumns(10);
		TFieldUsernameAng.setBounds(459, 76, 198, 20);
		Admin.add(TFieldUsernameAng);

		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(379, 107, 63, 14);
		Admin.add(lblPassword_1);

		TFieldPwdAng = new JTextField();
		TFieldPwdAng.setColumns(10);
		TFieldPwdAng.setBounds(459, 104, 198, 20);
		Admin.add(TFieldPwdAng);

		JLabel lblAngajati = new JLabel("Angajati:");
		lblAngajati.setBounds(379, 135, 63, 14);
		Admin.add(lblAngajati);

		textAreaAng = new TextArea();
		textAreaAng.setBounds(459, 131, 198, 111);
		Admin.add(textAreaAng);

		JButton btnAdaugaAngajat = new JButton("Adauga Angajat");
		btnAdaugaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManager um = new UserManager();
				User usr = getAnjatFields();
				if (usr != null) {
					boolean ok = um.addAngajat(usr);
					if (ok == true) {
						JOptionPane.showMessageDialog(null, "Success!");
					} else
						JOptionPane.showMessageDialog(null,
								"Error. Could not add Angajat.");
				} else
					JOptionPane.showMessageDialog(null,
							"Please fill all fields for Angajat.");
			}

		});
		btnAdaugaAngajat.setBounds(428, 253, 132, 23);
		Admin.add(btnAdaugaAngajat);

		JButton btnVeziAngajati = new JButton("Vezi Angajati");
		btnVeziAngajati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManager um = new UserManager();
				ArrayList<User> usrs = um.seeAllAngajati();
				String output = "";
				for (User user : usrs) {
					output = output + user.getNume() + " " + user.getUsername()
							+ ";\n";
				}
				textAreaAng.setText(output);
			}
		});
		btnVeziAngajati.setBounds(568, 253, 132, 23);
		Admin.add(btnVeziAngajati);

		Angajat = new JPanel();
		frame.getContentPane().add(Angajat, "name_296488364103678");
		Angajat.setLayout(null);

		JLabel lblSpectacol_1 = new JLabel("Spectacol:");
		lblSpectacol_1.setBounds(28, 31, 91, 14);
		Angajat.add(lblSpectacol_1);

		JLabel lblRand = new JLabel("Rand:");
		lblRand.setBounds(28, 56, 46, 14);
		Angajat.add(lblRand);

		JLabel lblNumarBilete = new JLabel("Numar bilete:");
		lblNumarBilete.setBounds(28, 81, 91, 14);
		Angajat.add(lblNumarBilete);

		TFieldSpecTitlu = new JTextField();
		TFieldSpecTitlu.setBounds(116, 28, 190, 20);
		Angajat.add(TFieldSpecTitlu);
		TFieldSpecTitlu.setColumns(10);

		TFieldRand = new JTextField();
		TFieldRand.setColumns(10);
		TFieldRand.setBounds(116, 53, 190, 20);
		Angajat.add(TFieldRand);

		TFieldNrBil = new JTextField();
		TFieldNrBil.setColumns(10);
		TFieldNrBil.setBounds(116, 78, 190, 20);
		Angajat.add(TFieldNrBil);

		JButton btnAdaugaBilet = new JButton("Adauga Bilet");
		btnAdaugaBilet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketManager tm = new TicketManager();
				Bilet b = getBiletFields();
				boolean ok = tm.addTicket(b);
				if (ok == true) {
					JOptionPane.showMessageDialog(null, "Success!");
				} else
					JOptionPane
							.showMessageDialog(null,
									"Error. Could not add Ticket. There might not be any more tickets.");
			}

		});
		btnAdaugaBilet.setBounds(116, 133, 111, 23);
		Angajat.add(btnAdaugaBilet);

		textAreaBilete = new TextArea();
		textAreaBilete.setBounds(365, 20, 327, 178);
		Angajat.add(textAreaBilete);

		JButton btnVeziBilete = new JButton("Vezi Bilete");
		btnVeziBilete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketManager tm = new TicketManager();
				ArrayList<Bilet> bilete = tm.seeAllTickets();
				String output = "";
				for (Bilet bilet : bilete) {
					output = output + bilet.getSpectacol().getTitlu()
							+ " nr bilete: " + bilet.getNumar() + "; \n";
				}
				textAreaBilete.setText(output);

			}
		});
		btnVeziBilete.setBounds(487, 227, 89, 23);
		Angajat.add(btnVeziBilete);
	}

}
