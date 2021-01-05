
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.DefaultListModel;
import javax.xml.stream.events.StartElement;

import org.eclipse.osgi.internal.hooks.EclipseLazyStarter;

import ParteServidor.Hilo;
import interfazgrafica.InterfazGrafica;
import interfazgrafica.Menu;
import logica.Barcos;
import logica.Juego;
import logica.MetodosHilos;
import logica.Protocolo;

public class principal {

	public static void main(String[] args) {

				DefaultListModel<Integer> lista= new DefaultListModel<Integer>();
				DefaultListModel<Integer> listaver= new DefaultListModel<Integer>();
				Barcos barcos=new Barcos();
				Juego juego=new Juego(barcos);
				
				Thread th=new Thread(new Runnable() {
					
			
					public void run() {
						Menu f =new  Menu(juego,lista,listaver);
						f.Mostrar();
						
						
						
						
						
						
					}
				});
				MetodosHilos.metodotimer(lista);
				MetodosHilos.metodotimer2(listaver);

				MetodosHilos.pool.execute(th);
			




			

			
	
			

				
				


	
			
	
				
	}
	}



