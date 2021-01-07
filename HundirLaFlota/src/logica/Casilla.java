package logica;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class Casilla extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private 	Posicion posicion;
	
	public Casilla() {
		super();
		this.posicion=null;
		// TODO Auto-generated constructor stub
	}

	public Casilla(Action a) {
		super(a);
this.posicion=null;
		// TODO Auto-generated constructor stub
	}

	public Casilla(Icon icon,Posicion pos) {
		
		super(icon);
		this.posicion=pos;
		// TODO Auto-generated constructor stub
	}

	public Casilla(String text, Icon icon,Posicion pos) {
		super(text, icon);
		this.posicion=pos;
		// TODO Auto-generated constructor stub
	}

	public Casilla(String text,Posicion pos ) {
		super(text);
		this.posicion=pos;
		
		// TODO Auto-generated constructor stub
	}


	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	

}
