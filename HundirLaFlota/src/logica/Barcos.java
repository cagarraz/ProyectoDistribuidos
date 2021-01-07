package logica;

import java.util.ArrayList;

import java.util.List;



public class Barcos {
private String nombre;
private Barco	portaviones;
private Barco	crucero;
private Barco	destructor;
private Barco	submarino;
private Barco	lancha;

public Barcos() {
	
this.portaviones=new Barco(5, "portaviones");	
this.crucero=new Barco(4, "crucero");		
this.destructor=new Barco(3, "destructor");		
this.submarino=new Barco(2, "submarino");	
this.lancha=new Barco(1, "lancha");	
	
}

public Barco Asignar () {
		if (!portaviones.completo()) 
		return this.portaviones;
		if (!crucero.completo())
			return crucero;
		if (!destructor.completo())
			return destructor;
		if (!submarino.completo())
			return submarino;
		if (!lancha.completo())
			return lancha;
		

		return null;
	



}
public void setNombre(String nombre) {
	this.nombre=nombre;
}

public String getNombre() {
	return this.nombre;
}

public List<Posicion> ListaPosiciones(){
	

	
	List <Posicion> f=new ArrayList<Posicion>();
	
	f.addAll(portaviones.Posicionanadir());
	f.addAll(crucero.Posicionanadir());	
	f.addAll(destructor.Posicionanadir());
	f.addAll(submarino.Posicionanadir());
	f.addAll(lancha.Posicionanadir());
	return f;
			
	
	
	


}







	
	
	


}
