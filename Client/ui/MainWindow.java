package ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.border.CompoundBorder;
import javax.swing.plaf.basic.BasicTreeUI.TreeExpansionHandler;

import controller.Controller;
import controller.Feld;
import controller.GameData;
import controller.Spielfeld;
import de.vs.monopoly.model.Place;
import de.vs.monopoly.model.Roll;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */

	private JLabel lblDice1 = new JLabel("");
	private JLabel lblDice2 = new JLabel("");
	public static JLabel lblDiceResult = new JLabel("Ergebnis:");
	private static JLabel spielername = new JLabel("");
	private static JLabel spiel = new JLabel("");
	private JPanel panel;
	public static MainWindow window;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameData.initGameObject();
					Controller.init();
					window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_mainUser = new JPanel();
		panel_mainUser.setBounds(10, 122, 233, 183);
		frame.getContentPane().add(panel_mainUser);
		panel_mainUser.setLayout(null);


		
		JLabel lblUsermenu = new JLabel("Spielmen\u00FC");
		lblUsermenu.setBounds(5, 5, 95, 22);
		lblUsermenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsermenu.setVerticalAlignment(SwingConstants.TOP);
		

		
		
		panel_mainUser.add(lblUsermenu);
		
		

		JLabel lblMenuMoney = new JLabel("Geld:");
		lblMenuMoney.setBounds(15, 38, 46, 14);
		panel_mainUser.add(lblMenuMoney);

		JLabel lblMenuStreets = new JLabel("Strassen:");
		lblMenuStreets.setBounds(15, 63, 46, 14);
		panel_mainUser.add(lblMenuStreets);

		JLabel lblMenuHouses = new JLabel("H\u00E4user:");
		lblMenuHouses.setBounds(15, 88, 46, 14);
		panel_mainUser.add(lblMenuHouses);

		JLabel lblMenuHotels = new JLabel("Hotels:");
		lblMenuHotels.setBounds(15, 113, 46, 14);
		panel_mainUser.add(lblMenuHotels);

		JLabel lblMenuItems = new JLabel("Spezielle Karten:");
		lblMenuItems.setBounds(15, 138, 85, 14);
		panel_mainUser.add(lblMenuItems);

		JLabel lblValueMoney = new JLabel("0");
		lblValueMoney.setBounds(120, 38, 46, 14);
		panel_mainUser.add(lblValueMoney);

		JLabel lblValueStreets = new JLabel("keine");
		lblValueStreets.setBounds(120, 63, 46, 14);
		panel_mainUser.add(lblValueStreets);

		JLabel lblValueHouses = new JLabel("keine");
		lblValueHouses.setBounds(120, 88, 46, 14);
		panel_mainUser.add(lblValueHouses);

		JLabel lblValueHotels = new JLabel("keine");
		lblValueHotels.setBounds(120, 113, 46, 14);
		panel_mainUser.add(lblValueHotels);

		JLabel lblValueItems = new JLabel("keine");
		lblValueItems.setBounds(120, 138, 46, 14);

		// JLabel lblNewLabel_1 = new JLabel("");
		spielername.setBounds(120, 12, 46, 14);
		panel_mainUser.add(spielername);

		JPanel panel_otherUsers = new JPanel();
		panel_otherUsers.setBounds(1000, 1000, 233, 183);

		frame.getContentPane().add(panel_otherUsers);

		JPanel panel_board = new JPanel();
		panel_board.setBounds(250, 150, 700, 700);
		frame.getContentPane().add(panel_board);

		JLabel lblNewLabel = new JLabel("");
		BufferedImage img = null;

		try {
			URL url = getClass().getResource("board.jpg");
			img = ImageIO.read(new File(url.getPath()));

			Image dimg = img.getScaledInstance(700, 700, Image.SCALE_SMOOTH);

			ImageIcon imageIcon = new ImageIcon(dimg);

			lblNewLabel.setIcon(imageIcon);
		} catch (IOException e) {
			lblNewLabel.setText("Fehler beim laden des Spielfelds!");

		}

		panel_board.add(lblNewLabel);

		JLabel lblTitel = new JLabel("");
		lblTitel.setBounds(10, 44, 233, 42);

		URL imageUrl;
		try {
			imageUrl = getClass().getResource("titel.png");
			BufferedImage image = ImageIO.read(imageUrl);
			Image dimg = image.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(dimg);
			lblTitel.setIcon(icon);

		} catch (IOException e) {
			lblTitel.setText("Verteiltes Monopoly");
		}

		frame.getContentPane().add(lblTitel);

		// spiel = new JLabel("Spielname:"
		// +GameData.initGameObject().getGame().getGameid());
		spiel.setBounds(10, 97, 215, 14);
		frame.getContentPane().add(spiel);

		panel = new JPanel();
		panel.setBounds(10, 326, 233, 157);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		
		lblDiceResult.setBounds(10, 132, 213, 14);
		panel.add(lblDiceResult);

		JLabel lblDiceMenu = new JLabel("W\u00FCrfelmen\u00FC");
		lblDiceMenu.setVerticalAlignment(SwingConstants.TOP);
		lblDiceMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDiceMenu.setBounds(10, 11, 125, 22);
		panel.add(lblDiceMenu);

		// W�rfel werden erstellt
		lblDice1.setBounds(10, 44, 80, 80);
		panel.add(lblDice1);

		lblDice2.setBounds(100, 44, 80, 80);
		panel.add(lblDice2);

		JButton btnRoll = new JButton("w\u00FCrfeln!");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDice2.setVisible(false);
				lblDice1.setVisible(false);
	
				AudioClip click;
				URL urlClick = MainWindow.class.getResource("wuerfelbecher.wav");
				click = Applet.newAudioClip(urlClick);
				click.play();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblDice2.setVisible(true);
				lblDice1.setVisible(true);
				int[] result;
				try {
					result = getDiceResult();
					lblDiceResult.setText("Ergebnis: Gehen Sie " + (result[0] + result[1]) + " Felder vor!");
				} catch (IOException e) {
					lblDiceResult.setText("Fehler bei der Kommunikation mit dem DICE Service");
					e.printStackTrace();
				}
				
			}
		});
		btnRoll.setBounds(134, 14, 89, 23);
		panel.add(btnRoll);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1000, 20);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("Spiel");
		menuBar.add(mnNewMenu);

		JMenuItem mnAnmelden = new JMenuItem("Anmelden");
		mnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new NewGame().setVisible(true);
			}
		});

		mnNewMenu.add(mnAnmelden);

		JMenuItem mnAbmelden = new JMenuItem("Abmelden");
		mnNewMenu.add(mnAbmelden);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 494, 233, 194);
		frame.getContentPane().add(panel_1);

		JLabel lblSpielmenuAktion = new JLabel("<html>Erstellen oder registieren Sie <br>sich für ein Spiel!</html>", SwingConstants.CENTER);
	
		lblSpielmenuAktion.setBounds(10, 112, 213, 71);
		lblSpielmenuAktion.setForeground(Color.RED);
		panel_1.add(lblSpielmenuAktion);

		JLabel lblSpielmen = new JLabel("Spielmen\u00FC");
		lblSpielmen.setVerticalAlignment(SwingConstants.TOP);
		lblSpielmen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSpielmen.setBounds(10, 11, 125, 22);
		panel_1.add(lblSpielmen);

		JButton btnKaufen = new JButton("kaufen");
		btnKaufen.setBounds(10, 44, 101, 23);
		panel_1.add(btnKaufen);

		JButton btnHausBauen = new JButton("Haus bauen");
		btnHausBauen.setBounds(10, 78, 101, 23);
		panel_1.add(btnHausBauen);

		JButton btnHotelBauen = new JButton("Hotel bauen");
		btnHotelBauen.setBounds(121, 78, 101, 23);
		panel_1.add(btnHotelBauen);

		JButton btnTauschen = new JButton("tauschen");
		btnTauschen.setBounds(122, 44, 101, 23);
		panel_1.add(btnTauschen);
		
		btnKaufen.setEnabled(false);
		btnRoll.setEnabled(false);
		btnTauschen.setEnabled(false);
		btnHausBauen.setEnabled(false);
		btnHotelBauen.setEnabled(false);
		
		JButton btnReady = new JButton("Bin Bereit!");
		btnReady.setBounds(5, 150, 101, 23);
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(GameData.initGameObject().getGame() != null && GameData.initGameObject().getPlayer() != null){
					try {
						Controller.setPlayerReady();
						
						btnKaufen.setEnabled(true);
						btnRoll.setEnabled(true);
						btnTauschen.setEnabled(false);
						btnHausBauen.setEnabled(false);
						btnHotelBauen.setEnabled(false);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		});
		panel_mainUser.add(btnReady);

	}

	public static void update() {

		spielername.setText(GameData.initGameObject().getPlayer().getName());
		spiel.setText(GameData.initGameObject().getGame().getGameid());
		frame.repaint();

	}

	private int[] getDiceResult() throws IOException {


		int dice1 = 0;
		int dice2 = 0;

		URL imageUrl1;
		ImageIcon icon1 = null;
		URL imageUrl2;
		ImageIcon icon2 = null;
		URL imageUrl3;
		ImageIcon icon3 = null;
		URL imageUrl4;
		ImageIcon icon4 = null;
		URL imageUrl5;
		ImageIcon icon5 = null;
		URL imageUrl6;
		ImageIcon icon6 = null;
		try {
			imageUrl1 = getClass().getResource("dice_1.jpg");
			BufferedImage image1 = ImageIO.read(imageUrl1);
			Image dimg1 = image1.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			icon1 = new ImageIcon(dimg1);

			imageUrl2 = getClass().getResource("dice_2.jpg");
			BufferedImage image2 = ImageIO.read(imageUrl1);
			Image dimg2 = image2.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			icon2 = new ImageIcon(dimg2);

			imageUrl3 = getClass().getResource("dice_3.jpg");
			BufferedImage image3 = ImageIO.read(imageUrl3);
			Image dimg3 = image3.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			icon3 = new ImageIcon(dimg3);

			imageUrl4 = getClass().getResource("dice_4.jpg");
			BufferedImage image4 = ImageIO.read(imageUrl4);
			Image dimg4 = image4.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			icon4 = new ImageIcon(dimg4);

			imageUrl5 = getClass().getResource("dice_5.jpg");
			BufferedImage image5 = ImageIO.read(imageUrl5);
			Image dimg5 = image5.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			icon5 = new ImageIcon(dimg5);

			imageUrl6 = getClass().getResource("dice_6.jpg");
			BufferedImage image6 = ImageIO.read(imageUrl6);
			Image dimg6 = image6.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			icon6 = new ImageIcon(dimg6);

		} catch (IOException e) {
			e.printStackTrace();
		}

		dice1 = ((Roll)Controller.dice()).getNumber();
		dice2 = ((Roll)Controller.dice()).getNumber();

		switch (dice1)

		{
		case 1:
			lblDice1.setIcon(icon1);
			break;
		case 2:
			lblDice1.setIcon(icon2);
			break;

		case 3:
			lblDice1.setIcon(icon3);
			break;

		case 4:
			lblDice1.setIcon(icon4);
			break;

		case 5:
			lblDice1.setIcon(icon5);
			break;

		case 6:
			lblDice1.setIcon(icon6);
			break;

		default:
			lblDice1.setIcon(icon1);
			break;
		}

		switch (dice2)

		{
		case 1:
			lblDice2.setIcon(icon1);
			break;
		case 2:
			lblDice2.setIcon(icon2);
			break;

		case 3:
			lblDice2.setIcon(icon3);
			break;

		case 4:
			lblDice2.setIcon(icon4);
			break;

		case 5:
			lblDice2.setIcon(icon5);
			break;

		case 6:
			lblDice2.setIcon(icon6);
			break;

		default:
			lblDice2.setIcon(icon2);
			break;
		}

		return new int[] { dice1, dice2 };
	}

	private void setCoordinates(Place place) {
		// Soll Spielfigueren, H�user, Hotels auf den Spielfeld ablegen und
		// verschieben

		Spielfeld board = new Spielfeld(700,700);
											//Title X/Y  X/Y   X/Y    X/Y
		board.getSpielfelder().add(new Feld("Start",0,0,0,107,107,0,107,107));
		board.getSpielfelder().add(new Feld("Brunnenstra�e",108,0,108,107,107,0,107,107));
		board.getSpielfelder().add(new Feld("Start",0,0,0,107,107,0,107,107));
		board.getSpielfelder().add(new Feld("Start",0,0,0,107,107,0,107,107));
		
		
		int[][] spielfeldKoordinaten = new int[40][];
		// Von X|Von Y|Bis X|Bis Y
		spielfeldKoordinaten[0] = new int[] { 0/0, 0/107, 107/0, 107/107 };
		spielfeldKoordinaten[1] = new int[] { 108, 0, 107, 107 };
		spielfeldKoordinaten[2] = new int[] { 0, 0, 107, 107 };
		spielfeldKoordinaten[3] = new int[] { 0, 0, 107, 107 };
		spielfeldKoordinaten[4] = new int[] { 0, 0, 107, 107 };

	}

}
