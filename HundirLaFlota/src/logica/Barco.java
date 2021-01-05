package logica;

import java.util.ArrayList;
import java.util.List;

public class Barco {
	private int tam;
	private String nombre;
	private  List<Posicion>  posiciones;

	 
	
	public Barco(int tam, String nombre) {
		
		this.tam = tam;
		this.nombre = nombre;
		this.posiciones= new ArrayList<Posicion>();

		
	}
	
	
	public int getTam() {
		return tam;
	}
	public void setTam(int tam) {
		this.tam = tam;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean completo() {
		return this.posiciones.size()==tam;
		
	}

	public List<Posicion> Posicionanadir() {
		//Cuidado
		return this.posiciones;
		
		
	}
	
	

	

	
	



}
