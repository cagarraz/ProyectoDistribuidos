package logica;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import ParteServidor.Historico;
import interfazgrafica.InterfazGrafica;
import interfazgrafica.InterfazGraficavisitante;

public class Juego {
	public CountDownLatch epera = new CountDownLatch(1);
	private Socket sock;
	private Barcos barc;

	private ObjectOutputStream out;
	private ObjectInputStream in;
	public boolean activo = false;

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public Barcos getBarc() {
		return barc;
	}

	public void setBarc(Barcos barc) {
		this.barc = barc;
	}

	public Juego(Barcos f) {
		this.barc = f;
	}

	public void CrearPartida() {
		this.sock = null;
		try {
			sock = new Socket("localhost", 6666);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			out.writeObject(new Protocolo<String>("Crear", barc.getNombre()));
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	

	public void UnirseEspectador(int numero) {
		this.sock = null;
		try {
			sock = new Socket("localhost", 6666);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());

			out.writeObject(new Protocolo<Integer>("Espectear", numero));
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
	

	
	
	public void jugarespectador(InterfazGraficavisitante f) {

		try {

			Protocolo<?> pro = null;

			while (!((pro = (Protocolo<?>) in.readObject()).getTipo()).equals("Finalizado")) {

				if (pro.getTipo().equals("Jugadorhisto")) {

					f.historicojug((Historico)pro.getCuerpo());
				}
				
				if (pro.getTipo().equals("Jugador1")) {
	
					f.anadirjug1((List<Posicion>)pro.getCuerpo());
					}
				
				if (pro.getTipo().equals("Jugador2")) {

					f.anadirjug2((List<Posicion>)pro.getCuerpo());
					}

	
				if (pro.getTipo().equals("Nombre1")) {

					f.nombreCont((String)pro.getCuerpo());
					
					}
				if (pro.getTipo().equals("Nombre2")) {

					f.nombreJug((String)pro.getCuerpo());
					
					}
				
			

				}
			f.finvist();



		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
		
			in.close();
			out.close();
				this.sock.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	public boolean Unirse(int numero) {
		this.sock = null;

		try {
			sock = new Socket("localhost", 6666);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());

			out.writeObject(new Protocolo<Integer>("Unirse", numero));
			out.flush();
			Protocolo<?> pro = null;
			pro= (Protocolo)in.readObject();
		if(pro.getTipo().equals("OK"))	{
			out.writeObject(new Protocolo<String>("Nombre", this.barc.getNombre()));
			out.flush();
		return true;
		}
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean DarPosicion(Posicion f) {

		try {

			if (this.activo) {
				out.writeObject(new Protocolo<Posicion>("Posicon", f));
				out.flush();
				this.activo = false;
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void jugarlectura(InterfazGrafica f) {

		try {

			Protocolo<?> pro = null;

			while (!((pro = (Protocolo) in.readObject()).getTipo()).equals("Fin")) {

				if (pro.getTipo().equals("NombreCont")) {

					f.nombreCont((String) pro.getCuerpo());
				}
				if (pro.getTipo().equals("NombreJug")) {

					f.nombreJug((String) pro.getCuerpo());

				}

				if (pro.getTipo().equals("DamePosicion")) {
					f.tuturno();
					this.activo = true;

				}
				if (pro.getTipo().equals("EscribirPosicion")) {
					f.escribir((Posicion) pro.getCuerpo());
				}
				if (pro.getTipo().equals("NoDado")) {
					f.escribirSuperior((Posicion) pro.getCuerpo(), false);
				}
				if (pro.getTipo().equals("Dado")) {
					f.escribirSuperior((Posicion) pro.getCuerpo(), true);
				}

			}

			f.fin((String) pro.getCuerpo());

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void guardar() {

		try {

			out.writeObject(new Protocolo<List<Posicion>>("Listas", this.barc.ListaPosiciones()));
			out.flush();



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.sock = null;
		}

	}

	public void cerrar() {
		try {
			this.out.close();

			this.in.close();
			this.sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

