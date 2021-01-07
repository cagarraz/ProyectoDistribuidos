package ParteServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


import logica.Barcos;
import logica.Posicion;
import logica.Protocolo;

public class Partida extends Thread{

	private ObjectOutputStream out1 ;
	private ObjectInputStream in1;
	private ObjectOutputStream out2 ;
	private ObjectInputStream in2;
	private List<Historico> listajugador;
	public CountDownLatch cuentalista=new CountDownLatch(1);
	private CountDownLatch count=new CountDownLatch(1);
	private boolean vivo=true;
	
	private Socket jugador1;
	private Socket jugador2;
	private String nombre1;
	private String nombre2;
	private List<Posicion> barcojug1;
	private List<Posicion> barcojug2;

	
	private static int numero=0;
	private  int id=factoria();
	
	public boolean isvivo( ) {
		return this.vivo;
	}
	
	
	public String nombre1( ) {
		return this.nombre1;
	}
	public String nombre2( ) {
		return this.nombre2;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	public List<Posicion> getBarcojug1guard() {
		return barcojug1guard;
	}


	

	public List<Posicion> getBarcojug2guard() {
		return barcojug2guard;
	}



	private List<Posicion> barcojug1guard=null;
	private List<Posicion> barcojug2guard=null;

	private boolean finalizado;
	public boolean isFinalizado() {
		return finalizado;
	}




	
	

	
	
	private static synchronized   int  factoria() {
		numero++;
		return numero;
		
	}

		
	public Partida(ObjectOutputStream out, ObjectInputStream in, String nombre1, Socket soc) {
		this.out1=out;
		this.jugador1=soc;
		this.in1=in;
		this.nombre1=nombre1;
		this.listajugador=new ArrayList<Historico>();
		this.jugador2=null;
	

	}
	public  int numero() {
		return this.id;
	}
	public void unirjugador(ObjectOutputStream out, ObjectInputStream in,String nombre,Socket soc) {
		nombreJugador2(nombre);
		this.jugador2=soc;
		this.out2=out;
		this.in2=in;
		count.countDown();
	}
	public List<Historico> historicojugador() {
	
	return this.listajugador;

	}

	
	
	
	public void nombreJugador2(String s) {
	this.nombre2=s;
	}
	

	
	
	
	public void run() {
		try {
			count.await();
		
					
//					out1.writeObject(new Protocolo<String>("Listas",""));
//					out1.flush();
//					out2.writeObject(new Protocolo<String>("Listas",""));
//					out2.flush();
//							
			this.barcojug1=	((Protocolo<List<Posicion>>)in1.readObject()).getCuerpo();
				
			this.barcojug2=((Protocolo<List<Posicion>>)in2.readObject()).getCuerpo();
			cuentalista.countDown();
			this.barcojug1guard =new ArrayList<Posicion>(this.barcojug1);
			this.barcojug2guard =new ArrayList<Posicion>(this.barcojug2);
				
				out1.writeObject(new Protocolo<String>("NombreJug",this.nombre1));	
				out1.writeObject(new Protocolo<String>("NombreCont",this.nombre2));	
				out1.flush();
				out2.writeObject(new Protocolo<String>("NombreCont",this.nombre1));	
				out2.writeObject(new Protocolo<String>("NombreJug",this.nombre2));	
				out1.flush();
			
			
				boolean acabado=false;
					while (!acabado) {
					
					boolean fallo=false;
					while (this.barcojug2.size()!=0 &&!fallo) {
					
						this.out1.writeObject(new Protocolo<String>("DamePosicion", ""));
						  out1.flush();
						Protocolo p=(Protocolo)	in1.readObject();
						Posicion pos=(Posicion)p.getCuerpo();
						out2.writeObject(new Protocolo<Posicion>("EscribirPosicion",pos));
						out2.flush();
						
						
					
						if(!this.barcojug2.remove(pos)) {
							this.listajugador.add(new Historico(pos, false,false));
							fallo =true;
							out1.writeObject(new Protocolo<Posicion>("NoDado",pos));	
							out1.flush();
						}else {
							this.listajugador.add(new Historico(pos, true,false));
							out1.writeObject(new Protocolo<Posicion>("Dado",pos));	
							out1.flush();
							
						}
				
						
					}
					fallo=false;
					while (this.barcojug1.size()!=0 && !fallo && this.barcojug2.size()!=0) {
			
						this.out2.writeObject(new Protocolo<String>("DamePosicion", ""));
						out2.flush();
						Protocolo p=(Protocolo)	in2.readObject();
						Posicion pos=(Posicion)p.getCuerpo();
						out1.writeObject(new Protocolo<Posicion>("EscribirPosicion",pos));
						out1.flush();
				
						if(!this.barcojug1.remove(pos)) {
							
							fallo =true;
							out2.writeObject(new Protocolo<Posicion>("NoDado",pos));	
							out2.flush();
							this.listajugador.add(new Historico(pos, false,true));
						}else {
							out2.writeObject(new Protocolo<Posicion>("Dado",pos));	
							out2.flush();
							this.listajugador.add(new Historico(pos, true,true));
							
						}
				
						
					}
					
					
					if(this.barcojug2.size()==0) {
					out1.writeObject(new Protocolo<String>("Fin","Ganador"));	
					out2.writeObject(new Protocolo<String>("Fin","Perdedor"));	
					out1.flush();
					out2.flush();
					acabado=true;
			
					
					}
					if(this.barcojug1.size()==0) {
						out2.writeObject(new Protocolo<String>("Fin","Ganador"));	
						out1.writeObject(new Protocolo<String>("Fin","Perdedor"));	
						out1.flush();
						out2.flush();	
						acabado=true;
	
					}
					
					
							
		

					}
					this.finalizado=true;
					
					
			
					
					
		
		}
		
				
				
				 
					 
										
					
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.vivo=false;
				
				}
				
				
			 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		finally {
			try {
				
				out1.close();
				in1.close();
				this.jugador1.close();
				if(this.jugador2!=null) {
				out2.close();
				in2.close();
				this.jugador2.close();}
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
		}
		
		} 
	

	}

			

			
		
