package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import controller.GameData;
import de.vs.monopoly.model.Game;
import de.vs.monopoly.model.Roll;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ResourceBundle.Control;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class NewGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldServerData;
	private JTextField textFieldUsername;
	private static NewGame dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new NewGame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewGame() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAnmeldung = new JLabel("Anmeldung:");
			lblAnmeldung.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblAnmeldung.setBounds(10, 11, 124, 29);
			contentPanel.add(lblAnmeldung);
		}
		{
			
			JLabel lblAnmeldeserver = new JLabel("Anmeldeserver:");
			lblAnmeldeserver.setBounds(10, 51, 90, 14);
			contentPanel.add(lblAnmeldeserver);
		}
		{
			JLabel lblSpielername = new JLabel("Spielername:");
			lblSpielername.setBounds(10, 76, 77, 14);
			contentPanel.add(lblSpielername);
		}
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		try{
			//Holt sich die existierenden Instanzen
			petStrings = Controller.getAllGames();
		}catch(Exception e){
			System.out.println("Fehler beim laden der existierenden GameInstanzen!");
		}
		
		JComboBox petList = new JComboBox(petStrings);
		petList.setBounds(105, 48, 320, 20);
		//petList.setSelectedIndex(0);
		petList.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
			
		}
		});
		
		
		
		//textFieldServerData = new JTextField();
		//textFieldServerData.setBounds(105, 48, 320, 20);
		contentPanel.add(petList);
		//textFieldServerData.setColumns(10);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(105, 73, 320, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JRadioButton rdbtnCreateGame = new JRadioButton("Spiel erstellen");
		rdbtnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//textFieldServerData.setEnabled(false);
				//textFieldServerData.setText("");
				petList.setEnabled(false);
				}
		});
		rdbtnCreateGame.setBounds(105, 100, 320, 23);
		contentPanel.add(rdbtnCreateGame);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("los gehts!");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// An dieser Stelle muss die Verbindung per
						// Verzeichnisdienst geschehen und dann erst die
						// speicherung in den GameData

						if (rdbtnCreateGame.isSelected()) { // Wenn man ein
															// eigenes Spiel
															// erstellen möchte
							boolean suc = Controller.createGame("",
									textFieldUsername.getText());

						} else { // Wenn man sich an einem bestehenden Spiel
									// anmelden möchte

							boolean suc = Controller.registerPlayer(petList.getSelectedItem().toString(),
									textFieldUsername.getText());

						}

						MainWindow.update();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbruch");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
