package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.GameData;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
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

		textFieldServerData = new JTextField();
		textFieldServerData.setBounds(105, 48, 320, 20);
		contentPanel.add(textFieldServerData);
		textFieldServerData.setColumns(10);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(105, 73, 320, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JRadioButton rdbtnCreateGame = new JRadioButton("Spiel erstellen");
		rdbtnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldServerData.setEnabled(false);
				textFieldServerData.setText("");
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
						GameData.initGameObject().getPlayer().setName(textFieldUsername.getText());
						GameData.initGameObject().getGame().setGameid(textFieldServerData.getText());
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
