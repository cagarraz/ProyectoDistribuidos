package interfazgrafica;



import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;



import logica.Barco;

import logica.Casilla;
import logica.Juego;
import logica.MetodosHilos;
import logica.Posicion;


import java.awt.Color;

import java.awt.GridLayout;
import javax.swing.JLabel;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class Flota   {

	private JFrame frame;

	private Juego juego;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private boolean rotar;
	private JButton guardar;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					Flota window = new Flota(new Barcos());
//		
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} 
//		});
//	}
	


	/**
	 * Create the application.
	 * @param cuenta 
	 */
	public Flota(Juego s) {
		this.rotar = false;
		this.juego=s;
	
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 82, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 10, 0, 0));

		JLabel lblNewLabel = new JLabel("Introduce tu:");
		lblNewLabel.setBounds(45, 35, 73, 14);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(119, 35, 146, 14);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setText(this.juego.getBarc().Asignar().getNombre());

		JButton btnNewButton = new JButton("Rotar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rotarbarco();
			}
		});
		btnNewButton.setBounds(290, 31, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		guardar= new JButton("Guardar");
		guardar.setEnabled(false);
		guardar.setBounds(156, 348, 89, 23);
		frame.getContentPane().add(guardar);
		
		guardar.addMouseListener(new MouseAdapter() {
			

			public void mouseClicked(MouseEvent e) {
				guardar();
			}	});

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Casilla boton = new Casilla("", new Posicion(i, j));
				boton.setBackground(Color.cyan);
				boton.setEnabled(true);
				boton.setSize(25, 25);

				boton.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						Calcular(e,Color.gray);
					}

					public void mouseExited(MouseEvent e) {
						Calcular(e,Color.cyan);
					}

					public void mouseClicked(MouseEvent e) {
						Asignar(e);
					}

				});

				panel.add(boton);
			}

		}

		
	}

	public void guardar() {
		this.juego.guardar();
		frame.setVisible(false);
		frame.dispose();

		InterfazGrafica gra =new InterfazGrafica(this.juego);
		gra.mostrar();

	
			




			
			
	
		
	
		
	}
	public void mostrar() {
		this.frame.setVisible(true);

	}

	protected void Asignar(MouseEvent e) {

		Barco barco;
		Casilla casilla = (Casilla) e.getSource();
		
		
		if ((barco = this.juego.getBarc().Asignar()) != null) {
		List<Posicion> lista=barco.Posicionanadir();
	

	
	
			if ((NoSobresale(barco.getTam(), casilla) && activos(casilla,barco.getTam()))) {
				
				int x = casilla.getPosicion().getX();
				int y = +casilla.getPosicion().getY();

				for (int i = 0; i < barco.getTam(); i++) {
					int posicionvector = x * 10 + y;

					Casilla a = (Casilla) panel.getComponent(posicionvector);
					lista.add(a.getPosicion());
					a.setBackground(Color.green);
					a.setEnabled(false);

					if (this.rotar) {
						x = x + 1;
					} else {
						y = y + 1;
					}
				}
		
				
				
			}
			if ((barco = this.juego.getBarc().Asignar()) == null) {
				lblNewLabel_1.setText("Lista Tu Flota");
				guardar.setEnabled(true);
			}
			else  
			lblNewLabel_1.setText(this.juego.getBarc().Asignar().getNombre());
			
			
		}

	}



	protected void Calcular(MouseEvent e,Color color) {
		Barco barco;
		Casilla f = (Casilla) e.getSource();

		if ((barco = this.juego.getBarc().Asignar()) != null) {

			pintar(color, f, barco.getTam());

		}

	}

	boolean activos(Casilla as, int tam) {
		int x = as.getPosicion().getX();
		int y = +as.getPosicion().getY();

		for (int i = 0; i < tam; i++) {
			int posicionvector = x * 10 + y;

			Casilla a = (Casilla) panel.getComponent(posicionvector);

			if (!a.isEnabled()) {
				return false;
			}

			if (this.rotar) {
				x = x + 1;
			} else {
				y = y + 1;
			}
		}
		return true;

	}

	protected boolean NoSobresale(int tam, Casilla f) {

		if (!this.rotar)
			return (f.getPosicion().getY() + tam <= 10);
		else
			return (f.getPosicion().getX() + tam <= 10);

	}

	protected void pintar(Color r, Casilla casilla, int tam) {

		if ((NoSobresale(tam, casilla) && activos(casilla,tam))) {

			int x = casilla.getPosicion().getX();
			int y = casilla.getPosicion().getY();

			for (int i = 0; i < tam; i++) {
				int posicionvector = x * 10 + y;

				Casilla a = (Casilla) panel.getComponent(posicionvector);

				a.setBackground(r);

				if (this.rotar) {
					x = x + 1;
				} else {
					y = y + 1;
				}
			}

		}
	}

	protected void rotarbarco() {

		this.rotar = !this.rotar;
	}
}
