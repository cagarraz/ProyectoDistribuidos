package interfazgrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;


import ParteServidor.Historico;
import logica.Barcos;
import logica.Casilla;
import logica.Juego;
import logica.MetodosHilos;
import logica.Posicion;



import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InterfazGrafica {
	private JLabel NombreJugador1 ;
	private JLabel NombreJugador2 ;
	private JFrame frame;
	private Juego juego;
	private JPanel panel_1;
	private JPanel panel;





	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param sock 
	 * @param string 
	 */
	public InterfazGrafica(Juego juego) {
		this.juego=juego;
		
		initialize();
		MetodosHilos.metodolectura(this.juego, this);

	
	}


	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public void mostrar() {
		
	
	frame.setVisible(true);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 panel = new JPanel();
		panel.setBounds(49, 43, 294, 264);
		frame.getContentPane().add(panel);
		

		panel.setLayout(new GridLayout(10, 10, 0, 0));
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				
			
				
				Casilla boton = new Casilla("",new Posicion(i,j));
				boton.setBackground(Color.cyan);
				boton.setEnabled(true);
				boton.setSize(25, 25);
				boton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						calcularposicon(e);
					
					}
				});
				panel.add(boton);
				
			
			}
			
		}
		
		 panel_1 = new JPanel();
		panel_1.setBounds(49, 353, 294, 264);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(10, 10, 0, 0));
		
		 NombreJugador1 = new JLabel("New label");
		NombreJugador1.setBounds(172, 328, 46, 14);
		frame.getContentPane().add(NombreJugador1);
		
		 NombreJugador2 = new JLabel("New label");
		NombreJugador2.setBounds(172, 18, 46, 14);
		frame.getContentPane().add(NombreJugador2);
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				JButton boton = new JButton("");
				boton.setBackground(Color.cyan);
				boton.setEnabled(true);
				boton.setSize(25, 25);
				boton.setEnabled(false);
				if(this.juego.getBarc().ListaPosiciones().contains(new Posicion(i, j))) {
					boton.setBackground(Color.green);
				}
				panel_1.add(boton);
				
			}
		}
		
		
	}

	protected void calcularposicon(MouseEvent e) {
		
	
	Casilla f=(Casilla)e.getSource();
	if(f.isEnabled()) {
	Posicion pos=f.getPosicion();
		if(this.juego.DarPosicion(pos)) {
			f.setEnabled(false);
		
		}
	}
	
		
		
		
	}

	public void escribir(Posicion cuerpo) {
		int apunta=cuerpo.getX()*10+cuerpo.getY();
	if(((JButton)panel_1.getComponent(apunta)).getBackground().equals(Color.green)) {
		((JButton)panel_1.getComponent(apunta)).setBackground(Color.RED);
	}else {
		((JButton)panel_1.getComponent(apunta)).setBackground(Color.white);
	}
		
		
		
		
	}

	public void fin(String cuerpo) {
	

		JOptionPane.showMessageDialog(this.frame,cuerpo, "Mi comandante", JOptionPane.INFORMATION_MESSAGE);

	
			this.juego.cerrar();
			MetodosHilos.timer=new Timer();
			Menu f=new Menu(new Juego(new Barcos()));
			f.Mostrar();
			
			frame.setVisible(false);
			frame.dispose(); 
		

		
	}

	public void escribirSuperior(Posicion cuerpo, boolean b) {
		int apunta=cuerpo.getX()*10+cuerpo.getY();
		
		if(b) {
			((JButton)panel.getComponent(apunta)).setBackground(Color.red);
			((JButton)panel.getComponent(apunta)).setEnabled(false);
		}else {
			((JButton)panel.getComponent(apunta)).setBackground(Color.white);
			((JButton)panel.getComponent(apunta)).setEnabled(false);
		}
		
	}

	public void nombreJug(String cuerpo) {
		NombreJugador1.setText(cuerpo);		
	}
	public void nombreCont(String cuerpo) {
		NombreJugador2.setText(cuerpo);
	}

	public void historicojug1(Historico cuerpo) {
	
		int apunta=cuerpo.getPosicion().getX()*10+cuerpo.getPosicion().getY();
		if(cuerpo.isDado()) {
		((JButton)panel_1.getComponent(apunta)).setBackground(Color.RED);}
		else {
			((JButton)panel_1.getComponent(apunta)).setBackground(Color.white);
		}
		
		
	}
	
	public void historicojug2(Historico cuerpo) {

		int apunta=cuerpo.getPosicion().getX()*10+cuerpo.getPosicion().getY();
		if(cuerpo.isDado()) {
		((JButton)panel_1.getComponent(apunta)).setBackground(Color.RED);}
		else {
			((JButton)panel_1.getComponent(apunta)).setBackground(Color.white);
		}
		
		
	}

	

	public void tuturno() {
	JOptionPane.showConfirmDialog(this.frame,"Tu turno","Mi comandante", JOptionPane.DEFAULT_OPTION);		
	}
	

	
		
			
		
	}

