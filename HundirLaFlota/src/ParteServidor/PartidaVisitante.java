package ParteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import logica.Posicion;
import logica.Protocolo;

public class PartidaVisitante {
	private ObjectOutputStream out1 ;
	private ObjectInputStream in1;
	private Partida par;
	
	
	public PartidaVisitante(ObjectOutputStream out1,ObjectInputStream in1,Partida par) {
		this.out1=out1;
		this.in1=in1;
		this.par=par;
		try {
	
		out1.writeObject(new Protocolo<List<Posicion>>("Jugador1",  this.par.getBarcojug2guard()));
		out1.flush();
		out1.writeObject(new Protocolo<List<Posicion>>("Jugador2", this.par.getBarcojug1guard()));
		out1.flush();
		
		
		
	
		recorrido();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
		
	
	public void recorrido () {
		int i=0;

		try {	
		while ((i<this.par.historicojugador().size())|| !this.par.isFinalizado()) {
			 Thread.sleep(3000);
		
			if( i<par.historicojugador().size()) {
				out1.writeObject(new  Protocolo<Historico>("Jugadorhisto",this.par.historicojugador().get(i)));	
			i++;
			
			}
	

		}
		out1.writeObject(new  Protocolo<String>("Finalizado",""));
		
		
		
	
		
	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		finally {
	
			try {
				in1.close();
				out1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}
	}


