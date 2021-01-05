package interfazgrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import logica.Barcos;
import logica.Juego;
import logica.MetodosHilos;
import logica.Protocolo;
import logica.Juego;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class Menu extends JFrame {

	private JFrame frame;
	private Barcos barcos;
	private JTextField txtJugador;
	private JList <Integer> list;
	private	JList <Integer> list_1;
	private	DefaultListModel<Integer> model;
	private	DefaultListModel<Integer> listaver;
	
	Juego jueg;



	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @param listaver 
	 */
	public Menu(Juego juego,DefaultListModel<Integer> lista, DefaultListModel<Integer> listaver) {
		this.model=lista;
		this.listaver=listaver;
		this.barcos=juego.getBarc();
		this.jueg=juego;
		initialize();


	}
	
	
		
		
	
	
	public void Mostrar() {
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{33, 59, 86, 0};
		gbl_panel_2.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel = new JLabel("Tu Nombre :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		txtJugador = new JTextField();
		GridBagConstraints gbc_txtJugador = new GridBagConstraints();
		gbc_txtJugador.insets = new Insets(0, 0, 5, 0);
		gbc_txtJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtJugador.gridx = 2;
		gbc_txtJugador.gridy = 2;
		panel_2.add(txtJugador, gbc_txtJugador);
		txtJugador.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("CrearPartida");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearFlota();
			
				
				


				
				
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 3;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		
		JPanel panel_4_1 = new JPanel();
		panel_1.add(panel_4_1);
		panel_4_1.setLayout(new MigLayout("", "[][][][][grow][][][][grow]", "[][grow][][grow]"));
		
		JLabel lblNewLabel_1 = new JLabel("Pardias Disponibles");
		panel_4_1.add(lblNewLabel_1, "cell 3 0");
		
		 list = new JList<>( model );
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  if (e.getClickCount() == 2) {
				        int index = list.locationToIndex(e.getPoint());
				        
				        
				        crearFlotaUnirse(  model.get(index));				        
				   
				    	
				    }
				
			}
			
		});
		
		panel_4_1.add(list, "cell 1 1 8 3,grow");
		
		JPanel panel_4_1_1 = new JPanel();
		panel_1.add(panel_4_1_1);
		panel_4_1_1.setLayout(new MigLayout("", "[][][][][][][][grow]", "[][][grow]"));
		
		JLabel lblNewLabel_1_1 = new JLabel("Partidas en curso");
		panel_4_1_1.add(lblNewLabel_1_1, "cell 3 0");
		
		 list_1 = new JList<>( listaver );
		 list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  if (e.getClickCount() == 2) {
				        int index = list_1.locationToIndex(e.getPoint());
				        
				        UnirseVisitante(  listaver.get(index));
				      
				        
				   
				    	
				    }
				
			}
			
		});
		
		panel_4_1_1.add(list_1, "cell 1 1 7 2,grow");
	}

	protected void UnirseVisitante(int index) {
		this.jueg.UnirseEspectador( index);
		
		InterfazGraficavisitante gra =new InterfazGraficavisitante(this.jueg);
		gra.mostrar();
	MetodosHilos.juegoespectadores(this.jueg,gra);
		
		frame.dispose();
	}
	

	protected void crearFlotaUnirse(int index) {
		this.barcos.setNombre(nombre());
		this.jueg.Unirse( index);
	
		Flota f=new Flota(this.jueg);
		f.mostrar();
		frame.dispose();
		


		
		
	}
	protected void Unirse(int index) {
		this.jueg.Unirse( index);


		frame.dispose();
		
	}
	






	public  String nombre() {
		return this.txtJugador.getText();
	}

	protected void crearFlota() {
		
		this.barcos.setNombre(nombre());
		this.jueg.CrearPartida();
	
		Flota f=new Flota(this.jueg);
		f.mostrar();
		frame.dispose();
	
	
	
	

	}

	
	
}
