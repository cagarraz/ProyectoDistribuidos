package logica;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.DefaultListModel;

import interfazgrafica.InterfazGrafica;
import interfazgrafica.InterfazGraficavisitante;
import interfazgrafica.Menu;

public class MetodosHilos {

public static 	Timer timer=new Timer();
	

	
	
	
	public   static void  metodolectura(Juego juego,InterfazGrafica f) {
		

		Thread t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					juego.jugarlectura(f);
					
				}
			});
	t.start();

		
	}
	public   static void  juegoespectadores(Juego juego,InterfazGraficavisitante f) {
		

		Thread t=new Thread(new Runnable() {
		
				@Override
				public void run() {
					juego.jugarespectador(f);
				
					
				}
			});
	t.start();

		
	}
	
	
	
public static void metodotimer(DefaultListModel<Integer> lista) {
	timer.schedule(new TimerTask() {

		@Override
		public void run() {
			try(	Socket sock=new Socket("localhost",6666);
					ObjectOutputStream out = new  ObjectOutputStream(sock.getOutputStream());
		 			ObjectInputStream in = new  ObjectInputStream(sock.getInputStream());) {
			
				out.writeObject(new Protocolo<String>("PartidasDisponibles",""));
				out.flush();
		
					Protocolo<List<Integer> > listas= (Protocolo<List<Integer>>)in.readObject();
					lista.removeAllElements();
				int tam=	listas.getCuerpo().size();
				for(int i=0;i<tam;i++) {
					lista.addElement(listas.getCuerpo().get(i));
				
				}
			
	
				
				
					
				}
				 catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		},0, 3000);
}




public static void metodotimer2(DefaultListModel<Integer> listaver) {
	timer.schedule(new TimerTask() {
		
		@Override
		public void run() {
			try(	Socket sock=new Socket("localhost",6666);
					ObjectOutputStream out = new  ObjectOutputStream(sock.getOutputStream());
		 			ObjectInputStream in = new  ObjectInputStream(sock.getInputStream());) {
			
				out.writeObject(new Protocolo<String>("PartidasParaVer",""));
				out.flush();
		
					Protocolo<List<Integer> > lista= (Protocolo<List<Integer>>)in.readObject();
					listaver.removeAllElements();
				int tam=	lista.getCuerpo().size();
				for(int i=0;i<tam;i++) {
					listaver.add(i, lista.getCuerpo().get(i));
				
				}
			
				
			}
			 catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	},0, 3000);
}

public static  void finalizaTimer() {
	timer.cancel();

	
}



	
	
	
	
	
	
}
