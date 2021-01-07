package ParteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logica.Protocolo;


public class Hilo extends Thread {

Socket	soc;

private static List<Partida> lista=Collections.synchronizedList(new ArrayList<Partida>());
private static List<Partida> visita=new ArrayList<Partida>();


	
	
	public Hilo(Socket s) {
		this.soc=s;
		
	}


// List<Posicion> f=  (List<Posicion>)out.readObject();
	
	
	public void run() {
		  
		try {
			ObjectInputStream in = new  ObjectInputStream(soc.getInputStream());
			ObjectOutputStream out = new  ObjectOutputStream(soc.getOutputStream());
		  	Protocolo prot=(Protocolo)in.readObject();
	  	if(prot.getTipo().equals("Espectear")) {
	  		Integer l=	(Integer)(prot.getCuerpo());
			
	  		for(int i=0;i<visita.size();i++) {
	  			if(visita.get(i).numero()==l && visita.get(i).isvivo()) {
	  				PartidaVisitante par=new PartidaVisitante(out, in, 	visita.get(i));
	  			}
	  	
	  		}
	  	
		  
		  		
		  		
		  	}
		  	
		  	if(prot.getTipo().equals("Crear")) {
		  		
		  	 	
		  		Partida f=new Partida(out,in,(String)prot.getCuerpo(),soc);
		  		lista.add(f);
		  		f.start();
		  	}
		  	if(prot.getTipo().equals("Unirse")) {
		  		Partida p=null;
		  		Integer l=	(Integer)(prot.getCuerpo());
		
		  		for(int i=0;i<lista.size();i++) {
		  			if(lista.get(i).numero()==l) {
		 				  p=lista.remove(i);
		 			
		  				break;
		  			}
		  		}
		  		
		  		if(p!=null) {
		  			out.writeObject(new Protocolo<String>("OK", null));
		  			out.flush();
		  			 prot=(Protocolo)in.readObject();
	  				 p.unirjugador(out,in,(String)prot.getCuerpo(),soc);
	  				visita.add(p);
		  			
		  		}else {
		  			out.writeObject(new Protocolo<String>("Error", null));
		  			out.flush();
		  		}
		  
		  	
		  	
		  	
		  			
		  		
		  	}
		  	
		  	if(prot.getTipo().equals("PartidasDisponibles")) {
	  			List<Integer> listapart=new ArrayList<Integer>();
	  			int tam=lista.size();
	  			for(int i=0;i<tam;i++) {
	  				if(lista.get(i).isvivo())
	  				listapart.add((lista.get(i).numero()));	
	  			}
	  			out.writeObject(new Protocolo<List<Integer>>("OK",listapart));
	  			out.flush();
	  			out.close();
	  			in.close();
	  			soc.close();
	  	}
		  	
		  	
		  	if(prot.getTipo().equals("PartidasParaVer")) {
	  			List<Integer> listapart=new ArrayList<Integer>();
	  			int tam=visita.size();
	  			for(int i=0;i<tam;i++) {
	  				if(visita.get(i).isvivo())
	  				listapart.add((visita.get(i).numero()));	
	  			}
	  			out.writeObject(new Protocolo<List<Integer>>("Partidas",listapart));
	  			out.flush();
	  			out.close();
	  			in.close();
	  			soc.close();

	  	}
		  	
		  	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		  	
		  	
	
		  		
		  	}
		  
	}


