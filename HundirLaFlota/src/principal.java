

import interfazgrafica.Menu;
import logica.Barcos;
import logica.Juego;
import logica.MetodosHilos;


public class principal {

	public static void main(String[] args) {

			
				Barcos barcos=new Barcos();
				Juego juego=new Juego(barcos);
				
				Thread th=new Thread(new Runnable() {
					
			
					public void run() {
						Menu f =new  Menu(juego);
						f.Mostrar();
						
						
						
						
						
						
					}
				});
	

th.start();
			




			

			
	
			

				
				


	
			
	
				
	}
	}



