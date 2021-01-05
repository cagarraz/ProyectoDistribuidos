package ParteServidor;

import java.io.Serializable;

import logica.Posicion;

public class Historico implements Serializable{
public Historico(Posicion posicion, boolean dado,boolean jugador1) {

		this.posicion = posicion;
		this.dado = dado;
		this.jugador1=jugador1;
		
	}
private 	Posicion posicion;
private		boolean jugador1;

public boolean isJugador1() {
	return this.jugador1;
}

public Posicion getPosicion() {
	return posicion;
}
public void setPosicion(Posicion posicion) {
	this.posicion = posicion;
}
public boolean isDado() {
	return dado;
}
public void setDado(boolean dado) {
	this.dado = dado;
}
private boolean dado;


}
