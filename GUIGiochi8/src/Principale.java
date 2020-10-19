import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Principale {
	static ArrayList<Videogioco> listaVideogiochi= new ArrayList<Videogioco>();
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;


	private static void inizializzaLista() {
		listaVideogiochi.add(new Videogioco("Last of us", 9));
		listaVideogiochi.add(new Videogioco("Bioshock", 9.5));
		listaVideogiochi.add(new Videogioco("Crash", 8.5));
		//System.out.println(listaVideogiochi.size());
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		caricaLista();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale window = new Principale();
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
	public Principale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));
		
		//Pannello principale di scelta
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "Pannello_1");
		panel_1.setLayout(new GridLayout(4,1));
		CardLayout c1 = (CardLayout) (panel.getLayout());
		
		JButton btnNewButton_1 = new JButton("Lista videogiochi");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Aggiungi un gioco");
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Rimuovi un gioco");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_10 = new JButton("Salva lista");
		panel_1.add(btnNewButton_10);
		
		//Pannello lista videogiochi
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "Pannello_2");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_3 = new JButton("Indietro");
		panel_2.add(btnNewButton_3, BorderLayout.SOUTH);
		
		JPanel panel_2_1 = new JPanel();
		panel_2.add(panel_2_1, BorderLayout.CENTER);
		panel_2_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2_2 = new JPanel();
		panel_2_1.add(panel_2_2, BorderLayout.CENTER);
		panel_2_2.setLayout(new BoxLayout(panel_2_2, BoxLayout.Y_AXIS));
		
		JPanel panel_2_3 = new JPanel();
		panel_2_1.add(panel_2_3, BorderLayout.SOUTH);
		panel_2_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_8 = new JButton("Ordina per nome");
		panel_2_3.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Ordina per voto");
		panel_2_3.add(btnNewButton_9);
		
		stampalista(panel_2_2);
		
		//Pannello aggiungi un gioco
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "Pannello_3");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_4 = new JButton("Indietro");
		panel_3.add(btnNewButton_4, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Aggiungi un gioco:");
		panel_3.add(lblNewLabel, BorderLayout.NORTH);
		
		JComboBox<Platform> comboBox_2 = new JComboBox<>();
		panel_3.add(comboBox_2, BorderLayout.EAST);
		platformComboBox(comboBox_2);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Nome gioco");
		panel_6.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Voto");
		panel_6.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("Conferma");
		panel_6.add(btnNewButton_6);
		
		
		//pannello rimuovi un gioco
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "Pannello_4");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_5 = new JButton("Indietro");
		panel_4.add(btnNewButton_5, BorderLayout.SOUTH);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JComboBox<Videogioco> comboBox = new JComboBox<>();
		panel_7.add(comboBox, BorderLayout.CENTER);
		popolaComboBox(comboBox);
		
		JButton btnNewButton_7 = new JButton("Conferma");
		panel_7.add(btnNewButton_7);
		
		
		

		//listeners
		
		//bottone "lista videogiochi" nel pannello principale
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.next(panel);
				panel_2_2.removeAll();
				stampalista(panel_2_2);
			}
		});
		
		//bottone "Aggiungi un gioco" nel pannello principale
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.next(panel);
				c1.next(panel);
			}
		});
		
		//bottone "Rimuovi un gioco" nel pannello principale
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popolaComboBox(comboBox);
				c1.last(panel);
			}
		});
		
		//bottone "salva lista" nel pannello principale
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaLista();
			}
		});
		
		
		//bottone "Indietro" nel pannello "lista videogiochi"
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.first(panel);
			}
		});
		
		//bottone "indietro" nel pannello "aggiungi un gioco"
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.first(panel);
			}
		});
		
		//bottone "conferma" nel pannello "aggiungi un gioco"
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().isEmpty() || textField.getText().isBlank()) {
				JOptionPane.showMessageDialog(null,"nome non valido","Errore",JOptionPane.ERROR_MESSAGE);	
				}
				else {
					
				try {
				double voto=Double.parseDouble(textField_1.getText());
				if(voto>=0 && voto <=10) {
					Videogioco nuovo= new Videogioco(textField.getText(),voto);
					nuovo.setPlatform((Platform)comboBox_2.getSelectedItem());
					if(!listaVideogiochi.contains(nuovo)) {
					inserisciVideogioco(nuovo);	
					textField.setText("");	
					textField_1.setText("");	
					c1.first(panel);
					}
					else {
						JOptionPane.showMessageDialog(null,"Gioco già inserito","Errore",JOptionPane.ERROR_MESSAGE);
					}
					}
						else {
							JOptionPane.showMessageDialog(null,"Voto non valido","Errore",JOptionPane.ERROR_MESSAGE);
						}
				}
				catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"Voto non valido","Errore",JOptionPane.ERROR_MESSAGE);	
				}	
			}
			}
		});
		
		//bottone "indietro" nel pannello "rimuovi un gioco"
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.first(panel);
			}
		});
		

		
		//bottone ordina per nome
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(listaVideogiochi,Opera.NameComparator);
				panel_2_2.removeAll();
				stampalista(panel_2_2);
				c1.first(panel);
				c1.next(panel);
			}
		});
		
		//Bottone ordina per voto
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(listaVideogiochi,Opera.VoteComparator);
				panel_2_2.removeAll();
				stampalista(panel_2_2);
				c1.first(panel);
				c1.next(panel);
			}
		});
		
		//bottone conferma nel pannello "Rimuovi un gioco"
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			rimuoviVideogioco((Videogioco)comboBox.getSelectedItem());
			c1.first(panel);
			}
		});
		
		
	}
	
	public static void stampalista(JPanel pannello) {
		for (int i=0; i< listaVideogiochi.size() ; i++) {
			pannello.add(new JLabel (i+1 +") "+listaVideogiochi.get(i).getNome() + "     " 
		+ listaVideogiochi.get(i).getVoto()+ "     "+ ((Videogioco)listaVideogiochi.get(i)).getPlatform()));
		}
	}
	
	
	public static void inserisciVideogioco(Videogioco nuovo) {
		

	
		listaVideogiochi.add(nuovo);
	}
	
	
	public static void rimuoviVideogioco(int numeroGioco) {
		listaVideogiochi.remove(numeroGioco-1);
	
	}
	
	public static void rimuoviVideogioco(Videogioco vid) {
		listaVideogiochi.remove(vid);
	
	}
	
	public static void popolaComboBox(JComboBox<Videogioco> box) {
		box.removeAllItems();
		for (int i=0; i< listaVideogiochi.size() ; i++) {
		box.addItem(listaVideogiochi.get(i));	
		}
	}
	
	public static void platformComboBox(JComboBox<Platform> box) {
		for (int i=0; i< Platform.values().length ; i++) {
		box.addItem(Platform.values()[i]);	
		}
	}
	
	private static void salvaLista() {
		try {
			FileWriter writer = new FileWriter ("Salvataggio.json");
			String json = new Gson().toJson(listaVideogiochi);
			writer.write(json);
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void caricaLista() {
		try {
		String json = new String (Files.readAllBytes(Paths.get("salvataggio.json")),StandardCharsets.UTF_8);
		TypeToken<ArrayList<Videogioco>> token = new TypeToken<ArrayList<Videogioco>>() {};
		listaVideogiochi = new Gson().fromJson(json, token.getType());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	

	}
	
	
	
	
	
	
	
	
