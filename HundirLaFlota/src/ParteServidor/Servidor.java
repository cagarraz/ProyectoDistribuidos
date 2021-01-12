package ParteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.source.tree.CatchTree;

import logica.Posicion;
import logica.Protocolo;


public class Servidor {
	
	public static void main(String[] args) {
		ExecutorService pool=Executors.newCachedThreadPool();
	try(	ServerSocket server=new ServerSocket(6666);) {
		
		

	while (true) {
		try {
		pool.execute(new Hilo(server.accept()));
		}catch (IOException e){
			e.printStackTrace();
			
		}
	}
	} catch (IOException e) {
		e.printStackTrace();} 
finally{
		
			pool.shutdown();
	}
	}
	
		
	


	

}
